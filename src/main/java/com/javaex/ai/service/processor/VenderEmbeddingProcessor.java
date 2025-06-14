package com.javaex.ai.service.processor;

import com.javaex.ai.model.SearchEmbeddingVo;
import com.javaex.ai.repository.SearchEmbeddingDao;
import com.javaex.ai.service.EmbeddingService;
import com.javaex.vendor.model.HyunVenderVo;
import com.javaex.vendor.repository.HyunVenderDao;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class VenderEmbeddingProcessor implements EmbeddingProcessor {

    private final HyunVenderDao hyunVenderDao;
    private final EmbeddingService embeddingService;
    private final SearchEmbeddingDao searchEmbeddingDao;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void processAll() {
        logger.info("[Vender 임베딩 시작]");
        List<HyunVenderVo> venders = hyunVenderDao.getAllVenders();

        for (HyunVenderVo v : venders) {
            if (v == null) continue;

            if (searchEmbeddingDao.existsByTableNameAndOriginalId("Vender", v.getVenderId())) {
                logger.info("[건너뜀] 이미 임베딩된 vender_id={}", v.getVenderId());
                continue;
            }

            String combinedText = """
                    업체명: %s
                    사업자번호: %s
                    대표자명: %s
                    주소: %s (%s)
                    설명: %s
                    카카오채널: %s
                    """.formatted(
                    safe(v.getVenderName()),
                    safe(v.getVenderNumber()),
                    safe(v.getRepresentativeName()),
                    safe(v.getVenderAddress()),
                    safe(v.getDistrict()),
                    safe(v.getVenderDescription()),
                    safe(v.getKakaoUrl())
            );

            List<Double> embedding = embeddingService.embed(combinedText);

            SearchEmbeddingVo vo = new SearchEmbeddingVo();
            vo.setTableName("Vender");
            vo.setOriginalId(v.getVenderId());
            vo.setCombinedText(combinedText);
            vo.setEmbedding(embedding.toString());

            searchEmbeddingDao.insert(vo);
            logger.info("[✅ 저장 완료] vender_id={}", v.getVenderId());
        }

        logger.info("[Vender 임베딩 완료] 총 {}건", venders.size());
    }

    @Override
    public void processOne(int id) {
        // 필요 시 개별 임베딩 처리도 추가 가능
    }

    private String safe(String input) {
        return Optional.ofNullable(input).filter(s -> !s.isBlank()).orElse("정보 없음");
    }
}
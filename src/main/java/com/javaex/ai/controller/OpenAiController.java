package com.javaex.ai.controller;

import com.javaex.ai.service.processor.VenderEmbeddingProcessor;
import com.javaex.ai.model.SearchEmbeddingVo;
import com.javaex.ai.repository.SearchEmbeddingDao;
import com.javaex.ai.service.EmbeddingService;
import com.javaex.ai.service.OpenAiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/chatgpt")
public class OpenAiController {

    @Autowired
    private EmbeddingService embeddingService;

    @Autowired
    private OpenAiService openAiService;

    @Autowired
    private SearchEmbeddingDao searchEmbeddingDao;

    @Autowired
    private VenderEmbeddingProcessor venderEmbeddingProcessor;

    @PostMapping
    public String ask(@RequestBody Map<String, String> payload) {
        String message = payload.get("message");
        return openAiService.ask(message);
    }

    @PostMapping("/embedding/vender")
    public String embedVender(@RequestBody Map<String, Object> payload) {
        int venderId = (int) payload.get("vender_id");
        String venderName = (String) payload.get("vender_name");
        String address = (String) payload.get("vender_address");
        String district = (String) payload.get("district");
        String description = (String) payload.get("vender_description");
        String kakaoUrl = (String) payload.get("kakao_url");
        String representative = (String) payload.get("representative_name");

        String combinedText = """
        업체명: %s
        지역: %s (%s)
        대표자: %s
        설명: %s
        카카오채널: %s
        """.formatted(venderName, address, district, representative, description, kakaoUrl);

        List<Double> embedding = embeddingService.embed(combinedText);

        SearchEmbeddingVo vo = new SearchEmbeddingVo();
        vo.setTableName("Vender");
        vo.setOriginalId(venderId);
        vo.setCombinedText(combinedText);
        vo.setEmbedding(embedding.toString());

        searchEmbeddingDao.insert(vo);

        return "저장 완료!";
    }

    @PostMapping("/embedding/vender/all")
    public String embedAllVenders() {
        venderEmbeddingProcessor.processAll();
        return "전체 벤더 임베딩 저장 완료!";
    }
}

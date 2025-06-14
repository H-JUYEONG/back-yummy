package com.javaex.option.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.common.storage.S3Service;
import com.javaex.option.model.HyunOptionTypeVO;
import com.javaex.option.model.HyunOptionValueVO;
import com.javaex.option.service.HyunOptionService;

@RestController
@RequestMapping("/api/options")
public class HyunOptionController {

	@Autowired
	private HyunOptionService hyunOptionService;
	@Autowired
	private S3Service s3Service;

	 // 1. 옵션 유형 및 옵션 값 가져오기
    @GetMapping("/{venderId}")
    public List<HyunOptionTypeVO> getOptionsWithValues(@PathVariable int venderId) {
        return hyunOptionService.getOptionsWithValues(venderId);
    }
    
    // 옵션 값 추가 및 이미지 업로드
    @PostMapping("/add")
    public HyunOptionValueVO addOptionValue(
            @RequestParam("file") MultipartFile file,
            @RequestParam("optionTypeId") int optionTypeId,
            @RequestParam("venderId") int venderId,
            @RequestParam("optionValueName") String optionValueName
    ) {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("파일이 비어있습니다.");
        }

        try {
            // 1. S3에 이미지 업로드
            String key = "images/" + UUID.randomUUID() + "_" + file.getOriginalFilename();
            String imageUrl = s3Service.uploadFile(key, file.getInputStream(), file.getSize(), file.getContentType());

            // 2. 옵션 값 생성 및 등록
            HyunOptionValueVO optionValueVO = new HyunOptionValueVO();
            optionValueVO.setOptionTypeId(optionTypeId);
            optionValueVO.setVenderId(venderId);
            optionValueVO.setOptionValueName(optionValueName);
            optionValueVO.setOptionValueImageUrl(imageUrl);

            hyunOptionService.addOptionValue(optionValueVO);

            return optionValueVO; // 등록된 옵션 값을 반환
        } catch (IOException e) {
            throw new RuntimeException("파일 업로드 중 오류가 발생했습니다.", e);
        }
    }

    //3. 옵션 값 삭제하기
    @DeleteMapping("/delete/{optionValueId}")
    public void deleteOptionValue(@PathVariable int optionValueId) {
        if (optionValueId == 0) {
            throw new IllegalArgumentException("유효하지 않은 optionValueId입니다: " + optionValueId);
        }
        hyunOptionService.deleteOptionValue(optionValueId);
    }
    
    @PostMapping("/details")
    public ResponseEntity<List<HyunOptionValueVO>> getOptionDetails(@RequestBody Map<String, Object> requestData) {
        // 요청 데이터 확인
        Object selectedOptionsObj = requestData.get("selectedOptions");
        System.out.println("Selected Options Object: " + selectedOptionsObj);

        // selectedOptions가 null이거나 잘못된 형식일 경우 예외 처리
        if (selectedOptionsObj == null || !(selectedOptionsObj instanceof List)) {
            throw new IllegalArgumentException("selectedOptions는 배열 형식이어야 합니다.");
        }

        @SuppressWarnings("unchecked")
        List<Integer> selectedOptions = ((List<Object>) selectedOptionsObj).stream()
            .map(o -> Integer.valueOf(o.toString())) // 각 객체를 Integer로 변환
            .toList();

        List<HyunOptionValueVO> optionDetails = hyunOptionService.getOptionDetailsByIds(selectedOptions);
        return ResponseEntity.ok(optionDetails);
    }

    
}

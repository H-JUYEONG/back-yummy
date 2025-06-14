package com.javaex.ai.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javaex.ai.model.AiGenRequest;
import com.javaex.ai.model.AiGenResponse;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class AiGenService {

    private final RestTemplate restTemplate;
    private static final String FASTAPI_BASE_URL = "http://localhost:8000"; // FastAPI 서버 주소

    public AiGenService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * 스케치 이미지 생성 (FastAPI /upload 엔드포인트 호출)
     */
    public AiGenResponse generateSketch(AiGenRequest request) throws IOException {
        String url = FASTAPI_BASE_URL + "/upload";

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("user_id", request.getUserId());
        body.add("prompt", request.getPrompt());
        body.add("mode", request.getMode());
        body.add("width", request.getWidth());
        body.add("height", request.getHeight());
        body.add("num_inference_steps", request.getNumInferenceSteps());
        body.add("use_english_prompt", request.isUseEnglishPrompt());
        body.add("negative_prompt", request.getNegativePrompt());
        body.add("sketch_style", request.getSketchStyle());

        MultipartFile file = request.getFile();
        if (file != null && !file.isEmpty()) {
            body.add("file", file.getResource());
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
        ResponseEntity<AiGenResponse> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, AiGenResponse.class);

        // 응답을 로깅해서 확인
        System.out.println("FastAPI 응답: " + responseEntity.getBody());

        return responseEntity.getBody();
    }

    public AiGenResponse generateDraft(AiGenRequest request) throws IOException {
        String url = FASTAPI_BASE_URL + "/select-concept";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<AiGenRequest> requestEntity = new HttpEntity<>(request, headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);

        // FastAPI 응답을 직접 확인 (디버깅용)
        System.out.println("FastAPI Raw Response (select-concept): " + responseEntity.getBody());

        try {
            // JSON 파싱을 위해 ObjectMapper 사용
            ObjectMapper objectMapper = new ObjectMapper();
            AiGenResponse aiGenResponse = objectMapper.readValue(responseEntity.getBody(), AiGenResponse.class);

            return aiGenResponse;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 초안 수정 요청 (JSON 형식으로 전송)
     */
    public AiGenResponse editDraft(AiGenRequest request) {
        String url = FASTAPI_BASE_URL + "/edit-draft/text";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<AiGenRequest> requestEntity = new HttpEntity<>(request, headers);
        ResponseEntity<AiGenResponse> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, AiGenResponse.class);
        return responseEntity.getBody();
    }

    /**
     * 최종 디자인 생성 요청 (JSON 형식으로 전송)
     */
    public AiGenResponse generateFinalDesign(AiGenRequest request) {
        String url = FASTAPI_BASE_URL + "/finalize";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<AiGenRequest> requestEntity = new HttpEntity<>(request, headers);
        ResponseEntity<AiGenResponse> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, AiGenResponse.class);
        return responseEntity.getBody();
    }
}

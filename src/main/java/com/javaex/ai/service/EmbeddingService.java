package com.javaex.ai.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
public class EmbeddingService {

    @Value("${openai.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    public List<Double> embed(String text) {
        String url = "https://api.openai.com/v1/embeddings";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey);

        Map<String, Object> body = Map.of(
                "input", text,
                "model", "text-embedding-3-small"
        );

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);

        ResponseEntity<Map> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                entity,
                Map.class
        );

        // 벡터 추출
        Map<String, Object> data = ((List<Map<String, Object>>) response.getBody().get("data")).get(0);
        return (List<Double>) data.get("embedding");
    }
}

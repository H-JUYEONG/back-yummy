package com.javaex.ai.service;

import com.javaex.ai.model.SearchEmbeddingVo;
import com.javaex.ai.repository.SearchEmbeddingDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class OpenAiService {

    private static final Logger logger = LoggerFactory.getLogger(OpenAiService.class);

    private final EmbeddingService embeddingService;
    private final SearchEmbeddingDao searchEmbeddingDao;
    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${openai.api.key}")
    private String apiKey;

    public OpenAiService(EmbeddingService embeddingService, SearchEmbeddingDao searchEmbeddingDao) {
        this.embeddingService = embeddingService;
        this.searchEmbeddingDao = searchEmbeddingDao;
    }

    public String ask(String userMessage) {
        logger.info("[챗봇 질문 수신] 사용자 질문: {}", userMessage);

        List<Double> questionVector = embeddingService.embed(userMessage);
        List<SearchEmbeddingVo> candidates = searchEmbeddingDao.getAllEmbeddings();

        candidates.sort(Comparator.comparingDouble(vo ->
                -cosineSimilarity(questionVector, parseVector(vo.getEmbedding()))));

        List<SearchEmbeddingVo> topResults = candidates.subList(0, Math.min(3, candidates.size()));

        logger.info("[챗봇 유사도 상위 결과] count: {}, ids: {}",
                topResults.size(),
                topResults.stream().map(SearchEmbeddingVo::getOriginalId).toList());

        String prompt = buildPrompt(userMessage, topResults);

        String answer = callGpt(prompt);
        logger.info("[GPT 응답] {}", answer);
        return answer;
    }

    private String buildPrompt(String userMessage, List<SearchEmbeddingVo> data) {
        StringBuilder sb = new StringBuilder();
        sb.append("너는 친절한 고객 지원 챗봇이야. 아래 정보를 참고해서 자연스럽게 답변해줘.\n\n");
        for (SearchEmbeddingVo vo : data) {
            sb.append("- ").append(vo.getCombinedText()).append("\n");
        }
        sb.append("\n질문: ").append(userMessage);
        return sb.toString();
    }

    private double cosineSimilarity(List<Double> a, List<Double> b) {
        double dot = 0.0, normA = 0.0, normB = 0.0;
        for (int i = 0; i < a.size(); i++) {
            dot += a.get(i) * b.get(i);
            normA += a.get(i) * a.get(i);
            normB += b.get(i) * b.get(i);
        }
        return dot / (Math.sqrt(normA) * Math.sqrt(normB));
    }

    private List<Double> parseVector(String jsonArray) {
        return Arrays.stream(jsonArray
                        .replace("[", "")
                        .replace("]", "")
                        .split(","))
                .map(String::trim)
                .map(Double::parseDouble)
                .collect(Collectors.toList());
    }

    private String callGpt(String prompt) {
        logger.info("[GPT 호출] prompt 생성 완료");

        String url = "https://api.openai.com/v1/chat/completions";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey);

        Map<String, Object> body = Map.of(
                "model", "gpt-3.5-turbo-1106",
                "messages", List.of(
                        Map.of("role", "system", "content", "너는 유용한 챗봇이야."),
                        Map.of("role", "user", "content", prompt)
                ),
                "temperature", 0.7,
                "max_tokens", 500
        );

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);

        ResponseEntity<Map> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                entity,
                Map.class
        );

        Map<String, Object> choice = ((List<Map<String, Object>>) response.getBody().get("choices")).get(0);
        Map<String, String> message = (Map<String, String>) choice.get("message");
        return message.get("content");
    }
}
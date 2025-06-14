package com.javaex.review.service;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javaex.review.model.ReviewAnalysisVo;
import com.javaex.review.repository.ReviewAnalysisDao;

@Service
public class ReviewAnalysisService {

	@Autowired
	private ReviewAnalysisDao juReviewAnalysisDao;
	
	// 속성 파일에서 설정 값 주입
	@Value("${review.openai.api.key}")
	private String OPENAI_API_KEY;
	
	@Value("${review.openai.temperature}")
	private float temperature;
	
	@Value("${openai.model}")
	private String model;
	
	@Value("${review.max_tokens}")
	private int max_tokens;

	// OpenAI API 호출을 위한 WebClient 인스턴스
	private final WebClient webClient;

	// WebClient Builder를 사용해 OpenAI API URL 설정
	public ReviewAnalysisService(WebClient.Builder builder) {
		this.webClient = builder.baseUrl("https://api.openai.com/v1/chat/completions").build();
	}

	// 특정 제품에 대한 리뷰 분석 결과를 반환
	public Map<String, Object> getReviewAnalysis(int productId) {
		
		// 최근 3개월 리뷰 가져오기 (날짜 제한 있는 리뷰)
	    List<ReviewAnalysisVo> reviews = juReviewAnalysisDao.getAllReviewsLimit(productId);
	    System.out.println("DB에서 가져온 최근 3개월 리뷰 개수: " + reviews.size());

	    // 최근 리뷰가 없으면 전체 리뷰 수 확인
	    int totalReviewCount = juReviewAnalysisDao.getAllReviewsCount(productId);
	    System.out.println("DB에서 가져온 전체 리뷰 개수: " + totalReviewCount);

	    // 최근 리뷰는 없지만 전체 리뷰가 있는 경우, 날짜 제한 없이 리뷰 가져오기
	    if (reviews.isEmpty() && totalReviewCount > 0) {
	        reviews = juReviewAnalysisDao.getAllReviews(productId);
	        System.out.println("날짜 제한 없이 가져온 리뷰 개수: " + reviews.size());
	    }

		// 리뷰가 없는 경우 처리
		if (reviews.isEmpty()) {
			Map<String, Object> noReviewResult = new HashMap<>();
			noReviewResult.put("positiveRate", 0); // 긍정률 0%로 설정
			noReviewResult.put("summary", "리뷰가 없습니다."); // 리뷰가 없다는 메시지
			noReviewResult.put("categories", Collections.emptyList()); // 빈 카테고리 리스트 설정
			return noReviewResult; // 결과 반환
		}

		// 리뷰를 1000자 단위로 분할
		List<String> reviewChunks = splitIntoChunks(
				reviews.stream().map(ReviewAnalysisVo::getReviewContent).collect(Collectors.toList()), 1000);
		System.out.println("분할된 Chunk 개수: " + reviewChunks.size()); // 분할된 Chunk 개수 출력

		// 전체 리뷰 요약 생성
		String summaryResponse = generateSummary(String.join("\n", reviewChunks));

		// 각 Chunk 분석 결과 저장
		List<Map<String, Object>> chunkResults = new ArrayList<>();
		int totalPositive = 0; // 총 긍정 리뷰 수 초기화
		int totalReviews = 0; // 총 리뷰 수 초기화

		// 각 Chunk에 대해 분석 수행
		for (String chunk : reviewChunks) {
			String gptResponse = callOpenAI(chunk); // OpenAI API 호출
			Map<String, Object> processedResult = processAnalysisResult(gptResponse); // 분석 결과 처리
			if (processedResult != null) {
				chunkResults.add(processedResult); // 결과 저장

				// 긍정적인 리뷰 수 계산
				Map<String, Object> sentimentAnalysis = analyzeSentiment(chunk);
				totalPositive += (int) sentimentAnalysis.get("positiveCount"); // 긍정 리뷰 수 누적
				totalReviews += (int) sentimentAnalysis.get("totalCount"); // 총 리뷰 수 누적
			}
		}

		// 최종 결과 조합
		Map<String, Object> finalResult = mergeChunkResults(chunkResults); // 모든 Chunk 결과 병합
		finalResult.put("positiveRate", totalReviews > 0 ? (totalPositive * 100 / totalReviews) : 0); // 긍정률 계산
		finalResult.put("summary", summaryResponse); // 리뷰 요약 추가

		return finalResult; // 최종 결과 반환
	}

	// OpenAI API를 사용하여 리뷰 요약 생성
	private String generateSummary(String reviews) {
		Map<String, Object> requestBody = Map.of("model", model, "messages",
				List.of(Map.of("role", "system", "content",
						"리뷰들을 분석하여 긍정적인 의견만 3줄로 간단히 요약해주세요. 반드시 모든 문장은 \"~습니다./했습니다.\"로 끝나는 공손한 문법의 **완전한 문장**이어야 합니다. 또한, 한국어로 자연스럽고 의미가 명확해야 합니다. 어색하거나 불완전한 표현(예: \"퀄리티다\")은 사용하지 마세요."),
						Map.of("role", "user", "content", reviews)),
				"temperature", temperature, "max_tokens", max_tokens);

		try {
			String response = webClient.post().header("Authorization", "Bearer " + OPENAI_API_KEY) // 인증 헤더 추가
					.header("Content-Type", "application/json") // 요청 Content-Type 설정
					.bodyValue(requestBody) // 요청 본문 설정
					.retrieve() // 요청 실행
					.bodyToMono(String.class).block(); // 응답 데이터를 문자열로 비동기 처리

			ObjectMapper mapper = new ObjectMapper();
			Map<String, Object> responseMap = mapper.readValue(response, Map.class); // 응답 JSON 파싱
			List<Map<String, Object>> choices = (List<Map<String, Object>>) responseMap.get("choices");
			Map<String, Object> message = (Map<String, Object>) choices.get(0).get("message");
			return (String) message.get("content"); // 요약된 내용 반환
		} catch (Exception e) {
			e.printStackTrace();
			return "리뷰 요약을 생성하는 중 오류가 발생했습니다."; // 오류 메시지 반환
		}
	}

	// OpenAI API를 사용하여 리뷰 긍정/부정 분석
	private Map<String, Object> analyzeSentiment(String reviewText) {
		Map<String, Object> requestBody = Map.of("model", model, "messages",
				List.of(Map.of("role", "system", "content",
						"리뷰의 긍정적인 내용을 분석하여 숫자로만 반환하세요. 형식: {\"positiveCount\": X, \"totalCount\": Y}"),
						Map.of("role", "user", "content", reviewText)),
				"temperature", temperature);

		try {
			String response = webClient.post().header("Authorization", "Bearer " + OPENAI_API_KEY) // 인증 헤더 추가
					.header("Content-Type", "application/json") // 요청 Content-Type 설정
					.bodyValue(requestBody) // 요청 본문 설정
					.retrieve() // 요청 실행
					.bodyToMono(String.class).block(); // 응답 데이터를 문자열로 비동기 처리

			ObjectMapper mapper = new ObjectMapper();
			Map<String, Object> responseMap = mapper.readValue(response, Map.class); // 응답 JSON 파싱
			List<Map<String, Object>> choices = (List<Map<String, Object>>) responseMap.get("choices");
			Map<String, Object> message = (Map<String, Object>) choices.get(0).get("message");
			String content = (String) message.get("content");

			return mapper.readValue(content, Map.class); // 분석 결과 반환
		} catch (Exception e) {
			e.printStackTrace();
			return Map.of("positiveCount", 0, "totalCount", 1); // 오류 시 기본 값 반환
		}
	}

	// OpenAI API를 사용하여 리뷰 데이터를 분석
	private String callOpenAI(String reviewText) {
		Map<String, Object> requestBody = Map.of("model", model, "messages",
				List.of(Map.of("role", "system", "content", """
						당신은 고객 리뷰를 분석하는 AI입니다. 입력된 리뷰 데이터를 분석하여 고객들이 공통적으로 중요하게 언급한 주요 항목과 그 세부 항목을 자동으로 추출하세요.

						### **분석 목표:**
						1. 리뷰 텍스트를 기반으로 **두 가지 주요 카테고리 항목**(예: 디자인, 맛, 배송 등)을 자동으로 추출하세요.
						2. **중복되는 주요 항목은 하나만 포함**해야 합니다. 동일한 항목이 여러 번 추출되지 않도록 유의하세요.
						3. 각 주요 카테고리에는 **정확히 3개의 주요 항목**만 포함해야 합니다.
						   - 3개를 초과하거나 미만인 경우 결과는 무효입니다.
						4. 세부 항목의 텍스트는 반드시 아래 조건을 충족해야 합니다:
						   - 띄어쓰기를 포함하여 **최대 11글자 이내**여야 합니다.
						   - 반드시 "**~요" 또는 "**~이에요"**로 끝나는 공손한 문법의 **완전한 문장**이어야 합니다.
						   - 한국어로 자연스럽고 의미가 명확해야 합니다. 어색하거나 불완전한 표현(예: "퀄리티요")은 사용하지 마세요.
						5. 각 세부 항목에 대해 비율(퍼센트)을 계산하여 반환합니다.
						6. 각 주요 항목의 세부 항목 비율 합계는 **정확히 100%**가 되어야 합니다.

						### **출력 형식:**
						{
						    "카테고리1": {
						        "항목1": 비율1,
						        "항목2": 비율2,
						        "항목3": 비율3
						    },
						    "카테고리2": {
						        "항목1": 비율1,
						        "항목2": 비율2,
						        "항목3": 비율3
						    }
						}
						"""), Map.of("role", "user", "content", "다음 리뷰를 분석하세요:\n" + reviewText)), "temperature", temperature,
				"max_tokens", max_tokens);

		try {
			return webClient.post().header("Authorization", "Bearer " + OPENAI_API_KEY) // 인증 헤더 추가
					.header("Content-Type", "application/json") // 요청 Content-Type 설정
					.bodyValue(requestBody) // 요청 본문 설정
					.retrieve() // 요청 실행
					.bodyToMono(String.class).block(); // 응답 데이터를 문자열로 비동기 처리
		} catch (Exception e) {
			e.printStackTrace();
			return "{\"error\":\"GPT API 호출 중 오류가 발생했습니다.\"}"; // 오류 발생 시 기본 메시지 반환
		}
	}

	// 리뷰를 최대 길이로 분할
	private List<String> splitIntoChunks(List<String> reviews, int maxLength) {
		List<String> chunks = new ArrayList<>(); // 분할된 결과 저장 리스트
		StringBuilder chunk = new StringBuilder(); // 현재 Chunk를 저장하는 StringBuilder

		for (String review : reviews) {
			if (chunk.length() + review.length() > maxLength) { // 최대 길이를 초과하면
				chunks.add(chunk.toString()); // Chunk를 리스트에 추가
				chunk = new StringBuilder(); // 새로운 Chunk 생성
			}
			chunk.append(review).append("\n"); // 리뷰 추가
		}

		if (chunk.length() > 0) { // 마지막 Chunk 처리
			chunks.add(chunk.toString());
		}

		return chunks; // 분할된 Chunk 리스트 반환
	}

	// GPT 분석 결과를 처리
	private Map<String, Object> processAnalysisResult(String gptResponse) {
	    ObjectMapper mapper = new ObjectMapper(); // JSON 파싱을 위한 ObjectMapper 생성
	    try {
	        // GPT 응답 JSON 파싱
	        Map<String, Object> responseMap = mapper.readValue(gptResponse, Map.class);
	        List<Map<String, Object>> choices = (List<Map<String, Object>>) responseMap.get("choices");
	        Map<String, Object> message = (Map<String, Object>) choices.get(0).get("message");
	        String content = (String) message.get("content");

	        // 결과가 JSON 형식으로 시작하는지 확인
	        if (content.trim().startsWith("{")) {
	            // JSON 데이터 파싱
	            Map<String, Object> parsedResult = mapper.readValue(content, Map.class);

	            // 각 카테고리의 주요 항목 수를 3개로 제한
	            parsedResult.forEach((key, value) -> {
	                if (value instanceof Map) {
	                    Map<String, Integer> items = (Map<String, Integer>) value;
	                    // 3개 항목으로 제한
	                    Map<String, Integer> limitedItems = items.entrySet().stream()
	                        .limit(3) // 최대 3개 항목만 유지
	                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
	                    parsedResult.put(key, limitedItems); // 제한된 결과로 다시 저장
	                }
	            });

	            return parsedResult; // 최종 결과 반환
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return null; // 오류 발생 시 null 반환
	}

	// Chunk 결과 병합
	private Map<String, Object> mergeChunkResults(List<Map<String, Object>> chunkResults) {
		Map<String, Object> finalResult = new HashMap<>(); // 최종 결과 저장 Map 생성

		for (Map<String, Object> chunk : chunkResults) {
			for (Map.Entry<String, Object> entry : chunk.entrySet()) {
				String category = entry.getKey(); // 카테고리 이름
				Map<String, Integer> items = (Map<String, Integer>) entry.getValue(); // 세부 항목

				if (!finalResult.containsKey(category)) { // 카테고리가 없으면 새로 추가
					List<Map<String, Object>> categoryItems = new ArrayList<>();
					finalResult.put(category, categoryItems);
				}

				List<Map<String, Object>> categoryList = (List<Map<String, Object>>) finalResult.get(category);

				for (Map.Entry<String, Integer> item : items.entrySet()) {
					Map<String, Object> itemMap = new HashMap<>(); // 세부 항목 저장
					itemMap.put("label", item.getKey()); // 항목 이름
					itemMap.put("percentage", item.getValue()); // 항목 비율
					categoryList.add(itemMap); // 리스트에 추가
				}
			}
		}

		return finalResult; // 병합된 결과 반환
	}
}
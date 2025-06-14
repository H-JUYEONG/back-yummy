package com.javaex.review.controller;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javaex.review.service.ReviewAnalysisService;

@RestController
public class ReviewAnalysisController {

	@Autowired
	private ReviewAnalysisService juReviewAnalysisService;

	@GetMapping("/api/review/analyze")
	public ResponseEntity<Map<String, Object>> getReviewAnalysis(@RequestParam("productId") int productId) {
		try {
			Map<String, Object> analysisResult = juReviewAnalysisService.getReviewAnalysis(productId);

			// 응답 로깅
			System.out.println("리액트에 전송할 분석 데이터:");
			System.out.println("긍정도: " + analysisResult.get("positiveRate"));
			System.out.println("리뷰 요약: " + analysisResult.get("summary"));
			System.out.println("상세 분석: " + analysisResult);

			return ResponseEntity.ok(analysisResult);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(500).body(Map.of("error", "리뷰 분석 중 오류가 발생했습니다. 관리자에게 문의하세요."));
		}
	}
}
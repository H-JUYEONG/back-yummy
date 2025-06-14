package com.javaex.vendor.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.review.model.HyunReviewVo;
import com.javaex.vendor.model.HyunVenderVo;
import com.javaex.vendor.repository.HyunVenderDao;

@Service
public class HyunVenderService {

	@Autowired
	private HyunVenderDao hyunVenderDao;

	public List<Map<String, Object>> getReservations(int venderId) {
		return hyunVenderDao.fetchReservations(venderId);
	}

	// 월별 주문 건수를 가져오는 서비스 메서드
	public int getMonthlyOrderCount(Long venderId) {
		return hyunVenderDao.getMonthlyOrderCount(venderId);
	}

	// 회원 포인트를 가져오는 서비스 메서드
	public HyunVenderVo getMemberPoints(int memberId) {
		return hyunVenderDao.getMemberPoints(memberId);
	}

	// 매출 데이터를 가져오는 서비스 메서드
	public HyunVenderVo getVenderRevenue(Long venderId) {
		return hyunVenderDao.getVenderRevenue(venderId);
	}

	// 신규 리뷰 조회 서비스
	public List<HyunReviewVo> getVenderReview(int venderId) {
		return hyunVenderDao.getVenderReview(venderId);
	}

	// 리뷰 통계 데이터
	public Map<String, Object> getReviewStats(int venderId) {
		// 긍정/부정 리뷰 비율
		Map<String, Integer> sentimentStats = hyunVenderDao.getReviewSentimentStats(venderId);

		// 별점 분포
		List<Map<String, Object>> ratingDistribution = hyunVenderDao.getRatingDistribution(venderId);

		// 워드 클라우드 데이터
		String wordCloudData = hyunVenderDao.getWordCloudData(venderId);

		// 결과 합치기
		Map<String, Object> reviewStats = new HashMap<>();
		reviewStats.put("positive_reviews", sentimentStats.get("positive_reviews"));
		reviewStats.put("negative_reviews", sentimentStats.get("negative_reviews"));
		reviewStats.put("rating_distribution", ratingDistribution);
		reviewStats.put("word_cloud", wordCloudData);

		return reviewStats;
	}

	// 매출 그래프
	public List<Map<String, Object>> getSalesDataByPeriod(Map<String, Object> params) {
		return hyunVenderDao.getSalesDataByPeriod(params);
	}

	// 리뷰 목록 조회
	public List<Map<String, Object>> getReviewsByVenderId(int venderId) {
		return hyunVenderDao.getReviewsByVenderId(venderId);
	}

	// alias로 venderId 조회
	public Long getVenderIdByAlias(String alias) {
		return hyunVenderDao.getVenderIdByAlias(alias);
	}

	// venderId로 alias 조회
	public String getAliasById(Long venderId) {
		return hyunVenderDao.getAliasById(venderId);
	}

}

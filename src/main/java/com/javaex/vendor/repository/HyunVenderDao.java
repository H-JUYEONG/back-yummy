package com.javaex.vendor.repository;

import com.javaex.review.model.HyunReviewVo;
import com.javaex.vendor.model.HyunVenderVo;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class HyunVenderDao {

	@Autowired
	private SqlSession sqlSession;

	public List<HyunVenderVo> getAllVenders() {
		return sqlSession.selectList("hyunVenderMapper.getAllVenders");
	}
	public List<Map<String, Object>> fetchReservations(int venderId) {
		return sqlSession.selectList("hyunVenderMapper.selectReservations", venderId);
	}

	// 월별 주문 건수를 가져오는 메서드
	public int getMonthlyOrderCount(Long venderId) {
		return sqlSession.selectOne("hyunVenderMapper.getMonthlyOrderCount", venderId);
	}

	// 회원 포인트를 가져오는 메서드
	public HyunVenderVo getMemberPoints(int memberId) {
		return sqlSession.selectOne("hyunVenderMapper.getMemberPoints", memberId);
	}

	// 매출 데이터를 가져오는 메서드
	public HyunVenderVo getVenderRevenue(Long venderId) {
		return sqlSession.selectOne("hyunVenderMapper.getVenderRevenue", venderId);
	}

	// 신규 리뷰 조회
	public List<HyunReviewVo> getVenderReview(int venderId) {
		return sqlSession.selectList("hyunVenderMapper.getVenderReview", venderId);
	}

	// 긍정/부정 리뷰 비율
	public Map<String, Integer> getReviewSentimentStats(int venderId) {
		return sqlSession.selectOne("hyunVenderMapper.getReviewSentimentStats", venderId);
	}

	// 별점 분포
	public List<Map<String, Object>> getRatingDistribution(int venderId) {
		return sqlSession.selectList("hyunVenderMapper.getRatingDistribution", venderId);
	}

	// 워드 클라우드 데이터
	public String getWordCloudData(int venderId) {
		return sqlSession.selectOne("hyunVenderMapper.getWordCloudData", venderId);
	}

	// 매출 그래프
	public List<Map<String, Object>> getSalesDataByPeriod(Map<String, Object> params) {
		return sqlSession.selectList("hyunVenderMapper.getSalesDataByPeriod", params);
	}

	// 리뷰 목록 조회
	public List<Map<String, Object>> getReviewsByVenderId(int venderId) {
		return sqlSession.selectList("hyunVenderMapper.getReviewList", venderId);
	}

	// alias로 venderId 조회
	public Long getVenderIdByAlias(String alias) {
		return sqlSession.selectOne("hyunVenderMapper.getVenderIdByAlias", alias);
	}
	
    public String getAliasById(Long venderId) {
        return sqlSession.selectOne("hyunVenderMapper.getAliasById", venderId);
    }

}

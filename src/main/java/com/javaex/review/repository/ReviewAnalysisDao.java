package com.javaex.review.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.review.model.ReviewAnalysisVo;

@Repository
public class ReviewAnalysisDao {

	@Autowired
	private SqlSession sqlSession;

	/* 날짜 제한 없이 모든 리뷰 조회 */
	public List<ReviewAnalysisVo> getAllReviews(int productId) {
		return sqlSession.selectList("review.getAllReviews", productId);
	}
	
	/* 날짜 제한 리뷰 조회 */
	public List<ReviewAnalysisVo> getAllReviewsLimit(int productId) {
		return sqlSession.selectList("review.getAllReviewsLimit", productId);
	}
	
	/* 모든 리뷰 데이터 개수 가져오기 */
	public int getAllReviewsCount(int productId) {
		return sqlSession.selectOne("review.getAllReviewsCount", productId);
	}
}

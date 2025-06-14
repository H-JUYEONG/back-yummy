package com.javaex.review.repository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.review.model.DdrReviewReplyVo;
import com.javaex.review.model.DdrReviewVo;
@Repository
public class DdrReviewDao {
    @Autowired
    private SqlSession sqlSession;
    // 리뷰 목록 조회
    public List<DdrReviewVo> selectProductReviews(Map<String, Object> param) {
        System.out.println("=== DAO ===");
        System.out.println("param received in dao: " + param);
        List<DdrReviewVo> result = sqlSession.selectList("DdrReview.selectProductReviews", param);
        System.out.println("Query result size: " + (result != null ? result.size() : "null"));
        return result;
    }
    // 리뷰 통계 조회
    public DdrReviewVo selectReviewStats(int productId) {
        return sqlSession.selectOne("DdrReview.selectReviewStats", productId);
    }
    public int checkPurchaseHistory(Map<String, Object> param) {
        return sqlSession.selectOne("DdrReview.checkPurchaseHistory", param);
    }
    public int checkReviewHistory(Map<String, Object> param) {
        return sqlSession.selectOne("DdrReview.checkReviewHistory", param);
    }
    public int insertReview(DdrReviewVo reviewVo) {
        System.out.println("[리뷰 등록 DAO] 데이터: " + reviewVo);
        return sqlSession.insert("DdrReview.insertReview", reviewVo);
    }
    // 리뷰 신고 처리
    public int updateReviewReport(int reviewId) {
        return sqlSession.update("DdrReview.updateReviewReport", reviewId);
    }
    public int selectOrderId(Map<String, Object> param) {
        Integer orderId = sqlSession.selectOne("DdrReview.selectOrderId", param);
        return (orderId != null) ? orderId : 0; // 기본값을 0으로 반환
    }
    public int checkReceiptStatus(Map<String, Object> param) {
        return sqlSession.selectOne("DdrReview.checkReceiptStatus", param);
    }
    public int getReviewVenderId(int reviewId) {
        return sqlSession.selectOne("DdrReview.getReviewVenderId", reviewId);
    }
    public int insertReviewReply(DdrReviewReplyVo replyVo) {
        return sqlSession.insert("DdrReview.insertReviewReply", replyVo);
    }
     public DdrReviewReplyVo selectReviewReply(int replyId) {
            return sqlSession.selectOne("DdrReview.selectReviewReply", replyId);
        }
        public int deleteReviewReply(Map<String, Object> param) {
            return sqlSession.delete("DdrReview.deleteReviewReply", param);
        }

    public int deleteReviewReplies(int reviewId) {
        return sqlSession.delete("DdrReview.deleteReviewReplies", reviewId);
    }
    public int deleteReview(Map<String, Object> param) {
        // 1. 먼저 리뷰에 달린 답글들을 삭제
    	sqlSession.delete("DdrReview.deleteReviewReplies", param.get("reviewId"));
        
        // 2. 그 다음 리뷰 삭제
        return sqlSession.delete("DdrReview.deleteReview", param);
    }
    // 새로 추가: 리뷰 권한 확인용 메서드
    public int countUserReview(Map<String, Object> param) {
        return sqlSession.selectOne("DdrReview.countUserReview", param);
    }
    public Map<String, Object> getOrderInfo(int productId, int userId) {
        Map<String, Object> param = new HashMap<>();
        param.put("productId", productId);
        param.put("userId", userId);
        return sqlSession.selectOne("DdrReview.selectOrderInfo", param);
    }
}
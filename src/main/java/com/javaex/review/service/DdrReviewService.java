package com.javaex.review.service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.common.util.JsonResult;
import com.javaex.review.model.DdrReviewReplyVo;
import com.javaex.review.model.DdrReviewVo;
import com.javaex.review.repository.DdrReviewDao;
@Service
public class DdrReviewService {
    @Autowired
    private DdrReviewDao ddrReviewDao;
    // 리뷰 목록 조회
    public List<DdrReviewVo> exegetProductReviews(Map<String, Object> param) {
        System.out.println("=== Service ===");
        System.out.println("param received in service: " + param);
        return  ddrReviewDao.selectProductReviews(param);
    }
    // 리뷰 통계 조회
    public DdrReviewVo exegetReviewStats(int productId) {
        return ddrReviewDao.selectReviewStats(productId);
    }
    // 리뷰 작성 자격 확인 - 수정된 로직
    public Map<String, Boolean> execheckReviewEligibility(int productId, int userId) {
        Map<String, Object> param = new HashMap<>();
        param.put("productId", productId);
        param.put("userId", userId);
        // 구매 완료 및 수령 완료 상태 체크
        boolean hasPurchased =  ddrReviewDao.checkPurchaseHistory(param) > 0;
        boolean hasReceived = ddrReviewDao.checkReceiptStatus(param) > 0;
        boolean hasReviewed = ddrReviewDao.checkReviewHistory(param) > 0;
        boolean canReview = hasPurchased && hasReceived && !hasReviewed;
        Map<String, Boolean> result = new HashMap<>();
        result.put("hasPurchased", hasPurchased);
        result.put("hasReceived", hasReceived);
        result.put("hasReviewed", hasReviewed);
        result.put("canReview", canReview);
        return result;
    }
    // 리뷰 등록
    public int exeaddReview(DdrReviewVo reviewVo) {
        System.out.println("[리뷰 등록 서비스] 데이터: " + reviewVo);
        return ddrReviewDao.insertReview(reviewVo);
    }
    // 리뷰 신고
    public int exereportReview(int reviewId) {
        return ddrReviewDao.updateReviewReport(reviewId);
    }
    public int exegetOrderId(Map<String, Object> param) {
        return ddrReviewDao.selectOrderId(param);
    }
    public boolean checkReviewPermission(int reviewId, int memberId) {
        Map<String, Object> param = new HashMap<>();
        param.put("reviewId", reviewId);
        param.put("userId", memberId);  // member_id를 전달
        return ddrReviewDao.countUserReview(param) > 0;
    }
    public int deleteReview(Map<String, Object> param) {
        return ddrReviewDao.deleteReview(param);
    }
     // 업체 대댓글 작성
    public int addReviewReply(DdrReviewReplyVo replyVo) {
        try {
            // 해당 리뷰의 상품이 이 업체의 것인지 확인
            int venderId = ddrReviewDao.getReviewVenderId(replyVo.getReviewId());
            if(venderId != replyVo.getReplyVenderId()) {
                return 0;  // 실패
            }

            // 대댓글 등록
            int result = ddrReviewDao.insertReviewReply(replyVo);
            return result;

        } catch(Exception e) {
            return 0; // 실패
        }
    }
    // 업체 대댓글 삭제
     public JsonResult deleteReviewReply(int replyId, int venderId) {
            try {
                Map<String, Object> param = new HashMap<>();
                param.put("replyId", replyId);
                param.put("venderId", venderId);

                // 먼저 해당 답글이 존재하는지, 그리고 이 업체의 것인지 확인
                DdrReviewReplyVo reply = ddrReviewDao.selectReviewReply(replyId);
                if (reply == null || reply.getReplyVenderId() != venderId) {
                    return JsonResult.fail("답글 삭제 권한이 없습니다.");
                }

                int result = ddrReviewDao.deleteReviewReply(param);
                if (result > 0) {
                    return JsonResult.success("대댓글이 삭제되었습니다.");
                } else {
                    return JsonResult.fail("대댓글 삭제 권한이 없습니다.");
                }
            } catch (Exception e) {
                return JsonResult.fail("대댓글 삭제 중 오류가 발생했습니다.");
            }
        }

    // 새로 추가: 대댓글 삭제 메서드
    public int deleteReviewReplies(int reviewId) {
        return ddrReviewDao.deleteReviewReplies(reviewId);
    }
}
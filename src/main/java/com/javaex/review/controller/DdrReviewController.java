package com.javaex.review.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.common.storage.S3Service;
import com.javaex.common.util.JsonResult;
import com.javaex.review.model.DdrReviewReplyVo;
import com.javaex.review.model.DdrReviewVo;
import com.javaex.review.repository.DdrReviewDao;
import com.javaex.review.service.DdrReviewService;

@RestController
@RequestMapping("/api/reviews")
public class DdrReviewController {

	@Autowired
	private DdrReviewService ddrReviewService;

	@Autowired
	private DdrReviewDao ddrReviewDao;

	@Autowired
	private S3Service s3Service;

	@GetMapping("/product/{productId}")
	public JsonResult getProductReviews(@PathVariable int productId,
	        @RequestParam(defaultValue = "recommend") String sort,
	        @RequestParam(defaultValue = "false") boolean showPhotoOnly) {
	    
	    System.out.println("=== Controller ===");
	    System.out.println("productId: " + productId);
	    System.out.println("sort: " + sort);
	    System.out.println("showPhotoOnly: " + showPhotoOnly);

	    Map<String, Object> param = new HashMap<>();
	    param.put("productId", productId);
	    param.put("sort", sort);
	    param.put("showPhotoOnly", showPhotoOnly);
	    
	    System.out.println("param map: " + param);

	    List<DdrReviewVo> reviews = ddrReviewService.exegetProductReviews(param);
	    return JsonResult.success(reviews);
	}
	// 리뷰 통계 조회
	@GetMapping("/stats/{productId}")
	public JsonResult getReviewStats(@PathVariable int productId) {
	    System.out.println("[리뷰 통계 조회] productId: " + productId);
	    DdrReviewVo stats = ddrReviewService.exegetReviewStats(productId);
	    return JsonResult.success(stats);
	}

	@GetMapping("/check-eligibility")
	public JsonResult checkReviewEligibility(@RequestParam(required = true) Integer productId,
			@RequestParam(required = true) Integer userId) {
		System.out.println("\n====== 리뷰 자격 확인 ======");
		System.out.println("상품 ID: " + productId);
		System.out.println("사용자 ID: " + userId);

		Map<String, Object> param = new HashMap<>();
		param.put("productId", productId);
		param.put("userId", userId);

		// 주문 있는지 확인
		boolean hasPurchase = ddrReviewDao.checkPurchaseHistory(param) > 0;
		System.out.println("주문 여부: " + hasPurchase);

		// 수령 완료 여부 확인
		boolean hasReceived = ddrReviewDao.checkReceiptStatus(param) > 0;
		System.out.println("수령 완료 여부: " + hasReceived);

		// 리뷰 작성 여부 확인
		boolean hasReviewed = ddrReviewDao.checkReviewHistory(param) > 0;
		System.out.println("리뷰 작성 여부: " + hasReviewed);

		Map<String, Boolean> result = ddrReviewService.execheckReviewEligibility(productId, userId);
		System.out.println("최종 결과: " + result);
		System.out.println("========================\n");

		return JsonResult.success(result);
	}

	@PostMapping
	public JsonResult addReview(@ModelAttribute DdrReviewVo reviewVo,
			@RequestParam(required = false) MultipartFile image) {
		System.out.println("[리뷰 등록] 요청 데이터: " + reviewVo);
		System.out.println("[리뷰 등록] 이미지 존재 여부: " + (image != null && !image.isEmpty()));

		try {
			// 이미지 처리
			if (image != null && !image.isEmpty()) {
				try {
					String imageUrl = s3Service.uploadFile(image);
					reviewVo.setReviewImageUrl(imageUrl);
					System.out.println("[리뷰 등록] 업로드된 이미지 URL: " + imageUrl);
				} catch (IOException e) {
					e.printStackTrace();
					return JsonResult.fail("이미지 업로드 실패: " + e.getMessage());
				}
			}

			// 주문 ID 확인
			Map<String, Object> param = new HashMap<>();
			param.put("productId", reviewVo.getProductId());
			param.put("userId", reviewVo.getReviewUserId());
			int orderId = ddrReviewService.exegetOrderId(param);

			if (orderId == 0) {
				return JsonResult.fail("주문 정보를 찾을 수 없습니다.");
			}

			reviewVo.setReviewOrderId(orderId);
			System.out.println("[리뷰 등록] 최종 등록할 데이터: " + reviewVo);

			int count = ddrReviewService.exeaddReview(reviewVo);
			if (count > 0) {
				return JsonResult.success(count);
			} else {
				return JsonResult.fail("리뷰 등록에 실패했습니다.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return JsonResult.fail("리뷰 등록 실패: " + e.getMessage());
		}
	}

	// 리뷰 신고
	@PostMapping("/report/{reviewId}")
	public JsonResult reportReview(@PathVariable int reviewId) {
		System.out.println("[리뷰 신고] reviewId: " + reviewId);
		int count = ddrReviewService.exereportReview(reviewId);
		return JsonResult.success(count);
	}
	
	//리뷰 삭제
	@DeleteMapping("/{reviewId}/{userId}")
	public JsonResult deleteReview(@PathVariable int reviewId, @PathVariable int userId) {
	    System.out.println("=========== 리뷰 삭제 요청 ===========");
	    System.out.println("reviewId: " + reviewId);
	    System.out.println("userId (member_id): " + userId);

	    try {
	        // 권한 체크
	        boolean hasPermission = ddrReviewService.checkReviewPermission(reviewId, userId);
	        System.out.println("권한 체크 결과: " + hasPermission);
	        
	        if (!hasPermission) {
	            System.out.println("권한 없음 - reviewId: " + reviewId + ", memberId: " + userId);
	            return JsonResult.fail("리뷰 삭제 권한이 없습니다.");
	        }

	        // 대댓글 삭제
	        int replyDeleteCount = ddrReviewService.deleteReviewReplies(reviewId);
	        System.out.println("삭제된 대댓글 수: " + replyDeleteCount);

	        // 리뷰 삭제
	        Map<String, Object> param = new HashMap<>();
	        param.put("reviewId", reviewId);
	        param.put("userId", userId);

	        int result = ddrReviewService.deleteReview(param);
	        System.out.println("리뷰 삭제 결과: " + result);

	        if (result > 0) {
	            return JsonResult.success("리뷰가 삭제되었습니다.");
	        } else {
	            return JsonResult.fail("리뷰 삭제에 실패했습니다.");
	        }
	    } catch (Exception e) {
	        System.out.println("리뷰 삭제 중 오류 발생: " + e.getMessage());
	        e.printStackTrace();
	        return JsonResult.fail("리뷰 삭제 중 오류가 발생했습니다: " + e.getMessage());
	    } finally {
	        System.out.println("================================");
	    }
	}
	// 업체가 댓글달기
	@PostMapping("/reply")
	public JsonResult addReviewReply(@RequestBody DdrReviewReplyVo replyVo) {
		System.out.println("[대댓글 작성] 데이터: " + replyVo);
		int result = ddrReviewService.addReviewReply(replyVo);

		if (result > 0) {
			return JsonResult.success(result);
		} else {
			return JsonResult.fail("대댓글 작성에 실패했습니다.");
		}
	}

	@DeleteMapping("/reply/{replyId}/{venderId}")
	public JsonResult deleteReviewReply(
	    @PathVariable("replyId") int replyId, 
	    @PathVariable("venderId") int venderId
	) {
	    System.out.println("=========== 답글 삭제 요청 ===========");
	    System.out.println("replyId: " + replyId);
	    System.out.println("venderId: " + venderId);
	    
	    try {
	        // 권한 확인 - 해당 답글이 이 업체의 것인지 확인
	        DdrReviewReplyVo reply = ddrReviewDao.selectReviewReply(replyId);
	        
	        if (reply == null) {
	            return JsonResult.fail("존재하지 않는 답글입니다.");
	        }
	        
	        if (reply.getReplyVenderId() != venderId) {
	            return JsonResult.fail("삭제 권한이 없습니다.");
	        }
	        
	        Map<String, Object> param = new HashMap<>();
	        param.put("replyId", replyId);
	        param.put("venderId", venderId);
	        
	        int result = ddrReviewDao.deleteReviewReply(param);
	        
	        if (result > 0) {
	            return JsonResult.success("답글이 삭제되었습니다.");
	        } else {
	            return JsonResult.fail("답글 삭제에 실패했습니다.");
	        }
	    } catch (Exception e) {
	        System.out.println("답글 삭제 중 오류 발생: " + e.getMessage());
	        e.printStackTrace();
	        return JsonResult.fail("답글 삭제 중 오류가 발생했습니다.");
	    } finally {
	        System.out.println("================================");
	    }
	}
}
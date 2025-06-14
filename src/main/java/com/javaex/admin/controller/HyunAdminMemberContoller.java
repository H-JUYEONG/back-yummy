package com.javaex.admin.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javaex.admin.model.HyunAdminMemberVo;
import com.javaex.admin.service.HyunAdminMemberService;
import com.javaex.order.model.HyunOrderVo;
import com.javaex.review.model.HyunReviewVo;
import com.javaex.vendor.model.HyunAdminVenderVo;

@RestController
@RequestMapping("/api/admin")
public class HyunAdminMemberContoller {

	@Autowired
	private HyunAdminMemberService hyunAdminMemberService;

	@GetMapping("/members")
	public List<HyunAdminMemberVo> getAllMembers() {
		return hyunAdminMemberService.getAllMembers();
	}
	
	@GetMapping("/venders")
	public List<HyunAdminVenderVo> getAllVenders() {
	    return hyunAdminMemberService.getAllVenders();
	}

    // 승인 대기 업체 조회 API
    @GetMapping("/venders/approval")
    public List<HyunAdminVenderVo> getApprovalVenders() {
        return hyunAdminMemberService.getApprovalVenders();
    }
    
    // 승인 상태 업데이트 API
    @PutMapping("/members/{memberId}/status")
    public ResponseEntity<String> updateMemberStatus(@PathVariable Long memberId, @RequestBody Map<String, Object> requestBody) {
        if (memberId == null) {
            throw new IllegalArgumentException("memberId cannot be null");
        }
        int isActive = (int) requestBody.get("isActive");
        hyunAdminMemberService.updateMemberStatus(memberId, isActive); // memberId를 사용
        return ResponseEntity.ok("Status updated successfully");
    }
    
    @GetMapping("/allOrders")
    public ResponseEntity<List<HyunOrderVo>> getAllOrders() {
        List<HyunOrderVo> orders = hyunAdminMemberService.getAllOrders();
        return ResponseEntity.ok(orders);
    }
    
    @GetMapping("/reports")
    public List<HyunReviewVo> getAllReports() {
        List<HyunReviewVo> reports = hyunAdminMemberService.getAllReports();
        reports.forEach(report -> System.out.println(report));
        return reports;
    }
    
    // 리뷰 삭제
    @DeleteMapping("/reviews/{reviewId}")
    public String deleteReview(@PathVariable int reviewId) {
        int result = hyunAdminMemberService.deleteReview(reviewId);
        return result > 0 ? "삭제 성공" : "삭제 실패";
    }

    // 신고 초기화
    @PostMapping("/reviews/{reviewId}/reset")
    public String resetReport(@PathVariable int reviewId) {
        int result = hyunAdminMemberService.resetReport(reviewId);
        return result > 0 ? "초기화 성공" : "초기화 실패";
    }
    
}

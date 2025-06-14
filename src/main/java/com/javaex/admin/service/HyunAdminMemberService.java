package com.javaex.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.admin.model.HyunAdminMemberVo;
import com.javaex.admin.repository.HyunAdminMemberDao;
import com.javaex.order.model.HyunOrderVo;
import com.javaex.review.model.HyunReviewVo;
import com.javaex.vendor.model.HyunAdminVenderVo;

@Service
public class HyunAdminMemberService {

	@Autowired
	private HyunAdminMemberDao hyunAdminMemberDao;

	public List<HyunAdminMemberVo> getAllMembers() {
		return hyunAdminMemberDao.selectAllMembers();
	}

	public List<HyunAdminVenderVo> getAllVenders() {
		return hyunAdminMemberDao.selectAllVenders();
	}

	// 승인 대기 업체 조회
	public List<HyunAdminVenderVo> getApprovalVenders() {
		return hyunAdminMemberDao.selectApprovalVenders();
	}

	public void updateMemberStatus(Long memberId, int isActive) {
		hyunAdminMemberDao.updateMemberStatus(memberId, isActive); // memberId를 사용
	}

	public List<HyunOrderVo> getAllOrders() {
		return hyunAdminMemberDao.selectGetOrders();
	}
	

    public List<HyunReviewVo> getAllReports() {
        return hyunAdminMemberDao.getAllReports();
    }
    
    public int deleteReview(int reviewId) {
        return hyunAdminMemberDao.deleteReview(reviewId);
    }

    public int resetReport(int reviewId) {
        return hyunAdminMemberDao.resetReport(reviewId);
    }
}

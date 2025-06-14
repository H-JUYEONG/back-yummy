package com.javaex.admin.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.admin.model.HyunAdminMemberVo;
import com.javaex.order.model.HyunOrderVo;
import com.javaex.review.model.HyunReviewVo;
import com.javaex.vendor.model.HyunAdminVenderVo;

@Repository
public class HyunAdminMemberDao {

	@Autowired
	private SqlSession sqlSession;

	// 회원 정보 전체 조회
	public List<HyunAdminMemberVo> selectAllMembers() {
		return sqlSession.selectList("hyunAdminMapper.selectAllMembers");
	}

	public List<HyunAdminVenderVo> selectAllVenders() {
		return sqlSession.selectList("hyunAdminMapper.selectAllVenders");
	}

	// 승인 대기 업체 조회
	public List<HyunAdminVenderVo> selectApprovalVenders() {
		return sqlSession.selectList("hyunAdminMapper.selectApprovalVenders");
	}

	public void updateMemberStatus(Long memberId, int isActive) {
		Map<String, Object> params = new HashMap<>();
		params.put("memberId", memberId); // memberId 사용
		params.put("isActive", isActive);
		sqlSession.update("hyunAdminMapper.updateMemberStatus", params);
	}

	public List<HyunOrderVo> selectGetOrders() {
		return sqlSession.selectList("hyunAdminMapper.selectGetOrders");
	}
	
	
    public List<HyunReviewVo> getAllReports() {
        return sqlSession.selectList("hyunAdminMapper.getAllReports");
    }
    
    public int deleteReview(int reviewId) {
        return sqlSession.delete("hyunAdminMapper.deleteReview", reviewId);
    }

    public int resetReport(int reviewId) {
        return sqlSession.update("hyunAdminMapper.resetReport", reviewId);
    }
}

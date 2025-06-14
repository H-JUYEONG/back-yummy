package com.javaex.mypage.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DdrMypagePointHistoryDao {

	@Autowired
	private SqlSession sqlSession;

	// 포인트 조회 쿼리 실행
	public Integer getTotalPointByMemberId(Long memberId) {
		return sqlSession.selectOne("DdrMypagePointHistory.getTotalPointByMemberId", memberId);
	}

	public void insertPointHistory(int memberId, int points, int cakeDesignId, int orderId, String reason) {
		// 기본값 설정
		if (reason == null || reason.trim().isEmpty()) {
			reason = "사유 없음";
		}

		Map<String, Object> params = new HashMap<>();
		params.put("memberId", memberId);
		params.put("points", points);
		params.put("cakeDesignId", cakeDesignId);
		params.put("orderId", orderId);
		params.put("reason", reason);

		sqlSession.insert("DdrMypagePointHistory.insertPointHistory", params);
	}

	public void updateMemberPoints(int memberId, int points) {
		Map<String, Object> params = new HashMap<>();
		params.put("memberId", memberId);
		params.put("points", points);

		sqlSession.update("DdrMypagePointHistory.updateMemberPoints", params);
	}
	
	public List<Map<String, Object>> getPointHistoryByMemberId(int memberId) {
	    return sqlSession.selectList("DdrMypagePointHistory.getPointHistoryByMemberId", memberId);
	}


}

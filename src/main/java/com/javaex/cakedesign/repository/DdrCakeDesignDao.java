package com.javaex.cakedesign.repository;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DdrCakeDesignDao {
	@Autowired
	private SqlSession sqlSession;

	// 케이크 디자인 작성자의 member_id와 type 조회
	public Map<String, Object> getMemberInfo(int cakeDesignId) {
		return sqlSession.selectOne("DdrCakeDesign.getMemberInfo", cakeDesignId);
	}

	// User 테이블의 포인트 업데이트
	public int updateUserPoints(int memberId, int points) {
		Map<String, Object> map = new HashMap<>();
		map.put("memberId", memberId);
		map.put("points", points);
		return sqlSession.update("DdrCakeDesign.updateUserPoints", map);
	}

	public Map<String, Object> getDesignInfo(int cakeDesignId) {
		return sqlSession.selectOne("DdrCakeDesign.getDesignInfo", cakeDesignId);
	}

	public int updateMemberPoints(int memberId, int points) {
		Map<String, Object> map = new HashMap<>();
		map.put("memberId", memberId);
		map.put("points", points);
		return sqlSession.update("DdrCakeDesign.updateMemberPoints", map);
	}
}
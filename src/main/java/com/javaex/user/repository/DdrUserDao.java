package com.javaex.user.repository;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.user.model.DdrUserNicknameVo;
import com.javaex.vendor.model.DdrVenderVo;

@Repository
public class DdrUserDao {

	@Autowired
	private SqlSession sqlSession;

	public DdrUserNicknameVo selectUserNickname(int userId) {
		System.out.println("[UserMypageDao.selectUserNickname()]");
		return sqlSession.selectOne("DdrUser.selectUserNickname", userId);
	}

	public int reportReview(int reviewId, int userId, String reason) {
		return sqlSession.update("DdrUser.reportReview",
				Map.of("reviewId", reviewId, "userId", userId, "reason", reason));
	}

	public int getReportStatus(int reviewId) {
		return sqlSession.selectOne("DdrUser.getReportStatus", reviewId);
	}

	public DdrVenderVo selectVenderProfile(int venderId) {
		return sqlSession.selectOne("DdrUser.selectVenderProfile", venderId);
	}
}

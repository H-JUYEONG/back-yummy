package com.javaex.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.user.model.DdrUserNicknameVo;
import com.javaex.user.repository.DdrUserDao;
import com.javaex.vendor.model.DdrVenderVo;

@Service
public class DdrUserService {

	@Autowired
	private DdrUserDao ddrUserDao;

	public DdrUserNicknameVo exeGetUserNickname(int userId) {
		return ddrUserDao.selectUserNickname(userId);
	}

	public String reportReview(int reviewId, int userId, String reason) {
		int status = ddrUserDao.getReportStatus(reviewId);

		if (status == 1) {
			return "이미 신고된 리뷰입니다.";
		}

		int result = ddrUserDao.reportReview(reviewId, userId, reason);
		return result > 0 ? "신고가 접수되었습니다." : "신고에 실패했습니다.";
	}

	public DdrVenderVo exeGetVenderProfile(int venderId) {
		return ddrUserDao.selectVenderProfile(venderId);
	}
}
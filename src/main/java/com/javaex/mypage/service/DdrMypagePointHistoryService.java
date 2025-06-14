package com.javaex.mypage.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.mypage.repository.DdrMypagePointHistoryDao;

@Service
public class DdrMypagePointHistoryService {

	@Autowired
	private DdrMypagePointHistoryDao ddrMypagePointHistoryDao;

	// 포인트 조회 메서드
    public Integer getUserTotalPoint(Long memberId) {
        return ddrMypagePointHistoryDao.getTotalPointByMemberId(memberId);
    }
   

    public List<Map<String, Object>> getPointHistoryByMemberId(int memberId) {
        return ddrMypagePointHistoryDao.getPointHistoryByMemberId(memberId);
    }
  
}
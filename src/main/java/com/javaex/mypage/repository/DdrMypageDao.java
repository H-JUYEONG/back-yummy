package com.javaex.mypage.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.mypage.model.DdrMypageOrderDetailVo;
import com.javaex.mypage.model.DdrMypageOrderStatusCountVo;
import com.javaex.mypage.model.DdrMypageOrderVideoVo;
import com.javaex.mypage.model.DdrMypageOrderVo;

@Repository
public class DdrMypageDao {

	@Autowired
	private SqlSession sqlSession;

	public List<DdrMypageOrderVo> getMyPageOrderList(int userId) {
		return sqlSession.selectList("DdrMypage.getOrderList", userId);
	}

	public DdrMypageOrderDetailVo getMyPageOrderDetail(int orderId) {
		return sqlSession.selectOne("DdrMypage.getOrderDetail", orderId);
	}

	public DdrMypageOrderStatusCountVo getOrderStatusCount(int userId) {
		return sqlSession.selectOne("DdrMypage.getOrderStatusCount", userId);
	}

	public  DdrMypageOrderVideoVo getOrderMediaDetail(int orderId) { 
		System.out.println("[DAO] 주문 미디어 상세 조회 시작 - orderId: " + orderId);
		 DdrMypageOrderVideoVo result = sqlSession.selectOne("DdrMypage.getOrderMediaDetail", orderId);
		if (result != null) {
			System.out.println("[DAO] 미디어 조회 결과: " + result);
		} else {
			System.out.println("[DAO] 조회된 미디어 없음");
		}
		return result;
	}
}

package com.javaex.mypage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.mypage.model.DdrMypageOrderDetailVo;
import com.javaex.mypage.model.DdrMypageOrderStatusCountVo;
import com.javaex.mypage.model.DdrMypageOrderVideoVo;
import com.javaex.mypage.model.DdrMypageOrderVo;
import com.javaex.mypage.repository.DdrMypageDao;

@Service
public class DdrMypageService {

	@Autowired
	private DdrMypageDao ddrMyPageDao;

	public List<DdrMypageOrderVo> exeMyPageOrderList(int userId) {
		return  ddrMyPageDao.getMyPageOrderList(userId);
	}

	public DdrMypageOrderDetailVo exeMyPageOrderDetail(int orderId) {
		return  ddrMyPageDao.getMyPageOrderDetail(orderId);
	}

	public DdrMypageOrderStatusCountVo exeOrderStatusCount(int userId) {
		return  ddrMyPageDao.getOrderStatusCount(userId);
	}

	public  DdrMypageOrderVideoVo exegetOrderMediaDetail(int orderId) { 
		System.out.println("[Service] 주문 미디어 상세 조회 시작 - orderId: " + orderId);
		 DdrMypageOrderVideoVo orderDetail =  ddrMyPageDao.getOrderMediaDetail(orderId); // 
		if (orderDetail != null) {
			System.out.println("[Service] 미디어 조회 결과: " + orderDetail);
		} else {
			System.out.println("[Service] 조회된 미디어 없음");
		}
		return orderDetail;
	}
}
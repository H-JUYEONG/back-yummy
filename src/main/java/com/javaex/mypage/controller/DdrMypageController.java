package com.javaex.mypage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.javaex.common.util.JsonResult;
import com.javaex.mypage.model.DdrMypageOrderDetailVo;
import com.javaex.mypage.model.DdrMypageOrderStatusCountVo;
import com.javaex.mypage.model.DdrMypageOrderVideoVo;
import com.javaex.mypage.model.DdrMypageOrderVo;
import com.javaex.mypage.service.DdrMypageService;

@RestController
@RequestMapping("/api/mypage/orders")
public class DdrMypageController {

	@Autowired
	private DdrMypageService ddrMyPageService;

	//마이페이지 조회목록 불러오기
	@GetMapping
	public List<DdrMypageOrderVo> getMyPageOrderList(@RequestParam("userId") int userId) {
		return ddrMyPageService.exeMyPageOrderList(userId);
	}
	
	//마이페이지 주문상세페이지 불러오기
	@GetMapping("/{orderId}")
	public DdrMypageOrderDetailVo getMyPageOrderDetail(@PathVariable("orderId") int orderId) {
		return ddrMyPageService.exeMyPageOrderDetail(orderId);
	}

	//마이페이지 상태 조회
	@GetMapping("/status-count")
	public DdrMypageOrderStatusCountVo getOrderStatusCount(@RequestParam("userId") int userId) {
		return ddrMyPageService.exeOrderStatusCount(userId);
	}
	
	//마이페이지 영상/사진 불러오기
	@GetMapping("/detail/media/{orderId}")
	@ResponseBody
	public JsonResult getOrderMediaDetail(@PathVariable("orderId") int orderId) {
	    System.out.println("[미디어 상세 조회] 시작 - orderId: " + orderId);
	    
	    DdrMypageOrderVideoVo orderDetail = ddrMyPageService.exegetOrderMediaDetail(orderId);
	    
	    if(orderDetail != null) {
	        System.out.println("비디오 URL: " + orderDetail.getOrderVideoUrl());
	        System.out.println("사진 URL: " + orderDetail.getOrderPhotoUrl());
	    }
	    
	    return JsonResult.success(orderDetail);
	}
}
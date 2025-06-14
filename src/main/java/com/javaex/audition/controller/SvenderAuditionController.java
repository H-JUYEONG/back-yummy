package com.javaex.audition.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.javaex.audition.model.SauditionInsertVo;
import com.javaex.audition.model.SauditionStatusVo;
import com.javaex.audition.model.SsoAuditionVo;
import com.javaex.audition.service.SvenderAuditionService;
import com.javaex.common.util.JsonResult;

@RestController
public class SvenderAuditionController {
	
	@Autowired
	private SvenderAuditionService auditionService;
	
	//실시간 리스트
	@GetMapping(value="/api/vender/auditionList/{venderId}")
	public JsonResult getAllAuditionList(@PathVariable ("venderId") int venderId) {
		//System.out.println("오디션 리스트 준완");
		//System.out.println(venderId);
		Map<String, Object> dmap = auditionService.exeAuditionList(venderId);
		
		return JsonResult.success(dmap);
	}
	
	//오디션 요청내역리스트, 업체 상품내역리스트 가져오기
	@GetMapping(value="/api/vender/order/{auditionId}/{venderId}")
	public JsonResult getOrderOne(@PathVariable("auditionId") int auditionId,
							@PathVariable("venderId") int venderId) {
		System.out.println("오디션 오더 준 완");
		System.out.println(auditionId);
		
		Map<String,Object> fmap = auditionService.exeGetOrderList(auditionId,venderId);
		
		return JsonResult.success(fmap);
		
	}
	
	//케이크 옵션리스트
	@GetMapping(value="/api/getOptions/{venderId}/{selectProductId}")
	public JsonResult getOptions(@PathVariable ("venderId") int venderId
						  ,@PathVariable ("selectProductId") int productId) {
		System.out.println("옵션 리스트 준비");
		Map<String, Object> optionMap = auditionService.exeGetOptions(venderId, productId);
		//System.out.println(optionMap);
		return JsonResult.success(optionMap);
		
	}
	
	//오디션 신청하기
	@PutMapping(value="/api/insertAudition")
	public JsonResult insertAudition(@RequestBody SauditionInsertVo insertDataVo) {
		//System.out.println("오디션 인서트 준비완");
		System.out.println(insertDataVo);
		int count = auditionService.exeInsertAudition(insertDataVo);
		
		return JsonResult.success(count);
	}
	
	//참여중인 오디션 리스트
	@GetMapping(value="api/getAuditionList/{venderId}")
	public JsonResult getIngAudition(@PathVariable ("venderId")int venderId) {
		//System.out.println(venderId);
		List<SauditionStatusVo> ingAuditionList = auditionService.exeGetIngAudition(venderId);
		
		return JsonResult.success(ingAuditionList);
	}
	
	//참여중인 오디션 취소하기
	@DeleteMapping(value="api/ingAuditionDelete/{auditionCartId}")
	public JsonResult ingAuditionDelete(@PathVariable("auditionCartId") int auditionCartId) {
		//System.out.println("삭제하기"+auditionCartId);
		int count = auditionService.exeDeleteAudition(auditionCartId);
		
		if(count > 0) {
			return JsonResult.success(count);
		}else {
			return JsonResult.fail("삭제 실패");
		}
	}

}

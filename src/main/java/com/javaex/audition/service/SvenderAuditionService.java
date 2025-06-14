package com.javaex.audition.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.audition.model.SauditionInsertVo;
import com.javaex.audition.model.SauditionStatusVo;
import com.javaex.audition.model.SsoAuditionOptionVo;
import com.javaex.audition.model.SsoAuditionVo;
import com.javaex.audition.repository.SvenderAuditionDao;

@Service
public class SvenderAuditionService {
	
	@Autowired
	private SvenderAuditionDao auditionDao;
	
	//실시간 오디션 리스트 가져오기
	public Map<String, Object> exeAuditionList(int venderId) {
		Map<String, Object> dmap = new HashMap<String, Object>();
		
		//업체상품도안정보 가져오기
		List<SsoAuditionVo> venderProduct = auditionDao.getVender(venderId);
		
		//오디션 정보 가져오기
		List<SsoAuditionVo> auditionList = auditionDao.auditionList(venderId);
		
		//1.수령방식 체크 후 Date필드값 변경
		for(int i=0; i<auditionList.size(); i++) {
			
			SsoAuditionVo audition = auditionList.get(i);
			
			if("픽업".equals(audition.getDeliveryMethod())) {
				auditionList.get(i).setDate(audition.getPickupDate());
				
			}else {
				auditionList.get(i).setDate(audition.getDeliveryDate());
			}
		}
		
		//2.도안보유 확인
		for (int i = 0; i < auditionList.size(); i++) {
			SsoAuditionVo audition = auditionList.get(i);

			String status = "미보유";

			if (audition.getDesignId() == null) {
				status = "요청도안없음";
			} else {
				for (int j = 0; j < venderProduct.size(); j++) {
					SsoAuditionVo product = venderProduct.get(j);

					if (audition.getDesignId().equals(product.getCakeDesignId())) {
						status = "보유중";
						break;
					}
				}
			}
			auditionList.get(i).setDesignStatus(status);
		}
		//System.out.println("@" + venderProduct);
		
		//3.업체 구 가져오기
		String district = auditionDao.getDistrict(venderId);
		//System.out.println("업체 구는 ??" + district);
		
		dmap.put("auditionList",auditionList);
		dmap.put("district", district);
		
		return dmap;
	}
	
	
	//오디션 요청내역리스트, 업체 상품내역리스트 가져오기
	public Map<String,Object> exeGetOrderList(int auditionId, int venderId) {
		Map<String,Object> fmap = new HashMap<String,Object>();
		
		//1.유저 주문내역
		SsoAuditionVo userOrder = auditionDao.getOrder(auditionId);
		System.out.println("@@"+userOrder);
		
		if(userOrder.getDeliveryMethod() != null && "픽업".equals(userOrder.getDeliveryMethod())) {
			userOrder.setDate(userOrder.getPickupDate());
		}else {
			userOrder.setDate(userOrder.getDeliveryDate());
		}
		//System.out.println("@@@@"+userOrder);
		
		//2.업체 상품리스트 가져오기
		List<SsoAuditionVo> productList = auditionDao.getProduct(venderId);
		
		for(int i=0; i<productList.size(); i++) {
			SsoAuditionVo product = productList.get(i);
			
			String selectProduct = "불일치";
			if(userOrder.getDesignId() != null && userOrder.getDesignId().equals(product.getCakeDesignId())) {
				selectProduct = "일치";
			}else if(userOrder.getDesignId() == null) {
				selectProduct = "요청도안없음";
			}
			productList.get(i).setSelectProduct(selectProduct);
		}
		
		fmap.put("productList", productList);
		fmap.put("userOrder", userOrder);
		
		return fmap;
	}
	
	//옵션들 가져오기
	public Map<String, Object> exeGetOptions(int venderId,int productId) {
		
		Map<String, Object> optionMap = new HashMap<>();
		SsoAuditionOptionVo params = new SsoAuditionOptionVo(venderId, productId);
		
		//1. 케이크 가져오기
		SsoAuditionOptionVo cake = auditionDao.getCake(params);
		
		//1-1. 상품타입
		SsoAuditionOptionVo cakeType = auditionDao.getCakeType(params);
		
		//1-2. 카테고리
		List<SsoAuditionOptionVo> cakeCar = auditionDao.getCar(params);
		
		//1-3. 배경색상
		List<SsoAuditionOptionVo> backgroundColor = auditionDao.getBackgroundColor(params);
		
		//2. 크림색상
		List<SsoAuditionOptionVo> creamList = auditionDao.getCream(params);
		
		//2-1. 크림위치
		List<SsoAuditionOptionVo> creamPosition = auditionDao.getCreamPosition(params);
		
		//2-2. 크림맛
		List<SsoAuditionOptionVo> creamTaste = auditionDao.getCreamTaste(params);
		
		//3. 사이즈
		List<SsoAuditionOptionVo> sizeList = auditionDao.getSize(params);
		
		//4. 맛
		List<SsoAuditionOptionVo> tasteList = auditionDao.getTaste(params);
		
		//5. 데코 타입
		List<SsoAuditionOptionVo> decorationType = auditionDao.getDecorationType(params);

		//5-1. 데코 색상
		List<SsoAuditionOptionVo> decorationColor = auditionDao.getDecorationColor(params);
		
		optionMap.put("cake", cake);
		optionMap.put("cakeType", cakeType);
		optionMap.put("cakeCar", cakeCar);
		optionMap.put("backgroundColor", backgroundColor);
		
		optionMap.put("creamList", creamList);
		optionMap.put("creamPosition", creamPosition);
		optionMap.put("creamTaste", creamTaste);
		
		optionMap.put("sizeList", sizeList);
		optionMap.put("tasteList", tasteList);
		
		optionMap.put("decorationType", decorationType);
		optionMap.put("decorationColor", decorationColor);
		
		System.out.println("###"+optionMap);
		return optionMap;
	}
	
	
	
	//오디션 신청하기
	public int exeInsertAudition(SauditionInsertVo insertDataVo) {
		int count = auditionDao.insertAudition(insertDataVo);
		return count;
	}
	
	//참여중인 오디션 리스트
	public List<SauditionStatusVo> exeGetIngAudition(int venderId) {
		List<SauditionStatusVo> ingAuditionList = auditionDao.getIngAuditionList(venderId);
		
		return ingAuditionList;
	}
	
	//참여중인 오디션 취소하기
	public int exeDeleteAudition(int auditionCartId) {
		int count = auditionDao.deleteAudition(auditionCartId);
		return count;
	}
	

}

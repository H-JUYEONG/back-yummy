package com.javaex.vendor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.vendor.model.SsoVenderProductVo;
import com.javaex.vendor.model.SvenderVo;
import com.javaex.vendor.repository.SsoVenderDao;

@Service
public class SsoVenderService {

	@Autowired
	private SsoVenderDao ssoVenderDao;
	
	//업체여부 확인
	public int exeCheckNo(int venderId) {
		int shopStatus = ssoVenderDao.checkNo(venderId);
		
		return shopStatus;
	}
	
	//업체 상태변경 : 생성, 비활성
	public int exestatusOne(int venderId) {
		int count = ssoVenderDao.statusOne(venderId);
		
		return count;
	}
	
	//업체등록
	public int exeinsertPage(SvenderVo svenderVo) {
		//System.out.println(venderVo);
		
		int count = ssoVenderDao.insertPage(svenderVo);
		
		return count;
	}
	//수정정보 가져오기
	public SvenderVo exeOneList(int venderId) {
		SvenderVo venderVo = ssoVenderDao.oneList(venderId);
		
		return venderVo;
	}
	
	////////////////////////////
	//업체별 정보 가져오기
	public SvenderVo exeGetDetails(int venderId) {
		SvenderVo venderVo = ssoVenderDao.getDetails(venderId);
		
		return venderVo;
	}
	//헤더 베너가져오기
	public String exeGetBanner(int venderId) {
		String bannerURL = ssoVenderDao.getBanner(venderId);
		
		return bannerURL;
	}
	//업체별 전체 상품 가져오기
	public List<SsoVenderProductVo> getAllProductList(int venderId) {
		List<SsoVenderProductVo> getAllProductList = ssoVenderDao.getAllProductList(venderId);
		return getAllProductList;
	}
	
	
	//카테고리별 상품리스트가져오기
	public List<SsoVenderProductVo> exeGetGoodsList(SsoVenderProductVo nums){
		List<SsoVenderProductVo> productList = ssoVenderDao.getGoodeList(nums);
		
		return productList;
	}
	
	//카테고리 가져오기
	public List<SsoVenderProductVo> exeCarList(int venderId) {
		List<SsoVenderProductVo> carList = ssoVenderDao.getCarList(venderId);
		
		return carList;
	}
	
	//사이드바 로고사진 가져오기
	public String exeGetLogo(int venderId) {
		String logoURL = ssoVenderDao.getLogo(venderId);
		
		return logoURL;
	}
	
	
	
}

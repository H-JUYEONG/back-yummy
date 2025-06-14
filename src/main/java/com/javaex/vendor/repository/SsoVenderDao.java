package com.javaex.vendor.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vendor.model.SsoVenderProductVo;
import com.javaex.vendor.model.SvenderVo;

@Repository
public class SsoVenderDao {

	@Autowired
	private SqlSession sqlSession;
	
	//업체 등록여부 확인
	public int checkNo(int venderId) {
		int shopStatus = sqlSession.selectOne("sVender.checkNo", venderId);
		
		return shopStatus;
	}
	
	//업체 상태변경 : 생성, 비활성
	public int statusOne(int venderId) {
		int count = sqlSession.update("sVender.updateOne", venderId);
		
		return count;
	}
	
	//업체등록
	public int insertPage(SvenderVo svenderVo) {
		System.out.println(svenderVo);
		
		int count = sqlSession.update("sVender.venderMakePage",svenderVo);
		System.out.println(count);
		return count;
	}
	
	//수정정보 가져오기
	public SvenderVo oneList(int venderId) {
		SvenderVo venderVo = sqlSession.selectOne("sVender.selectAll", venderId);
		//System.out.println(venderVo);
		return venderVo;
	}
	
	//////////////////////////////////
	//업체별 정보 가져오기
	public SvenderVo getDetails(int venderId) {
		SvenderVo venderVo = sqlSession.selectOne("sVender.getDetails", venderId);
		//System.out.println(venderVo);
		return venderVo;
	}
	
	//헤더 배너사진 가져오기
	public String getBanner(int venderId) {
		String bannerURL = sqlSession.selectOne("sVender.getBanner", venderId);
		//System.out.println(bannerURL);
		return bannerURL;
	}
	//업체별 전체 상품 가져오기
	public List<SsoVenderProductVo> getAllProductList(int venderId) {
		List<SsoVenderProductVo> getAllProductList = sqlSession.selectList("sVender.getAllProductListShop",venderId);
		//System.out.println("~~"+getAllProductList);
		return getAllProductList;
	}
	
	
	//카테고리별 상품 리스트 가져오기
	public List<SsoVenderProductVo> getGoodeList(SsoVenderProductVo nums){
		List<SsoVenderProductVo> productList = sqlSession.selectList("sVender.getProductList", nums);
		//System.out.println(productList);
		return productList;
	}
	
	//카테고리 가져오기
	public List<SsoVenderProductVo> getCarList(int venderId){
		List<SsoVenderProductVo> carList = sqlSession.selectList("sVender.getCarList", venderId);
		
		return carList;
	}
	
	//사이드바 로고사진 가져오기
	public String getLogo(int venderId) {
		String logoURL = sqlSession.selectOne("sVender.getLogo", venderId);
		
		return logoURL;
	}
	
}

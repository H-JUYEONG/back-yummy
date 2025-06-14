package com.javaex.audition.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.audition.model.SauditionInsertVo;
import com.javaex.audition.model.SauditionStatusVo;
import com.javaex.audition.model.SsoAuditionOptionVo;
import com.javaex.audition.model.SsoAuditionVo;

@Repository
public class SvenderAuditionDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	//실시간 오디션리스트 가져오기
	public List<SsoAuditionVo> auditionList(int venderId) {
		List<SsoAuditionVo> auditionList = sqlSession.selectList("SsoAudition.auditionAllList");
		System.out.println(auditionList);
		
		return auditionList;
	}
	
	//실시간 리스트 업체정보 가져오기
	public List<SsoAuditionVo> getVender(int venderId){
		List<SsoAuditionVo> venderProduct= sqlSession.selectList("SsoAudition.getVender", venderId);
		System.out.println("district왔으면 좋겠다"+venderProduct);
		return venderProduct;
	}
	
	// - 해당업체 구 가져오기
	public String getDistrict(int venderId) {
		String district = sqlSession.selectOne("SsoAudition.getDistrict", venderId);
		return district;
	}
	
	
	//업체 오디션 신청폼 주문정보 가져오기
	public SsoAuditionVo getOrder(int auditionId) {
		SsoAuditionVo userOrder = sqlSession.selectOne("SsoAudition.getOrder", auditionId);
		System.out.println(userOrder);
		return userOrder;
	}
	
	//업체 오디션 신청폼 상품리스트 가져오기
	public List<SsoAuditionVo> getProduct(int venderId){
		List<SsoAuditionVo> productList = sqlSession.selectList("SsoAudition.getProduct", venderId);
		//System.out.println("!"+productList);
		return productList;
	}
	
	//옵션 : 케이크 가져오기
	public SsoAuditionOptionVo getCake(SsoAuditionOptionVo params) {
		SsoAuditionOptionVo cake = sqlSession.selectOne("SsoAudition.cake",params);
		//System.out.println(cake);
		return cake;
	}
	
	///옵션: 타입가져오기
	public SsoAuditionOptionVo getCakeType(SsoAuditionOptionVo params) {
		SsoAuditionOptionVo cakeType = sqlSession.selectOne("SsoAudition.cakeType",params);
		
		return cakeType;
	}
	
	//옵션: 카테고리 가져오기
	public List<SsoAuditionOptionVo> getCar(SsoAuditionOptionVo params) {
		List<SsoAuditionOptionVo> cakeCar = sqlSession.selectList("SsoAudition.cakeCar",params);
		
		return cakeCar;
	}
	
	//옵션: 배경색상
	public List<SsoAuditionOptionVo> getBackgroundColor(SsoAuditionOptionVo params){
		List<SsoAuditionOptionVo> backgroundColor = sqlSession.selectList("SsoAudition.backgroundColor",params);
		
		return backgroundColor;
	}
	//옵션: 크림색상
	public List<SsoAuditionOptionVo> getCream(SsoAuditionOptionVo params) {
		List<SsoAuditionOptionVo> creamList = sqlSession.selectList("SsoAudition.creams", params);
		//System.out.println("ddddd"+creamList);
		return creamList;
	}
	
	//크림 위치
	public List<SsoAuditionOptionVo> getCreamPosition(SsoAuditionOptionVo params){
		List<SsoAuditionOptionVo> creamPosition = sqlSession.selectList("SsoAudition.creamPosition",params);
		return creamPosition;
	}
	
	//크림 맛
	public List<SsoAuditionOptionVo> getCreamTaste(SsoAuditionOptionVo params){
		List<SsoAuditionOptionVo> creamTaste = sqlSession.selectList("SsoAudition.creamTaste",params);
		return creamTaste;
	}
	//옵션: 사이즈
	public List<SsoAuditionOptionVo> getSize(SsoAuditionOptionVo params){
		List<SsoAuditionOptionVo> sizeList = sqlSession.selectList("SsoAudition.size",params);
		
		return sizeList;
	}
	
	//옵션: 맛
	public List<SsoAuditionOptionVo> getTaste(SsoAuditionOptionVo params){
		List<SsoAuditionOptionVo> tasteList = sqlSession.selectList("SsoAudition.taste", params);
		
		return tasteList;
	}
	
	//옵션: 데코 타입
	public List<SsoAuditionOptionVo> getDecorationType(SsoAuditionOptionVo params){
		List<SsoAuditionOptionVo> decorationType = sqlSession.selectList("SsoAudition.decorationType",params);
		return decorationType;
	}
	//옵션: 데코 색상
	public List<SsoAuditionOptionVo> getDecorationColor(SsoAuditionOptionVo params){
		List<SsoAuditionOptionVo> decorationColor = sqlSession.selectList("SsoAudition.decorationColor",params);
		return decorationColor;
	}
	
	//오디션 신청하기
	public int insertAudition(SauditionInsertVo insertDataVo) {
		int count = sqlSession.insert("SsoAudition.insertAudition",insertDataVo);
		System.out.println("오디션 등록 성공?"+count);
		return count;
	}
	
	//참여중인 오디션 리스트
	public List<SauditionStatusVo> getIngAuditionList(int venderId) {
		List<SauditionStatusVo> ingAuditionList = sqlSession.selectList("SsoAudition.getIngAuditionList",venderId);
		//System.out.println("참여중인 오디션리스트:"+ingAuditionList);
		return ingAuditionList;
	}
	
	//참여중인 오디션 취소하기
	public int deleteAudition(int auditionCartId) {
		int count = sqlSession.delete("SsoAudition.deleteAudition",auditionCartId);
		return count;
	}
	
	
	
}

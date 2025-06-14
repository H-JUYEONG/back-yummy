package com.javaex.cakedesign.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.cakedesign.model.VendorCakeDesignVo;

@Repository
public class VendorCakeDesignDao {

	@Autowired
	private SqlSession sqlSession;

	/* 도안 데이터 삽입 */
	public int insertVenderCakeDesign(VendorCakeDesignVo juVenderCakeDesignVo) {
		return sqlSession.insert("venderCakeDesign.insertVenderCakeDesign", juVenderCakeDesignVo);
	}

	/* 이미지 데이터 삽입 */
	public int insertVenderCakeDesignImg(VendorCakeDesignVo imageVo) {
		return sqlSession.insert("venderCakeDesign.insertVenderCakeDesignImg", imageVo);
	}

	/* 업체 도안 리스트 */
	public List<VendorCakeDesignVo> selectVenderCakeDesignList(int memberId) {
		return sqlSession.selectList("venderCakeDesign.selectVenderCakeDesignList", memberId);
	}

	/* 업체 도안 찜 리스트 */
	public List<VendorCakeDesignVo> selectVenderCakeDesignLikeList(int memberId) {
		return sqlSession.selectList("venderCakeDesign.selectVenderCakeDesignLikeList", memberId);
	}

	/* 도안 상세정보 */
	public List<VendorCakeDesignVo> getCakeDesignDetailWithImages(int cakeDesignId) {
		return sqlSession.selectList("venderCakeDesign.getCakeDesignDetailWithImages", cakeDesignId);
	}

	// 도안 정보 업데이트
	public void updateCakeDesignInfo(VendorCakeDesignVo juVenderCakeDesignVo) {
		sqlSession.update("venderCakeDesign.updateCakeDesignInfo", juVenderCakeDesignVo);
	}

	// 이미지 삭제
	public void deleteCakeDesignImage(String imageUrl) {
		sqlSession.delete("venderCakeDesign.deleteCakeDesignImage", imageUrl);
	}

	// 새로운 이미지 저장
	public void insertCakeDesignImage(VendorCakeDesignVo imageVo) {
		sqlSession.insert("venderCakeDesign.insertCakeDesignImage", imageVo);
	}

	// 도안 소유 여부 확인
	public int isOwnerOfDesign(int cakeDesignId, int memberId) {
		Map<String, Object> params = new HashMap<>();
		params.put("cakeDesignId", cakeDesignId);
		params.put("memberId", memberId);
		return sqlSession.selectOne("venderCakeDesign.isOwnerOfDesign", params);
	}

	// Product 테이블 참조 개수
	public int countProductByDesignId(int cakeDesignId) {
		return sqlSession.selectOne("venderCakeDesign.countProductByDesignId", cakeDesignId);
	}

	// Review 테이블 참조 개수
	public int countReviewByDesignId(int cakeDesignId) {
		return sqlSession.selectOne("venderCakeDesign.countReviewByDesignId", cakeDesignId);
	}

	// Order 테이블 참조 개수
	public int countOrderByDesignId(int cakeDesignId) {
		return sqlSession.selectOne("venderCakeDesign.countOrderByDesignId", cakeDesignId);
	}

	// AuditionApplication 테이블 참조 개수
	public int countAuditionApplicationByDesignId(int cakeDesignId) {
		return sqlSession.selectOne("venderCakeDesign.countAuditionApplicationByDesignId", cakeDesignId);
	}

	// AuditionVendorCart 테이블 참조 개수
	public int countAuditionVendorCartByDesignId(int cakeDesignId) {
		return sqlSession.selectOne("venderCakeDesign.countAuditionVendorCartByDesignId", cakeDesignId);
	}

	// CakeDesignWishlist 테이블 참조 개수
	public int countCakeDesignWishlistByDesignId(int cakeDesignId) {
		return sqlSession.selectOne("venderCakeDesign.countCakeDesignWishlistByDesignId", cakeDesignId);
	}

	// ProductWishlist 테이블 참조 개수
	public int countProductWishlistByDesignId(int cakeDesignId) {
		return sqlSession.selectOne("venderCakeDesign.countProductWishlistByDesignId", cakeDesignId);
	}

	// 도안 이미지 삭제
	public int deleteCakeDesignImages(int cakeDesignId) {
		return sqlSession.delete("venderCakeDesign.deleteCakeDesignImages", cakeDesignId);
	}

	// 도안 삭제
	public int deleteCakeDesign(int cakeDesignId) {
		return sqlSession.delete("venderCakeDesign.deleteCakeDesign", cakeDesignId);
	}
}

package com.javaex.cakedesign.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.cakedesign.model.VendorCakeDesignVo;
import com.javaex.cakedesign.repository.VendorCakeDesignDao;

@Service
public class JuVenderCakeDesignService {

	@Autowired
	private VendorCakeDesignDao juVenderCakeDesignDao;

	/* 도안 데이터 저장 */
	public int exeAddVenderCakeDesign(VendorCakeDesignVo juVenderCakeDesignVo) {
		// Step 1: CakeDesign 테이블에 삽입
		int cakeDesignInsertCount = juVenderCakeDesignDao.insertVenderCakeDesign(juVenderCakeDesignVo);

		if (cakeDesignInsertCount != 1) {
			throw new RuntimeException("도안 데이터 삽입 실패");
		}

		// Step 2: 생성된 cake_design_id 반환 (MyBatis의 useGeneratedKeys로 자동 설정)
		return juVenderCakeDesignVo.getCakeDesignId();
	}

	/* 이미지 데이터 저장 */
	public void saveVenderCakeDesignImage(VendorCakeDesignVo imageVo) {
		int imageInsertCount = juVenderCakeDesignDao.insertVenderCakeDesignImg(imageVo);

		if (imageInsertCount != 1) {
			throw new RuntimeException("이미지 데이터 삽입 실패");
		}
	}

	/* 업체 도안 리스트 */
	public List<VendorCakeDesignVo> getVenderCakeDesignList(int memberId) {
		// DAO 호출하여 도안 리스트 가져오기
		return juVenderCakeDesignDao.selectVenderCakeDesignList(memberId);
	}

	/* 업체 도안 찜 리스트 */
	public List<VendorCakeDesignVo> getVenderCakeDesignLikeList(int memberId) {
		// DAO 호출하여 도안 리스트 가져오기
		return juVenderCakeDesignDao.selectVenderCakeDesignLikeList(memberId);
	}

	/* 도안 상세정보 */
	public VendorCakeDesignVo getCakeDesignDetail(int cakeDesignId) {
		// DAO에서 데이터 가져오기
		List<VendorCakeDesignVo> results = juVenderCakeDesignDao.getCakeDesignDetailWithImages(cakeDesignId);

		if (results == null || results.isEmpty()) {
			return null; // 데이터가 없을 경우
		}

		// 첫 번째 행을 기준으로 CakeDesign의 기본 정보 설정
		VendorCakeDesignVo detail = results.get(0);
		List<String> subImages = new ArrayList<>();

		for (VendorCakeDesignVo row : results) {
			if (row.getCakeDesignImageUrl() != null) {
				subImages.add(row.getCakeDesignImageUrl());
			}
		}

		detail.setMainImageUrl(subImages.isEmpty() ? null : subImages.get(0)); // 첫 번째 이미지를 메인 이미지로 설정
		detail.setSubImages(subImages); // 서브 이미지 리스트 설정

		return detail; // React로 보낼 데이터 반환
	}

	// 도안 정보 업데이트
	public void updateVenderCakeDesign(VendorCakeDesignVo juVenderCakeDesignVo) {
		juVenderCakeDesignDao.updateCakeDesignInfo(juVenderCakeDesignVo);
	}

	// 이미지 삭제
	public void deleteVenderCakeDesignImage(String imageUrl) {
		juVenderCakeDesignDao.deleteCakeDesignImage(imageUrl);
	}

	// 새로운 이미지 저장
	public void saveNewVenderCakeDesignImage(VendorCakeDesignVo imageVo) {
		juVenderCakeDesignDao.insertCakeDesignImage(imageVo);
	}

	/* 도안 삭제 가능 여부 확인 */
	public boolean isDeletable(int cakeDesignId) {
		// 다른 테이블에서 참조 여부 확인
		// 상품
		int productCount = juVenderCakeDesignDao.countProductByDesignId(cakeDesignId);

		// 리뷰
		int reviewCount = juVenderCakeDesignDao.countReviewByDesignId(cakeDesignId);

		// 주문
		int orderCount = juVenderCakeDesignDao.countOrderByDesignId(cakeDesignId);

		// 오디션글
		int auditionApplicationCount = juVenderCakeDesignDao.countAuditionApplicationByDesignId(cakeDesignId);

		// 오디션 장바구니
		int auditionVendorCartCount = juVenderCakeDesignDao.countAuditionVendorCartByDesignId(cakeDesignId);

		// 6. 찜(CakeDesignWishlist)에서 참조 중인지 확인
		int cakeDesignWishlistCount = juVenderCakeDesignDao.countCakeDesignWishlistByDesignId(cakeDesignId);

		// 7. 상품 찜(ProductWishlist)에서 참조 중인지 확인
		int productWishlistCount = juVenderCakeDesignDao.countProductWishlistByDesignId(cakeDesignId);

		// 참조가 없을 때만 삭제 가능
		return productCount == 0 && reviewCount == 0 && orderCount == 0 && auditionApplicationCount == 0
				&& auditionVendorCartCount == 0 && cakeDesignWishlistCount == 0 && productWishlistCount == 0;
	}

	/* 도안 삭제 */
	public int deleteCakeDesign(int cakeDesignId, int memberId) {
		// 소유자 확인
		int ownerCheck = juVenderCakeDesignDao.isOwnerOfDesign(cakeDesignId, memberId);
		if (ownerCheck == 0) {
			throw new IllegalArgumentException("해당 도안은 사용자의 소유가 아닙니다.");
		}

		// 3. 연관된 이미지 삭제
		juVenderCakeDesignDao.deleteCakeDesignImages(cakeDesignId);

		// 도안 삭제
		return juVenderCakeDesignDao.deleteCakeDesign(cakeDesignId);
	}
}

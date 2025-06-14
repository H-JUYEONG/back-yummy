package com.javaex.cakedesign.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javaex.cakedesign.model.CakeDesignReviewVo;
import com.javaex.cakedesign.model.VendorCakeDesignVo;
import com.javaex.cakedesign.repository.UserCakeDesignDao;
import com.javaex.option.model.HyunOptionValueVO;
import com.fasterxml.jackson.core.type.TypeReference;

@Service
public class JuUserCakeDesignService {

	@Autowired
	private UserCakeDesignDao juUserCakeDesignDao;

//	/* 도안 데이터 저장 */
//	public int exeAddUserCakeDesign(VendorCakeDesignVo juVenderCakeDesignVo) {
//		// Step 1: CakeDesign 테이블에 삽입
//		int cakeDesignInsertCount = juUserCakeDesignDao.insertUserCakeDesign(juVenderCakeDesignVo);
//
//		if (cakeDesignInsertCount != 1) {
//			throw new RuntimeException("도안 데이터 삽입 실패");
//		}
//
//		// Step 2: 생성된 cake_design_id 반환 (MyBatis의 useGeneratedKeys로 자동 설정)
//		return juVenderCakeDesignVo.getCakeDesignId();
//	}
	
	/* 도안 데이터 저장 (카테고리와 해시태그 처리 포함) */
	public int exeAddUserCakeDesign(VendorCakeDesignVo juVenderCakeDesignVo, String hashtagsJson) {
	    // Step 1: CakeDesign 테이블에 삽입
	    int cakeDesignInsertCount = juUserCakeDesignDao.insertUserCakeDesign(juVenderCakeDesignVo);

	    if (cakeDesignInsertCount != 1) {
	        throw new RuntimeException("도안 데이터 삽입 실패");
	    }

	    // Step 2: 해시태그 처리
	    int cakeDesignId = juVenderCakeDesignVo.getCakeDesignId();

	    if (hashtagsJson != null && !hashtagsJson.isEmpty()) {
	        try {
	            // JSON 문자열을 자바 배열로 변환
	            ObjectMapper objectMapper = new ObjectMapper();
	            List<String> hashtags = objectMapper.readValue(hashtagsJson, new TypeReference<List<String>>() {});

	            // 각 해시태그 처리
	            for (String tag : hashtags) {
	                // a. 해당 해시태그가 존재하는지 확인
	                Integer hashtagId = juUserCakeDesignDao.findHashtagByName(tag);

	                // b. 존재하지 않으면 새로 생성
	                if (hashtagId == null) {
	                    // 해시태그 VO 생성 및 설정
	                    VendorCakeDesignVo hashtagVo = new VendorCakeDesignVo();
	                    // 문자열 그대로 설정 (Integer.parseInt 제거)
	                    hashtagVo.setCakeDesignHashtagName(tag);

	                    // 해시태그 저장
	                    hashtagId = juUserCakeDesignDao.insertHashtag(hashtagVo);
	                }

	                // c. 해시태그와 케이크 디자인 연결
	                juUserCakeDesignDao.connectCakeDesignWithHashtag(cakeDesignId, hashtagId);
	            }
	        } catch (Exception e) {
	            // 로깅 및 예외 처리
	            // 오류 로그 출력
	            e.printStackTrace();
	            // 예외 전파
	            throw new RuntimeException("해시태그 처리 중 오류가 발생했습니다.", e);
	        }
	    }

	    // Step 3: 생성된 cake_design_id 반환 (MyBatis의 useGeneratedKeys로 자동 설정)
	    return cakeDesignId;
	}

	/* 이미지 데이터 저장 */
	public void saveUserCakeDesignImage(VendorCakeDesignVo imageVo) {
		int imageInsertCount = juUserCakeDesignDao.insertUserCakeDesignImg(imageVo);

		if (imageInsertCount != 1) {
			throw new RuntimeException("이미지 데이터 삽입 실패");
		}
	}

	/* 총 데이터 개수 가져오기 */
	public int getUserCakeDesignBoardCount(String searchTerm) {
		// DAO를 통해 총 데이터 개수 가져오기
		return juUserCakeDesignDao.selectUserCakeDesignBoardCount(searchTerm);
	}

	/* 도안 게시판 리스트 최신순 */
	public List<VendorCakeDesignVo> getUserCakeDesignBoardLatest(int offset, int size, String searchTerm) {
		// DAO를 통해 데이터 가져오기
		return juUserCakeDesignDao.selectUserCakeDesignBoardLatest(offset, size, searchTerm);
	}

	/* 도안 게시판 리스트 조회수순 */
	public List<VendorCakeDesignVo> getUserCakeDesignBoardViews(int offset, int size, String searchTerm) {
		// DAO를 통해 데이터 가져오기
		return juUserCakeDesignDao.selectUserCakeDesignBoardViews(offset, size, searchTerm);
	}

	/* 도안 게시판 리스트 찜순 */
	public List<VendorCakeDesignVo> getUserCakeDesignBoardLikes(int offset, int size, String searchTerm) {
		// DAO를 통해 데이터 가져오기
		return juUserCakeDesignDao.selectUserCakeDesignBoardLikes(offset, size, searchTerm);
	}

	/* ALL 전체 갯수 */
	public int getCakeDesignTotalCount() {
		return juUserCakeDesignDao.selectUserCakeDesignTotalCount();
	}

	/* 도안 상세정보 */
	public VendorCakeDesignVo getCakeDesignDetail(int cakeDesignId) {
		// DAO에서 데이터 가져오기
		List<VendorCakeDesignVo> results = juUserCakeDesignDao.getCakeDesignDetailWithImages(cakeDesignId);

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

		// 이미지 설정
		detail.setMainImageUrl(subImages.isEmpty() ? null : subImages.get(0)); // 첫 번째 이미지를 메인 이미지로 설정
		detail.setSubImages(subImages); // 서브 이미지 리스트 설정

		// 찜 개수 가져오기
		int wishlistCount = juUserCakeDesignDao.getWishlistCount(cakeDesignId);
		detail.setCakeDesignWishlistCount(wishlistCount);

		return detail;
	}

	/* 조회수 증가 */
	public int exeUserCakeDesignViews(int cakeDesignId) {
		return juUserCakeDesignDao.updateHit(cakeDesignId);
	}

	/* 찜 상태 확인을 위한 새로운 메소드 */
	public boolean checkFavoriteStatus(int memberId, int cakeDesignId) {
		try {
			return juUserCakeDesignDao.checkFavoriteStatus(memberId, cakeDesignId);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/* 도안 사용 후기 */
	public List<CakeDesignReviewVo> getUserCakeDesignReviews(int cakeDesignId) {
		return juUserCakeDesignDao.selectUserCakeDesignReviews(cakeDesignId);
	}

	/* 도안 상세정보(조회수 증가 포함X -> 수정할때 사용하는 정보) */
	public VendorCakeDesignVo getCakeDesignDetail2(int cakeDesignId) {
		// DAO에서 데이터 가져오기
		List<VendorCakeDesignVo> results = juUserCakeDesignDao.getCakeDesignDetailWithImages2(cakeDesignId);

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

	/* 도안 정보 업데이트 */
	public void updateVenderCakeDesign(VendorCakeDesignVo juVenderCakeDesignVo) {
		juUserCakeDesignDao.updateCakeDesignInfo(juVenderCakeDesignVo);
	}

	/* 이미지 삭제 */
	public void deleteVenderCakeDesignImage(String imageUrl) {
		juUserCakeDesignDao.deleteCakeDesignImage(imageUrl);
	}

	/* 새로운 이미지 저장 */
	public void saveNewVenderCakeDesignImage(VendorCakeDesignVo imageVo) {
		juUserCakeDesignDao.insertCakeDesignImage(imageVo);
	}
	
	/* 해시태그 업데이트 - 새로 추가 */
	public void updateCakeDesignHashtags(int cakeDesignId, String hashtagsJson) {
	    try {
	        // 1. 기존 해시태그 매핑 삭제
	        juUserCakeDesignDao.deleteHashtagMappings(cakeDesignId);
	        
	        // 2. 새 해시태그 처리
	        ObjectMapper objectMapper = new ObjectMapper();
	        List<String> hashtags = objectMapper.readValue(hashtagsJson, new TypeReference<List<String>>() {});
	        
	        for (String tag : hashtags) {
	            // a. 해당 해시태그가 존재하는지 확인
	            Integer hashtagId = juUserCakeDesignDao.findHashtagByName(tag);
	            
	            // b. 존재하지 않으면 새로 생성
	            if (hashtagId == null) {
	                VendorCakeDesignVo hashtagVo = new VendorCakeDesignVo();
	                hashtagVo.setCakeDesignHashtagName(tag);
	                hashtagId = juUserCakeDesignDao.insertHashtag(hashtagVo);
	            }
	            
	            // c. 해시태그와 케이크 디자인 연결
	            juUserCakeDesignDao.connectCakeDesignWithHashtag(cakeDesignId, hashtagId);
	        }
	    } catch (Exception e) {
	        throw new RuntimeException("해시태그 처리 중 오류가 발생했습니다.", e);
	    }
	}

	/* 내가 그린 도안 전체 갯수 */
	public int getTotalCount(int memberId, String keyword) {
		String searchKeyword = (keyword == null) ? "" : keyword.trim();
		return juUserCakeDesignDao.selectTotalCount(memberId, searchKeyword);
	}

	/* 찜한 도안 전체 갯수 */
	public int getTotalCount2(int memberId, String keyword) {
		String searchKeyword = (keyword == null) ? "" : keyword.trim();
		return juUserCakeDesignDao.selectTotalCount2(memberId, searchKeyword);
	}

	/* 유저 마이페이지 내가 그린 도안 전체 */
	public List<VendorCakeDesignVo> getUserCakeDesignList(int memberId, String keyword, int offset, int limit) {
		String searchKeyword = (keyword == null) ? "" : keyword.trim();
		return juUserCakeDesignDao.selectUserCakeDesignList(memberId, searchKeyword, offset, limit);
	}

	/* 유저 마이페이지 내가 그린 도안 최신순 */
	public List<VendorCakeDesignVo> getUserCakeDesignListLatest(int memberId, String keyword, int offset, int limit) {
		String searchKeyword = (keyword == null) ? "" : keyword.trim();
		return juUserCakeDesignDao.getUserCakeDesignListLatest(memberId, searchKeyword, offset, limit);
	}

	/* 유저 마이페이지 내가 그린 도안 조회수순 */
	public List<VendorCakeDesignVo> getUserCakeDesignListViews(int memberId, String keyword, int offset, int limit) {
		String searchKeyword = (keyword == null) ? "" : keyword.trim();
		return juUserCakeDesignDao.getUserCakeDesignListViews(memberId, searchKeyword, offset, limit);
	}

	/* 유저 마이페이지 내가 그린 도안 찜순 */
	public List<VendorCakeDesignVo> getUserCakeDesignListLikes(int memberId, String keyword, int offset, int limit) {
		String searchKeyword = (keyword == null) ? "" : keyword.trim();
		return juUserCakeDesignDao.getUserCakeDesignListLikes(memberId, searchKeyword, offset, limit);
	}

	/* 유저 마이페이지 찜한 도안 전체 */
	public List<VendorCakeDesignVo> getUserCakeDesignMyLikes(int memberId, String keyword, int offset, int limit) {
		String searchKeyword = (keyword == null) ? "" : keyword.trim();
		return juUserCakeDesignDao.getUserCakeDesignMyLikes(memberId, searchKeyword, offset, limit);
	}

	/* 유저 마이페이지 찜한 도안 최신순 */
	public List<VendorCakeDesignVo> getUserCakeDesignMyLikesLatest(int memberId, String keyword, int offset,
			int limit) {
		String searchKeyword = (keyword == null) ? "" : keyword.trim();
		return juUserCakeDesignDao.getUserCakeDesignMyLikesLatest(memberId, searchKeyword, offset, limit);
	}

	/* 유저 마이페이지 찜한 도안 조회수순 */
	public List<VendorCakeDesignVo> getUserCakeDesignMyLikesViews(int memberId, String keyword, int offset,
			int limit) {
		String searchKeyword = (keyword == null) ? "" : keyword.trim();
		return juUserCakeDesignDao.getUserCakeDesignMyLikesViews(memberId, searchKeyword, offset, limit);
	}

	/* 유저 마이페이지 찜한 도안 찜순 */
	public List<VendorCakeDesignVo> getUserCakeDesignMyLikesLikes(int memberId, String keyword, int offset,
			int limit) {
		String searchKeyword = (keyword == null) ? "" : keyword.trim();
		return juUserCakeDesignDao.getUserCakeDesignMyLikesLikes(memberId, searchKeyword, offset, limit);
	}

	// 찜하기/취소하기 토글
	public Map<String, Object> toggleFavorite(int memberId, int cakeDesignId) {
		Map<String, Object> result = new HashMap<>();

		try {
			// 1. 현재 찜 상태 확인
			boolean isCurrentlyFavorited = juUserCakeDesignDao.checkFavoriteStatus(memberId, cakeDesignId);

			// 2. 찜 상태 토글
			if (isCurrentlyFavorited) {
				// 찜 제거
				juUserCakeDesignDao.deleteFavorite(memberId, cakeDesignId);
				result.put("isFavorited", false);
			} else {
				// 찜 추가
				juUserCakeDesignDao.insertFavorite(memberId, cakeDesignId);
				result.put("isFavorited", true);
			}

			// 3. 업데이트된 찜 개수 조회
			int updatedCount = juUserCakeDesignDao.getWishlistCount(cakeDesignId);
			result.put("wishlistCount", updatedCount);

			return result;

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("찜하기 처리 중 오류가 발생했습니다.");
		}
	}
	
	/* 카테고리 가져오기 */
	public List<HyunOptionValueVO> getCategory() {
		return juUserCakeDesignDao.selectCategory();
	}
}

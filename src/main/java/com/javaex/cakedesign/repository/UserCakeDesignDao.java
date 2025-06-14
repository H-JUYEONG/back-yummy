package com.javaex.cakedesign.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.cakedesign.model.CakeDesignReviewVo;
import com.javaex.cakedesign.model.VendorCakeDesignVo;
import com.javaex.option.model.HyunOptionValueVO;

@Repository
public class UserCakeDesignDao {

	@Autowired
	private SqlSession sqlSession;

	/* 도안 데이터 삽입 */
	public int insertUserCakeDesign(VendorCakeDesignVo juVenderCakeDesignVo) {
		return sqlSession.insert("userCakeDesign.insertUserCakeDesign", juVenderCakeDesignVo);
	}

	/* 이미지 데이터 삽입 */
	public int insertUserCakeDesignImg(VendorCakeDesignVo imageVo) {
		return sqlSession.insert("userCakeDesign.insertUserCakeDesignImg", imageVo);
	}
	
	/* 해시태그 이름으로 ID 찾기 */
	public Integer findHashtagByName(String tagName) {
	    return sqlSession.selectOne("userCakeDesign.findHashtagByName", tagName);
	}

	/* 해시태그 추가 */
	public Integer insertHashtag(VendorCakeDesignVo hashtagVo) {
	    sqlSession.insert("userCakeDesign.insertHashtag", hashtagVo);
	    return hashtagVo.getCakeDesignHashtagId(); // MyBatis에서 생성된 키를 돌려받기 위해
	}
	
	/* 해시태그 매핑 삭제 - 새로 추가 */
	public void deleteHashtagMappings(int cakeDesignId) {
	    sqlSession.delete("userCakeDesign.deleteHashtagMappings", cakeDesignId);
	}

	/* 케이크 디자인과 해시태그 연결 */
	public void connectCakeDesignWithHashtag(int cakeDesignId, int hashtagId) {
	    Map<String, Object> map = new HashMap<>();
	    map.put("cakeDesignId", cakeDesignId);
	    map.put("hashtagId", hashtagId);
	    sqlSession.insert("userCakeDesign.connectCakeDesignWithHashtag", map);
	}

	/* 총 데이터 개수 가져오기 */
	public int selectUserCakeDesignBoardCount(String searchTerm) {
		Map<String, Object> params = new HashMap<>();
		params.put("searchTerm", searchTerm);

		return sqlSession.selectOne("userCakeDesign.selectUserCakeDesignBoardCount", params);
	}

	/* 도안 게시판 리스트 최신순 */
	public List<VendorCakeDesignVo> selectUserCakeDesignBoardLatest(int offset, int size, String searchTerm) {
		Map<String, Object> params = new HashMap<>();
		params.put("offset", offset);
		params.put("size", size);
		params.put("searchTerm", searchTerm);

		return sqlSession.selectList("userCakeDesign.selectUserCakeDesignBoardLatest", params);
	}

	/* 도안 게시판 리스트 조회수순 */
	public List<VendorCakeDesignVo> selectUserCakeDesignBoardViews(int offset, int size, String searchTerm) {
		Map<String, Object> params = new HashMap<>();
		params.put("offset", offset);
		params.put("size", size);
		params.put("searchTerm", searchTerm);

		return sqlSession.selectList("userCakeDesign.selectUserCakeDesignBoardViews", params);
	}

	/* 도안 게시판 리스트 찜순 */
	public List<VendorCakeDesignVo> selectUserCakeDesignBoardLikes(int offset, int size, String searchTerm) {
		Map<String, Object> params = new HashMap<>();
		params.put("offset", offset);
		params.put("size", size);
		params.put("searchTerm", searchTerm);

		return sqlSession.selectList("userCakeDesign.selectUserCakeDesignBoardLikes", params);
	}

	/* ALL 전체 갯수 */
	public int selectUserCakeDesignTotalCount() {
		return sqlSession.selectOne("userCakeDesign.selectUserCakeDesignTotalCount");
	}

	/* 도안 상세정보 */
	public List<VendorCakeDesignVo> getCakeDesignDetailWithImages(int cakeDesignId) {
		return sqlSession.selectList("userCakeDesign.getCakeDesignDetailWithImages", cakeDesignId);
	}

	/* 조회수 증가 */
	public int updateHit(int cakeDesignId) {
		return sqlSession.update("userCakeDesign.updateHit", cakeDesignId);
	}
	
	/* 도안 사용 후기 */
	public List<CakeDesignReviewVo> selectUserCakeDesignReviews(int cakeDesignId) {
		return sqlSession.selectList("userCakeDesign.selectUserCakeDesignReviews", cakeDesignId);
	}

	/* 도안 상세정보(조회수 증가 포함X -> 수정할때 사용하는 정보) */
	public List<VendorCakeDesignVo> getCakeDesignDetailWithImages2(int cakeDesignId) {
		return sqlSession.selectList("userCakeDesign.getCakeDesignDetailWithImages2", cakeDesignId);
	}

	/* 도안 정보 업데이트 */
	public void updateCakeDesignInfo(VendorCakeDesignVo juVenderCakeDesignVo) {
		sqlSession.update("userCakeDesign.updateCakeDesignInfo2", juVenderCakeDesignVo);
	}

	/* 이미지 삭제 */
	public void deleteCakeDesignImage(String imageUrl) {
		sqlSession.delete("userCakeDesign.deleteCakeDesignImage2", imageUrl);
	}

	/* 새로운 이미지 저장 */
	public void insertCakeDesignImage(VendorCakeDesignVo imageVo) {
		sqlSession.insert("userCakeDesign.insertCakeDesignImage2", imageVo);
	}

	/* 내가 그린 도안 개수 가져오기 */
	public int selectTotalCount(int memberId, String keyword) {
		Map<String, Object> params = new HashMap<>();
		params.put("memberId", memberId);
		params.put("keyword", keyword);

		return sqlSession.selectOne("userCakeDesign.selectTotalCount", params);
	}
	
	/* 찜한 도안 개수 가져오기 */
	public int selectTotalCount2(int memberId, String keyword) {
		Map<String, Object> params = new HashMap<>();
		params.put("memberId", memberId);
		params.put("keyword", keyword);

		return sqlSession.selectOne("userCakeDesign.selectTotalCount2", params);
	}

	/* 유저 마이페이지 내가 그린 도안 전체 */
	public List<VendorCakeDesignVo> selectUserCakeDesignList(int memberId, String keyword, int offset, int limit) {
		Map<String, Object> params = new HashMap<>();
		params.put("memberId", memberId);
		params.put("keyword", keyword);
		params.put("offset", offset);
		params.put("limit", limit);

		return sqlSession.selectList("userCakeDesign.selectUserCakeDesignList", params);
	}

	/* 유저 마이페이지 내가 그린 도안 최신순 */
	public List<VendorCakeDesignVo> getUserCakeDesignListLatest(int memberId, String keyword, int offset, int limit) {
		Map<String, Object> params = new HashMap<>();
		params.put("memberId", memberId);
		params.put("keyword", keyword);
		params.put("offset", offset);
		params.put("limit", limit);

		return sqlSession.selectList("userCakeDesign.selectUserCakeDesignListLatest", params);
	}

	/* 유저 마이페이지 내가 그린 도안 조회수순 */
	public List<VendorCakeDesignVo> getUserCakeDesignListViews(int memberId, String keyword, int offset, int limit) {
		Map<String, Object> params = new HashMap<>();
		params.put("memberId", memberId);
		params.put("keyword", keyword);
		params.put("offset", offset);
		params.put("limit", limit);

		return sqlSession.selectList("userCakeDesign.selectUserCakeDesignListViews", params);
	}

	/* 유저 마이페이지 내가 그린 도안 찜순 */
	public List<VendorCakeDesignVo> getUserCakeDesignListLikes(int memberId, String keyword, int offset, int limit) {
		Map<String, Object> params = new HashMap<>();
		params.put("memberId", memberId);
		params.put("keyword", keyword);
		params.put("offset", offset);
		params.put("limit", limit);

		return sqlSession.selectList("userCakeDesign.selectUserCakeDesignListLikes", params);
	}

	/* 유저 마이페이지 찜한 도안 전체 */
	public List<VendorCakeDesignVo> getUserCakeDesignMyLikes(int memberId, String keyword, int offset, int limit) {
		Map<String, Object> params = new HashMap<>();
		params.put("memberId", memberId);
		params.put("keyword", keyword);
		params.put("offset", offset);
		params.put("limit", limit);

		return sqlSession.selectList("userCakeDesign.selectUserCakeDesignMyLikes", params);
	}

	/* 유저 마이페이지 찜한 도안 최신순 */
	public List<VendorCakeDesignVo> getUserCakeDesignMyLikesLatest(int memberId, String keyword, int offset,
			int limit) {
		Map<String, Object> params = new HashMap<>();
		params.put("memberId", memberId);
		params.put("keyword", keyword);
		params.put("offset", offset);
		params.put("limit", limit);

		return sqlSession.selectList("userCakeDesign.selectUserCakeDesignMyLikesLatest", params);
	}

	/* 유저 마이페이지 찜한 도안 조회수순 */
	public List<VendorCakeDesignVo> getUserCakeDesignMyLikesViews(int memberId, String keyword, int offset,
			int limit) {
		Map<String, Object> params = new HashMap<>();
		params.put("memberId", memberId);
		params.put("keyword", keyword);
		params.put("offset", offset);
		params.put("limit", limit);

		return sqlSession.selectList("userCakeDesign.selectUserCakeDesignMyLikesViews", params);
	}

	/* 유저 마이페이지 찜한 도안 찜순 */
	public List<VendorCakeDesignVo> getUserCakeDesignMyLikesLikes(int memberId, String keyword, int offset,
			int limit) {
		Map<String, Object> params = new HashMap<>();
		params.put("memberId", memberId);
		params.put("keyword", keyword);
		params.put("offset", offset);
		params.put("limit", limit);

		return sqlSession.selectList("userCakeDesign.selectUserCakeDesignMyLikesLikes", params);
	}

	// 찜 상태 확인
	public boolean checkFavoriteStatus(int memberId, int cakeDesignId) {
		Map<String, Object> params = new HashMap<>();
		params.put("memberId", memberId);
		params.put("cakeDesignId", cakeDesignId);

		// 찜 정보가 있으면 true, 없으면 false 반환
		Integer result = sqlSession.selectOne("userCakeDesign.checkFavorite", params);
		return result != null && result > 0;
	}

	// 찜 추가
	public void insertFavorite(int memberId, int cakeDesignId) {
		Map<String, Object> params = new HashMap<>();
		params.put("memberId", memberId);
		params.put("cakeDesignId", cakeDesignId);

		sqlSession.insert("userCakeDesign.insertFavorite", params);
	}

	// 찜 삭제
	public void deleteFavorite(int memberId, int cakeDesignId) {
		Map<String, Object> params = new HashMap<>();
		params.put("memberId", memberId);
		params.put("cakeDesignId", cakeDesignId);

		sqlSession.delete("userCakeDesign.deleteFavorite", params);
	}

	// 찜 개수 조회
	public int getWishlistCount(int cakeDesignId) {
		return sqlSession.selectOne("userCakeDesign.getWishlistCount", cakeDesignId);
	}
	
	/* 카테고리 가져오기 */
	public List<HyunOptionValueVO> selectCategory() {
		return sqlSession.selectList("userCakeDesign.getCategory");
	}
}

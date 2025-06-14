package com.javaex.audition.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.audition.model.JuAuditionApplicationVo;
import com.javaex.audition.model.JuAuditionVendorCartVo;
import com.javaex.order.model.JuOrderVo;

@Repository
public class JuAuditionDao {

	@Autowired
	private SqlSession sqlSession;

	// 찜한 도안 - 픽업
	public int insertAuditionWithDesign(JuAuditionApplicationVo juAuditionApplicationVo) {
		return sqlSession.insert("audition.insertAuditionWithDesign", juAuditionApplicationVo);
	}

	// 사진 첨부 - 픽업
	public int insertAuditionWithImage(JuAuditionApplicationVo juAuditionApplicationVo) {
		return sqlSession.insert("audition.insertAuditionWithImage", juAuditionApplicationVo);
	}

	// 사진 없음 - 픽업
	public int insertAuditionWithoutImage(JuAuditionApplicationVo juAuditionApplicationVo) {
		return sqlSession.insert("audition.insertAuditionWithoutImage", juAuditionApplicationVo);
	}

	// 찜한 도안 - 배송
	public int insertAuditionWithDesignDelivery(JuAuditionApplicationVo juAuditionApplicationVo) {
		return sqlSession.insert("audition.insertAuditionWithDesignDelivery", juAuditionApplicationVo);
	}

	// 사진 첨부 - 배송
	public int insertAuditionWithImageDelivery(JuAuditionApplicationVo juAuditionApplicationVo) {
		return sqlSession.insert("audition.insertAuditionWithImageDelivery", juAuditionApplicationVo);
	}

	// 사진 없음 - 배송
	public int insertAuditionWithoutImageDelivery(JuAuditionApplicationVo juAuditionApplicationVo) {
		return sqlSession.insert("audition.insertAuditionWithoutImageDelivery", juAuditionApplicationVo);
	}

	/* member_id로 user_id 가져오기 */
	public JuAuditionApplicationVo selectUserId(int memberId) {
		return sqlSession.selectOne("audition.selectUserId", memberId);
	}

	/* 찜한 도안 리스트 가져오기 */
	public List<JuAuditionApplicationVo> selectUserCakeDesignMyLikes(int memberId) {
		return sqlSession.selectList("audition.selectUserCakeDesignMyLikes", memberId);
	}

	/* 게시판 총 데이터 개수 가져오기 */
	public int selectUserAuditionBoardCount(String searchTerm) {
		Map<String, Object> params = new HashMap<>();
		params.put("searchTerm", searchTerm);

		return sqlSession.selectOne("audition.selectUserAuditionBoardCount", params);
	}

	/* 진행중 총 데이터 개수 가져오기 */
	public int selectUserAuditionOngoingCount(String searchTerm) {
		Map<String, Object> params = new HashMap<>();
		params.put("searchTerm", searchTerm);

		return sqlSession.selectOne("audition.selectUserAuditionOngoingCount", params);
	}

	/* 종료 총 데이터 개수 가져오기 */
	public int selectUserAuditionEndCount(String searchTerm) {
		Map<String, Object> params = new HashMap<>();
		params.put("searchTerm", searchTerm);

		return sqlSession.selectOne("audition.selectUserAuditionEndCount", params);
	}

	/* 오디션 게시판 리스트 전체 */
	public List<JuAuditionApplicationVo> selectUserCakeAuditionBoardList(int offset, int size, String searchTerm) {
		Map<String, Object> params = new HashMap<>();
		params.put("offset", offset);
		params.put("size", size);
		params.put("searchTerm", searchTerm);

		return sqlSession.selectList("audition.selectUserCakeAuditionBoardList", params);
	}

	/* 오디션 게시판 진행중 */
	public List<JuAuditionApplicationVo> selectUserCakeAuditionOngoing(int offset, int size, String searchTerm) {
		Map<String, Object> params = new HashMap<>();
		params.put("offset", offset);
		params.put("size", size);
		params.put("searchTerm", searchTerm);

		return sqlSession.selectList("audition.selectUserCakeAuditionOngoing", params);
	}

	/* 오디션 게시판 종료 */
	public List<JuAuditionApplicationVo> selectUserCakeAuditionEnd(int offset, int size, String searchTerm) {
		Map<String, Object> params = new HashMap<>();
		params.put("offset", offset);
		params.put("size", size);
		params.put("searchTerm", searchTerm);

		return sqlSession.selectList("audition.selectUserCakeAuditionEnd", params);
	}

	/* 오디션 상세정보 */
	public JuAuditionApplicationVo selectUserAuditionDetail(int auditionApplicationId) {
		return sqlSession.selectOne("audition.selectUserAuditionDetail", auditionApplicationId);
	}

	/* 조회수 증가 */
	public int updateHit(int auditionApplicationId) {
		return sqlSession.update("audition.updateAuditionHit", auditionApplicationId);
	}

	/* 참가 업체 정보(진행중) */
	public List<JuAuditionVendorCartVo> selectUserAuditionVenders(int auditionApplicationId) {
		return sqlSession.selectList("audition.selectUserAuditionVenders", auditionApplicationId);
	}

	/* 참가 업체 정보(종료) */
	public JuAuditionVendorCartVo selectUserAuditionVendersEnd(int auditionApplicationId) {
		return sqlSession.selectOne("audition.selectUserAuditionVendersEnd", auditionApplicationId);
	}

	/* 참가 업체 리뷰 */
	public List<JuAuditionVendorCartVo> selectUserAuditionVendersReviews(int auditionApplicationId) {
		return sqlSession.selectList("audition.selectUserAuditionVendersReviews", auditionApplicationId);
	}

	/* 오디션 신청 */
	// 선택 여부 변경
	public int updateUserAuditionSelect(int auditionApplicationId, int auditionCartId) {
		Map<String, Object> params = new HashMap<>();
		params.put("auditionApplicationId", auditionApplicationId);
		params.put("auditionCartId", auditionCartId);
		return sqlSession.update("audition.updateUserAuditionSelect", params);
	}

	// 게시글 상태 진행중에서 종료로 변경
	public int updateUserAuditionChange(int auditionApplicationId) {
		return sqlSession.update("audition.updateUserAuditionChange", auditionApplicationId);
	}

	// Order 주문 완료(픽업)
	public int insertOrderPickup(JuAuditionVendorCartVo juAuditionVendorCartVo) {
		return sqlSession.insert("audition.insertOrderPickup", juAuditionVendorCartVo);
	}

	// Order 주문 완료(배송)
	public int insertOrderDelivery(JuAuditionVendorCartVo juAuditionVendorCartVo) {
		return sqlSession.insert("audition.insertOrderDelivery", juAuditionVendorCartVo);
	}

	/* 오디션 신청글 내용 가져오기 */
	public JuAuditionApplicationVo selectUserAuditionContent(int auditionApplicationId) {
		return sqlSession.selectOne("audition.selectUserAuditionContent", auditionApplicationId);
	}

	/* 오디션 수정 */
	// 오디션 신청글 ID 존재 여부 확인
	public boolean existsById(int auditionApplicationId) {
		Integer count = sqlSession.selectOne("audition.existsById", auditionApplicationId);
		return count != null && count > 0;
	}

	// 찜한 도안이 선택된 경우
	public int updateAuditionWithDesign(JuAuditionApplicationVo vo) {
		return sqlSession.update("audition.updateAuditionWithDesign", vo);
	}

	// 사진 첨부로 업데이트
	public int updateAuditionWithImage(JuAuditionApplicationVo vo) {
		return sqlSession.update("audition.updateAuditionWithImage", vo);
	}

	// 사진 없음으로 업데이트
	public int updateAuditionWithoutImage(JuAuditionApplicationVo vo) {
		return sqlSession.update("audition.updateAuditionWithoutImage", vo);
	}

	/* 글 삭제 가능 여부 확인 */
	// Order 테이블 참조 개수
	public int checkAuditionApplicationId(int auditionApplicationId) {
		return sqlSession.selectOne("audition.checkAuditionApplicationId", auditionApplicationId);
	}

	/* AuditionVendorCart에서 삭제하기 */
	public int deleteAuditionVendorCart(int auditionApplicationId) {
		return sqlSession.delete("audition.deleteAuditionVendorCart", auditionApplicationId);
	}

	/* 글 삭제 */
	public int deleteAudition(int auditionApplicationId) {
		return sqlSession.delete("audition.deleteAudition", auditionApplicationId);
	}

	/* 결제 정보 가져오기 */
	public JuOrderVo selectPaymentCompleted(int auditionApplicationId) {
		return sqlSession.selectOne("audition.selectPaymentCompleted", auditionApplicationId);
	}

	/* 마이페이지 오디션 관리 - 진행중 */
	public List<JuAuditionApplicationVo> selectUserAuditionOngoing(int userId) {
		return sqlSession.selectList("audition.selectUserAuditionOngoing", userId);
	}

	/* 마이페이지 오디션 관리 - 종료 */
	public List<JuAuditionApplicationVo> selectUserAuditionEnd(int userId) {
		return sqlSession.selectList("audition.selectUserAuditionEnd", userId);
	}

}

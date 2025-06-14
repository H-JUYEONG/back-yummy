package com.javaex.audition.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.audition.model.JuAuditionApplicationVo;
import com.javaex.audition.model.JuAuditionVendorCartVo;
import com.javaex.audition.repository.JuAuditionDao;
import com.javaex.order.model.JuOrderVo;

@Service
public class JuAuditionService {

	@Autowired
	private JuAuditionDao juAuditionApplicationDao;

	/* 오디션 신청글 등록 - 픽업 */
	public int addAuditionPickup(JuAuditionApplicationVo juAuditionApplicationVo) {
		// 선택한 탭에 따른 분기 처리
		if (juAuditionApplicationVo.getDesignId() != 0) {
			// My 도안
			return juAuditionApplicationDao.insertAuditionWithDesign(juAuditionApplicationVo);
		} else if (juAuditionApplicationVo.getDesignId() == 0 && juAuditionApplicationVo.getImageUrl() != null) {
			// 사진 첨부
			return juAuditionApplicationDao.insertAuditionWithImage(juAuditionApplicationVo);
		} else {
			// 사진 없음
			return juAuditionApplicationDao.insertAuditionWithoutImage(juAuditionApplicationVo);
		}

	}

	/* 오디션 신청글 등록 - 배송 */
	public int addAuditionDelivery(JuAuditionApplicationVo juAuditionApplicationVo) {
		// 선택한 탭에 따른 분기 처리
		if (juAuditionApplicationVo.getDesignId() != 0) {
			// My 도안
			return juAuditionApplicationDao.insertAuditionWithDesignDelivery(juAuditionApplicationVo);
		} else if (juAuditionApplicationVo.getDesignId() == 0 && juAuditionApplicationVo.getImageUrl() != null) {
			// 사진 첨부
			return juAuditionApplicationDao.insertAuditionWithImageDelivery(juAuditionApplicationVo);
		} else {
			// 사진 없음
			return juAuditionApplicationDao.insertAuditionWithoutImageDelivery(juAuditionApplicationVo);
		}
	}

	/* member_id로 user_id 가져오기 */
	public JuAuditionApplicationVo getUserId(int memberId) {
		return juAuditionApplicationDao.selectUserId(memberId);
	}

	/* My 도안 리스트 가져오기 */
	public List<JuAuditionApplicationVo> getUserCakeDesignMyLikes(int memberId) {
		return juAuditionApplicationDao.selectUserCakeDesignMyLikes(memberId);
	}

	/* 게시판 총 데이터 개수 가져오기 */
	public int getUserAuditionBoardCount(String searchTerm) {
		return juAuditionApplicationDao.selectUserAuditionBoardCount(searchTerm);
	}

	/* 진행중 총 데이터 개수 가져오기 */
	public int getUserAuditionOngoingCount(String searchTerm) {
		return juAuditionApplicationDao.selectUserAuditionOngoingCount(searchTerm);
	}

	/* 종료 총 데이터 개수 가져오기 */
	public int getUserAuditionEndCount(String searchTerm) {
		return juAuditionApplicationDao.selectUserAuditionEndCount(searchTerm);
	}

	/* 오디션 게시판 리스트 전체 */
	public List<JuAuditionApplicationVo> getUserCakeAuditionBoardList(int offset, int size, String searchTerm) {
		return juAuditionApplicationDao.selectUserCakeAuditionBoardList(offset, size, searchTerm);
	}

	/* 오디션 게시판 리스트 진행중 */
	public List<JuAuditionApplicationVo> getUserCakeAuditionOngoing(int offset, int size, String searchTerm) {
		return juAuditionApplicationDao.selectUserCakeAuditionOngoing(offset, size, searchTerm);
	}

	/* 오디션 게시판 리스트 종료 */
	public List<JuAuditionApplicationVo> getUserCakeAuditionEnd(int offset, int size, String searchTerm) {
		return juAuditionApplicationDao.selectUserCakeAuditionEnd(offset, size, searchTerm);
	}

	/* 오디션 상세정보 */
	public Map<String, Object> getUserAuditionDetail(int auditionApplicationId) {
		// 오디션 상세정보 가져오기
		JuAuditionApplicationVo auditionApplicationDetail = juAuditionApplicationDao
				.selectUserAuditionDetail(auditionApplicationId);

		// 반환할 데이터 구성
		Map<String, Object> result = new HashMap<>();
		result.put("auditionDetail", auditionApplicationDetail); // 공통: 오디션 상세정보

		// 상태에 따라 분기 처리
		if ("진행중".equals(auditionApplicationDetail.getStatus())) {
			// 참가 업체 정보(진행중)
			List<JuAuditionVendorCartVo> auditionVenders = juAuditionApplicationDao
					.selectUserAuditionVenders(auditionApplicationId);
			result.put("auditionVenders", auditionVenders); // 진행중 업체 리스트 추가

		} else if ("종료".equals(auditionApplicationDetail.getStatus())) {
			// 참가 업체 정보(종료)
			JuAuditionVendorCartVo auditionVendersEnd = juAuditionApplicationDao
					.selectUserAuditionVendersEnd(auditionApplicationId);
			// 참가 업체 리뷰
			List<JuAuditionVendorCartVo> auditionVendersReviews = juAuditionApplicationDao
					.selectUserAuditionVendersReviews(auditionApplicationId);

			result.put("auditionVendersEnd", auditionVendersEnd); // 종료된 업체 추가
			result.put("auditionVendersReviews", auditionVendersReviews); // 리뷰 리스트 추가
		}

		return result; // 상태에 따라 구성된 데이터를 반환
	}

	/* 조회수 증가 */
	public int exeUserAuditionViews(int auditionApplicationId) {
		return juAuditionApplicationDao.updateHit(auditionApplicationId);
	}

	/* 오디션 신청 */
	public int exeUserAuditionSelect(JuAuditionVendorCartVo juAuditionVendorCart) {
		// 선택 여부 변경
		juAuditionApplicationDao.updateUserAuditionSelect(juAuditionVendorCart.getAuditionApplicationId(),
				juAuditionVendorCart.getAuditionCartId());

		// 게시글 상태 진행중에서 종료로 변경
		juAuditionApplicationDao.updateUserAuditionChange(juAuditionVendorCart.getAuditionApplicationId());

		if ("픽업".equals(juAuditionVendorCart.getDeliveryMethod())) { // 픽업일 경우
			return juAuditionApplicationDao.insertOrderPickup(juAuditionVendorCart);
		} else { // 배송일 경우
			return juAuditionApplicationDao.insertOrderDelivery(juAuditionVendorCart);
		}

	}

	/* 오디션 신청글 내용 가져오기 */
	public JuAuditionApplicationVo exeGetUserAuditionContent(int auditionApplicationId) {
		return juAuditionApplicationDao.selectUserAuditionContent(auditionApplicationId);
	}

	/* 오디션 신청글 수정 */
	public int exeEditAudition(JuAuditionApplicationVo juAuditionApplicationVo) {

		if (!juAuditionApplicationDao.existsById(juAuditionApplicationVo.getAuditionApplicationId())) {
			throw new IllegalArgumentException("수정하려는 오디션 신청글이 존재하지 않습니다.");
		}

		if ("픽업".equals(juAuditionApplicationVo.getDeliveryMethod())) {
			juAuditionApplicationVo.setDeliveryAddress(null);
		}

		if (juAuditionApplicationVo.getDesignId() > 0) { // My 도안
			return juAuditionApplicationDao.updateAuditionWithDesign(juAuditionApplicationVo);
		} else if (juAuditionApplicationVo.getImageUrl() != null && juAuditionApplicationVo.getDesignId() == 0) { // 사진첨부
			return juAuditionApplicationDao.updateAuditionWithImage(juAuditionApplicationVo);
		} else { // 사진 없음
			return juAuditionApplicationDao.updateAuditionWithoutImage(juAuditionApplicationVo);
		}
	}

	/* 글 삭제 가능 여부 확인 */
	public boolean isDeletable(int auditionApplicationId) {
		// 다른 테이블에서 참조 여부 확인
		int count = juAuditionApplicationDao.checkAuditionApplicationId(auditionApplicationId);
		return count == 0; // 참조가 없으면 true, 있으면 false
	}

	/* AuditionVendorCart에서 삭제하기 */
	public int deleteAuditionVendorCart(int auditionApplicationId) {
		return juAuditionApplicationDao.deleteAuditionVendorCart(auditionApplicationId);
	}

	/* 글 삭제 */
	public int deleteAudition(int auditionApplicationId) {
		return juAuditionApplicationDao.deleteAudition(auditionApplicationId);
	}

	/* 결제 정보 가져오기 */
	public JuOrderVo exeGetpaymentCompleted(int auditionApplicationId) {
		return juAuditionApplicationDao.selectPaymentCompleted(auditionApplicationId);
	}

	/* 마이페이지 오디션 관리 */
	public Map<String, Object> exeGetUserAuditionOngoing(int userId) {
		Map<String, Object> result = new HashMap<>();

		List<JuAuditionApplicationVo> auditionDetailOngoing = juAuditionApplicationDao
				.selectUserAuditionOngoing(userId);
		result.put("auditionDetailOngoing", auditionDetailOngoing);

		List<JuAuditionApplicationVo> auditionDetailEnd = juAuditionApplicationDao.selectUserAuditionEnd(userId);
		result.put("auditionDetailEnd", auditionDetailEnd);

		return result;
	}
}

package com.javaex.audition.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.audition.model.JuAuditionApplicationVo;
import com.javaex.audition.model.JuAuditionVendorCartVo;
import com.javaex.audition.service.JuAuditionService;
import com.javaex.common.storage.S3Service;
import com.javaex.common.util.JsonResult;
import com.javaex.common.util.JwtUtil;
import com.javaex.order.model.JuOrderVo;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class JuAuditionController {

	@Autowired
	private JuAuditionService juAuditionService;

	@Autowired
	private S3Service s3Service;

	/* 오디션 신청글 등록 */
	@PostMapping("/api/user/add/audition")
	public JsonResult addAudition(@RequestParam(value = "uploadedImage", required = false) MultipartFile file,
			@RequestParam("title") String auditionApplicationTitle, @RequestParam("price") int expectedPrice,
			@RequestParam("size") String auditionApplicationSize, @RequestParam("cakeLettering") String cakeLettering,
			@RequestParam("plateLettering") String plateLettering,
			@RequestParam("deliveryMethod") String deliveryMethod, @RequestParam("desiredDate") String desiredDate,
			@RequestParam("desiredTime") String desiredTime, @RequestParam("recipient") String recipientName,
			@RequestParam("recipientPhone") String recipientPhone, @RequestParam("region") String region,
			@RequestParam("requests") String additionalRequests,
			@RequestParam("deliveryAddress") String deliveryAddress,
			@RequestParam(value = "designId", required = false, defaultValue = "0") int designId,
			@RequestParam(value = "cakeDesignImageUrl", required = false) String cakeDesignImageUrl,
			@RequestParam("selectedTab") String selectedTab, HttpServletRequest request) {

		try {
			// 1. JWT에서 member_id 추출
			int memberId = JwtUtil.getNoFromHeader(request);
			System.out.println("추출된 memberId: " + memberId);

			// 2. member_id로 user_id 추출
			JuAuditionApplicationVo userVo = juAuditionService.getUserId(memberId);

			int userId = userVo.getUserId();
			System.out.println("조회된 userId: " + userId);

			// 4. VO 객체 생성 및 값 설정
			JuAuditionApplicationVo juAuditionApplicationVo = new JuAuditionApplicationVo();
			juAuditionApplicationVo.setUserId(userId);
			juAuditionApplicationVo.setAuditionApplicationTitle(auditionApplicationTitle);
			juAuditionApplicationVo.setExpectedPrice(expectedPrice);
			juAuditionApplicationVo.setAuditionApplicationSize(auditionApplicationSize);
			juAuditionApplicationVo.setCakeLettering(cakeLettering);
			juAuditionApplicationVo.setPlateLettering(plateLettering);
			juAuditionApplicationVo.setDeliveryMethod(deliveryMethod);
			juAuditionApplicationVo.setAdditionalRequests(additionalRequests);
			juAuditionApplicationVo.setRecipientName(recipientName);
			juAuditionApplicationVo.setRecipientPhone(recipientPhone);

			// 5. 선택한 탭에 따른 처리
			if ("My 도안".equals(selectedTab)) {
				if (designId <= 0 || cakeDesignImageUrl == null) {
					throw new IllegalArgumentException("My 도안 정보가 올바르지 않습니다.");
				}
				juAuditionApplicationVo.setDesignId(designId); // 도안 번호 설정
				juAuditionApplicationVo.setImageUrl(cakeDesignImageUrl); // 도안 이미지 설정

			} else if ("사진 첨부".equals(selectedTab)) {
				if (file == null || file.isEmpty()) {
					throw new IllegalArgumentException("첨부된 이미지가 없습니다.");
				}
				// 이미지 업로드 처리
				String key = "images/" + UUID.randomUUID() + "_" + file.getOriginalFilename();
				String imageUrl = s3Service.uploadFile(key, file.getInputStream(), file.getSize(),
						file.getContentType());
				juAuditionApplicationVo.setImageUrl(imageUrl); // 업로드된 이미지 URL 설정

			} else if ("사진 없음".equals(selectedTab)) {
				juAuditionApplicationVo.setImageUrl(null); // 이미지 없음 설정

			} else {
				throw new IllegalArgumentException("선택한 탭 정보가 올바르지 않습니다: " + selectedTab);
			}

			int count = 0; // 등록된 행 개수

			// 6. 배송 방법에 따른 처리
			if ("픽업".equals(deliveryMethod)) {
				juAuditionApplicationVo.setDesiredDate(desiredDate); // 픽업 날짜
				juAuditionApplicationVo.setDesiredTime(desiredTime); // 픽업 시간
				juAuditionApplicationVo.setRegion(region); // 지역 구

				// 오디션 신청글 등록 - 픽업
				count = juAuditionService.addAuditionPickup(juAuditionApplicationVo);

			} else if ("배송".equals(deliveryMethod)) {
				juAuditionApplicationVo.setDesiredDate(desiredDate);
				juAuditionApplicationVo.setDesiredTime(desiredTime);
				juAuditionApplicationVo.setRegion(region);
				juAuditionApplicationVo.setDeliveryAddress(deliveryAddress);

				// 오디션 신청글 등록 - 배송
				count = juAuditionService.addAuditionDelivery(juAuditionApplicationVo);

			} else {
				throw new IllegalArgumentException("배송 방법 값이 올바르지 않습니다: " + deliveryMethod);
			}

			return JsonResult.success(count);
		} catch (IOException e) {
			throw new RuntimeException("파일 업로드 중 오류가 발생했습니다.", e);
		} catch (IllegalArgumentException e) {
			return JsonResult.fail(e.getMessage());
		} catch (Exception e) {
			throw new RuntimeException("오디션 등록 중 오류가 발생했습니다: " + e.getMessage(), e);
		}
	}

	/* My 도안 리스트 가져오기(나의 도안 + 찜 포함) */
	@GetMapping("/api/user/cakeDesign/myLikes")
	public JsonResult getUserCakeDesignMyLikes(HttpServletRequest request) {
		// 1. 토큰에서 업체 member_id 추출
		int memberId = JwtUtil.getNoFromHeader(request);

		List<JuAuditionApplicationVo> juAuditionApplicationVo = juAuditionService.getUserCakeDesignMyLikes(memberId);

		if (juAuditionApplicationVo != null) {
			return JsonResult.success(juAuditionApplicationVo);
		} else {
			return JsonResult.fail("조회 실패");
		}
	}

	/* 오디션 신청글 내용 가져오기(수정폼) */
	@GetMapping("/api/users/audition/content/{auditionApplicationId}")
	public JsonResult getUserAuditionContent(@PathVariable("auditionApplicationId") int auditionApplicationId) {
		JuAuditionApplicationVo juAuditionApplicationVo = juAuditionService
				.exeGetUserAuditionContent(auditionApplicationId);

		if (juAuditionApplicationVo != null) {
			return JsonResult.success(juAuditionApplicationVo); // 성공 시 데이터 반환
		} else {
			return JsonResult.fail("조회 실패"); // 실패 시 메시지 반환
		}
	}

	/* 오디션 신청글 수정 */
	@PostMapping("/api/user/edit/audition/{auditionApplicationId}")
	public JsonResult editAudition(@RequestParam(value = "uploadedImage", required = false) MultipartFile file,
			@RequestParam("title") String auditionApplicationTitle, @RequestParam("price") int expectedPrice,
			@RequestParam("size") String auditionApplicationSize, @RequestParam("cakeLettering") String cakeLettering,
			@RequestParam("plateLettering") String plateLettering,
			@RequestParam("deliveryMethod") String deliveryMethod, @RequestParam("desiredDate") String desiredDate,
			@RequestParam("desiredTime") String desiredTime,
			@RequestParam(value = "recipient", required = false) String recipientName,
			@RequestParam("recipientPhone") String recipientPhone, @RequestParam("region") String region,
			@RequestParam("requests") String additionalRequests,
			@RequestParam(value = "deliveryAddress", required = false) String deliveryAddress,
			@RequestParam(value = "designId", required = false, defaultValue = "0") int designId,
			@RequestParam(value = "cakeDesignImageUrl", required = false) String cakeDesignImageUrl,
			@RequestParam("selectedTab") String selectedTab, HttpServletRequest request,
			@PathVariable("auditionApplicationId") int auditionApplicationId) {
		try {
			System.out.println("수정 요청 받음. Audition ID: " + auditionApplicationId);

			int memberId = JwtUtil.getNoFromHeader(request);
			JuAuditionApplicationVo userVo = juAuditionService.getUserId(memberId);

			JuAuditionApplicationVo auditionVo = new JuAuditionApplicationVo();
			auditionVo.setAuditionApplicationId(auditionApplicationId);
			auditionVo.setUserId(userVo.getUserId());
			auditionVo.setAuditionApplicationTitle(auditionApplicationTitle);
			auditionVo.setExpectedPrice(expectedPrice);
			auditionVo.setAuditionApplicationSize(auditionApplicationSize);
			auditionVo.setCakeLettering(cakeLettering);
			auditionVo.setPlateLettering(plateLettering);
			auditionVo.setDeliveryMethod(deliveryMethod);
			auditionVo.setAdditionalRequests(additionalRequests);
			auditionVo.setRecipientName(recipientName);
			auditionVo.setRecipientPhone(recipientPhone);

			// 기존 이미지 URL 가져오기
			JuAuditionApplicationVo existingAudition = juAuditionService
					.exeGetUserAuditionContent(auditionApplicationId);
			String existingImageUrl = existingAudition.getImageUrl();

			// 탭 처리
			switch (selectedTab) {
			case "My 도안":
				auditionVo.setDesignId(designId); // 사용자가 선택한 디자인 ID
				auditionVo.setImageUrl(cakeDesignImageUrl); // 선택된 도안의 이미지 URL
				break;
			case "사진 첨부":
				if (file != null && !file.isEmpty()) {
					// 새 이미지 업로드 처리
					String key = "images/" + UUID.randomUUID() + "_" + file.getOriginalFilename();
					String imageUrl = s3Service.uploadFile(key, file.getInputStream(), file.getSize(),
							file.getContentType());
					auditionVo.setImageUrl(imageUrl);
				} else if (existingImageUrl != null) {
					// 새 파일이 없을 경우 기존 이미지 유지
					auditionVo.setImageUrl(existingImageUrl);
				} else {
					// 새 파일도 없고 기존 이미지도 없을 경우 null 처리
					auditionVo.setImageUrl(null);
				}
				auditionVo.setDesignId(0); // 디자인 ID는 초기화
				break;
			case "사진 없음":
				auditionVo.setDesignId(0); // 디자인 ID 초기화
				auditionVo.setImageUrl(null); // 이미지 URL도 초기화
				break;
			default:
				throw new IllegalArgumentException("선택한 탭 정보가 올바르지 않습니다: " + selectedTab);
			}

			// 배송 방식 처리
			if ("픽업".equals(deliveryMethod)) {
				auditionVo.setDesiredDate(desiredDate); // 픽업 날짜
				auditionVo.setDesiredTime(desiredTime); // 픽업 시간
				auditionVo.setRegion(region); // 지역 구
				auditionVo.setDeliveryAddress(null); // 배송 주소 제거
			} else if ("배송".equals(deliveryMethod)) {
				auditionVo.setDesiredDate(desiredDate);
				auditionVo.setDesiredTime(desiredTime);
				auditionVo.setDeliveryAddress(deliveryAddress); // 배송 주소 설정
				auditionVo.setRegion(region);
			} else {
				throw new IllegalArgumentException("수령 방식 값이 올바르지 않습니다: " + deliveryMethod);
			}

			// 오디션 수정
			int result = juAuditionService.exeEditAudition(auditionVo);
			return JsonResult.success("수정 성공: " + result + "개의 데이터가 업데이트되었습니다.");
		} catch (Exception e) {
			e.printStackTrace();
			return JsonResult.fail("오류 발생: " + e.getMessage());
		}
	}

	/* 글 삭제 */
	@DeleteMapping("/api/user/audition/delete/{auditionApplicationId}")
	public JsonResult delAudition(@PathVariable("auditionApplicationId") int auditionApplicationId) {

		try {
			// 2. 삭제 가능 여부 확인
			boolean isDeletable = juAuditionService.isDeletable(auditionApplicationId);

			if (isDeletable) {
				// 3. 삭제 수행
				// AuditionVendorCart 먼저 삭제하기
				juAuditionService.deleteAuditionVendorCart(auditionApplicationId);

				// AuditionApplication에서 삭제하기
				int count = juAuditionService.deleteAudition(auditionApplicationId);
				return JsonResult.success(count);
			} else {
				return JsonResult.fail("주문 완료된 글은 삭제할 수 없습니다.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return JsonResult.fail("서버 에러로 인해 삭제를 실패했습니다.");
		}
	}

	/* 오디션 게시판 전체 */
	@GetMapping("/api/user/audition/board")
	public JsonResult getUserAuditionBoardList(@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "size", defaultValue = "12") int size,
			@RequestParam(value = "search", required = false) String searchTerm) {
		try {
			// 페이징 처리
			int offset = (page - 1) * size;
			List<JuAuditionApplicationVo> auditionList = juAuditionService.getUserCakeAuditionBoardList(offset, size,
					searchTerm);
			int totalCount = juAuditionService.getUserAuditionBoardCount(searchTerm);
			int totalPages = (int) Math.ceil((double) totalCount / size);

			// 응답 데이터 구성
			Map<String, Object> apiData = new HashMap<>();
			apiData.put("data", auditionList);
			apiData.put("totalCount", totalCount);
			apiData.put("totalPages", totalPages);

			return JsonResult.success(apiData);
		} catch (Exception e) {
			return JsonResult.fail("오디션 리스트를 가져오는 중 오류가 발생했습니다.");
		}
	}

	/* 오디션 게시판 진행중 */
	@GetMapping("/api/user/audition/board/ongoing")
	public JsonResult getUserAuditionOngoing(@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "size", defaultValue = "12") int size,
			@RequestParam(value = "search", required = false) String searchTerm) {
		try {
			// 페이징 처리
			int offset = (page - 1) * size;
			List<JuAuditionApplicationVo> auditionList = juAuditionService.getUserCakeAuditionOngoing(offset, size,
					searchTerm);
			int totalCount = juAuditionService.getUserAuditionOngoingCount(searchTerm);
			int totalPages = (int) Math.ceil((double) totalCount / size);

			// 응답 데이터 구성
			Map<String, Object> apiData = new HashMap<>();
			apiData.put("data", auditionList);
			apiData.put("totalCount", totalCount);
			apiData.put("totalPages", totalPages);

			return JsonResult.success(apiData);
		} catch (Exception e) {
			return JsonResult.fail("오디션 리스트를 가져오는 중 오류가 발생했습니다.");
		}
	}

	/* 오디션 게시판 종료 */
	@GetMapping("/api/user/audition/board/end")
	public JsonResult getUserAuditionEnd(@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "size", defaultValue = "12") int size,
			@RequestParam(value = "search", required = false) String searchTerm) {
		try {
			// 페이징 처리
			int offset = (page - 1) * size;
			List<JuAuditionApplicationVo> auditionList = juAuditionService.getUserCakeAuditionEnd(offset, size,
					searchTerm);
			int totalCount = juAuditionService.getUserAuditionEndCount(searchTerm);
			int totalPages = (int) Math.ceil((double) totalCount / size);

			// 응답 데이터 구성
			Map<String, Object> apiData = new HashMap<>();
			apiData.put("data", auditionList);
			apiData.put("totalCount", totalCount);
			apiData.put("totalPages", totalPages);

			return JsonResult.success(apiData);
		} catch (Exception e) {
			return JsonResult.fail("오디션 리스트를 가져오는 중 오류가 발생했습니다.");
		}
	}

	/* 오디션 상세정보 */
	@GetMapping("/api/users/audition/detail/{auditionApplicationId}")
	public JsonResult getUserAuditionDetail(@PathVariable("auditionApplicationId") int auditionApplicationId) {
		Map<String, Object> apiData = juAuditionService.getUserAuditionDetail(auditionApplicationId);

		if (apiData != null && !apiData.isEmpty()) {
			return JsonResult.success(apiData); // 성공 시 데이터 반환
		} else {
			return JsonResult.fail("조회 실패"); // 실패 시 메시지 반환
		}
	}

	/* 조회수 증가 */
	@PutMapping("/api/audition/views/{auditionApplicationId}")
	public JsonResult getUserAuditionViews(@PathVariable("auditionApplicationId") int auditionApplicationId) {
		int count = juAuditionService.exeUserAuditionViews(auditionApplicationId);

		if (count != 0) {
			return JsonResult.success(count); // 성공 시 데이터 반환
		} else {
			return JsonResult.fail("조회 실패"); // 실패 시 메시지 반환
		}
	}

	/* 오디션 신청(업체 선택 -> 결제하기) */
	@PostMapping("/api/users/audition/select")
	public JsonResult userAuditionSelect(@RequestBody JuAuditionVendorCartVo juAuditionVendorCartVo,
			HttpServletRequest request) {

		// 1. 토큰에서 업체 member_id 추출
		int memberId = JwtUtil.getNoFromHeader(request);
		// member_id로 user_id 추출
		JuAuditionApplicationVo juAuditionApplicationVo = juAuditionService.getUserId(memberId);

		// user_id만 다시 추출
		int userId = juAuditionApplicationVo.getUserId();
		// user_id를 juAuditionApplicationVo 객체에 담기
		juAuditionVendorCartVo.setUserId(userId);

		int count = juAuditionService.exeUserAuditionSelect(juAuditionVendorCartVo);

		if (count != 0) {
			return JsonResult.success(count); // 성공 시 데이터 반환
		} else {
			return JsonResult.fail("신청 실패"); // 실패 시 메시지 반환
		}
	}

	/* 결제 정보 가져오기 */
	@GetMapping("/api/user/payment/completed")
	public JsonResult getCompletedPayment(@RequestParam("auditionApplicationId") int auditionApplicationId) {
		JuOrderVo juOrderVo = juAuditionService.exeGetpaymentCompleted(auditionApplicationId);

		if (juOrderVo != null) {
			return JsonResult.success(juOrderVo); // 성공 시 데이터 반환
		} else {
			return JsonResult.fail("조회 실패"); // 실패 시 메시지 반환
		}
	}

	/* 마이페이지 오디션 관리 */
	@GetMapping("/api/user/mypage/audition")
	public JsonResult getUserAuditionOngoing(HttpServletRequest request) {

		// 1. member_id추출
		int memberId = JwtUtil.getNoFromHeader(request);

		// 2. member_id로 user_id 추출
		JuAuditionApplicationVo userVo = juAuditionService.getUserId(memberId);

		Map<String, Object> apiData = juAuditionService.exeGetUserAuditionOngoing(userVo.getUserId());

		if (apiData != null && !apiData.isEmpty()) {
			return JsonResult.success(apiData); // 성공 시 데이터 반환
		} else {
			return JsonResult.fail("조회 실패"); // 실패 시 메시지 반환
		}
	}
}

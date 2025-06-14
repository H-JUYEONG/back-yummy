package com.javaex.cakedesign.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.cakedesign.model.CakeDesignReviewVo;
import com.javaex.cakedesign.model.VendorCakeDesignVo;
import com.javaex.cakedesign.service.JuUserCakeDesignService;
import com.javaex.common.storage.S3Service;
import com.javaex.common.util.JsonResult;
import com.javaex.common.util.JwtUtil;
import com.javaex.option.model.HyunOptionValueVO;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class UserCakeDesignController {

	@Autowired
	private JuUserCakeDesignService juUserCakeDesignService;

	@Autowired
	private S3Service s3Service;

	/* 유저 도안 등록 */
	@PostMapping("/api/add/user/cakeDesign")
	public JsonResult addVenderCakeDesign(@RequestParam("files") List<MultipartFile> files,
			@RequestParam("cakeDesignTitle") String cakeDesignTitle,
			@RequestParam("cakeDesignDescription") String cakeDesignDescription,
			@RequestParam("cakeDesignPreferredAge") String cakeDesignPreferredAge,
			@RequestParam("cakeDesignRecommendedEvent") String cakeDesignRecommendedEvent,
			@RequestParam("cakeDesignPreferredShape") String cakeDesignPreferredShape,
			@RequestParam("cakeDesignCategory") String cakeDesignCategory,
			@RequestParam(value = "hashtags", required = false) String hashtagsJson, HttpServletRequest request) {

		if (files == null || files.isEmpty()) {
			throw new IllegalArgumentException("파일이 비어있습니다.");
		}

		try {
			// 1. 토큰에서 업체 member_id 추출
			int memberId = JwtUtil.getNoFromHeader(request);

			// 2. 도안 데이터 먼저 저장
			VendorCakeDesignVo juVenderCakeDesignVo = new VendorCakeDesignVo();
			juVenderCakeDesignVo.setMemberId(memberId);
			juVenderCakeDesignVo.setCakeDesignTitle(cakeDesignTitle);
			juVenderCakeDesignVo.setCakeDesignDescription(cakeDesignDescription);
			juVenderCakeDesignVo.setCakeDesignPreferredAge(cakeDesignPreferredAge);
			juVenderCakeDesignVo.setCakeDesignRecommendedEvent(cakeDesignRecommendedEvent);
			juVenderCakeDesignVo.setCakeDesignPreferredShape(cakeDesignPreferredShape);
			juVenderCakeDesignVo.setCakeDesignCategory(cakeDesignCategory);

			// 도안 데이터 저장 및 생성된 cakeDesignId 가져오기
			int cakeDesignId = juUserCakeDesignService.exeAddUserCakeDesign(juVenderCakeDesignVo, hashtagsJson);

			// 3. 각 이미지 파일 처리
			for (MultipartFile file : files) {
				if (!file.isEmpty()) {
					// 이미지 업로드 처리
					String key = "images/" + UUID.randomUUID() + "_" + file.getOriginalFilename();
					String imageUrl = s3Service.uploadFile(key, file.getInputStream(), file.getSize(),
							file.getContentType());

					// 이미지 데이터 저장
					VendorCakeDesignVo imageVo = new VendorCakeDesignVo();
					imageVo.setCakeDesignId(cakeDesignId); // 외래키로 사용될 도안 ID
					imageVo.setCakeDesignImageUrl(imageUrl); // 업로드된 이미지 URL 설정

					// 서비스 호출로 이미지 데이터 저장
					juUserCakeDesignService.saveUserCakeDesignImage(imageVo);
				}
			}

			return JsonResult.success("도안 등록이 완료되었습니다.");
		} catch (IOException e) {
			throw new RuntimeException("파일 업로드 중 오류가 발생했습니다.", e);
		}
	}

	/* 도안 게시판 리스트 최신순 */
	@GetMapping("/api/user/cakeDesign/board/latest")
	public JsonResult getUserCakeDesignBoardListLatest(@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "size", defaultValue = "12") int size,
			@RequestParam(value = "search", required = false) String searchTerm) {

		try {
			// 페이징 처리
			int offset = (page - 1) * size;
			List<VendorCakeDesignVo> cakeDesignList = juUserCakeDesignService.getUserCakeDesignBoardLatest(offset, size,
					searchTerm);
			int totalCount = juUserCakeDesignService.getUserCakeDesignBoardCount(searchTerm);
			int totalPages = (int) Math.ceil((double) totalCount / size);

			// 응답 데이터 구성
			Map<String, Object> apiData = new HashMap<>();
			apiData.put("data", cakeDesignList);
			apiData.put("totalCount", totalCount);
			apiData.put("totalPages", totalPages);

			return JsonResult.success(apiData);
		} catch (Exception e) {
			return JsonResult.fail("도안 리스트를 가져오는 중 오류가 발생했습니다.");
		}
	}

	/* 도안 게시판 리스트 조회수순 */
	@GetMapping("/api/user/cakeDesign/board/views")
	public JsonResult getUserCakeDesignBoardListViews(@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "size", defaultValue = "12") int size,
			@RequestParam(value = "search", required = false) String searchTerm) {

		try {
			// 페이징 처리
			int offset = (page - 1) * size;
			List<VendorCakeDesignVo> cakeDesignList = juUserCakeDesignService.getUserCakeDesignBoardViews(offset, size,
					searchTerm);
			int totalCount = juUserCakeDesignService.getUserCakeDesignBoardCount(searchTerm);
			int totalPages = (int) Math.ceil((double) totalCount / size);

			// 응답 데이터 구성
			Map<String, Object> apiData = new HashMap<>();
			apiData.put("data", cakeDesignList);
			apiData.put("totalCount", totalCount);
			apiData.put("totalPages", totalPages);

			return JsonResult.success(apiData);
		} catch (Exception e) {
			return JsonResult.fail("도안 리스트를 가져오는 중 오류가 발생했습니다.");
		}
	}

	/* 도안 게시판 리스트 찜순 */
	@GetMapping("/api/user/cakeDesign/board/likes")
	public JsonResult getUserCakeDesignBoardListLikes(@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "size", defaultValue = "12") int size,
			@RequestParam(value = "search", required = false) String searchTerm) {

		try {
			// 페이징 처리
			int offset = (page - 1) * size;
			List<VendorCakeDesignVo> cakeDesignList = juUserCakeDesignService.getUserCakeDesignBoardLikes(offset, size,
					searchTerm);
			int totalCount = juUserCakeDesignService.getUserCakeDesignBoardCount(searchTerm);
			int totalPages = (int) Math.ceil((double) totalCount / size);

			// 응답 데이터 구성
			Map<String, Object> apiData = new HashMap<>();
			apiData.put("data", cakeDesignList);
			apiData.put("totalCount", totalCount);
			apiData.put("totalPages", totalPages);

			return JsonResult.success(apiData);
		} catch (Exception e) {
			return JsonResult.fail("도안 리스트를 가져오는 중 오류가 발생했습니다.");
		}
	}

	/* ALL 전체 갯수 */
	@GetMapping("/api/user/cakeDesign/board/count")
	public JsonResult getCakeDesignTotalCount() {
		int totalCount = juUserCakeDesignService.getCakeDesignTotalCount();

		// JSON 구조를 맞춰서 반환
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("totalCount", totalCount); // totalCount를 key로 설정

		if (totalCount > 0) {
			return JsonResult.success(resultMap); // 수정된 구조 반환
		} else {
			return JsonResult.fail("도안 리스트를 가져오지 못했습니다.");
		}
	}

	/* 도안 상세정보 */
	@GetMapping("/api/user/detail/{cakeDesignId}")
	public JsonResult getCakeDesignDetail(HttpServletRequest request, @PathVariable("cakeDesignId") int cakeDesignId) {
		// 1. 기본 도안 정보 가져오기
		VendorCakeDesignVo cakeDesignDetail = juUserCakeDesignService.getCakeDesignDetail(cakeDesignId);

		if (cakeDesignDetail != null) {
			// 2. 로그인한 사용자인 경우 찜 상태 확인
			try {
				int memberId = JwtUtil.getNoFromHeader(request);
				boolean isFavorited = juUserCakeDesignService.checkFavoriteStatus(memberId, cakeDesignId);
				cakeDesignDetail.setIsFavorited(isFavorited);
			} catch (Exception e) {
				// 토큰이 없는 경우(비로그인) - 기본값 false로 설정
				cakeDesignDetail.setIsFavorited(false);
			}
			return JsonResult.success(cakeDesignDetail);
		} else {
			return JsonResult.fail("도안 상세 정보를 가져오지 못했습니다.");
		}
	}

	/* 조회수 증가 */
	@PutMapping("/api/cakeDesign/views/{cakeDesignId}")
	public JsonResult getUserCakeDesignViews(@PathVariable("cakeDesignId") int cakeDesignId) {
		int count = juUserCakeDesignService.exeUserCakeDesignViews(cakeDesignId);

		if (count != 0) {
			return JsonResult.success(count); // 성공 시 데이터 반환
		} else {
			return JsonResult.fail("조회 실패"); // 실패 시 메시지 반환
		}
	}

	/* 도안 사용 후기 */
	@GetMapping("/api/user/cakeDesign/reviews/{cakeDesignId}")
	public JsonResult getUserCakeDesignReviews(@PathVariable("cakeDesignId") int cakeDesignId) {

		List<CakeDesignReviewVo> cakeDesignReviews = juUserCakeDesignService.getUserCakeDesignReviews(cakeDesignId);

		if (cakeDesignReviews != null && !cakeDesignReviews.isEmpty()) {
			return JsonResult.success(cakeDesignReviews);
		} else {
			return JsonResult.fail("리뷰를 가져오지 못했습니다.");
		}
	}

	/* 도안 상세정보(조회수 증가 포함X -> 수정할때 사용하는 정보) */
	@GetMapping("/api/cakeDesign/detail/{cakeDesignId}")
	public JsonResult getCakeDesignDetail2(@PathVariable("cakeDesignId") int cakeDesignId) {
		VendorCakeDesignVo cakeDesignDetail = juUserCakeDesignService.getCakeDesignDetail2(cakeDesignId);

		if (cakeDesignDetail != null) {
			return JsonResult.success(cakeDesignDetail);
		} else {
			return JsonResult.fail("도안 상세 정보를 가져오지 못했습니다.");
		}
	}

	/* 도안 수정 */
	/* 도안 수정 */
	@PostMapping("/api/cakeDesign/detail/edit")
	public JsonResult editVenderCakeDesign(@RequestParam("cakeDesignId") int cakeDesignId,
	    @RequestParam("cakeDesignTitle") String cakeDesignTitle,
	    @RequestParam("cakeDesignDescription") String cakeDesignDescription,
	    @RequestParam("cakeDesignPreferredAge") String cakeDesignPreferredAge,
	    @RequestParam("cakeDesignRecommendedEvent") String cakeDesignRecommendedEvent,
	    @RequestParam("cakeDesignPreferredShape") String cakeDesignPreferredShape,
	    @RequestParam("cakeDesignCategory") String cakeDesignCategory, // 카테고리 파라미터 추가
	    @RequestParam(value = "hashtags", required = false) String hashtagsJson, // 해시태그 파라미터 추가
	    @RequestParam(value = "files", required = false) List<MultipartFile> newImages,
	    @RequestParam(value = "deletedImages", required = false) List<String> deletedImages,
	    HttpServletRequest request) {

	    try {
	        // 1. 토큰에서 업체 member_id 추출
	        int memberId = JwtUtil.getNoFromHeader(request);

	        // 2. 도안 정보 업데이트
	        VendorCakeDesignVo juVenderCakeDesignVo = new VendorCakeDesignVo();
	        juVenderCakeDesignVo.setCakeDesignId(cakeDesignId);
	        juVenderCakeDesignVo.setMemberId(memberId);
	        juVenderCakeDesignVo.setCakeDesignTitle(cakeDesignTitle);
	        juVenderCakeDesignVo.setCakeDesignDescription(cakeDesignDescription);
	        juVenderCakeDesignVo.setCakeDesignPreferredAge(cakeDesignPreferredAge);
	        juVenderCakeDesignVo.setCakeDesignRecommendedEvent(cakeDesignRecommendedEvent);
	        juVenderCakeDesignVo.setCakeDesignPreferredShape(cakeDesignPreferredShape);
	        juVenderCakeDesignVo.setCakeDesignCategory(cakeDesignCategory); // 카테고리 추가

	        // 서비스 호출로 도안 정보 업데이트
	        juUserCakeDesignService.updateVenderCakeDesign(juVenderCakeDesignVo);

	        // 2-1. 해시태그 처리 (기존 해시태그 제거 후 새 해시태그 추가)
	        if (hashtagsJson != null && !hashtagsJson.isEmpty()) {
	            juUserCakeDesignService.updateCakeDesignHashtags(cakeDesignId, hashtagsJson);
	        }

	        // 3. 삭제된 이미지 처리
	        if (deletedImages != null && !deletedImages.isEmpty()) {
	            for (String imageUrl : deletedImages) {
	                // s3Service.deleteFile(imageUrl); // S3에서 파일 삭제
	                juUserCakeDesignService.deleteVenderCakeDesignImage(imageUrl); // DB에서도 삭제
	            }
	        }

	        // 4. 새로운 이미지 저장
	        if (newImages != null && !newImages.isEmpty()) {
	            for (MultipartFile file : newImages) {
	                if (!file.isEmpty()) {
	                    // 이미지 업로드 처리
	                    String key = "images/" + UUID.randomUUID() + "_" + file.getOriginalFilename();
	                    String imageUrl = s3Service.uploadFile(key, file.getInputStream(), file.getSize(),
	                            file.getContentType());

	                    // 이미지 데이터 저장
	                    VendorCakeDesignVo imageVo = new VendorCakeDesignVo();
	                    imageVo.setCakeDesignId(cakeDesignId); // 외래키로 사용될 도안 ID
	                    imageVo.setCakeDesignImageUrl(imageUrl); // 업로드된 이미지 URL 설정

	                    juUserCakeDesignService.saveNewVenderCakeDesignImage(imageVo);
	                }
	            }
	        }

	        return JsonResult.success("도안 수정이 완료되었습니다.");
	    } catch (IOException e) {
	        throw new RuntimeException("파일 업로드 중 오류가 발생했습니다.", e);
	    }
	}

	// 도안 삭제는 JuVenderCakeDesignController와 동일

	/* 유저 마이페이지 내가 그린 도안 전체 */
	@GetMapping("/api/mypage/cakeDesign")
	public JsonResult getUserCakeDesignList(HttpServletRequest request, @RequestParam(defaultValue = "1") int page,
			@RequestParam(defaultValue = "8") int size, @RequestParam(defaultValue = "") String keyword) {
		try {
			int memberId = JwtUtil.getNoFromHeader(request);

			// 단순히 offset 계산
			int offset = (page - 1) * size;

			// 데이터 조회
			List<VendorCakeDesignVo> cakeDesignList = juUserCakeDesignService.getUserCakeDesignList(memberId, keyword,
					offset, size);

			// 전체 데이터 개수 조회
			int totalCount = juUserCakeDesignService.getTotalCount(memberId, keyword);

			// 전체 페이지 수 계산
			int totalPages = (int) Math.ceil((double) totalCount / size);

			Map<String, Object> apiData = new HashMap<>();
			apiData.put("content", cakeDesignList);
			apiData.put("totalPages", totalPages);
			apiData.put("totalElements", totalCount);
			apiData.put("currentPage", page);
			apiData.put("size", size);

			return JsonResult.success(apiData);

		} catch (Exception e) {
			e.printStackTrace();
			return JsonResult.fail("도안 리스트를 가져오는 중 오류가 발생했습니다.");
		}
	}

	/* 유저 마이페이지 내가 그린 도안 최신순 */
	@GetMapping("/api/mypage/cakeDesign/latest")
	public JsonResult getUserCakeDesignListLatest(HttpServletRequest request,
			@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "8") int size,
			@RequestParam(defaultValue = "") String keyword) {
		try {
			int memberId = JwtUtil.getNoFromHeader(request);

			// 단순히 offset 계산
			int offset = (page - 1) * size;

			// 데이터 조회
			List<VendorCakeDesignVo> cakeDesignList = juUserCakeDesignService.getUserCakeDesignListLatest(memberId,
					keyword, offset, size);

			// 전체 데이터 개수 조회
			int totalCount = juUserCakeDesignService.getTotalCount(memberId, keyword);

			// 전체 페이지 수 계산
			int totalPages = (int) Math.ceil((double) totalCount / size);

			Map<String, Object> apiData = new HashMap<>();
			apiData.put("content", cakeDesignList);
			apiData.put("totalPages", totalPages);
			apiData.put("totalElements", totalCount);
			apiData.put("currentPage", page);
			apiData.put("size", size);

			return JsonResult.success(apiData);

		} catch (Exception e) {
			e.printStackTrace();
			return JsonResult.fail("도안 리스트를 가져오는 중 오류가 발생했습니다.");
		}
	}

	/* 유저 마이페이지 내가 그린 도안 조회수순 */
	@GetMapping("/api/mypage/cakeDesign/views")
	public JsonResult getUserCakeDesignListViews(HttpServletRequest request, @RequestParam(defaultValue = "1") int page,
			@RequestParam(defaultValue = "8") int size, @RequestParam(defaultValue = "") String keyword) {
		try {
			int memberId = JwtUtil.getNoFromHeader(request);

			// 단순히 offset 계산
			int offset = (page - 1) * size;

			// 데이터 조회
			List<VendorCakeDesignVo> cakeDesignList = juUserCakeDesignService.getUserCakeDesignListViews(memberId,
					keyword, offset, size);

			// 전체 데이터 개수 조회
			int totalCount = juUserCakeDesignService.getTotalCount(memberId, keyword);

			// 전체 페이지 수 계산
			int totalPages = (int) Math.ceil((double) totalCount / size);

			Map<String, Object> apiData = new HashMap<>();
			apiData.put("content", cakeDesignList);
			apiData.put("totalPages", totalPages);
			apiData.put("totalElements", totalCount);
			apiData.put("currentPage", page);
			apiData.put("size", size);

			return JsonResult.success(apiData);

		} catch (Exception e) {
			e.printStackTrace();
			return JsonResult.fail("도안 리스트를 가져오는 중 오류가 발생했습니다.");
		}
	}

	/* 유저 마이페이지 내가 그린 도안 찜순 */
	@GetMapping("/api/mypage/cakeDesign/likes")
	public JsonResult getUserCakeDesignListLikes(HttpServletRequest request, @RequestParam(defaultValue = "1") int page,
			@RequestParam(defaultValue = "8") int size, @RequestParam(defaultValue = "") String keyword) {
		try {
			int memberId = JwtUtil.getNoFromHeader(request);

			// 단순히 offset 계산
			int offset = (page - 1) * size;

			// 데이터 조회
			List<VendorCakeDesignVo> cakeDesignList = juUserCakeDesignService.getUserCakeDesignListLikes(memberId,
					keyword, offset, size);

			// 전체 데이터 개수 조회
			int totalCount = juUserCakeDesignService.getTotalCount(memberId, keyword);

			// 전체 페이지 수 계산
			int totalPages = (int) Math.ceil((double) totalCount / size);

			Map<String, Object> apiData = new HashMap<>();
			apiData.put("content", cakeDesignList);
			apiData.put("totalPages", totalPages);
			apiData.put("totalElements", totalCount);
			apiData.put("currentPage", page);
			apiData.put("size", size);

			return JsonResult.success(apiData);

		} catch (Exception e) {
			e.printStackTrace();
			return JsonResult.fail("도안 리스트를 가져오는 중 오류가 발생했습니다.");
		}
	}

	/* 유저 마이페이지 찜한 도안 전체 */
	@GetMapping("/api/mypage/cakeDesign/myLikes")
	public JsonResult getUserCakeDesignMyLikes(HttpServletRequest request, @RequestParam(defaultValue = "1") int page,
			@RequestParam(defaultValue = "8") int size, @RequestParam(defaultValue = "") String keyword) {
		try {
			int memberId = JwtUtil.getNoFromHeader(request);

			// 단순히 offset 계산
			int offset = (page - 1) * size;

			// 데이터 조회
			List<VendorCakeDesignVo> cakeDesignList = juUserCakeDesignService.getUserCakeDesignMyLikes(memberId,
					keyword, offset, size);

			// 전체 데이터 개수 조회
			int totalCount = juUserCakeDesignService.getTotalCount2(memberId, keyword);

			// 전체 페이지 수 계산
			int totalPages = (int) Math.ceil((double) totalCount / size);

			Map<String, Object> apiData = new HashMap<>();
			apiData.put("content", cakeDesignList);
			apiData.put("totalPages", totalPages);
			apiData.put("totalElements", totalCount);
			apiData.put("currentPage", page);
			apiData.put("size", size);

			return JsonResult.success(apiData);

		} catch (Exception e) {
			e.printStackTrace();
			return JsonResult.fail("도안 리스트를 가져오는 중 오류가 발생했습니다.");
		}
	}

	/* 유저 마이페이지 찜한 도안 최신순 */
	@GetMapping("/api/mypage/cakeDesign/myLikes/latest")
	public JsonResult getUserCakeDesignMyLikesLatest(HttpServletRequest request,
			@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "8") int size,
			@RequestParam(defaultValue = "") String keyword) {
		try {
			int memberId = JwtUtil.getNoFromHeader(request);

			// 단순히 offset 계산
			int offset = (page - 1) * size;

			// 데이터 조회
			List<VendorCakeDesignVo> cakeDesignList = juUserCakeDesignService.getUserCakeDesignMyLikesLatest(memberId,
					keyword, offset, size);

			// 전체 데이터 개수 조회
			int totalCount = juUserCakeDesignService.getTotalCount2(memberId, keyword);

			// 전체 페이지 수 계산
			int totalPages = (int) Math.ceil((double) totalCount / size);

			Map<String, Object> apiData = new HashMap<>();
			apiData.put("content", cakeDesignList);
			apiData.put("totalPages", totalPages);
			apiData.put("totalElements", totalCount);
			apiData.put("currentPage", page);
			apiData.put("size", size);

			return JsonResult.success(apiData);

		} catch (Exception e) {
			e.printStackTrace();
			return JsonResult.fail("도안 리스트를 가져오는 중 오류가 발생했습니다.");
		}
	}

	/* 유저 마이페이지 찜한 도안 조회수순 */
	@GetMapping("/api/mypage/cakeDesign/myLikes/veiws")
	public JsonResult getUserCakeDesignMyLikesViews(HttpServletRequest request,
			@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "8") int size,
			@RequestParam(defaultValue = "") String keyword) {
		try {
			int memberId = JwtUtil.getNoFromHeader(request);

			// 단순히 offset 계산
			int offset = (page - 1) * size;

			// 데이터 조회
			List<VendorCakeDesignVo> cakeDesignList = juUserCakeDesignService.getUserCakeDesignMyLikesViews(memberId,
					keyword, offset, size);

			// 전체 데이터 개수 조회
			int totalCount = juUserCakeDesignService.getTotalCount2(memberId, keyword);

			// 전체 페이지 수 계산
			int totalPages = (int) Math.ceil((double) totalCount / size);

			Map<String, Object> apiData = new HashMap<>();
			apiData.put("content", cakeDesignList);
			apiData.put("totalPages", totalPages);
			apiData.put("totalElements", totalCount);
			apiData.put("currentPage", page);
			apiData.put("size", size);

			return JsonResult.success(apiData);

		} catch (Exception e) {
			e.printStackTrace();
			return JsonResult.fail("도안 리스트를 가져오는 중 오류가 발생했습니다.");
		}
	}

	/* 유저 마이페이지 찜한 도안 찜순 */
	@GetMapping("/api/mypage/cakeDesign/myLikes/likes")
	public JsonResult getUserCakeDesignMyLikesLikes(HttpServletRequest request,
			@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "8") int size,
			@RequestParam(defaultValue = "") String keyword) {
		try {
			int memberId = JwtUtil.getNoFromHeader(request);

			// 단순히 offset 계산
			int offset = (page - 1) * size;

			// 데이터 조회
			List<VendorCakeDesignVo> cakeDesignList = juUserCakeDesignService.getUserCakeDesignMyLikesLikes(memberId,
					keyword, offset, size);

			// 전체 데이터 개수 조회
			int totalCount = juUserCakeDesignService.getTotalCount2(memberId, keyword);

			// 전체 페이지 수 계산
			int totalPages = (int) Math.ceil((double) totalCount / size);

			Map<String, Object> apiData = new HashMap<>();
			apiData.put("content", cakeDesignList);
			apiData.put("totalPages", totalPages);
			apiData.put("totalElements", totalCount);
			apiData.put("currentPage", page);
			apiData.put("size", size);

			return JsonResult.success(apiData);

		} catch (Exception e) {
			e.printStackTrace();
			return JsonResult.fail("도안 리스트를 가져오는 중 오류가 발생했습니다.");
		}
	}

	/* 찜하기/취소하기 토글 */
	@PostMapping("/api/user/favorite/{cakeDesignId}")
	public JsonResult toggleFavorite(HttpServletRequest request, @PathVariable int cakeDesignId,
			@RequestBody Map<String, Boolean> requestBody) {

		try {
			// 1. JWT 토큰에서 사용자 ID 추출
			int memberId = JwtUtil.getNoFromHeader(request);
			boolean currentStatus = requestBody.get("isFavorited");

			// 2. 서비스 호출하여 찜 상태 토글
			Map<String, Object> result = juUserCakeDesignService.toggleFavorite(memberId, cakeDesignId);

			// 3. 응답 데이터 구성
			Map<String, Object> apiData = new HashMap<>();
			apiData.put("isFavorited", result.get("isFavorited")); // 변경된 찜 상태
			apiData.put("updatedWishlistCount", result.get("wishlistCount")); // 업데이트된 찜 개수

			return JsonResult.success(apiData);

		} catch (Exception e) {
			e.printStackTrace();
			return JsonResult.fail("찜하기 처리 중 오류가 발생했습니다.");
		}
	}

	/* 카테고리 가져오기 */
	@GetMapping("/api/cakeDesign/category")
	public JsonResult getCategory() {
		try {
			// 데이터 조회
			List<HyunOptionValueVO> categoryList = juUserCakeDesignService.getCategory();
			return JsonResult.success(categoryList);

		} catch (Exception e) {
			e.printStackTrace();
			return JsonResult.fail("카테고리 리스트를 가져오는 중 오류가 발생했습니다.");
		}
	}
}

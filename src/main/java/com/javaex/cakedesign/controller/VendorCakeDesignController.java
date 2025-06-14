package com.javaex.cakedesign.controller;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.cakedesign.model.VendorCakeDesignVo;
import com.javaex.cakedesign.service.JuVenderCakeDesignService;
import com.javaex.common.storage.S3Service;
import com.javaex.common.util.JsonResult;
import com.javaex.common.util.JwtUtil;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class VendorCakeDesignController {

	@Autowired
	private JuVenderCakeDesignService juVenderCakeDesignService;

	@Autowired
	private S3Service s3Service;

	/* 업체 도안 등록 */
	@PostMapping("/api/add/vender/cakeDesign")
	public JsonResult addVenderCakeDesign(@RequestParam("files") List<MultipartFile> files,
			@RequestParam("cakeDesignTitle") String cakeDesignTitle,
			@RequestParam("cakeDesignDescription") String cakeDesignDescription,
			@RequestParam("cakeDesignPreferredAge") String cakeDesignPreferredAge,
			@RequestParam("cakeDesignRecommendedEvent") String cakeDesignRecommendedEvent,
			@RequestParam("cakeDesignPreferredShape") String cakeDesignPreferredShape,
			@RequestParam("cakeDesignVisibility") boolean cakeDesignVisibility, HttpServletRequest request) {

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
			juVenderCakeDesignVo.setCakeDesignVisibility(cakeDesignVisibility);

			// 도안 데이터 저장 및 생성된 cakeDesignId 가져오기
			int cakeDesignId = juVenderCakeDesignService.exeAddVenderCakeDesign(juVenderCakeDesignVo);

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
					juVenderCakeDesignService.saveVenderCakeDesignImage(imageVo);
				}
			}

			return JsonResult.success("도안 등록이 완료되었습니다.");
		} catch (IOException e) {
			throw new RuntimeException("파일 업로드 중 오류가 발생했습니다.", e);
		}
	}

	/* 업체 도안 리스트 */
	@GetMapping("/api/vender/cakeDesign/list")
	public JsonResult getVenderCakeDesignList(HttpServletRequest request) {
		// 1. 토큰에서 member_id 추출
		int memberId = JwtUtil.getNoFromHeader(request);

		// 2. 도안 리스트 가져오기 
		List<VendorCakeDesignVo> cakeDesignList = juVenderCakeDesignService.getVenderCakeDesignList(memberId);

		if (cakeDesignList != null && !cakeDesignList.isEmpty()) {
			return JsonResult.success(cakeDesignList);
		} else {
			return JsonResult.fail("도안 리스트를 가져오지 못했습니다.");
		}
	}

	/* 업체 도안 찜 리스트 */
	@GetMapping("/api/vender/cakeDesign/like/list")
	public JsonResult getVenderCakeDesignLikeList(HttpServletRequest request) {
		// 1. 토큰에서 member_id 추출
		int memberId = JwtUtil.getNoFromHeader(request);

		// 2. 도안 리스트 가져오기
		List<VendorCakeDesignVo> cakeDesignLikeList = juVenderCakeDesignService.getVenderCakeDesignLikeList(memberId);

		if (cakeDesignLikeList != null && !cakeDesignLikeList.isEmpty()) {
			return JsonResult.success(cakeDesignLikeList);
		} else {
			return JsonResult.fail("도안 찜 리스트를 가져오지 못했습니다.");
		}
	}

	/* 도안 상세정보 */
	@GetMapping("/api/vender/detail/{cakeDesignId}")
	public JsonResult getCakeDesignDetail(@PathVariable("cakeDesignId") int cakeDesignId) {
		VendorCakeDesignVo cakeDesignDetail = juVenderCakeDesignService.getCakeDesignDetail(cakeDesignId);

		if (cakeDesignDetail != null) {
			return JsonResult.success(cakeDesignDetail);
		} else {
			return JsonResult.fail("도안 상세 정보를 가져오지 못했습니다.");
		}
	}

	/* 도안 수정 */
	@PostMapping("/api/vender/cakeDesign/edit")
	public JsonResult editVenderCakeDesign(@RequestParam("cakeDesignId") int cakeDesignId,
			@RequestParam("cakeDesignTitle") String cakeDesignTitle,
			@RequestParam("cakeDesignDescription") String cakeDesignDescription,
			@RequestParam("cakeDesignPreferredAge") String cakeDesignPreferredAge,
			@RequestParam("cakeDesignRecommendedEvent") String cakeDesignRecommendedEvent,
			@RequestParam("cakeDesignPreferredShape") String cakeDesignPreferredShape,
			@RequestParam(value = "files", required = false) List<MultipartFile> newImages,
			@RequestParam(value = "deletedImages", required = false) List<String> deletedImages,
			@RequestParam("cakeDesignVisibility") boolean cakeDesignVisibility,
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
			juVenderCakeDesignVo.setCakeDesignVisibility(cakeDesignVisibility);

			// 서비스 호출로 도안 정보 업데이트
			juVenderCakeDesignService.updateVenderCakeDesign(juVenderCakeDesignVo);

			// 3. 삭제된 이미지 처리
			if (deletedImages != null && !deletedImages.isEmpty()) {
				for (String imageUrl : deletedImages) {
//	                s3Service.deleteFile(imageUrl); // S3에서 파일 삭제
					juVenderCakeDesignService.deleteVenderCakeDesignImage(imageUrl); // DB에서도 삭제
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

						juVenderCakeDesignService.saveNewVenderCakeDesignImage(imageVo);
					}
				}
			}

			return JsonResult.success("도안 수정이 완료되었습니다.");
		} catch (IOException e) {
			throw new RuntimeException("파일 업로드 중 오류가 발생했습니다.", e);
		}
	}

	/* 도안 삭제 */
	@DeleteMapping("/api/vender/cakeDesign/delete/{cakeDesignId}")
	public JsonResult delBoard(@PathVariable("cakeDesignId") int cakeDesignId, HttpServletRequest request) {
		// 1. 토큰에서 member_id 추출
		int memberId = JwtUtil.getNoFromHeader(request);

		try {
			// 2. 삭제 가능 여부 확인
			boolean isDeletable = juVenderCakeDesignService.isDeletable(cakeDesignId);

			if (isDeletable) {
				// 3. 삭제 수행
				int count = juVenderCakeDesignService.deleteCakeDesign(cakeDesignId, memberId);
				return JsonResult.success(count);
			} else {
				return JsonResult.fail("해당 도안은 다른곳에서 사용중이므로 삭제할 수 없습니다.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return JsonResult.fail("서버 에러로 인해 삭제를 실패했습니다.");
		}
	}

}

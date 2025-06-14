package com.javaex.vendor.controller;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.common.storage.S3Service;
import com.javaex.common.util.JsonResult;
import com.javaex.vendor.model.SsoVenderProductVo;
import com.javaex.vendor.model.SvenderVo;
import com.javaex.vendor.service.SsoVenderService;

@RestController
public class SsoVenderController {
	
	@Autowired
	private SsoVenderService ssoVenderService;
	@Autowired
	private S3Service s3Service;
	
	//업체등록여부 확인
	@GetMapping(value="/api/svender/{venderId}")
	public JsonResult checkNo(@PathVariable("venderId") int venderId) {
		//System.out.println(venderId);
		int shopStatus = ssoVenderService.exeCheckNo(venderId);
		//System.out.println(shopStatus);
		
		if(shopStatus > -1) {
			return JsonResult.success(shopStatus);
		}else {
			return JsonResult.fail("확인 불가");
		}
	}
	
	//업체 생성, 비활성
	@PutMapping(value="/api/svender/{venderId}")
	public JsonResult statusChange(@PathVariable ("venderId") int venderId) {
		System.out.println("수정"+venderId);
		int count = ssoVenderService.exestatusOne(venderId);
		//System.out.println(count);
		if(count != 0) {
			return JsonResult.success("업체등록 성공");
		}else {
			return JsonResult.fail("등록실패");
		}
		
	}
	
	//업체정보 등록하기
	@PutMapping(value="/api/svenderVo/{venderId}")
	public JsonResult test(@PathVariable ("venderId") int venderId,
					@RequestParam ("venderName") String venderName,
					@RequestParam ("venderAddress") String venderAddress,
					@RequestParam ("district") String district,
					@RequestParam ("latitude") double latitude,
					@RequestParam ("longitude") double longitude,
					@RequestParam ("kakaoURL") String kakaoURL,
					@RequestParam ("venderDescription") String venderDescription,
					@RequestParam ("bannerFile") MultipartFile bannerImgFile,
					@RequestParam ("profileFile") MultipartFile profileImgFile){
			//System.out.println("!!"+district);
		
		try {
			// 1. S3에 이미지 업로드
			String bannerkey = "images/" + UUID.randomUUID() + "_" + bannerImgFile.getOriginalFilename();
			String bannerimageUrl = s3Service.uploadFile(bannerkey, bannerImgFile.getInputStream(),
					bannerImgFile.getSize(), bannerImgFile.getContentType());
			
			String profilekey = "images/" + UUID.randomUUID() + "_" + profileImgFile.getOriginalFilename();
			String profileimageUrl = s3Service.uploadFile(profilekey, profileImgFile.getInputStream(),
					profileImgFile.getSize(), profileImgFile.getContentType());

			// 2. Vender 데이터 저장
			SvenderVo svenderVo = new SvenderVo();
			
			svenderVo.setVenderId(venderId);
			svenderVo.setVenderName(venderName);
			svenderVo.setVenderAddress(venderAddress);
			svenderVo.setDistrict(district);
			svenderVo.setLatitude(latitude);
			svenderVo.setLongitude(longitude);
			svenderVo.setBannerURL(bannerimageUrl);
			svenderVo.setProfileURL(profileimageUrl);
			svenderVo.setKakaoURL(kakaoURL);
			svenderVo.setVenderDescription(venderDescription);
			
			//System.out.println(svenderVo);
			int count = ssoVenderService.exeinsertPage(svenderVo);
			
			if(count != 0) {
				return JsonResult.success("1");
			}else {
				return JsonResult.fail("등록이 실패되었습니다.");
			}
			
			
		} catch (IOException e) {
			throw new RuntimeException("파일 업로드 중 오류가 발생했습니다.", e);
		}
			

	}
	//수정정보 가져오기
	@GetMapping(value="/api/svenderlist/{venderId}")
	public JsonResult editeList(@PathVariable("venderId") int venderId) {
		System.out.println("edite"+venderId);
		SvenderVo venderVo = ssoVenderService.exeOneList(venderId);
		System.out.println("!@!@!@!@"+venderVo);
		return JsonResult.success(venderVo);
	}
	
	
	/////////////////////////////////////////////////////////////////////////////
	
	//업체별 정보 가져오기
	@GetMapping("/api/vender/getdetails/{venderId}")
	public JsonResult shopGetDetails(@PathVariable("venderId") int venderId) {
		//System.out.println("jw");
		SvenderVo venderVo = ssoVenderService.exeGetDetails(venderId);
		//System.out.println("로케이션왔니"+venderVo);
		return JsonResult.success(venderVo);
	}
	
	//venderHead 배너사진 가져오기
	@GetMapping(value="/api/vender/getBanner/{venderId}")
	public JsonResult getBannerIMG(@PathVariable("venderId")int venderId) {
		//System.out.println("vener"+venderId);
		String bannerURL = ssoVenderService.exeGetBanner(venderId);
		
		return JsonResult.success(bannerURL);
	}
	
	
	//////////////
	//전체상품 가져오기
	@GetMapping("/api/vender/allGoods/{venderId}")
	public JsonResult getAllProductList(@PathVariable("venderId") int venderId) {
		List<SsoVenderProductVo> getAllProductList = ssoVenderService.getAllProductList(venderId);
		return JsonResult.success(getAllProductList);
	}
	
	
	//해당업체 카테고리 상품 가져오기
	@GetMapping("/api/veder/goodsList/{venderId}/{optionValueId}")
	public JsonResult getGoodeList(@PathVariable("venderId") int venderId,
									@PathVariable("optionValueId") int optionValueId) {
		System.out.println("productlist j w");
		
		SsoVenderProductVo nums = new SsoVenderProductVo(venderId, optionValueId);
		
		List<SsoVenderProductVo> productList = ssoVenderService.exeGetGoodsList(nums);
		System.out.println("22222"+productList);
		return JsonResult.success(productList);
	}
	
	//업체 카테고리 가져오기
	@GetMapping("/api/vender/options/{venderId}")
	public JsonResult getCarList(@PathVariable ("venderId") int venderId) {
		System.out.println("option j w");
		List<SsoVenderProductVo> carList = ssoVenderService.exeCarList(venderId);
		
		System.out.println("이거!!"+carList);
		return JsonResult.success(carList);
	}
	
	//사이드바 로고사진 가져오기
	@GetMapping(value="/api/vender/sidebarLogo/{venderId}")
	public JsonResult getLogoOne(@PathVariable("venderId")int venderId) {
		//System.out.println("사이드바 로고사진 가져오기");
		String logoURL = ssoVenderService.exeGetLogo(venderId);
		//System.out.println(logoURL);
		return JsonResult.success(logoURL);
		
	}
	
	
	
	
}

package com.javaex.product.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javaex.common.storage.S3Service;
import com.javaex.option.model.HyunOptionTypeVO;
import com.javaex.product.model.HyunProductRegistrationVo;
import com.javaex.product.model.HyunProductVo;
import com.javaex.product.service.HyunProductService;

@RestController
@RequestMapping("/api")
public class HyunProductController {
	@Autowired
	private HyunProductService hyunProductService;
	@Autowired
	private S3Service s3Service;

	@GetMapping("/wishlist-designs")
	public List<HyunProductVo> getWishlistCakeDesigns(@RequestParam("memberId") int memberId) {
		System.out.println("요청받은 memberId: " + memberId);
		return hyunProductService.getWishlistCakeDesigns(memberId);
	}

	@GetMapping("/my-designs")
	public List<HyunProductVo> getMyCakeDesigns(@RequestParam("memberId") int memberId) {
		return hyunProductService.getMyCakeDesigns(memberId);
	}

	/**
	 * 상품 등록 API
	 * 
	 * @param productVo 등록할 상품 데이터
	 * @return 성공 메시지
	 */
	@PostMapping(value = "/products", consumes = { "multipart/form-data" })
	public String registerProduct(@RequestParam("venderId") Long venderId,
			@RequestParam("productName") String productName, @RequestParam("price") Double price,
			@RequestParam("description") String description, @RequestParam("isVisible") Integer isVisible,
			@RequestParam(value = "selectedOptions", required = false) String selectedOptionsJson,
			@RequestPart("mainImage") MultipartFile mainImage,
			@RequestPart(value = "subImages", required = false) List<MultipartFile> subImages,
			@RequestParam(value = "cakeDesignId", required = false) Long cakeDesignId) {
		// 이미지 업로드
		String productImage1Url = null;
		String productImage2Url = null;
		String productImage3Url = null;
		String productImage4Url = null;

		try {
			if (mainImage != null) {
				productImage1Url = s3Service.uploadFile(mainImage);
			}
			if (subImages != null) {
				if (subImages.size() > 0)
					productImage2Url = s3Service.uploadFile(subImages.get(0));
				if (subImages.size() > 1)
					productImage3Url = s3Service.uploadFile(subImages.get(1));
				if (subImages.size() > 2)
					productImage4Url = s3Service.uploadFile(subImages.get(2));
			}
		} catch (IOException e) {
			throw new RuntimeException("이미지 업로드 중 오류 발생", e);
		}

		// JSON 문자열로 전달받은 옵션 데이터를 Map으로 변환
		ObjectMapper objectMapper = new ObjectMapper();
		Map<Integer, List<Integer>> selectedOptions = new HashMap<>();
		if (selectedOptionsJson != null && !selectedOptionsJson.isEmpty()) {
			try {
				selectedOptions = objectMapper.readValue(selectedOptionsJson, new TypeReference<>() {
				});
			} catch (JsonProcessingException e) {
				throw new RuntimeException("옵션 데이터 파싱 중 오류 발생", e);
			}
		}

		// VO 객체 생성 및 데이터 설정
		HyunProductRegistrationVo productVo = new HyunProductRegistrationVo();
		productVo.setVenderId(venderId);
		productVo.setProductName(productName);
		productVo.setPrice(price);
		productVo.setDescription(description);
		productVo.setIsVisible(isVisible);
		productVo.setProductImage1Url(productImage1Url);
		productVo.setProductImage2Url(productImage2Url);
		productVo.setProductImage3Url(productImage3Url);
		productVo.setProductImage4Url(productImage4Url);
		productVo.setSelectedOptions(selectedOptions);
		productVo.setCakeDesignId(cakeDesignId);

		// 서비스 호출
		hyunProductService.registerProduct(productVo);

		return "상품이 성공적으로 등록되었습니다.";
	}

	@GetMapping("/products")
	public List<HyunProductVo> getProductsByVenderId(@RequestParam Long venderId) {
		return hyunProductService.getProductsByVenderId(venderId);
	}

	@PutMapping("/products/{productId}/toggle-visibility")
	public String toggleProductVisibility(@PathVariable Long productId) {
		try {
			hyunProductService.toggleProductVisibility(productId);
			return "상품 노출 상태가 성공적으로 변경되었습니다.";
		} catch (Exception e) {
			throw new RuntimeException("상품 노출 상태 변경 중 오류가 발생했습니다.", e);
		}
	}

	// 삭제
	@PutMapping("/products/{productId}/mark-deleted")
	public ResponseEntity<String> markProductAsDeleted(@PathVariable Long productId) {
		try {
			boolean updated = hyunProductService.markProductAsDeleted(productId);
			if (updated) {
				return ResponseEntity.ok("상품이 성공적으로 삭제 상태로 변경되었습니다.");
			} else {
				return ResponseEntity.badRequest().body("상품이 미노출 상태가 아니어서 삭제할 수 없습니다.");
			}
		} catch (Exception e) {
			// 디버깅: 예외 내용 출력
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("상품 삭제 상태 변경 중 오류가 발생했습니다: " + e.getMessage());
		}
	}

	// 수정
	// 특정 상품 조회와 옵션 데이터 반환
	@GetMapping("/vender/products/{productId}")
	public ResponseEntity<Map<String, Object>> getProductDetails(@PathVariable Long productId) {
		try {
			HyunProductRegistrationVo product = hyunProductService.getProductById(productId);
			if (product == null) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", "상품 정보를 찾을 수 없습니다."));
			}

			System.out.println("API 응답 데이터: " + product); // 디버깅 로그
			return ResponseEntity.ok(Map.of("product", product));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("message", "서버 오류가 발생했습니다."));
		}
	}

	@GetMapping("/vender/products/{productId}/options")
	public ResponseEntity<?> getProductOptions(@PathVariable Long productId, @RequestParam Long venderId) {
		try {
			List<HyunOptionTypeVO> options = hyunProductService.getOptionsForProductAndVender(productId, venderId);
			if (options.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", "옵션 데이터가 없습니다."));
			}
			return ResponseEntity.ok(Map.of("options", options));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("message", "서버 오류가 발생했습니다."));
		}
	}

	// 상품 수정

	@PutMapping(value = "/vender/products/{productId}", consumes = { "multipart/form-data" })
	public String updateProduct(@PathVariable("productId") Long productId,
			@ModelAttribute HyunProductRegistrationVo productRegistrationVo,
			@RequestPart(value = "mainImage", required = false) MultipartFile mainImage,
			@RequestPart(value = "subImages", required = false) List<MultipartFile> subImages) throws IOException {

		hyunProductService.updateProduct(productId, productRegistrationVo, mainImage, subImages);
		return "상품이 성공적으로 수정되었습니다.";
	}

	// 옵션 수정
	@PutMapping("/vender/products/{productId}/options")
	public ResponseEntity<String> updateProductOptions(
	        @PathVariable Long productId,
	        @RequestBody Map<String, List<Integer>> selectedOptions) {
	    try {
	        // 전달받은 데이터 디버깅
	        System.out.println("전달받은 옵션 데이터: " + selectedOptions);

	        // 키를 Integer로 변환
	        Map<Integer, List<Integer>> convertedOptions = new HashMap<>();
	        for (Map.Entry<String, List<Integer>> entry : selectedOptions.entrySet()) {
	            Integer key;
	            try {
	                key = Integer.parseInt(entry.getKey()); // String 키를 Integer로 변환
	            } catch (NumberFormatException e) {
	                System.out.println("키 변환 실패: " + entry.getKey());
	                throw new NumberFormatException("옵션 키는 정수여야 합니다: " + entry.getKey());
	            }
	            convertedOptions.put(key, entry.getValue());
	        }

	        // 서비스 호출
	        hyunProductService.updateProductOptions(productId, convertedOptions);
	        return ResponseEntity.ok("옵션 수정 성공");
	    } catch (NumberFormatException e) {
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("옵션 데이터의 키 형식이 잘못되었습니다.");
	    } catch (Exception e) {
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("옵션 수정 중 서버 오류 발생");
	    }
	}



	@GetMapping(value = "/allList")
	public ResponseEntity<List<HyunProductVo>> getProductList() {
		System.out.println("!!!!!!");
		List<HyunProductVo> productList = hyunProductService.getProductList();
		System.out.println(productList);
		if (productList.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(productList);
	}

}

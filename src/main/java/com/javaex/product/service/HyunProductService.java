package com.javaex.product.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.common.storage.S3Service;
import com.javaex.option.model.HyunOptionTypeVO;
import com.javaex.product.model.HyunProductRegistrationVo;
import com.javaex.product.model.HyunProductVo;
import com.javaex.product.repository.HyunProductDao;

@Service
public class HyunProductService {

	@Autowired
	private HyunProductDao hyunProductDao;

	@Autowired
	private S3Service s3Service;

	/**
	 * 특정 회원이 찜한 케이크 도안 목록 조회
	 * 
	 * @param memberId 회원 ID
	 * @return 찜한 케이크 도안 목록
	 */
	public List<HyunProductVo> getWishlistCakeDesigns(int memberId) {
		return hyunProductDao.getWishlistCakeDesigns(memberId);
	}

	public List<HyunProductVo> getMyCakeDesigns(int memberId) {
		return hyunProductDao.findMyCakeDesigns(memberId);
	}

	/**
	 * 상품 등록 서비스 메서드
	 * 
	 * @param productVo 상품 등록 데이터
	 */

	@Transactional
	public void registerProduct(HyunProductRegistrationVo productVo) {
		// 상품 등록
		hyunProductDao.insertProduct(productVo);

		// 최근 삽입된 상품 ID 가져오기
		Long productId = hyunProductDao.selectLastInsertedProductId();

		// 옵션 매핑 처리
		Map<Integer, List<Integer>> selectedOptions = productVo.getSelectedOptions();
		if (selectedOptions != null && !selectedOptions.isEmpty()) {
			selectedOptions.forEach((optionTypeId, optionValueIds) -> {
				optionValueIds.forEach(optionValueId -> {
					hyunProductDao.insertProductOptionMapping(productId, optionTypeId, optionValueId);
				});
			});
		}
	}

	public List<HyunProductVo> getProductsByVenderId(Long venderId) {
		return hyunProductDao.getProductsByVenderId(venderId);
	}

	@Transactional
	public void toggleProductVisibility(Long productId) {
		HyunProductRegistrationVo product = hyunProductDao.getProductById(productId);
		if (product != null) {
			int newVisibility = product.getIsVisible() == 1 ? 0 : 1; // 노출 상태 변경
			hyunProductDao.updateProductVisibility(productId, newVisibility);
		} else {
			throw new RuntimeException("해당 ID의 상품을 찾을 수 없습니다: " + productId);
		}
	}

	// 상품삭제
	public boolean markProductAsDeleted(Long productId) {
		try {
			// 현재 상품 상태 확인
			Map<String, Object> productStatus = hyunProductDao.getProductStatus(productId);
			System.out.println("현재 상품 상태: " + productStatus);

			// 상품이 존재하지 않거나 미노출 상태가 아니면 false 반환
			if (productStatus == null || !(Boolean) productStatus.get("is_visible").equals(false)) {
				System.out.println("상품이 미노출 상태가 아님 또는 존재하지 않음.");
				return false;
			}

			// is_deleted 상태를 1로 업데이트
			int updatedRows = hyunProductDao.updateProductAsDeleted(productId);
			System.out.println("업데이트된 행 개수: " + updatedRows);
			return updatedRows > 0;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("상품 삭제 상태 변경 중 오류 발생", e);
		}
	}

	public void updateProduct(Long productId, HyunProductRegistrationVo productVo, MultipartFile mainImage,
			List<MultipartFile> subImages) throws IOException {
// 기존 상품 데이터를 가져옵니다.
		HyunProductRegistrationVo existingProduct = hyunProductDao.getProductById(productId);

// 메인 이미지 처리
		if (mainImage != null && !mainImage.isEmpty()) {
			String mainImageUrl = s3Service.uploadFile(mainImage); // 새 이미지를 업로드
			productVo.setProductImage1Url(mainImageUrl); // 새 이미지 URL 설정
		} else {
			productVo.setProductImage1Url(existingProduct.getProductImage1Url()); // 기존 이미지 유지
		}

// 서브 이미지 처리
		if (subImages != null) {
			for (int i = 0; i < 3; i++) {
				if (i < subImages.size() && subImages.get(i) != null && !subImages.get(i).isEmpty()) {
					String subImageUrl = s3Service.uploadFile(subImages.get(i)); // 새 이미지를 업로드
					switch (i) {
					case 0 -> productVo.setProductImage2Url(subImageUrl);
					case 1 -> productVo.setProductImage3Url(subImageUrl);
					case 2 -> productVo.setProductImage4Url(subImageUrl);
					}
				} else {
					// 서브 이미지가 새로 업로드되지 않은 경우 기존 이미지를 유지
					switch (i) {
					case 0 -> productVo.setProductImage2Url(existingProduct.getProductImage2Url());
					case 1 -> productVo.setProductImage3Url(existingProduct.getProductImage3Url());
					case 2 -> productVo.setProductImage4Url(existingProduct.getProductImage4Url());
					}
				}
			}
		} else {
// 서브 이미지 리스트가 비어 있으면 기존 이미지 전체 유지
			productVo.setProductImage2Url(existingProduct.getProductImage2Url());
			productVo.setProductImage3Url(existingProduct.getProductImage3Url());
			productVo.setProductImage4Url(existingProduct.getProductImage4Url());
		}

// 상품 데이터 업데이트
		hyunProductDao.updateProduct(productId, productVo);
	}

	@Transactional
	public void updateProductOptions(Long productId, Map<Integer, List<Integer>> selectedOptions) {
		System.out.println("전달받은 옵션 데이터: " + selectedOptions);

		// 기존 옵션 조회
		List<Map<String, Object>> existingOptions = hyunProductDao.getProductOptions(productId, null);
		System.out.println("기존 옵션 데이터: " + existingOptions);

		// Object -> Integer 타입 변환
		List<Map<String, Integer>> typedOptions = existingOptions.stream().map(option -> Map.of("optionTypeId",
				(Integer) option.get("optionTypeId"), "optionValueId", (Integer) option.get("optionValueId"))).toList();

		// 삭제할 옵션 계산
		List<Map<String, Integer>> optionsToDelete = typedOptions.stream().filter(existing -> !selectedOptions
				.getOrDefault(existing.get("optionTypeId"), List.of()).contains(existing.get("optionValueId")))
				.toList();
		System.out.println("삭제할 옵션: " + optionsToDelete);

		// 추가할 옵션 계산
		List<Map<String, Integer>> optionsToAdd = selectedOptions.entrySet().stream()
				.flatMap(entry -> entry.getValue().stream()
						.filter(value -> typedOptions.stream()
								.noneMatch(existing -> existing.get("optionTypeId").equals(entry.getKey())
										&& existing.get("optionValueId").equals(value)))
						.map(value -> Map.of("optionTypeId", entry.getKey(), "optionValueId", value)))
				.toList();
		System.out.println("추가할 옵션: " + optionsToAdd);

		// 삭제 처리
		optionsToDelete.forEach(option -> hyunProductDao.deleteProductOption(productId, option.get("optionTypeId"),
				option.get("optionValueId")));

		// 추가 처리
		optionsToAdd.forEach(option -> hyunProductDao.insertProductOptionMapping(productId, option.get("optionTypeId"),
				option.get("optionValueId")));
	}

	// 상품 ID로 상품 정보 가져오기
	public HyunProductRegistrationVo getProductById(Long productId) {
		return hyunProductDao.getProductById(productId);
	}

	// 특정 상품 ID로 옵션 데이터 가져오기
	public List<Map<String, Object>> getProductOptions(Long productId, Long venderId) {
		return hyunProductDao.getProductOptions(productId, venderId);
	}

	// 상품 ID로 옵션 가져오기
	public List<HyunOptionTypeVO> getOptionsForProductAndVender(Long productId, Long venderId) {
		Map<String, Object> params = new HashMap<>();
		params.put("productId", productId);
		params.put("venderId", venderId);
		return hyunProductDao.getOptionsByProductAndVender(params);
	}

	public List<HyunProductVo> getProductList() {
		return hyunProductDao.getProductList();
	}

}

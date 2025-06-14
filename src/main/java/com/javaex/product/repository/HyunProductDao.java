package com.javaex.product.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.option.model.HyunOptionTypeVO;
import com.javaex.product.model.HyunProductRegistrationVo;
import com.javaex.product.model.HyunProductVo;

@Repository
public class HyunProductDao {

	@Autowired
	private SqlSession sqlSession;

	private static final String NAMESPACE = "hyunProductMapper";

	/**
	 * 특정 회원이 찜한 케이크 도안 목록을 가져오는 메서드
	 * 
	 * @param memberId 특정 회원의 ID
	 * @return 찜한 도안 리스트
	 */

	public List<HyunProductVo> getWishlistCakeDesigns(int memberId) {
		return sqlSession.selectList(NAMESPACE + ".getWishlistCakeDesigns", memberId);
	}

	public List<HyunProductVo> findMyCakeDesigns(int memberId) {
		return sqlSession.selectList(NAMESPACE + ".findMyCakeDesigns", memberId);
	}

	/**
	 * 상품 데이터 삽입
	 * 
	 * @param productVo 상품 데이터
	 */
	public void insertProduct(HyunProductRegistrationVo productVo) {
		sqlSession.insert(NAMESPACE + ".insertProduct", productVo);
	}

	/**
	 * 
	 * /** 옵션 매핑 데이터 삽입
	 * 
	 * @param productId     상품 ID
	 * @param optionTypeId  옵션 타입 ID
	 * @param optionValueId 옵션 값 ID
	 */
	public void insertProductOptionMapping(Long productId, Integer optionTypeId, Integer optionValueId) {
		Map<String, Object> params = new HashMap<>();
		params.put("productId", productId);
		params.put("optionTypeId", optionTypeId);
		params.put("optionValueId", optionValueId);
		sqlSession.insert(NAMESPACE + ".insertProductOptionMapping", params);
	}

	/**
	 * 최근 삽입된 상품 ID 조회**@return 상품 ID
	 */

	public Long selectLastInsertedProductId() {
		return sqlSession.selectOne(NAMESPACE + ".selectLastInsertedProductId");
	}

	public List<HyunProductVo> getProductsByVenderId(Long venderId) {
		List<HyunProductVo> products = sqlSession.selectList(NAMESPACE + ".getProductsByVenderId", venderId);
		return products != null ? products : new ArrayList<>();
	}

	public HyunProductRegistrationVo getProductById(Long productId) {
		HyunProductRegistrationVo product = sqlSession.selectOne(NAMESPACE + ".getProductById", productId);
		System.out.println("상품 데이터: " + product); // 디버깅 로그
		return product;
	}

	public void updateProductVisibility(Long productId, int isVisible) {
		Map<String, Object> params = new HashMap<>();
		params.put("productId", productId);
		params.put("isVisible", isVisible);
		sqlSession.update(NAMESPACE + ".updateProductVisibility", params);
	}

	// 상품 상태 확인
	public Map<String, Object> getProductStatus(Long productId) {
		return sqlSession.selectOne("hyunProductMapper.getProductStatus", productId);
	}

	// 상품을 삭제 상태로 업데이트
	public int updateProductAsDeleted(Long productId) {
		return sqlSession.update("hyunProductMapper.markProductAsDeleted", productId);
	}

	/**
	 * 상품 수정
	 * 
	 * @param productId 상품 ID
	 * @param productVo 수정할 상품 데이터
	 */

	public void updateProduct(Long productId, HyunProductRegistrationVo productVo) {
		Map<String, Object> params = new HashMap<>();
		params.put("productId", productId);
		params.put("productName", productVo.getProductName());
		params.put("description", productVo.getDescription());
		params.put("price", productVo.getPrice());
		params.put("cakeDesignId", productVo.getCakeDesignId());
		params.put("productImage1Url", productVo.getProductImage1Url());
		params.put("productImage2Url", productVo.getProductImage2Url());
		params.put("productImage3Url", productVo.getProductImage3Url());
		params.put("productImage4Url", productVo.getProductImage4Url());

		sqlSession.update(NAMESPACE + ".updateProduct", params);
	}

	/**
	 * 상품 옵션 삭제
	 * 
	 * @param productId 상품 ID
	 */

	/**
	 * 옵션 매핑 추가
	 * 
	 * @param productId     상품 ID
	 * @param optionTypeId  옵션 타입 ID
	 * @param optionValueId 옵션 값 ID
	 */

	// 특정 상품 ID로 옵션 데이터 가져오기
	public List<Map<String, Object>> getProductOptions(Long productId, Long venderId) {
		Map<String, Object> params = new HashMap<>();
		params.put("productId", productId);
		params.put("venderId", venderId);
		return sqlSession.selectList(NAMESPACE + ".getProductOptionsByProductIdAndVenderId", params);
	}

	public List<HyunOptionTypeVO> getOptionsByProductAndVender(Map<String, Object> params) {
		return sqlSession.selectList("hyunProductMapper.getOptionsByProductAndVender", params);
	}

	public List<HyunProductVo> getProductList() {
		return sqlSession.selectList(NAMESPACE + ".getProductList");
	}

	// 옵션 수정 로직: 기존 옵션 삭제 후 새로운 옵션 삽입
	public void updateProductOptions(Long productId, Map<Integer, List<Integer>> selectedOptions) {
		// 1. 기존 옵션 삭제
		sqlSession.delete("hyunProductMapper.deleteProductOptions", productId);

		// 2. 새로운 옵션 삽입
		if (selectedOptions != null) {
			for (Map.Entry<Integer, List<Integer>> entry : selectedOptions.entrySet()) {
				Integer optionTypeId = entry.getKey();
				List<Integer> optionValueIds = entry.getValue();

				if (optionValueIds != null) {
					for (Integer optionValueId : optionValueIds) {
						Map<String, Object> params = new HashMap<>();
						params.put("productId", productId);
						params.put("optionTypeId", optionTypeId);
						params.put("optionValueId", optionValueId);
						sqlSession.insert("hyunProductMapper.insertProductOptionMapping", params);
					}
				}
			}
		}
	}

	// 기존 옵션 삭제
	public void deleteProductOption(Long productId, Integer optionTypeId, Integer optionValueId) {
		Map<String, Object> params = new HashMap<>();
		params.put("productId", productId);
		params.put("optionTypeId", optionTypeId);
		params.put("optionValueId", optionValueId);

		sqlSession.delete(NAMESPACE + ".deleteProductOption", params);
	}

}

package com.javaex.product.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HyunProductRegistrationVo {

	private String productName; // 상품명
	private String description; // 상품 설명
	private Double price; // 가격
	private Integer isVisible; // 노출 여부
	private Long cakeDesignId; // 케이크 도안 ID
	private Long venderId; // 업체 ID
	private String productImage1Url = "";
	private String productImage2Url = "";
	private String productImage3Url = "";
	private String productImage4Url = "";
	private String cakeDesignTitle;
	private String cakeDesignImageUrl;
	private Map<Integer, List<Integer>> selectedOptions = new HashMap<>();

	public HyunProductRegistrationVo() {
		super();
	}

	public HyunProductRegistrationVo(String productName, String description, Double price, Integer isVisible,
			Long cakeDesignId, Long venderId, String productImage1Url, String productImage2Url, String productImage3Url,
			String productImage4Url, String cakeDesignTitle, String cakeDesignImageUrl,
			Map<Integer, List<Integer>> selectedOptions) {
		super();
		this.productName = productName;
		this.description = description;
		this.price = price;
		this.isVisible = isVisible;
		this.cakeDesignId = cakeDesignId;
		this.venderId = venderId;
		this.productImage1Url = productImage1Url;
		this.productImage2Url = productImage2Url;
		this.productImage3Url = productImage3Url;
		this.productImage4Url = productImage4Url;
		this.cakeDesignTitle = cakeDesignTitle;
		this.cakeDesignImageUrl = cakeDesignImageUrl;
		this.selectedOptions = selectedOptions;
	}

	public String getCakeDesignTitle() {
		return cakeDesignTitle;
	}

	public void setCakeDesignTitle(String cakeDesignTitle) {
		this.cakeDesignTitle = cakeDesignTitle;
	}

	public String getCakeDesignImageUrl() {
		return cakeDesignImageUrl;
	}

	public void setCakeDesignImageUrl(String cakeDesignImageUrl) {
		this.cakeDesignImageUrl = cakeDesignImageUrl;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getIsVisible() {
		return isVisible;
	}

	public void setIsVisible(Integer isVisible) {
		this.isVisible = isVisible;
	}

	public Long getVenderId() {
		return venderId;
	}

	public void setVenderId(Long venderId) {
		this.venderId = venderId;
	}

	public String getProductImage1Url() {
		return productImage1Url;
	}

	public void setProductImage1Url(String productImage1Url) {
		this.productImage1Url = productImage1Url;
	}

	public String getProductImage2Url() {
		return productImage2Url;
	}

	public void setProductImage2Url(String productImage2Url) {
		this.productImage2Url = productImage2Url;
	}

	public String getProductImage3Url() {
		return productImage3Url;
	}

	public void setProductImage3Url(String productImage3Url) {
		this.productImage3Url = productImage3Url;
	}

	public String getProductImage4Url() {
		return productImage4Url;
	}

	public void setProductImage4Url(String productImage4Url) {
		this.productImage4Url = productImage4Url;
	}

	public Map<Integer, List<Integer>> getSelectedOptions() {
		return selectedOptions;
	}

	public void setSelectedOptions(Map<Integer, List<Integer>> selectedOptions) {
		this.selectedOptions = selectedOptions;
	}

	public Long getCakeDesignId() {
		return cakeDesignId;
	}

	public void setCakeDesignId(Long cakeDesignId) {
		this.cakeDesignId = cakeDesignId;
	}

	@Override
	public String toString() {
		return "HyunProductRegistrationVo [productName=" + productName + ", description=" + description + ", price="
				+ price + ", isVisible=" + isVisible + ", cakeDesignId=" + cakeDesignId + ", venderId=" + venderId
				+ ", productImage1Url=" + productImage1Url + ", productImage2Url=" + productImage2Url
				+ ", productImage3Url=" + productImage3Url + ", productImage4Url=" + productImage4Url
				+ ", cakeDesignTitle=" + cakeDesignTitle + ", cakeDesignImageUrl=" + cakeDesignImageUrl
				+ ", selectedOptions=" + selectedOptions + "]";
	}

}

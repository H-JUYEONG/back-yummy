package com.javaex.product.model;

import java.math.BigDecimal;

public class HyunProductVo {
	private int cakeDesignId;
	private int memberId;
	private String cakeDesignTitle;
	private String cakeDesignDescription;
	private String cakeDesignPreferredAge;
	private String cakeDesignRecommendedEvent;
	private String cakeDesignPreferredShape;
	private int cakeDesignViewCount;
	private String cakeDesignCreatedAt;
	private String cakeDesignImageUrl;
	private boolean isWishlisted; // 해당 도안이 찜된 상태인지 여부

	private Long productId;
	private String productName;
	private BigDecimal price;
	private Integer isVisible;
	private Long venderId; // 업체 ID
	private String description; // 상품 설명

	private String productImage1Url;
	private int reviewRating;
	private String venderProfileImageUrl;
	private String venderName;
	private String district;
	
	private String optionValueName;

	public HyunProductVo() {
		super();
	}

	public HyunProductVo(int cakeDesignId, int memberId, String cakeDesignTitle, String cakeDesignDescription,
			String cakeDesignPreferredAge, String cakeDesignRecommendedEvent, String cakeDesignPreferredShape,
			int cakeDesignViewCount, String cakeDesignCreatedAt, String cakeDesignImageUrl, boolean isWishlisted,
			Long productId, String productName, BigDecimal price, Integer isVisible, Long venderId, String description,
			String productImage1Url, int reviewRating, String venderProfileImageUrl, String venderName, String district,
			String optionValueName) {
		super();
		this.cakeDesignId = cakeDesignId;
		this.memberId = memberId;
		this.cakeDesignTitle = cakeDesignTitle;
		this.cakeDesignDescription = cakeDesignDescription;
		this.cakeDesignPreferredAge = cakeDesignPreferredAge;
		this.cakeDesignRecommendedEvent = cakeDesignRecommendedEvent;
		this.cakeDesignPreferredShape = cakeDesignPreferredShape;
		this.cakeDesignViewCount = cakeDesignViewCount;
		this.cakeDesignCreatedAt = cakeDesignCreatedAt;
		this.cakeDesignImageUrl = cakeDesignImageUrl;
		this.isWishlisted = isWishlisted;
		this.productId = productId;
		this.productName = productName;
		this.price = price;
		this.isVisible = isVisible;
		this.venderId = venderId;
		this.description = description;
		this.productImage1Url = productImage1Url;
		this.reviewRating = reviewRating;
		this.venderProfileImageUrl = venderProfileImageUrl;
		this.venderName = venderName;
		this.district = district;
		this.optionValueName = optionValueName;
	}





	public String getProductImage1Url() {
		return productImage1Url;
	}

	public void setProductImage1Url(String productImage1Url) {
		this.productImage1Url = productImage1Url;
	}

	public int getReviewRating() {
		return reviewRating;
	}

	public void setReviewRating(int reviewRating) {
		this.reviewRating = reviewRating;
	}

	public String getVenderProfileImageUrl() {
		return venderProfileImageUrl;
	}

	public void setVenderProfileImageUrl(String venderProfileImageUrl) {
		this.venderProfileImageUrl = venderProfileImageUrl;
	}

	public String getVenderName() {
		return venderName;
	}

	public void setVenderName(String venderName) {
		this.venderName = venderName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getVenderId() {
		return venderId;
	}

	public void setVenderId(Long venderId) {
		this.venderId = venderId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Integer getIsVisible() {
		return isVisible;
	}

	public void setIsVisible(Integer isVisible) {
		this.isVisible = isVisible;
	}

	public String getCakeDesignImageUrl() {
		return cakeDesignImageUrl;
	}

	public void setCakeDesignImageUrl(String cakeDesignImageUrl) {
		this.cakeDesignImageUrl = cakeDesignImageUrl;
	}

	public int getCakeDesignId() {
		return cakeDesignId;
	}

	public void setCakeDesignId(int cakeDesignId) {
		this.cakeDesignId = cakeDesignId;
	}

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public String getCakeDesignTitle() {
		return cakeDesignTitle;
	}

	public void setCakeDesignTitle(String cakeDesignTitle) {
		this.cakeDesignTitle = cakeDesignTitle;
	}

	public String getCakeDesignDescription() {
		return cakeDesignDescription;
	}

	public void setCakeDesignDescription(String cakeDesignDescription) {
		this.cakeDesignDescription = cakeDesignDescription;
	}

	public String getCakeDesignPreferredAge() {
		return cakeDesignPreferredAge;
	}

	public void setCakeDesignPreferredAge(String cakeDesignPreferredAge) {
		this.cakeDesignPreferredAge = cakeDesignPreferredAge;
	}

	public String getCakeDesignRecommendedEvent() {
		return cakeDesignRecommendedEvent;
	}

	public void setCakeDesignRecommendedEvent(String cakeDesignRecommendedEvent) {
		this.cakeDesignRecommendedEvent = cakeDesignRecommendedEvent;
	}

	public String getCakeDesignPreferredShape() {
		return cakeDesignPreferredShape;
	}

	public void setCakeDesignPreferredShape(String cakeDesignPreferredShape) {
		this.cakeDesignPreferredShape = cakeDesignPreferredShape;
	}

	public int getCakeDesignViewCount() {
		return cakeDesignViewCount;
	}

	public void setCakeDesignViewCount(int cakeDesignViewCount) {
		this.cakeDesignViewCount = cakeDesignViewCount;
	}

	public String getCakeDesignCreatedAt() {
		return cakeDesignCreatedAt;
	}

	public void setCakeDesignCreatedAt(String cakeDesignCreatedAt) {
		this.cakeDesignCreatedAt = cakeDesignCreatedAt;
	}

	public boolean isWishlisted() {
		return isWishlisted;
	}

	public void setWishlisted(boolean wishlisted) {
		isWishlisted = wishlisted;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getOptionValueName() {
		return optionValueName;
	}

	public void setOptionValueName(String optionValueName) {
		this.optionValueName = optionValueName;
	}

	@Override
	public String toString() {
		return "HyunProductVo [cakeDesignId=" + cakeDesignId + ", memberId=" + memberId + ", cakeDesignTitle="
				+ cakeDesignTitle + ", cakeDesignDescription=" + cakeDesignDescription + ", cakeDesignPreferredAge="
				+ cakeDesignPreferredAge + ", cakeDesignRecommendedEvent=" + cakeDesignRecommendedEvent
				+ ", cakeDesignPreferredShape=" + cakeDesignPreferredShape + ", cakeDesignViewCount="
				+ cakeDesignViewCount + ", cakeDesignCreatedAt=" + cakeDesignCreatedAt + ", cakeDesignImageUrl="
				+ cakeDesignImageUrl + ", isWishlisted=" + isWishlisted + ", productId=" + productId + ", productName="
				+ productName + ", price=" + price + ", isVisible=" + isVisible + ", venderId=" + venderId
				+ ", description=" + description + ", productImage1Url=" + productImage1Url + ", reviewRating="
				+ reviewRating + ", venderProfileImageUrl=" + venderProfileImageUrl + ", venderName=" + venderName
				+ ", district=" + district + ", optionValueName=" + optionValueName + "]";
	}


}

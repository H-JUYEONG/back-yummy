package com.javaex.audition.model;

public class JuAuditionVendorCartVo {

	private int auditionCartId;
	private int auditionApplicationId;
	private int venderId;
	private int productId;
	private String deliveryMethod;
	private String deliveryAddress; // 배송 주소
	private String recipientName; // 받는 사람 이름
	private String recipientPhone; // 받는 사람 연락처
	private String desiredDate; // 희망 날짜(픽업, 배송 공통부분이므로 한개 사용)
	private String desiredTime; // 희망 시간(픽업, 배송 공통부분이므로 한개 사용)
	private String productType;
	private String cakeSize;
	private String flavorSheet;
	private String flavorCream;
	private String backgroundColor;
	private String creamPosition;
	private String creamColor;
	private String decorationType;
	private String decorationColor;
	private String category;
	private int orderAmount;
	private String cakeLettering;
	private String plateLettering;
	private int proposedAmount;
	private String additionalRequests;
	private String deliveryDate;
	private int isSelected;
	private String createdAt;
	private String shopRequests;

	private String productImage1Url;
	private String venderName;

	private String productName;
	private int reviewId;
	private int reviewUserId;
	private int reviewOrderId;
	private int reviewRating;
	private String reviewContent;
	private String reviewImageUrl;
	private String reviewCreatedAt;

	private String userNickname;
	private int userId;
	private int memberId;

	public JuAuditionVendorCartVo() {

	}

	public JuAuditionVendorCartVo(int auditionCartId, int auditionApplicationId, int venderId, int productId,
			String deliveryMethod, String deliveryAddress, String recipientName, String recipientPhone,
			String desiredDate, String desiredTime, String productType, String cakeSize, String flavorSheet,
			String flavorCream, String backgroundColor, String creamPosition, String creamColor, String decorationType,
			String decorationColor, String category, int orderAmount, String cakeLettering, String plateLettering,
			int proposedAmount, String additionalRequests, String deliveryDate, int isSelected, String createdAt,
			String shopRequests, String productImage1Url, String venderName, String productName, int reviewId,
			int reviewUserId, int reviewOrderId, int reviewRating, String reviewContent, String reviewImageUrl,
			String reviewCreatedAt, String userNickname, int userId, int memberId) {
		this.auditionCartId = auditionCartId;
		this.auditionApplicationId = auditionApplicationId;
		this.venderId = venderId;
		this.productId = productId;
		this.deliveryMethod = deliveryMethod;
		this.deliveryAddress = deliveryAddress;
		this.recipientName = recipientName;
		this.recipientPhone = recipientPhone;
		this.desiredDate = desiredDate;
		this.desiredTime = desiredTime;
		this.productType = productType;
		this.cakeSize = cakeSize;
		this.flavorSheet = flavorSheet;
		this.flavorCream = flavorCream;
		this.backgroundColor = backgroundColor;
		this.creamPosition = creamPosition;
		this.creamColor = creamColor;
		this.decorationType = decorationType;
		this.decorationColor = decorationColor;
		this.category = category;
		this.orderAmount = orderAmount;
		this.cakeLettering = cakeLettering;
		this.plateLettering = plateLettering;
		this.proposedAmount = proposedAmount;
		this.additionalRequests = additionalRequests;
		this.deliveryDate = deliveryDate;
		this.isSelected = isSelected;
		this.createdAt = createdAt;
		this.shopRequests = shopRequests;
		this.productImage1Url = productImage1Url;
		this.venderName = venderName;
		this.productName = productName;
		this.reviewId = reviewId;
		this.reviewUserId = reviewUserId;
		this.reviewOrderId = reviewOrderId;
		this.reviewRating = reviewRating;
		this.reviewContent = reviewContent;
		this.reviewImageUrl = reviewImageUrl;
		this.reviewCreatedAt = reviewCreatedAt;
		this.userNickname = userNickname;
		this.userId = userId;
		this.memberId = memberId;
	}

	public int getAuditionCartId() {
		return auditionCartId;
	}

	public void setAuditionCartId(int auditionCartId) {
		this.auditionCartId = auditionCartId;
	}

	public int getAuditionApplicationId() {
		return auditionApplicationId;
	}

	public void setAuditionApplicationId(int auditionApplicationId) {
		this.auditionApplicationId = auditionApplicationId;
	}

	public int getVenderId() {
		return venderId;
	}

	public void setVenderId(int venderId) {
		this.venderId = venderId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getDeliveryMethod() {
		return deliveryMethod;
	}

	public void setDeliveryMethod(String deliveryMethod) {
		this.deliveryMethod = deliveryMethod;
	}

	public String getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public String getRecipientName() {
		return recipientName;
	}

	public void setRecipientName(String recipientName) {
		this.recipientName = recipientName;
	}

	public String getRecipientPhone() {
		return recipientPhone;
	}

	public void setRecipientPhone(String recipientPhone) {
		this.recipientPhone = recipientPhone;
	}

	public String getDesiredDate() {
		return desiredDate;
	}

	public void setDesiredDate(String desiredDate) {
		this.desiredDate = desiredDate;
	}

	public String getDesiredTime() {
		return desiredTime;
	}

	public void setDesiredTime(String desiredTime) {
		this.desiredTime = desiredTime;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getCakeSize() {
		return cakeSize;
	}

	public void setCakeSize(String cakeSize) {
		this.cakeSize = cakeSize;
	}

	public String getFlavorSheet() {
		return flavorSheet;
	}

	public void setFlavorSheet(String flavorSheet) {
		this.flavorSheet = flavorSheet;
	}

	public String getFlavorCream() {
		return flavorCream;
	}

	public void setFlavorCream(String flavorCream) {
		this.flavorCream = flavorCream;
	}

	public String getBackgroundColor() {
		return backgroundColor;
	}

	public void setBackgroundColor(String backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	public String getCreamPosition() {
		return creamPosition;
	}

	public void setCreamPosition(String creamPosition) {
		this.creamPosition = creamPosition;
	}

	public String getCreamColor() {
		return creamColor;
	}

	public void setCreamColor(String creamColor) {
		this.creamColor = creamColor;
	}

	public String getDecorationType() {
		return decorationType;
	}

	public void setDecorationType(String decorationType) {
		this.decorationType = decorationType;
	}

	public String getDecorationColor() {
		return decorationColor;
	}

	public void setDecorationColor(String decorationColor) {
		this.decorationColor = decorationColor;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(int orderAmount) {
		this.orderAmount = orderAmount;
	}

	public String getCakeLettering() {
		return cakeLettering;
	}

	public void setCakeLettering(String cakeLettering) {
		this.cakeLettering = cakeLettering;
	}

	public String getPlateLettering() {
		return plateLettering;
	}

	public void setPlateLettering(String plateLettering) {
		this.plateLettering = plateLettering;
	}

	public int getProposedAmount() {
		return proposedAmount;
	}

	public void setProposedAmount(int proposedAmount) {
		this.proposedAmount = proposedAmount;
	}

	public String getAdditionalRequests() {
		return additionalRequests;
	}

	public void setAdditionalRequests(String additionalRequests) {
		this.additionalRequests = additionalRequests;
	}

	public String getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public int getIsSelected() {
		return isSelected;
	}

	public void setIsSelected(int isSelected) {
		this.isSelected = isSelected;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getShopRequests() {
		return shopRequests;
	}

	public void setShopRequests(String shopRequests) {
		this.shopRequests = shopRequests;
	}

	public String getProductImage1Url() {
		return productImage1Url;
	}

	public void setProductImage1Url(String productImage1Url) {
		this.productImage1Url = productImage1Url;
	}

	public String getVenderName() {
		return venderName;
	}

	public void setVenderName(String venderName) {
		this.venderName = venderName;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getReviewId() {
		return reviewId;
	}

	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}

	public int getReviewUserId() {
		return reviewUserId;
	}

	public void setReviewUserId(int reviewUserId) {
		this.reviewUserId = reviewUserId;
	}

	public int getReviewOrderId() {
		return reviewOrderId;
	}

	public void setReviewOrderId(int reviewOrderId) {
		this.reviewOrderId = reviewOrderId;
	}

	public int getReviewRating() {
		return reviewRating;
	}

	public void setReviewRating(int reviewRating) {
		this.reviewRating = reviewRating;
	}

	public String getReviewContent() {
		return reviewContent;
	}

	public void setReviewContent(String reviewContent) {
		this.reviewContent = reviewContent;
	}

	public String getReviewImageUrl() {
		return reviewImageUrl;
	}

	public void setReviewImageUrl(String reviewImageUrl) {
		this.reviewImageUrl = reviewImageUrl;
	}

	public String getReviewCreatedAt() {
		return reviewCreatedAt;
	}

	public void setReviewCreatedAt(String reviewCreatedAt) {
		this.reviewCreatedAt = reviewCreatedAt;
	}

	public String getUserNickname() {
		return userNickname;
	}

	public void setUserNickname(String userNickname) {
		this.userNickname = userNickname;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	@Override
	public String toString() {
		return "JuAuditionVendorCartVo [auditionCartId=" + auditionCartId + ", auditionApplicationId="
				+ auditionApplicationId + ", venderId=" + venderId + ", productId=" + productId + ", deliveryMethod="
				+ deliveryMethod + ", deliveryAddress=" + deliveryAddress + ", recipientName=" + recipientName
				+ ", recipientPhone=" + recipientPhone + ", desiredDate=" + desiredDate + ", desiredTime=" + desiredTime
				+ ", productType=" + productType + ", cakeSize=" + cakeSize + ", flavorSheet=" + flavorSheet
				+ ", flavorCream=" + flavorCream + ", backgroundColor=" + backgroundColor + ", creamPosition="
				+ creamPosition + ", creamColor=" + creamColor + ", decorationType=" + decorationType
				+ ", decorationColor=" + decorationColor + ", category=" + category + ", orderAmount=" + orderAmount
				+ ", cakeLettering=" + cakeLettering + ", plateLettering=" + plateLettering + ", proposedAmount="
				+ proposedAmount + ", additionalRequests=" + additionalRequests + ", deliveryDate=" + deliveryDate
				+ ", isSelected=" + isSelected + ", createdAt=" + createdAt + ", shopRequests=" + shopRequests
				+ ", productImage1Url=" + productImage1Url + ", venderName=" + venderName + ", productName="
				+ productName + ", reviewId=" + reviewId + ", reviewUserId=" + reviewUserId + ", reviewOrderId="
				+ reviewOrderId + ", reviewRating=" + reviewRating + ", reviewContent=" + reviewContent
				+ ", reviewImageUrl=" + reviewImageUrl + ", reviewCreatedAt=" + reviewCreatedAt + ", userNickname="
				+ userNickname + ", userId=" + userId + ", memberId=" + memberId + "]";
	}

}

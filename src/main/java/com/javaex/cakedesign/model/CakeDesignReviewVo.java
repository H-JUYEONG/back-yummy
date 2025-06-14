package com.javaex.cakedesign.model;

public class CakeDesignReviewVo {

	private int venderId; // 업체 ID
	private int productId; // 상품 ID
	private int cakeDesignId; // 도안 고유 식별자
	private int memberId; // 도안 등록자 ID (유저 또는 업체)
	private String userNickname; // 유저 닉네임
	private String type; // 유저, 업체, 어드민

	private int reviewId; // 리뷰 고유 식별자
	private int reviewUserId; // 리뷰 작성자 ID (유저)
	private int reviewOrderId; // 리뷰 대상 주문 ID
	private int reviewRating; // 별점 (1-5)
	private String reviewContent; // 리뷰 내용
	private String reviewImageUrl; // 이미지 URL
	private String reviewCreatedAt; // 리뷰 작성일

	private String venderName; // 업체 이름
	private String productName; // 상품 이름

	public CakeDesignReviewVo() {

	}

	public CakeDesignReviewVo(int venderId, int productId, int cakeDesignId, int memberId, String userNickname,
			String type, int reviewId, int reviewUserId, int reviewOrderId, int reviewRating, String reviewContent,
			String reviewImageUrl, String reviewCreatedAt, String venderName, String productName) {
		this.venderId = venderId;
		this.productId = productId;
		this.cakeDesignId = cakeDesignId;
		this.memberId = memberId;
		this.userNickname = userNickname;
		this.type = type;
		this.reviewId = reviewId;
		this.reviewUserId = reviewUserId;
		this.reviewOrderId = reviewOrderId;
		this.reviewRating = reviewRating;
		this.reviewContent = reviewContent;
		this.reviewImageUrl = reviewImageUrl;
		this.reviewCreatedAt = reviewCreatedAt;
		this.venderName = venderName;
		this.productName = productName;
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

	public String getUserNickname() {
		return userNickname;
	}

	public void setUserNickname(String userNickname) {
		this.userNickname = userNickname;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	@Override
	public String toString() {
		return "JuCakeDesignReviewVo [venderId=" + venderId + ", productId=" + productId + ", cakeDesignId="
				+ cakeDesignId + ", memberId=" + memberId + ", userNickname=" + userNickname + ", type=" + type
				+ ", reviewId=" + reviewId + ", reviewUserId=" + reviewUserId + ", reviewOrderId=" + reviewOrderId
				+ ", reviewRating=" + reviewRating + ", reviewContent=" + reviewContent + ", reviewImageUrl="
				+ reviewImageUrl + ", reviewCreatedAt=" + reviewCreatedAt + ", venderName=" + venderName
				+ ", productName=" + productName + "]";
	}

}

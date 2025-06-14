package com.javaex.review.model;

public class ReviewAnalysisVo {

	private int productId;
	private int reviewId;
	private int reviewUserId;
	private int reviewOrderId;
	private int reviewRating;
	private String reviewContent;
	private String reviewImageUrl;
	private String reviewCreatedAt;

	public ReviewAnalysisVo() {

	}

	public ReviewAnalysisVo(int productId, int reviewId, int reviewUserId, int reviewOrderId, int reviewRating,
			String reviewContent, String reviewImageUrl, String reviewCreatedAt) {
		super();
		this.productId = productId;
		this.reviewId = reviewId;
		this.reviewUserId = reviewUserId;
		this.reviewOrderId = reviewOrderId;
		this.reviewRating = reviewRating;
		this.reviewContent = reviewContent;
		this.reviewImageUrl = reviewImageUrl;
		this.reviewCreatedAt = reviewCreatedAt;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
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

	@Override
	public String toString() {
		return "JuReviewAnalysisVo [productId=" + productId + ", reviewId=" + reviewId + ", reviewUserId="
				+ reviewUserId + ", reviewOrderId=" + reviewOrderId + ", reviewRating=" + reviewRating
				+ ", reviewContent=" + reviewContent + ", reviewImageUrl=" + reviewImageUrl + ", reviewCreatedAt="
				+ reviewCreatedAt + "]";
	}

}

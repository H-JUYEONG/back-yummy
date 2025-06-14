package com.javaex.review.model;

import java.util.List;

public class DdrReviewVo {

	private int reviewId;
	private int reviewUserId;
	private int reviewOrderId;
	private int productId;
	private int reviewRating;
	private String reviewContent;
	private String reviewImageUrl;
	private String reviewCreatedAt;
	private String authorNickname; // 리뷰 작성자 이름
	private int hasReviewed; // 리뷰 작성 여부 확인용
	private int hasPurchased; // 구매했었는지 확인
	private double averageRating;
	private int totalReviews;
	private int ratingCounts5;
	private int ratingCounts4;
	private int ratingCounts3;
	private int ratingCounts2;
	private int ratingCounts1;
	private List<DdrReviewReplyVo> replies;

	public DdrReviewVo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DdrReviewVo(int reviewId, int reviewUserId, int reviewOrderId, int productId, int reviewRating,
			String reviewContent, String reviewImageUrl, String reviewCreatedAt, String authorNickname, int hasReviewed,
			int hasPurchased, double averageRating, int totalReviews, int ratingCounts5, int ratingCounts4,
			int ratingCounts3, int ratingCounts2, int ratingCounts1, List<DdrReviewReplyVo> replies) {
		super();
		this.reviewId = reviewId;
		this.reviewUserId = reviewUserId;
		this.reviewOrderId = reviewOrderId;
		this.productId = productId;
		this.reviewRating = reviewRating;
		this.reviewContent = reviewContent;
		this.reviewImageUrl = reviewImageUrl;
		this.reviewCreatedAt = reviewCreatedAt;
		this.authorNickname = authorNickname;
		this.hasReviewed = hasReviewed;
		this.hasPurchased = hasPurchased;
		this.averageRating = averageRating;
		this.totalReviews = totalReviews;
		this.ratingCounts5 = ratingCounts5;
		this.ratingCounts4 = ratingCounts4;
		this.ratingCounts3 = ratingCounts3;
		this.ratingCounts2 = ratingCounts2;
		this.ratingCounts1 = ratingCounts1;
		this.replies = replies;
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

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
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

	public String getAuthorNickname() {
		return authorNickname;
	}

	public void setAuthorNickname(String authorNickname) {
		this.authorNickname = authorNickname;
	}

	public int getHasReviewed() {
		return hasReviewed;
	}

	public void setHasReviewed(int hasReviewed) {
		this.hasReviewed = hasReviewed;
	}

	public int getHasPurchased() {
		return hasPurchased;
	}

	public void setHasPurchased(int hasPurchased) {
		this.hasPurchased = hasPurchased;
	}

	public double getAverageRating() {
		return averageRating;
	}

	public void setAverageRating(double averageRating) {
		this.averageRating = averageRating;
	}

	public int getTotalReviews() {
		return totalReviews;
	}

	public void setTotalReviews(int totalReviews) {
		this.totalReviews = totalReviews;
	}

	public int getRatingCounts5() {
		return ratingCounts5;
	}

	public void setRatingCounts5(int ratingCounts5) {
		this.ratingCounts5 = ratingCounts5;
	}

	public int getRatingCounts4() {
		return ratingCounts4;
	}

	public void setRatingCounts4(int ratingCounts4) {
		this.ratingCounts4 = ratingCounts4;
	}

	public int getRatingCounts3() {
		return ratingCounts3;
	}

	public void setRatingCounts3(int ratingCounts3) {
		this.ratingCounts3 = ratingCounts3;
	}

	public int getRatingCounts2() {
		return ratingCounts2;
	}

	public void setRatingCounts2(int ratingCounts2) {
		this.ratingCounts2 = ratingCounts2;
	}

	public int getRatingCounts1() {
		return ratingCounts1;
	}

	public void setRatingCounts1(int ratingCounts1) {
		this.ratingCounts1 = ratingCounts1;
	}

	public List<DdrReviewReplyVo> getReplies() {
		return replies;
	}

	public void setReplies(List<DdrReviewReplyVo> replies) {
		this.replies = replies;
	}

	@Override
	public String toString() {
		return "DdrReviewVo [reviewId=" + reviewId + ", reviewUserId=" + reviewUserId + ", reviewOrderId="
				+ reviewOrderId + ", productId=" + productId + ", reviewRating=" + reviewRating + ", reviewContent="
				+ reviewContent + ", reviewImageUrl=" + reviewImageUrl + ", reviewCreatedAt=" + reviewCreatedAt
				+ ", authorNickname=" + authorNickname + ", hasReviewed=" + hasReviewed + ", hasPurchased="
				+ hasPurchased + ", averageRating=" + averageRating + ", totalReviews=" + totalReviews
				+ ", ratingCounts5=" + ratingCounts5 + ", ratingCounts4=" + ratingCounts4 + ", ratingCounts3="
				+ ratingCounts3 + ", ratingCounts2=" + ratingCounts2 + ", ratingCounts1=" + ratingCounts1 + ", replies="
				+ replies + "]";
	}

}
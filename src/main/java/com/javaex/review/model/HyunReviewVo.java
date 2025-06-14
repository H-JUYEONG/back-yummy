package com.javaex.review.model;

import org.joda.time.LocalDateTime;

public class HyunReviewVo {
	private int reviewId;
	private int reviewUserId;
	private int reviewOrderId;
	private int productId;
	private int reviewRating;
	private String reviewContent;
	private LocalDateTime reviewCreatedAt;
	private int replyId;
	private String reviewStatus;
	private String reason;
	private LocalDateTime reportCreatedAt;
	private String link; // 게시물 링크
	private String userNickname;

	public HyunReviewVo() {
		super();
	}

	public HyunReviewVo(int reviewId, int reviewUserId, int reviewOrderId, int productId, int reviewRating,
			String reviewContent, LocalDateTime reviewCreatedAt, int replyId, String reviewStatus, String reason,
			LocalDateTime reportCreatedAt, String link, String userNickname) {
		super();
		this.reviewId = reviewId;
		this.reviewUserId = reviewUserId;
		this.reviewOrderId = reviewOrderId;
		this.productId = productId;
		this.reviewRating = reviewRating;
		this.reviewContent = reviewContent;
		this.reviewCreatedAt = reviewCreatedAt;
		this.replyId = replyId;
		this.reviewStatus = reviewStatus;
		this.reason = reason;
		this.reportCreatedAt = reportCreatedAt;
		this.link = link;
		this.userNickname = userNickname;
	}

	public String getUserNickname() {
		return userNickname;
	}

	public void setUserNickname(String userNickname) {
		this.userNickname = userNickname;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public LocalDateTime getReportCreatedAt() {
		return reportCreatedAt;
	}

	public void setReportCreatedAt(LocalDateTime reportCreatedAt) {
		this.reportCreatedAt = reportCreatedAt;
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

	public LocalDateTime getReviewCreatedAt() {
		return reviewCreatedAt;
	}

	public void setReviewCreatedAt(LocalDateTime reviewCreatedAt) {
		this.reviewCreatedAt = reviewCreatedAt;
	}

	public int getReplyId() {
		return replyId;
	}

	public void setReplyId(int replyId) {
		this.replyId = replyId;
	}

	public String getReviewStatus() {
		return reviewStatus;
	}

	public void setReviewStatus(String reviewStatus) {
		this.reviewStatus = reviewStatus;
	}

	@Override
	public String toString() {
		return "HyunReviewVo [reviewId=" + reviewId + ", reviewUserId=" + reviewUserId + ", reviewOrderId="
				+ reviewOrderId + ", productId=" + productId + ", reviewRating=" + reviewRating + ", reviewContent="
				+ reviewContent + ", reviewCreatedAt=" + reviewCreatedAt + ", replyId=" + replyId + ", reviewStatus="
				+ reviewStatus + ", reason=" + reason + ", reportCreatedAt=" + reportCreatedAt + ", link=" + link
				+ ", userNickname=" + userNickname + "]";
	}

}

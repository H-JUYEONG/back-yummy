package com.javaex.review.model;

public class DdrReviewReplyVo {
	private int replyId;
	private int reviewId;
	private int replyVenderId;
	private String replyContent;
	private String replyCreatedAt;
	private String venderName;

	public DdrReviewReplyVo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DdrReviewReplyVo(int replyId, int reviewId, int replyVenderId, String replyContent, String replyCreatedAt,
			String venderName) {
		super();
		this.replyId = replyId;
		this.reviewId = reviewId;
		this.replyVenderId = replyVenderId;
		this.replyContent = replyContent;
		this.replyCreatedAt = replyCreatedAt;
		this.venderName = venderName;
	}

	public int getReplyId() {
		return replyId;
	}

	public void setReplyId(int replyId) {
		this.replyId = replyId;
	}

	public int getReviewId() {
		return reviewId;
	}

	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}

	public int getReplyVenderId() {
		return replyVenderId;
	}

	public void setReplyVenderId(int replyVenderId) {
		this.replyVenderId = replyVenderId;
	}

	public String getReplyContent() {
		return replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

	public String getReplyCreatedAt() {
		return replyCreatedAt;
	}

	public void setReplyCreatedAt(String replyCreatedAt) {
		this.replyCreatedAt = replyCreatedAt;
	}

	public String getVenderName() {
		return venderName;
	}

	public void setVenderName(String venderName) {
		this.venderName = venderName;
	}

	@Override
	public String toString() {
		return "DdrReviewReplyVo [replyId=" + replyId + ", reviewId=" + reviewId + ", replyVenderId=" + replyVenderId
				+ ", replyContent=" + replyContent + ", replyCreatedAt=" + replyCreatedAt + ", venderName=" + venderName
				+ "]";
	}
}

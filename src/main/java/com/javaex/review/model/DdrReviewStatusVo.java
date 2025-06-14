package com.javaex.review.model;

public class DdrReviewStatusVo {
	private int orderId;
	private int productId;
	private int userId;
	 private boolean hasReview;
	 private boolean canReview;
	public DdrReviewStatusVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DdrReviewStatusVo(int orderId, int productId, int userId, boolean hasReview, boolean canReview) {
		super();
		this.orderId = orderId;
		this.productId = productId;
		this.userId = userId;
		this.hasReview = hasReview;
		this.canReview = canReview;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public boolean isHasReview() {
		return hasReview;
	}
	public void setHasReview(boolean hasReview) {
		this.hasReview = hasReview;
	}
	public boolean isCanReview() {
		return canReview;
	}
	public void setCanReview(boolean canReview) {
		this.canReview = canReview;
	}
	@Override
	public String toString() {
		return "DdrReviewStatusVo [orderId=" + orderId + ", productId=" + productId + ", userId=" + userId
				+ ", hasReview=" + hasReview + ", canReview=" + canReview + "]";
	}

}
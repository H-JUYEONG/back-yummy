package com.javaex.mypage.model;

public class DdrMypageOrderStatusCountVo {
	private int paymentCompletedCount;
	private int inProductionCount;
	private int productionCompletedCount;
	private int deliveryCount;
	private int completedCount;

	public DdrMypageOrderStatusCountVo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DdrMypageOrderStatusCountVo(int paymentCompletedCount, int inProductionCount, int productionCompletedCount,
			int deliveryCount, int completedCount) {
		super();
		this.paymentCompletedCount = paymentCompletedCount;
		this.inProductionCount = inProductionCount;
		this.productionCompletedCount = productionCompletedCount;
		this.deliveryCount = deliveryCount;
		this.completedCount = completedCount;
	}

	public int getPaymentCompletedCount() {
		return paymentCompletedCount;
	}

	public void setPaymentCompletedCount(int paymentCompletedCount) {
		this.paymentCompletedCount = paymentCompletedCount;
	}

	public int getInProductionCount() {
		return inProductionCount;
	}

	public void setInProductionCount(int inProductionCount) {
		this.inProductionCount = inProductionCount;
	}

	public int getProductionCompletedCount() {
		return productionCompletedCount;
	}

	public void setProductionCompletedCount(int productionCompletedCount) {
		this.productionCompletedCount = productionCompletedCount;
	}

	public int getDeliveryCount() {
		return deliveryCount;
	}

	public void setDeliveryCount(int deliveryCount) {
		this.deliveryCount = deliveryCount;
	}

	public int getCompletedCount() {
		return completedCount;
	}

	public void setCompletedCount(int completedCount) {
		this.completedCount = completedCount;
	}

	@Override
	public String toString() {
		return "DdrMypageOrderStatusCountVo [paymentCompletedCount=" + paymentCompletedCount + ", inProductionCount="
				+ inProductionCount + ", productionCompletedCount=" + productionCompletedCount + ", deliveryCount="
				+ deliveryCount + ", completedCount=" + completedCount + "]";
	}

}

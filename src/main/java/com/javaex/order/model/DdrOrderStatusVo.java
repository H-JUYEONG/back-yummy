package com.javaex.order.model;

public class DdrOrderStatusVo {

	private int orderId;
	private String status;

	public DdrOrderStatusVo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public DdrOrderStatusVo(int orderId, String status) {
		super();
		this.orderId = orderId;
		this.status = status;
	}

	@Override
	public String toString() {
		return "DdrOrderStatusVo [orderId=" + orderId + ", status=" + status + "]";
	}
}

package com.javaex.order.model;

public class JuOrderVo {

	private int orderId;
	private int totalPrice;
	private String orderDate;

	public JuOrderVo() {

	}

	public JuOrderVo(int orderId, int totalPrice, String orderDate) {
		this.orderId = orderId;
		this.totalPrice = totalPrice;
		this.orderDate = orderDate;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	@Override
	public String toString() {
		return "JuOrderVo [orderId=" + orderId + ", totalPrice=" + totalPrice + ", orderDate=" + orderDate + "]";
	}

}

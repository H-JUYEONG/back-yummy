package com.javaex.mypage.model;

public class DdrMypageOrderVo {

	private int id;
	private String date;
	private String productName;
	private String orderStatus;
	private int productId;
	private String statusMessage;
	private String actions;
	private int venderId;

	public DdrMypageOrderVo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DdrMypageOrderVo(int id, String date, String productName, String orderStatus, int productId,
			String statusMessage, String actions, int venderId) {
		super();
		this.id = id;
		this.date = date;
		this.productName = productName;
		this.orderStatus = orderStatus;
		this.productId = productId;
		this.statusMessage = statusMessage;
		this.actions = actions;
		this.venderId = venderId;
	}

	public int getVenderId() {
		return venderId;
	}

	public void setVenderId(int venderId) {
		this.venderId = venderId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}

	public String getActions() {
		return actions;
	}

	public void setActions(String actions) {
		this.actions = actions;
	}

	@Override
	public String toString() {
		return "DdrMypageOrderVo [id=" + id + ", date=" + date + ", productName=" + productName + ", orderStatus="
				+ orderStatus + ", productId=" + productId + ", statusMessage=" + statusMessage + ", actions=" + actions
				+ ", venderId=" + venderId + "]";
	}

}

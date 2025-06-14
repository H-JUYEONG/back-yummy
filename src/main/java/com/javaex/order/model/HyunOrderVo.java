package com.javaex.order.model;

public class HyunOrderVo {

	private int productId;
	private int orderId;
	private int userId;
	private String productName;
	private String orderDate;
	private String desiredDeliveryDate;
	private String desiredDeliveryTime;
	private String desiredPickupDatetime;
	private String desiredPickupTime;
	private String deliveryMethod;
	private String orderStatus;
	private int venderid;
	private String recipientName;
	private int totalPrice;
	private String venderName;

	public HyunOrderVo() {
		super();
	}

	public HyunOrderVo(int productId, int orderId, int userId, String productName, String orderDate,
			String desiredDeliveryDate, String desiredDeliveryTime, String desiredPickupDatetime,
			String desiredPickupTime, String deliveryMethod, String orderStatus, int venderid, String recipientName,
			int totalPrice, String venderName) {
		super();
		this.productId = productId;
		this.orderId = orderId;
		this.userId = userId;
		this.productName = productName;
		this.orderDate = orderDate;
		this.desiredDeliveryDate = desiredDeliveryDate;
		this.desiredDeliveryTime = desiredDeliveryTime;
		this.desiredPickupDatetime = desiredPickupDatetime;
		this.desiredPickupTime = desiredPickupTime;
		this.deliveryMethod = deliveryMethod;
		this.orderStatus = orderStatus;
		this.venderid = venderid;
		this.recipientName = recipientName;
		this.totalPrice = totalPrice;
		this.venderName = venderName;
	}

	public String getRecipientName() {
		return recipientName;
	}

	public void setRecipientName(String recipientName) {
		this.recipientName = recipientName;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getVenderName() {
		return venderName;
	}

	public void setVenderName(String venderName) {
		this.venderName = venderName;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String getDesiredDeliveryDate() {
		return desiredDeliveryDate;
	}

	public void setDesiredDeliveryDate(String desiredDeliveryDate) {
		this.desiredDeliveryDate = desiredDeliveryDate;
	}

	public String getDesiredDeliveryTime() {
		return desiredDeliveryTime;
	}

	public void setDesiredDeliveryTime(String desiredDeliveryTime) {
		this.desiredDeliveryTime = desiredDeliveryTime;
	}

	public String getDesiredPickupDatetime() {
		return desiredPickupDatetime;
	}

	public void setDesiredPickupDatetime(String desiredPickupDatetime) {
		this.desiredPickupDatetime = desiredPickupDatetime;
	}

	public String getDesiredPickupTime() {
		return desiredPickupTime;
	}

	public void setDesiredPickupTime(String desiredPickupTime) {
		this.desiredPickupTime = desiredPickupTime;
	}

	public String getDeliveryMethod() {
		return deliveryMethod;
	}

	public void setDeliveryMethod(String deliveryMethod) {
		this.deliveryMethod = deliveryMethod;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public int getVenderid() {
		return venderid;
	}

	public void setVenderid(int venderid) {
		this.venderid = venderid;
	}

	@Override
	public String toString() {
		return "HyunOrderVo [productId=" + productId + ", orderId=" + orderId + ", userId=" + userId + ", productName="
				+ productName + ", orderDate=" + orderDate + ", desiredDeliveryDate=" + desiredDeliveryDate
				+ ", desiredDeliveryTime=" + desiredDeliveryTime + ", desiredPickupDatetime=" + desiredPickupDatetime
				+ ", desiredPickupTime=" + desiredPickupTime + ", deliveryMethod=" + deliveryMethod + ", orderStatus="
				+ orderStatus + ", venderid=" + venderid + ", recipientName=" + recipientName + ", totalPrice="
				+ totalPrice + ", venderName=" + venderName + "]";
	}

}

package com.javaex.order.model;

import java.math.BigDecimal;

public class HyunOrderDetailVo {
	private int productId;
	private int orderId;
	private int userId;
	private String productName;
	private String deliveryAddress;
	private String desiredDeliveryDate;
	private String desiredDeliveryTime;
	private String desiredPickupDatetime;
	private String desiredPickupTime;
	private String deliveryMethod;
	private String orderStatus;
	private String productType;
	private String cakeSize;
	private String flavorSheet;
	private String flavorCream;
	private String cakeBackgroundColor;
	private String creamPosition;
	private String creamColor;
	private String decorationType;
	private String decorationColor;
	private int totalPrice;
	private String cakeLettering;
	private String plateLettering;
	private String additionalRequests;
	private String productImageUrl;
	private String cakeDesignImageUrl;
	private String orderVideoUrl;
	private String orderPhotoUrl;
	private BigDecimal settlementPrice; // 정산 금액

	public HyunOrderDetailVo() {
		super();
	}

	public HyunOrderDetailVo(int productId, int orderId, int userId, String productName, String deliveryAddress,
			String desiredDeliveryDate, String desiredDeliveryTime, String desiredPickupDatetime,
			String desiredPickupTime, String deliveryMethod, String orderStatus, String productType, String cakeSize,
			String flavorSheet, String flavorCream, String cakeBackgroundColor, String creamPosition, String creamColor,
			String decorationType, String decorationColor, int totalPrice, String cakeLettering, String plateLettering,
			String additionalRequests, String productImageUrl, String cakeDesignImageUrl, String orderVideoUrl,
			String orderPhotoUrl, BigDecimal settlementPrice) {
		super();
		this.productId = productId;
		this.orderId = orderId;
		this.userId = userId;
		this.productName = productName;
		this.deliveryAddress = deliveryAddress;
		this.desiredDeliveryDate = desiredDeliveryDate;
		this.desiredDeliveryTime = desiredDeliveryTime;
		this.desiredPickupDatetime = desiredPickupDatetime;
		this.desiredPickupTime = desiredPickupTime;
		this.deliveryMethod = deliveryMethod;
		this.orderStatus = orderStatus;
		this.productType = productType;
		this.cakeSize = cakeSize;
		this.flavorSheet = flavorSheet;
		this.flavorCream = flavorCream;
		this.cakeBackgroundColor = cakeBackgroundColor;
		this.creamPosition = creamPosition;
		this.creamColor = creamColor;
		this.decorationType = decorationType;
		this.decorationColor = decorationColor;
		this.totalPrice = totalPrice;
		this.cakeLettering = cakeLettering;
		this.plateLettering = plateLettering;
		this.additionalRequests = additionalRequests;
		this.productImageUrl = productImageUrl;
		this.cakeDesignImageUrl = cakeDesignImageUrl;
		this.orderVideoUrl = orderVideoUrl;
		this.orderPhotoUrl = orderPhotoUrl;
		this.settlementPrice = settlementPrice;
	}

	public BigDecimal getSettlementPrice() {
		return settlementPrice;
	}

	public void setSettlementPrice(BigDecimal settlementPrice) {
		this.settlementPrice = settlementPrice;
	}

	public String getOrderVideoUrl() {
		return orderVideoUrl;
	}

	public void setOrderVideoUrl(String orderVideoUrl) {
		this.orderVideoUrl = orderVideoUrl;
	}

	public String getOrderPhotoUrl() {
		return orderPhotoUrl;
	}

	public void setOrderPhotoUrl(String orderPhotoUrl) {
		this.orderPhotoUrl = orderPhotoUrl;
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

	public String getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
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

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getCakeSize() {
		return cakeSize;
	}

	public void setCakeSize(String cakeSize) {
		this.cakeSize = cakeSize;
	}

	public String getFlavorSheet() {
		return flavorSheet;
	}

	public void setFlavorSheet(String flavorSheet) {
		this.flavorSheet = flavorSheet;
	}

	public String getFlavorCream() {
		return flavorCream;
	}

	public void setFlavorCream(String flavorCream) {
		this.flavorCream = flavorCream;
	}

	public String getCakeBackgroundColor() {
		return cakeBackgroundColor;
	}

	public void setCakeBackgroundColor(String cakeBackgroundColor) {
		this.cakeBackgroundColor = cakeBackgroundColor;
	}

	public String getCreamPosition() {
		return creamPosition;
	}

	public void setCreamPosition(String creamPosition) {
		this.creamPosition = creamPosition;
	}

	public String getCreamColor() {
		return creamColor;
	}

	public void setCreamColor(String creamColor) {
		this.creamColor = creamColor;
	}

	public String getDecorationType() {
		return decorationType;
	}

	public void setDecorationType(String decorationType) {
		this.decorationType = decorationType;
	}

	public String getDecorationColor() {
		return decorationColor;
	}

	public void setDecorationColor(String decorationColor) {
		this.decorationColor = decorationColor;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getCakeLettering() {
		return cakeLettering;
	}

	public void setCakeLettering(String cakeLettering) {
		this.cakeLettering = cakeLettering;
	}

	public String getPlateLettering() {
		return plateLettering;
	}

	public void setPlateLettering(String plateLettering) {
		this.plateLettering = plateLettering;
	}

	public String getAdditionalRequests() {
		return additionalRequests;
	}

	public void setAdditionalRequests(String additionalRequests) {
		this.additionalRequests = additionalRequests;
	}

	public String getProductImageUrl() {
		return productImageUrl;
	}

	public void setProductImageUrl(String productImageUrl) {
		this.productImageUrl = productImageUrl;
	}

	public String getCakeDesignImageUrl() {
		return cakeDesignImageUrl;
	}

	public void setCakeDesignImageUrl(String cakeDesignImageUrl) {
		this.cakeDesignImageUrl = cakeDesignImageUrl;
	}

	@Override
	public String toString() {
		return "HyunOrderDetailVo [productId=" + productId + ", orderId=" + orderId + ", userId=" + userId
				+ ", productName=" + productName + ", deliveryAddress=" + deliveryAddress + ", desiredDeliveryDate="
				+ desiredDeliveryDate + ", desiredDeliveryTime=" + desiredDeliveryTime + ", desiredPickupDatetime="
				+ desiredPickupDatetime + ", desiredPickupTime=" + desiredPickupTime + ", deliveryMethod="
				+ deliveryMethod + ", orderStatus=" + orderStatus + ", productType=" + productType + ", cakeSize="
				+ cakeSize + ", flavorSheet=" + flavorSheet + ", flavorCream=" + flavorCream + ", cakeBackgroundColor="
				+ cakeBackgroundColor + ", creamPosition=" + creamPosition + ", creamColor=" + creamColor
				+ ", decorationType=" + decorationType + ", decorationColor=" + decorationColor + ", totalPrice="
				+ totalPrice + ", cakeLettering=" + cakeLettering + ", plateLettering=" + plateLettering
				+ ", additionalRequests=" + additionalRequests + ", productImageUrl=" + productImageUrl
				+ ", cakeDesignImageUrl=" + cakeDesignImageUrl + ", orderVideoUrl=" + orderVideoUrl + ", orderPhotoUrl="
				+ orderPhotoUrl + "]";
	}

}

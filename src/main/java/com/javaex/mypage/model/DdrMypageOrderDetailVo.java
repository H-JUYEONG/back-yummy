package com.javaex.mypage.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

public class DdrMypageOrderDetailVo {

	// DdrOrderVo의 모든 필드를 포함
	private int orderId;
	private int productId;
	private int userId;
	private int venderId;
	private String deliveryMethod;
	private String deliveryAddress;
	private String recipientName;
	private String recipientPhone;
	private String senderPhone;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate desiredPickupDatetime;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate desiredDeliveryDate;

	@JsonFormat(pattern = "HH:mm:ss")
	private String desiredDeliveryTime;

	@JsonFormat(pattern = "HH:mm:ss")
	private String desiredPickupTime;

	private String productType;
	private String cakeSize;
	private String flavorSheet;
	private String flavorCream;
	private String cakeBackgroundColor;
	private String creamPosition;
	private String creamColor;
	private String decorationType;
	private String decorationColor;
	private String category;
	private BigDecimal totalPrice;
	private String cakeLettering;
	private String plateLettering;
	private String additionalRequests;
	private String orderStatus;

	// 추가 필드: 주문 상세에 필요한 정보
	private String orderDate;
	private String productName;
	private String productImage;

	public DdrMypageOrderDetailVo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DdrMypageOrderDetailVo(int orderId, int productId, int userId, int venderId, String deliveryMethod,
			String deliveryAddress, String recipientName, String recipientPhone, String senderPhone,
			LocalDate desiredPickupDatetime, LocalDate desiredDeliveryDate, String desiredDeliveryTime,
			String desiredPickupTime, String productType, String cakeSize, String flavorSheet, String flavorCream,
			String cakeBackgroundColor, String creamPosition, String creamColor, String decorationType,
			String decorationColor, String category, BigDecimal totalPrice, String cakeLettering, String plateLettering,
			String additionalRequests, String orderStatus, String orderDate, String productName, String productImage) {
		super();
		this.orderId = orderId;
		this.productId = productId;
		this.userId = userId;
		this.venderId = venderId;
		this.deliveryMethod = deliveryMethod;
		this.deliveryAddress = deliveryAddress;
		this.recipientName = recipientName;
		this.recipientPhone = recipientPhone;
		this.senderPhone = senderPhone;
		this.desiredPickupDatetime = desiredPickupDatetime;
		this.desiredDeliveryDate = desiredDeliveryDate;
		this.desiredDeliveryTime = desiredDeliveryTime;
		this.desiredPickupTime = desiredPickupTime;
		this.productType = productType;
		this.cakeSize = cakeSize;
		this.flavorSheet = flavorSheet;
		this.flavorCream = flavorCream;
		this.cakeBackgroundColor = cakeBackgroundColor;
		this.creamPosition = creamPosition;
		this.creamColor = creamColor;
		this.decorationType = decorationType;
		this.decorationColor = decorationColor;
		this.category = category;
		this.totalPrice = totalPrice;
		this.cakeLettering = cakeLettering;
		this.plateLettering = plateLettering;
		this.additionalRequests = additionalRequests;
		this.orderStatus = orderStatus;
		this.orderDate = orderDate;
		this.productName = productName;
		this.productImage = productImage;
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

	public String getDeliveryMethod() {
		return deliveryMethod;
	}

	public void setDeliveryMethod(String deliveryMethod) {
		this.deliveryMethod = deliveryMethod;
	}

	public String getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public String getRecipientName() {
		return recipientName;
	}

	public void setRecipientName(String recipientName) {
		this.recipientName = recipientName;
	}

	public String getRecipientPhone() {
		return recipientPhone;
	}

	public void setRecipientPhone(String recipientPhone) {
		this.recipientPhone = recipientPhone;
	}

	public String getSenderPhone() {
		return senderPhone;
	}

	public void setSenderPhone(String senderPhone) {
		this.senderPhone = senderPhone;
	}

	public LocalDate getDesiredPickupDatetime() {
		return desiredPickupDatetime;
	}

	public void setDesiredPickupDatetime(LocalDate desiredPickupDatetime) {
		this.desiredPickupDatetime = desiredPickupDatetime;
	}

	public LocalDate getDesiredDeliveryDate() {
		return desiredDeliveryDate;
	}

	public void setDesiredDeliveryDate(LocalDate desiredDeliveryDate) {
		this.desiredDeliveryDate = desiredDeliveryDate;
	}

	public String getDesiredDeliveryTime() {
		return desiredDeliveryTime;
	}

	public void setDesiredDeliveryTime(String desiredDeliveryTime) {
		this.desiredDeliveryTime = desiredDeliveryTime;
	}

	public String getDesiredPickupTime() {
		return desiredPickupTime;
	}

	public void setDesiredPickupTime(String desiredPickupTime) {
		this.desiredPickupTime = desiredPickupTime;
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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
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

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductImage() {
		return productImage;
	}

	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}

	@Override
	public String toString() {
		return "DdrMypageOrderDetailVo [orderId=" + orderId + ", productId=" + productId + ", userId=" + userId
				+ ", venderId=" + venderId + ", deliveryMethod=" + deliveryMethod + ", deliveryAddress="
				+ deliveryAddress + ", recipientName=" + recipientName + ", recipientPhone=" + recipientPhone
				+ ", senderPhone=" + senderPhone + ", desiredPickupDatetime=" + desiredPickupDatetime
				+ ", desiredDeliveryDate=" + desiredDeliveryDate + ", desiredDeliveryTime=" + desiredDeliveryTime
				+ ", desiredPickupTime=" + desiredPickupTime + ", productType=" + productType + ", cakeSize=" + cakeSize
				+ ", flavorSheet=" + flavorSheet + ", flavorCream=" + flavorCream + ", cakeBackgroundColor="
				+ cakeBackgroundColor + ", creamPosition=" + creamPosition + ", creamColor=" + creamColor
				+ ", decorationType=" + decorationType + ", decorationColor=" + decorationColor + ", category="
				+ category + ", totalPrice=" + totalPrice + ", cakeLettering=" + cakeLettering + ", plateLettering="
				+ plateLettering + ", additionalRequests=" + additionalRequests + ", orderStatus=" + orderStatus
				+ ", orderDate=" + orderDate + ", productName=" + productName + ", productImage=" + productImage + "]";
	}

}
package com.javaex.order.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DdrOrderVo {
	private int orderId;
	private int productId;
	private int userId;
	private int venderId;
	private String paymentOrderId;

	@JsonProperty("paymentKey")
	private String paymentKey; // Toss 결제 승인 키 추가

	@JsonProperty("deliveryMethod")
	private String deliveryMethod;

	@JsonProperty("deliveryAddress")
	private String deliveryAddress;

	@JsonProperty("recipientName")
	private String recipientName;

	@JsonProperty("recipientPhone")
	private String recipientPhone;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate desiredPickupDatetime;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate desiredDeliveryDate;

	@JsonFormat(pattern = "HH:mm:ss")
	private String desiredDeliveryTime; // LocalTime에서 String으로 변경

	@JsonFormat(pattern = "HH:mm:ss")
	private String desiredPickupTime; // LocalTime에서 String으로 변경

	@JsonProperty("productType")
	private String productType;

	@JsonProperty("cakeSize")
	private String cakeSize;

	@JsonProperty("flavorSheet")
	private String flavorSheet;

	@JsonProperty("flavorCream")
	private String flavorCream;

	@JsonProperty("cakeBackgroundColor")
	private String cakeBackgroundColor;

	@JsonProperty("creamPosition")
	private String creamPosition;

	@JsonProperty("creamColor")
	private String creamColor;

	@JsonProperty("decorationType")
	private String decorationType;

	@JsonProperty("decorationColor")
	private String decorationColor;

	@JsonProperty("category")
	private String category;

	@JsonProperty("totalPrice")
	private BigDecimal totalPrice;

	@JsonProperty("cakeLettering")
	private String cakeLettering;

	@JsonProperty("plateLettering")
	private String plateLettering;

	@JsonProperty("additionalRequests")
	private String additionalRequests;

	@JsonProperty("orderStatus")
	private String orderStatus;

	@JsonProperty("식재료")
	private String 식재료;

	@JsonProperty("장식품")
	private String 장식품;

	@JsonProperty("소모품")
	private String 소모품;

	private BigDecimal settlementPrice; // 정산 금액

	public DdrOrderVo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DdrOrderVo(int orderId, int productId, int userId, int venderId, String deliveryMethod,
			String deliveryAddress, String recipientName, String recipientPhone, LocalDate desiredPickupDatetime,
			LocalDate desiredDeliveryDate, String desiredDeliveryTime, String desiredPickupTime, String productType,
			String cakeSize, String flavorSheet, String flavorCream, String cakeBackgroundColor, String creamPosition,
			String creamColor, String decorationType, String decorationColor, String category, BigDecimal totalPrice,
			String cakeLettering, String plateLettering, String additionalRequests, String orderStatus, String 식재료,
			String 장식품, String 소모품, BigDecimal settlementPrice) {
		super();
		this.orderId = orderId;
		this.productId = productId;
		this.userId = userId;
		this.venderId = venderId;
		this.deliveryMethod = deliveryMethod;
		this.deliveryAddress = deliveryAddress;
		this.recipientName = recipientName;
		this.recipientPhone = recipientPhone;
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
		this.식재료 = 식재료;
		this.장식품 = 장식품;
		this.소모품 = 소모품;
		this.settlementPrice = settlementPrice;
	}

	public BigDecimal getSettlementPrice() {
		return settlementPrice;
	}

	public void setSettlementPrice(BigDecimal settlementPrice) {
		this.settlementPrice = settlementPrice;
	}

	public String get장식품() {
		return 장식품;
	}

	public void set장식품(String 장식품) {
		this.장식품 = 장식품;
	}

	public String get식재료() {
		return 식재료;
	}

	public void set식재료(String 식재료) {
		this.식재료 = 식재료;
	}

	public String get소모품() {
		return 소모품;
	}

	public void set소모품(String 소모품) {
		this.소모품 = 소모품;
	}

	public int getVenderId() {
		return venderId;
	}

	public void setVenderId(int venderId) {
		this.venderId = venderId;
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

	public String getPaymentKey() {
		return paymentKey;
	}

	public void setPaymentKey(String paymentKey) {
		this.paymentKey = paymentKey;
	}

	// ✅ Getter & Setter 추가
	public String getPaymentOrderId() {
		return paymentOrderId;
	}

	public void setPaymentOrderId(String paymentOrderId) {
		this.paymentOrderId = paymentOrderId;
	}

	@Override
	public String toString() {
		return "DdrOrderVo [orderId=" + orderId + ", productId=" + productId + ", userId=" + userId + ", venderId="
				+ venderId + ", deliveryMethod=" + deliveryMethod + ", deliveryAddress=" + deliveryAddress
				+ ", recipientName=" + recipientName + ", recipientPhone=" + recipientPhone + ", desiredPickupDatetime="
				+ desiredPickupDatetime + ", desiredDeliveryDate=" + desiredDeliveryDate + ", desiredDeliveryTime="
				+ desiredDeliveryTime + ", desiredPickupTime=" + desiredPickupTime + ", productType=" + productType
				+ ", cakeSize=" + cakeSize + ", flavorSheet=" + flavorSheet + ", flavorCream=" + flavorCream
				+ ", cakeBackgroundColor=" + cakeBackgroundColor + ", creamPosition=" + creamPosition + ", creamColor="
				+ creamColor + ", decorationType=" + decorationType + ", decorationColor=" + decorationColor
				+ ", category=" + category + ", totalPrice=" + totalPrice + ", cakeLettering=" + cakeLettering
				+ ", plateLettering=" + plateLettering + ", additionalRequests=" + additionalRequests + ", orderStatus="
				+ orderStatus + ", 식재료=" + 식재료 + ", 장식품=" + 장식품 + ", 소모품=" + 소모품 + "]";
	}

}
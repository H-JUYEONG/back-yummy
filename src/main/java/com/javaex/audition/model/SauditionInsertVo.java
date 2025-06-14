package com.javaex.audition.model;

public class SauditionInsertVo {
	
	//필드
	private int auditionId;
	private int venderId;
	private int productId;
	
	private String deliveryMethod;	//수령방식
	private String deliveryDate;	//수령일자
	
	private String productType;
	private String cakeSize;
	private String cakeTaste;
	private String category;
	
	private String creamColor;
	private String creamTaste;
	private String creamPosition;
	
	private String backgroundColor;
	
	private String decorationType;
	private String decorationColor;
	
	private String cakeLettering;	//케이크레터링
	private String plateLettering;	//판 레터링
	
	private String applicationRequests;		//소비자 요청사항
	private String shopRequests;			//업체측 가격층정사유
	
	private String orderAmount;		//유저 신청금액
	private String totalPrice;		//최종금액
	
	
	//생성자
	
	public SauditionInsertVo() {
		super();
	}
	
	public SauditionInsertVo(int auditionId, int venderId, int productId, String deliveryMethod, String deliveryDate,
			String productType, String cakeSize, String cakeTaste, String category, String creamColor,
			String creamTaste, String creamPosition, String backgroundColor, String decorationType,
			String decorationColor, String cakeLettering, String plateLettering, String applicationRequests,
			String orderAmount, String totalPrice, String shopRequests) {
		super();
		this.auditionId = auditionId;
		this.venderId = venderId;
		this.productId = productId;
		this.deliveryMethod = deliveryMethod;
		this.deliveryDate = deliveryDate;
		this.productType = productType;
		this.cakeSize = cakeSize;
		this.cakeTaste = cakeTaste;
		this.category = category;
		this.creamColor = creamColor;
		this.creamTaste = creamTaste;
		this.creamPosition = creamPosition;
		this.backgroundColor = backgroundColor;
		this.decorationType = decorationType;
		this.decorationColor = decorationColor;
		this.cakeLettering = cakeLettering;
		this.plateLettering = plateLettering;
		this.applicationRequests = applicationRequests;
		this.orderAmount = orderAmount;
		this.totalPrice = totalPrice;
		this.shopRequests = shopRequests;
	}
	//메서드 gs

	public int getAuditionId() {
		return auditionId;
	}

	public void setAuditionId(int auditionId) {
		this.auditionId = auditionId;
	}

	public int getVenderId() {
		return venderId;
	}

	public void setVenderId(int venderId) {
		this.venderId = venderId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getDeliveryMethod() {
		return deliveryMethod;
	}

	public void setDeliveryMethod(String deliveryMethod) {
		this.deliveryMethod = deliveryMethod;
	}

	public String getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
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

	public String getCakeTaste() {
		return cakeTaste;
	}

	public void setCakeTaste(String cakeTaste) {
		this.cakeTaste = cakeTaste;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getCreamColor() {
		return creamColor;
	}

	public void setCreamColor(String creamColor) {
		this.creamColor = creamColor;
	}

	public String getCreamTaste() {
		return creamTaste;
	}

	public void setCreamTaste(String creamTaste) {
		this.creamTaste = creamTaste;
	}

	public String getCreamPosition() {
		return creamPosition;
	}

	public void setCreamPosition(String creamPosition) {
		this.creamPosition = creamPosition;
	}

	public String getBackgroundColor() {
		return backgroundColor;
	}

	public void setBackgroundColor(String backgroundColor) {
		this.backgroundColor = backgroundColor;
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

	public String getApplicationRequests() {
		return applicationRequests;
	}

	public void setApplicationRequests(String applicationRequests) {
		this.applicationRequests = applicationRequests;
	}

	public String getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(String orderAmount) {
		this.orderAmount = orderAmount;
	}

	public String getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getShopRequests() {
		return shopRequests;
	}

	public void setShopRequests(String shopRequests) {
		this.shopRequests = shopRequests;
	}

	

	//메서드 일반
	@Override
	public String toString() {
		return "SauditionInsertVo [auditionId=" + auditionId + ", venderId=" + venderId + ", productId=" + productId
				+ ", deliveryMethod=" + deliveryMethod + ", deliveryDate=" + deliveryDate + ", productType="
				+ productType + ", cakeSize=" + cakeSize + ", cakeTaste=" + cakeTaste + ", category=" + category
				+ ", creamColor=" + creamColor + ", creamTaste=" + creamTaste + ", creamPosition=" + creamPosition
				+ ", backgroundColor=" + backgroundColor + ", decorationType=" + decorationType + ", decorationColor="
				+ decorationColor + ", cakeLettering=" + cakeLettering + ", plateLettering=" + plateLettering
				+ ", applicationRequests=" + applicationRequests + ", shopRequests=" + shopRequests + ", orderAmount="
				+ orderAmount + ", totalPrice=" + totalPrice + "]";
	}
	

}

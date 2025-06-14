package com.javaex.audition.model;

public class SauditionStatusVo {
	
	//필드
	private int auditionId;
	private int auditionCartId;
	private int venderId;
	
	private String orderName;
	private String price;
	private String deliveryMethod;
	private String deliveryDate;
	
	private String auditionStatus;
	private String isSelected;
	
	//생성자
	public SauditionStatusVo() {
		super();
	}
	
	public SauditionStatusVo(int auditionId, int auditionCartId, int venderId, String orderName, String price,
			String deliveryMethod, String deliveryDate, String auditionStatus, String isSelected) {
		super();
		this.auditionId = auditionId;
		this.auditionCartId = auditionCartId;
		this.venderId = venderId;
		this.orderName = orderName;
		this.price = price;
		this.deliveryMethod = deliveryMethod;
		this.deliveryDate = deliveryDate;
		this.auditionStatus = auditionStatus;
		this.isSelected = isSelected;
	}

	
	//메서드 gs
	
	public int getAuditionId() {
		return auditionId;
	}

	public void setAuditionId(int auditionId) {
		this.auditionId = auditionId;
	}

	public int getAuditionCartId() {
		return auditionCartId;
	}

	public void setAuditionCartId(int auditionCartId) {
		this.auditionCartId = auditionCartId;
	}

	public int getVenderId() {
		return venderId;
	}

	public void setVenderId(int venderId) {
		this.venderId = venderId;
	}

	public String getOrderName() {
		return orderName;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
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

	public String getAuditionStatus() {
		return auditionStatus;
	}

	public void setAuditionStatus(String auditionStatus) {
		this.auditionStatus = auditionStatus;
	}

	public String getIsSelected() {
		return isSelected;
	}

	public void setIsSelected(String isSelected) {
		this.isSelected = isSelected;
	}


	//메서드 일반
	@Override
	public String toString() {
		return "SauditionStatusVo [auditionId=" + auditionId + ", auditionCartId=" + auditionCartId + ", venderId="
				+ venderId + ", orderName=" + orderName + ", price=" + price + ", deliveryMethod=" + deliveryMethod
				+ ", deliveryDate=" + deliveryDate + ", auditionStatus=" + auditionStatus + ", isSelected=" + isSelected
				+ "]";
	}
	
}

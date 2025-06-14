package com.javaex.mypage.model;

public class DdrMypageOrderVideoVo {

	private int id;
    private String date;
    private String productName;
    private String orderVideoUrl;    // 언더바 제거
    private String orderPhotoUrl;    // 언더바 제거
	public DdrMypageOrderVideoVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DdrMypageOrderVideoVo(int id, String date, String productName, String orderVideoUrl, String orderPhotoUrl) {
		super();
		this.id = id;
		this.date = date;
		this.productName = productName;
		this.orderVideoUrl = orderVideoUrl;
		this.orderPhotoUrl = orderPhotoUrl;
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
	@Override
	public String toString() {
		return "DdrMypageOrderVideoVo [id=" + id + ", date=" + date + ", productName=" + productName
				+ ", orderVideoUrl=" + orderVideoUrl + ", orderPhotoUrl=" + orderPhotoUrl + "]";
	}

}
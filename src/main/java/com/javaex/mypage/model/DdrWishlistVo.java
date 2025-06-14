package com.javaex.mypage.model;

public class DdrWishlistVo {

	private int productwishlistId;
	private int productId;
	private int memberId;

	public DdrWishlistVo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DdrWishlistVo(int productwishlistId, int productId, int memberId) {
		super();
		this.productwishlistId = productwishlistId;
		this.productId = productId;
		this.memberId = memberId;
	}

	public int getProductwishlistId() {
		return productwishlistId;
	}

	public void setProductwishlistId(int productwishlistId) {
		this.productwishlistId = productwishlistId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	@Override
	public String toString() {
		return "DdrWishlistVo [productwishlistId=" + productwishlistId + ", productId=" + productId + ", memberId="
				+ memberId + "]";
	}

}
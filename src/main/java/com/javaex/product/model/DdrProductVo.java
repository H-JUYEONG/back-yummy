package com.javaex.product.model;

public class DdrProductVo {
    private int productId;
    private int venderId;
    private String venderBannerUrl;
    private int cakeDesignId;
    private String productName;
    private double productPrice;
    private String productImage1Url;
    private String productImage2Url;
    private String productImage3Url;
    private String productImage4Url;
    private String description; 
    // JOIN 결과 포함할 Vender 정보
    private String venderName;
    private String venderAddress;
    private double latitude;
    private double longitude;
    private String kakaoUrl;
	public DdrProductVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DdrProductVo(int productId, int venderId, String venderBannerUrl, int cakeDesignId, String productName,
			double productPrice, String productImage1Url, String productImage2Url, String productImage3Url,
			String productImage4Url, String description, String venderName, String venderAddress, double latitude,
			double longitude, String kakaoUrl) {
		super();
		this.productId = productId;
		this.venderId = venderId;
		this.venderBannerUrl = venderBannerUrl;
		this.cakeDesignId = cakeDesignId;
		this.productName = productName;
		this.productPrice = productPrice;
		this.productImage1Url = productImage1Url;
		this.productImage2Url = productImage2Url;
		this.productImage3Url = productImage3Url;
		this.productImage4Url = productImage4Url;
		this.description = description;
		this.venderName = venderName;
		this.venderAddress = venderAddress;
		this.latitude = latitude;
		this.longitude = longitude;
		this.kakaoUrl = kakaoUrl;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getVenderId() {
		return venderId;
	}
	public void setVenderId(int venderId) {
		this.venderId = venderId;
	}
	public String getVenderBannerUrl() {
		return venderBannerUrl;
	}
	public void setVenderBannerUrl(String venderBannerUrl) {
		this.venderBannerUrl = venderBannerUrl;
	}
	public int getCakeDesignId() {
		return cakeDesignId;
	}
	public void setCakeDesignId(int cakeDesignId) {
		this.cakeDesignId = cakeDesignId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public double getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}
	public String getProductImage1Url() {
		return productImage1Url;
	}
	public void setProductImage1Url(String productImage1Url) {
		this.productImage1Url = productImage1Url;
	}
	public String getProductImage2Url() {
		return productImage2Url;
	}
	public void setProductImage2Url(String productImage2Url) {
		this.productImage2Url = productImage2Url;
	}
	public String getProductImage3Url() {
		return productImage3Url;
	}
	public void setProductImage3Url(String productImage3Url) {
		this.productImage3Url = productImage3Url;
	}
	public String getProductImage4Url() {
		return productImage4Url;
	}
	public void setProductImage4Url(String productImage4Url) {
		this.productImage4Url = productImage4Url;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getVenderName() {
		return venderName;
	}
	public void setVenderName(String venderName) {
		this.venderName = venderName;
	}
	public String getVenderAddress() {
		return venderAddress;
	}
	public void setVenderAddress(String venderAddress) {
		this.venderAddress = venderAddress;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public String getKakaoUrl() {
		return kakaoUrl;
	}
	public void setKakaoUrl(String kakaoUrl) {
		this.kakaoUrl = kakaoUrl;
	}
	@Override
	public String toString() {
		return "DdrProductVo [productId=" + productId + ", venderId=" + venderId + ", venderBannerUrl="
				+ venderBannerUrl + ", cakeDesignId=" + cakeDesignId + ", productName=" + productName
				+ ", productPrice=" + productPrice + ", productImage1Url=" + productImage1Url + ", productImage2Url="
				+ productImage2Url + ", productImage3Url=" + productImage3Url + ", productImage4Url=" + productImage4Url
				+ ", description=" + description + ", venderName=" + venderName + ", venderAddress=" + venderAddress
				+ ", latitude=" + latitude + ", longitude=" + longitude + ", kakaoUrl=" + kakaoUrl + "]";
	}

}
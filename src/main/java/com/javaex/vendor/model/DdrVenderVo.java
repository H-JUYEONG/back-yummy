package com.javaex.vendor.model;

public class DdrVenderVo {

	private int venderId;// 업체 아이디
	private String venderName;// 업체 이름
	private String venderProfileImageUrl;// 업체 프로필 이미지
	private String venderAddress;// 업체 주소
	private Double latitude;// 위도
	private Double longitude;// 경도
	private String kakaoUrl;// 업체 카카오톡 url
	private String bannerUrl;// 업체 배너 url
	private String venderDescription;// 업체 상세설명
	private int productCount; // 상품 개수
	private int reviewCount; // 리뷰 개수

	public DdrVenderVo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DdrVenderVo(int venderId, String venderName, String venderProfileImageUrl, String venderAddress,
			Double latitude, Double longitude, String kakaoUrl, String bannerUrl, String venderDescription,
			int productCount, int reviewCount) {
		super();
		this.venderId = venderId;
		this.venderName = venderName;
		this.venderProfileImageUrl = venderProfileImageUrl;
		this.venderAddress = venderAddress;
		this.latitude = latitude;
		this.longitude = longitude;
		this.kakaoUrl = kakaoUrl;
		this.bannerUrl = bannerUrl;
		this.venderDescription = venderDescription;
		this.productCount = productCount;
		this.reviewCount = reviewCount;
	}

	public int getVenderId() {
		return venderId;
	}

	public void setVenderId(int venderId) {
		this.venderId = venderId;
	}

	public String getVenderName() {
		return venderName;
	}

	public void setVenderName(String venderName) {
		this.venderName = venderName;
	}

	public String getVenderProfileImageUrl() {
		return venderProfileImageUrl;
	}

	public void setVenderProfileImageUrl(String venderProfileImageUrl) {
		this.venderProfileImageUrl = venderProfileImageUrl;
	}

	public String getVenderAddress() {
		return venderAddress;
	}

	public void setVenderAddress(String venderAddress) {
		this.venderAddress = venderAddress;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public String getKakaoUrl() {
		return kakaoUrl;
	}

	public void setKakaoUrl(String kakaoUrl) {
		this.kakaoUrl = kakaoUrl;
	}

	public String getBannerUrl() {
		return bannerUrl;
	}

	public void setBannerUrl(String bannerUrl) {
		this.bannerUrl = bannerUrl;
	}

	public String getVenderDescription() {
		return venderDescription;
	}

	public void setVenderDescription(String venderDescription) {
		this.venderDescription = venderDescription;
	}

	public int getProductCount() {
		return productCount;
	}

	public void setProductCount(int productCount) {
		this.productCount = productCount;
	}

	public int getReviewCount() {
		return reviewCount;
	}

	public void setReviewCount(int reviewCount) {
		this.reviewCount = reviewCount;
	}

	@Override
	public String toString() {
		return "DdrVenderVo [venderId=" + venderId + ", venderName=" + venderName + ", venderProfileImageUrl="
				+ venderProfileImageUrl + ", venderAddress=" + venderAddress + ", latitude=" + latitude + ", longitude="
				+ longitude + ", kakaoUrl=" + kakaoUrl + ", bannerUrl=" + bannerUrl + ", venderDescription="
				+ venderDescription + ", productCount=" + productCount + ", reviewCount=" + reviewCount + "]";
	}

}

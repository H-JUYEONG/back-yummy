package com.javaex.vendor.model;

public class SvenderVo {
	
	//필드
	private int venderId;	//업체 no값
	private String venderName;	//업체명
	private String venderAddress;	//주소
	private String district;	//구
	private double latitude;	//위도
	private double longitude;	//경도
	private String bannerURL;	//배너사진
	private String profileURL;	//로고사진
	private String kakaoURL;	//카카오url
	private String venderDescription;	//업체설명
	private int shopStatus;
	
	

	
	
	//생성자
	public SvenderVo() {
		super();
	}
	

	public SvenderVo(int venderId, String venderName, String venderAddress, String district, double latitude,
			double longitude, String bannerURL, String profileURL, String kakaoURL, String venderDescription,
			int shopStatus) {
		super();
		this.venderId = venderId;
		this.venderName = venderName;
		this.venderAddress = venderAddress;
		this.district = district;
		this.latitude = latitude;
		this.longitude = longitude;
		this.bannerURL = bannerURL;
		this.profileURL = profileURL;
		this.kakaoURL = kakaoURL;
		this.venderDescription = venderDescription;
		this.shopStatus = shopStatus;
	}




	//메서드 gs
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

	public String getVenderAddress() {
		return venderAddress;
	}

	public void setVenderAddress(String venderAddress) {
		this.venderAddress = venderAddress;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
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

	public String getBannerURL() {
		return bannerURL;
	}

	public void setBannerURL(String bannerURL) {
		this.bannerURL = bannerURL;
	}

	public String getProfileURL() {
		return profileURL;
	}

	public void setProfileURL(String profileURL) {
		this.profileURL = profileURL;
	}

	public String getKakaoURL() {
		return kakaoURL;
	}

	public void setKakaoURL(String kakaoURL) {
		this.kakaoURL = kakaoURL;
	}

	public String getVenderDescription() {
		return venderDescription;
	}

	public void setVenderDescription(String venderDescription) {
		this.venderDescription = venderDescription;
	}


	public int getShopStatus() {
		return shopStatus;
	}


	public void setShopStatus(int shopStatus) {
		this.shopStatus = shopStatus;
	}


	
	@Override
	public String toString() {
		return "SvenderVo [venderId=" + venderId + ", venderName=" + venderName + ", venderAddress=" + venderAddress
				+ ", district=" + district + ", latitude=" + latitude + ", longitude=" + longitude + ", bannerURL="
				+ bannerURL + ", profileURL=" + profileURL + ", kakaoURL=" + kakaoURL + ", venderDescription="
				+ venderDescription + ", shopStatus=" + shopStatus + "]";
	}
	
	

	

	
	
}
	
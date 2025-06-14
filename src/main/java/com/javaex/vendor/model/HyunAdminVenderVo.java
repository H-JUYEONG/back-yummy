package com.javaex.vendor.model;

public class HyunAdminVenderVo {
	private Long memberId; // memberId 필드 추가
	private Long venderId;
	private String venderName;
	private String venderAddress;
	private String venderNumber;
	private String representativeName;
	private String email;
	private String phoneNumber;
	private String createdAt;
	private Double latitude;	//위도
	private Double longitude;	//경도
	private int isActive;
	private String type;
	private String businessLicenseUrl;

	public HyunAdminVenderVo() {
		super();
	}

	public HyunAdminVenderVo(Long venderId, String venderName, String venderAddress, String venderNumber,
			String representativeName, String email, String phoneNumber, String createdAt, Double latitude,
			Double longitude, int isActive, String type) {
		super();
		this.venderId = venderId;
		this.venderName = venderName;
		this.venderAddress = venderAddress;
		this.venderNumber = venderNumber;
		this.representativeName = representativeName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.createdAt = createdAt;
		this.latitude = latitude;
		this.longitude = longitude;
		this.isActive = isActive;
		this.type = type;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public String getBusinessLicenseUrl() {
		return businessLicenseUrl;
	}

	public void setBusinessLicenseUrl(String businessLicenseUrl) {
		this.businessLicenseUrl = businessLicenseUrl;
	}

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getVenderId() {
		return venderId;
	}

	public void setVenderId(Long venderId) {
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

	public String getVenderNumber() {
		return venderNumber;
	}

	public void setVenderNumber(String venderNumber) {
		this.venderNumber = venderNumber;
	}

	public String getRepresentativeName() {
		return representativeName;
	}

	public void setRepresentativeName(String representativeName) {
		this.representativeName = representativeName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
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

	@Override
	public String toString() {
		return "HyunAdminVenderVo [venderId=" + venderId + ", venderName=" + venderName + ", venderAddress="
				+ venderAddress + ", venderNumber=" + venderNumber + ", representativeName=" + representativeName
				+ ", email=" + email + ", phoneNumber=" + phoneNumber + ", createdAt=" + createdAt + ", latitude="
				+ latitude + ", longitude=" + longitude + ", isActive=" + isActive + ", type=" + type + "]";
	}

}

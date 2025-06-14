package com.javaex.user.model;

import org.springframework.web.multipart.MultipartFile;

public class JuUserVo {

	private int member_id;
	private String type;
	private String email;
	private String password_hash;
	private String phone_number;
	private String name;
	private String created_at;
	private boolean is_active;

	private int user_id;
	private String user_nickname;
	private String user_profile_image_url;
	private int user_points;
	private String user_provider;

	private int vender_id;
	private String vender_name; // 상호명
	private String vender_number; // 사업자번호
	private String representative_name; // 대표자명
	private String vender_address;
	private String district;
	private long latitude; // 위도
	private long longitude; // 경도
	private String banner_url;
	private MultipartFile vender_profile_image_url; // 사업자 등록증 이미지 url
	private String kakao_url;
	private String vender_description;
	private String business_license_url;

	private String code;

	public JuUserVo() {

	}

	public JuUserVo(int member_id, String type, String email, String password_hash, String phone_number, String name,
			String created_at, boolean is_active, int user_id, String user_nickname, String user_profile_image_url,
			int user_points, String user_provider, int vender_id, String vender_name, String vender_number,
			String representative_name, String vender_address, String district, long latitude, long longitude,
			String banner_url, MultipartFile vender_profile_image_url, String kakao_url, String vender_description,
			String business_license_url, String code) {
		this.member_id = member_id;
		this.type = type;
		this.email = email;
		this.password_hash = password_hash;
		this.phone_number = phone_number;
		this.name = name;
		this.created_at = created_at;
		this.is_active = is_active;
		this.user_id = user_id;
		this.user_nickname = user_nickname;
		this.user_profile_image_url = user_profile_image_url;
		this.user_points = user_points;
		this.user_provider = user_provider;
		this.vender_id = vender_id;
		this.vender_name = vender_name;
		this.vender_number = vender_number;
		this.representative_name = representative_name;
		this.vender_address = vender_address;
		this.district = district;
		this.latitude = latitude;
		this.longitude = longitude;
		this.banner_url = banner_url;
		this.vender_profile_image_url = vender_profile_image_url;
		this.kakao_url = kakao_url;
		this.vender_description = vender_description;
		this.business_license_url = business_license_url;
		this.code = code;
	}

	public int getMember_id() {
		return member_id;
	}

	public void setMember_id(int member_id) {
		this.member_id = member_id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword_hash() {
		return password_hash;
	}

	public void setPassword_hash(String password_hash) {
		this.password_hash = password_hash;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	public boolean isIs_active() {
		return is_active;
	}

	public void setIs_active(boolean is_active) {
		this.is_active = is_active;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getUser_nickname() {
		return user_nickname;
	}

	public void setUser_nickname(String user_nickname) {
		this.user_nickname = user_nickname;
	}

	public String getUser_profile_image_url() {
		return user_profile_image_url;
	}

	public void setUser_profile_image_url(String user_profile_image_url) {
		this.user_profile_image_url = user_profile_image_url;
	}

	public int getUser_points() {
		return user_points;
	}

	public void setUser_points(int user_points) {
		this.user_points = user_points;
	}

	public String getUser_provider() {
		return user_provider;
	}

	public void setUser_provider(String user_provider) {
		this.user_provider = user_provider;
	}

	public int getVender_id() {
		return vender_id;
	}

	public void setVender_id(int vender_id) {
		this.vender_id = vender_id;
	}

	public String getVender_name() {
		return vender_name;
	}

	public void setVender_name(String vender_name) {
		this.vender_name = vender_name;
	}

	public String getVender_number() {
		return vender_number;
	}

	public void setVender_number(String vender_number) {
		this.vender_number = vender_number;
	}

	public String getRepresentative_name() {
		return representative_name;
	}

	public void setRepresentative_name(String representative_name) {
		this.representative_name = representative_name;
	}

	public String getVender_address() {
		return vender_address;
	}

	public void setVender_address(String vender_address) {
		this.vender_address = vender_address;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public long getLatitude() {
		return latitude;
	}

	public void setLatitude(long latitude) {
		this.latitude = latitude;
	}

	public long getLongitude() {
		return longitude;
	}

	public void setLongitude(long longitude) {
		this.longitude = longitude;
	}

	public String getBanner_url() {
		return banner_url;
	}

	public void setBanner_url(String banner_url) {
		this.banner_url = banner_url;
	}

	public MultipartFile getVender_profile_image_url() {
		return vender_profile_image_url;
	}

	public void setVender_profile_image_url(MultipartFile vender_profile_image_url) {
		this.vender_profile_image_url = vender_profile_image_url;
	}

	public String getKakao_url() {
		return kakao_url;
	}

	public void setKakao_url(String kakao_url) {
		this.kakao_url = kakao_url;
	}

	public String getVender_description() {
		return vender_description;
	}

	public void setVender_description(String vender_description) {
		this.vender_description = vender_description;
	}

	public String getBusiness_license_url() {
		return business_license_url;
	}

	public void setBusiness_license_url(String business_license_url) {
		this.business_license_url = business_license_url;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return "JuUserVo [member_id=" + member_id + ", type=" + type + ", email=" + email + ", password_hash="
				+ password_hash + ", phone_number=" + phone_number + ", name=" + name + ", created_at=" + created_at
				+ ", is_active=" + is_active + ", user_id=" + user_id + ", user_nickname=" + user_nickname
				+ ", user_profile_image_url=" + user_profile_image_url + ", user_points=" + user_points
				+ ", user_provider=" + user_provider + ", vender_id=" + vender_id + ", vender_name=" + vender_name
				+ ", vender_number=" + vender_number + ", representative_name=" + representative_name
				+ ", vender_address=" + vender_address + ", district=" + district + ", latitude=" + latitude
				+ ", longitude=" + longitude + ", banner_url=" + banner_url + ", vender_profile_image_url="
				+ vender_profile_image_url + ", kakao_url=" + kakao_url + ", vender_description=" + vender_description
				+ ", business_license_url=" + business_license_url + ", code=" + code + "]";
	}

}
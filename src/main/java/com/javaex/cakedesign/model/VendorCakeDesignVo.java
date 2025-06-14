package com.javaex.cakedesign.model;

import java.util.List;

public class VendorCakeDesignVo {

	private String type; // 업체, 유저, 어드민
	private String venderName;

	private int cakeDesignId; // 도안 고유 식별자
	private int memberId; // 도안 등록자 ID (유저 또는 업체)
	private String cakeDesignTitle; // 도안 제목
	private String cakeDesignDescription; // 도안 설명
	private String cakeDesignPreferredAge; // 선호하는 연령
	private String cakeDesignRecommendedEvent; // 추천 이벤트
	private String cakeDesignPreferredShape; // 선호하는 케이크 형태
	private String cakeDesignCategory; // 카테고리
	private int cakeDesignViewCount; // 조회수
	private String cakeDesignCreatedAt; // 등록일

	private int cakeDesignImageId; // 이미지 고유 식별자
	private String cakeDesignImageUrl; // 이미지 url

	private String mainImageUrl; // 메인 이미지 URL
	private List<String> subImages; // 서브 이미지 리스트

	private int cakeDesignWishlistId; // 찜 리스트 고유 식별자
	private int cakeDesignWishlistCount; // 찜 수
	private boolean IsFavorited; // 찜 여부 확인

	private String userNickname;
	private boolean cakeDesignVisibility; // 도안 공개 여부

	private int totalCount; // 총 갯수
	private int totalPages; // 전체 페이지 수
	private int currentPage; // 현재 페이지

	private int hashtagMappingId; // 매핑 테이블 고유 식별자
	private int cakeDesignHashtagId; // 해시태그 고유 식별자
	private String cakeDesignHashtagName; // 해시태그 이름

	public VendorCakeDesignVo() {

	}

	public VendorCakeDesignVo(String type, String venderName, int cakeDesignId, int memberId, String cakeDesignTitle,
			String cakeDesignDescription, String cakeDesignPreferredAge, String cakeDesignRecommendedEvent,
			String cakeDesignPreferredShape, String cakeDesignCategory, int cakeDesignViewCount,
			String cakeDesignCreatedAt, int cakeDesignImageId, String cakeDesignImageUrl, String mainImageUrl,
			List<String> subImages, int cakeDesignWishlistId, int cakeDesignWishlistCount, boolean isFavorited,
			String userNickname, boolean cakeDesignVisibility, int totalCount, int totalPages, int currentPage,
			int hashtagMappingId, int cakeDesignHashtagId, String cakeDesignHashtagName) {
		this.type = type;
		this.venderName = venderName;
		this.cakeDesignId = cakeDesignId;
		this.memberId = memberId;
		this.cakeDesignTitle = cakeDesignTitle;
		this.cakeDesignDescription = cakeDesignDescription;
		this.cakeDesignPreferredAge = cakeDesignPreferredAge;
		this.cakeDesignRecommendedEvent = cakeDesignRecommendedEvent;
		this.cakeDesignPreferredShape = cakeDesignPreferredShape;
		this.cakeDesignCategory = cakeDesignCategory;
		this.cakeDesignViewCount = cakeDesignViewCount;
		this.cakeDesignCreatedAt = cakeDesignCreatedAt;
		this.cakeDesignImageId = cakeDesignImageId;
		this.cakeDesignImageUrl = cakeDesignImageUrl;
		this.mainImageUrl = mainImageUrl;
		this.subImages = subImages;
		this.cakeDesignWishlistId = cakeDesignWishlistId;
		this.cakeDesignWishlistCount = cakeDesignWishlistCount;
		IsFavorited = isFavorited;
		this.userNickname = userNickname;
		this.cakeDesignVisibility = cakeDesignVisibility;
		this.totalCount = totalCount;
		this.totalPages = totalPages;
		this.currentPage = currentPage;
		this.hashtagMappingId = hashtagMappingId;
		this.cakeDesignHashtagId = cakeDesignHashtagId;
		this.cakeDesignHashtagName = cakeDesignHashtagName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getVenderName() {
		return venderName;
	}

	public void setVenderName(String venderName) {
		this.venderName = venderName;
	}

	public int getCakeDesignId() {
		return cakeDesignId;
	}

	public void setCakeDesignId(int cakeDesignId) {
		this.cakeDesignId = cakeDesignId;
	}

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public String getCakeDesignTitle() {
		return cakeDesignTitle;
	}

	public void setCakeDesignTitle(String cakeDesignTitle) {
		this.cakeDesignTitle = cakeDesignTitle;
	}

	public String getCakeDesignDescription() {
		return cakeDesignDescription;
	}

	public void setCakeDesignDescription(String cakeDesignDescription) {
		this.cakeDesignDescription = cakeDesignDescription;
	}

	public String getCakeDesignPreferredAge() {
		return cakeDesignPreferredAge;
	}

	public void setCakeDesignPreferredAge(String cakeDesignPreferredAge) {
		this.cakeDesignPreferredAge = cakeDesignPreferredAge;
	}

	public String getCakeDesignRecommendedEvent() {
		return cakeDesignRecommendedEvent;
	}

	public void setCakeDesignRecommendedEvent(String cakeDesignRecommendedEvent) {
		this.cakeDesignRecommendedEvent = cakeDesignRecommendedEvent;
	}

	public String getCakeDesignPreferredShape() {
		return cakeDesignPreferredShape;
	}

	public void setCakeDesignPreferredShape(String cakeDesignPreferredShape) {
		this.cakeDesignPreferredShape = cakeDesignPreferredShape;
	}

	public String getCakeDesignCategory() {
		return cakeDesignCategory;
	}

	public void setCakeDesignCategory(String cakeDesignCategory) {
		this.cakeDesignCategory = cakeDesignCategory;
	}

	public int getCakeDesignViewCount() {
		return cakeDesignViewCount;
	}

	public void setCakeDesignViewCount(int cakeDesignViewCount) {
		this.cakeDesignViewCount = cakeDesignViewCount;
	}

	public String getCakeDesignCreatedAt() {
		return cakeDesignCreatedAt;
	}

	public void setCakeDesignCreatedAt(String cakeDesignCreatedAt) {
		this.cakeDesignCreatedAt = cakeDesignCreatedAt;
	}

	public int getCakeDesignImageId() {
		return cakeDesignImageId;
	}

	public void setCakeDesignImageId(int cakeDesignImageId) {
		this.cakeDesignImageId = cakeDesignImageId;
	}

	public String getCakeDesignImageUrl() {
		return cakeDesignImageUrl;
	}

	public void setCakeDesignImageUrl(String cakeDesignImageUrl) {
		this.cakeDesignImageUrl = cakeDesignImageUrl;
	}

	public String getMainImageUrl() {
		return mainImageUrl;
	}

	public void setMainImageUrl(String mainImageUrl) {
		this.mainImageUrl = mainImageUrl;
	}

	public List<String> getSubImages() {
		return subImages;
	}

	public void setSubImages(List<String> subImages) {
		this.subImages = subImages;
	}

	public int getCakeDesignWishlistId() {
		return cakeDesignWishlistId;
	}

	public void setCakeDesignWishlistId(int cakeDesignWishlistId) {
		this.cakeDesignWishlistId = cakeDesignWishlistId;
	}

	public int getCakeDesignWishlistCount() {
		return cakeDesignWishlistCount;
	}

	public void setCakeDesignWishlistCount(int cakeDesignWishlistCount) {
		this.cakeDesignWishlistCount = cakeDesignWishlistCount;
	}

	public boolean isIsFavorited() {
		return IsFavorited;
	}

	public void setIsFavorited(boolean isFavorited) {
		IsFavorited = isFavorited;
	}

	public String getUserNickname() {
		return userNickname;
	}

	public void setUserNickname(String userNickname) {
		this.userNickname = userNickname;
	}

	public boolean isCakeDesignVisibility() {
		return cakeDesignVisibility;
	}

	public void setCakeDesignVisibility(boolean cakeDesignVisibility) {
		this.cakeDesignVisibility = cakeDesignVisibility;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getHashtagMappingId() {
		return hashtagMappingId;
	}

	public void setHashtagMappingId(int hashtagMappingId) {
		this.hashtagMappingId = hashtagMappingId;
	}

	public int getCakeDesignHashtagId() {
		return cakeDesignHashtagId;
	}

	public void setCakeDesignHashtagId(int cakeDesignHashtagId) {
		this.cakeDesignHashtagId = cakeDesignHashtagId;
	}

	public String getCakeDesignHashtagName() {
		return cakeDesignHashtagName;
	}

	public void setCakeDesignHashtagName(String cakeDesignHashtagName) {
		this.cakeDesignHashtagName = cakeDesignHashtagName;
	}

	@Override
	public String toString() {
		return "VendorCakeDesignVo [type=" + type + ", venderName=" + venderName + ", cakeDesignId=" + cakeDesignId
				+ ", memberId=" + memberId + ", cakeDesignTitle=" + cakeDesignTitle + ", cakeDesignDescription="
				+ cakeDesignDescription + ", cakeDesignPreferredAge=" + cakeDesignPreferredAge
				+ ", cakeDesignRecommendedEvent=" + cakeDesignRecommendedEvent + ", cakeDesignPreferredShape="
				+ cakeDesignPreferredShape + ", cakeDesignCategory=" + cakeDesignCategory + ", cakeDesignViewCount="
				+ cakeDesignViewCount + ", cakeDesignCreatedAt=" + cakeDesignCreatedAt + ", cakeDesignImageId="
				+ cakeDesignImageId + ", cakeDesignImageUrl=" + cakeDesignImageUrl + ", mainImageUrl=" + mainImageUrl
				+ ", subImages=" + subImages + ", cakeDesignWishlistId=" + cakeDesignWishlistId
				+ ", cakeDesignWishlistCount=" + cakeDesignWishlistCount + ", IsFavorited=" + IsFavorited
				+ ", userNickname=" + userNickname + ", cakeDesignVisibility=" + cakeDesignVisibility + ", totalCount="
				+ totalCount + ", totalPages=" + totalPages + ", currentPage=" + currentPage + ", hashtagMappingId="
				+ hashtagMappingId + ", cakeDesignHashtagId=" + cakeDesignHashtagId + ", cakeDesignHashtagName="
				+ cakeDesignHashtagName + "]";
	}

}

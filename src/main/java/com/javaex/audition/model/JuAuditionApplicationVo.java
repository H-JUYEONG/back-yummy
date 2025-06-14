package com.javaex.audition.model;

public class JuAuditionApplicationVo {

	private int auditionApplicationId; // 오디션 신청글 고유 식별자
	private int memberId; // 회원 고유ID
	private int userId; // 오디션 신청글을 작성한 유저 ID
	private String userNickname; // 유저 닉네임
	private String auditionApplicationTitle; // 오디션 신청글 제목
	private String auditionApplicationSize; // 케이크 사이즈
	private int expectedPrice; // 유저가 예상하는 가격
	private String cakeLettering; // 케이크 위 레터링
	private String plateLettering; // 케이크 판 레터링
	private String deliveryMethod; // 수령 방법 (픽업, 퀵배송)
	private String deliveryAddress; // 배송 주소
	private String recipientName; // 받는 사람 이름
	private String recipientPhone; // 받는 사람 연락처
	private String desiredDate; // 희망 날짜(픽업, 배송 공통부분이므로 한개 사용)
	private String desiredTime; // 희망 시간(픽업, 배송 공통부분이므로 한개 사용)
	private String region; // 주문 희망 구
	private String additionalRequests; // 요청 사항
	private int designId; // 도안 ID
	private String imageUrl; // 오디션 신청글과 관련된 이미지 (S3 경로 등)
	private String status; // 오디션 상태 (진행 중, 완료)
	private String createdAt; // 오디션 신청글 생성일
	private int participationCount; // 참여중인 업체 개수
	private int auditionViewCount; // 조회수

	private int totalCount; // 총 갯수
	private int totalPages; // 전체 페이지 수
	private int currentPage; // 현재 페이지

	public JuAuditionApplicationVo() {

	}

	public JuAuditionApplicationVo(int auditionApplicationId, int memberId, int userId, String userNickname,
			String auditionApplicationTitle, String auditionApplicationSize, int expectedPrice, String cakeLettering,
			String plateLettering, String deliveryMethod, String deliveryAddress, String recipientName,
			String recipientPhone, String desiredDate, String desiredTime, String region, String additionalRequests,
			int designId, String imageUrl, String status, String createdAt, int participationCount,
			int auditionViewCount, int totalCount, int totalPages, int currentPage) {
		this.auditionApplicationId = auditionApplicationId;
		this.memberId = memberId;
		this.userId = userId;
		this.userNickname = userNickname;
		this.auditionApplicationTitle = auditionApplicationTitle;
		this.auditionApplicationSize = auditionApplicationSize;
		this.expectedPrice = expectedPrice;
		this.cakeLettering = cakeLettering;
		this.plateLettering = plateLettering;
		this.deliveryMethod = deliveryMethod;
		this.deliveryAddress = deliveryAddress;
		this.recipientName = recipientName;
		this.recipientPhone = recipientPhone;
		this.desiredDate = desiredDate;
		this.desiredTime = desiredTime;
		this.region = region;
		this.additionalRequests = additionalRequests;
		this.designId = designId;
		this.imageUrl = imageUrl;
		this.status = status;
		this.createdAt = createdAt;
		this.participationCount = participationCount;
		this.auditionViewCount = auditionViewCount;
		this.totalCount = totalCount;
		this.totalPages = totalPages;
		this.currentPage = currentPage;
	}

	public int getAuditionApplicationId() {
		return auditionApplicationId;
	}

	public void setAuditionApplicationId(int auditionApplicationId) {
		this.auditionApplicationId = auditionApplicationId;
	}

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserNickname() {
		return userNickname;
	}

	public void setUserNickname(String userNickname) {
		this.userNickname = userNickname;
	}

	public String getAuditionApplicationTitle() {
		return auditionApplicationTitle;
	}

	public void setAuditionApplicationTitle(String auditionApplicationTitle) {
		this.auditionApplicationTitle = auditionApplicationTitle;
	}

	public String getAuditionApplicationSize() {
		return auditionApplicationSize;
	}

	public void setAuditionApplicationSize(String auditionApplicationSize) {
		this.auditionApplicationSize = auditionApplicationSize;
	}

	public int getExpectedPrice() {
		return expectedPrice;
	}

	public void setExpectedPrice(int expectedPrice) {
		this.expectedPrice = expectedPrice;
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

	public String getDeliveryMethod() {
		return deliveryMethod;
	}

	public void setDeliveryMethod(String deliveryMethod) {
		this.deliveryMethod = deliveryMethod;
	}

	public String getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public String getRecipientName() {
		return recipientName;
	}

	public void setRecipientName(String recipientName) {
		this.recipientName = recipientName;
	}

	public String getRecipientPhone() {
		return recipientPhone;
	}

	public void setRecipientPhone(String recipientPhone) {
		this.recipientPhone = recipientPhone;
	}

	public String getDesiredDate() {
		return desiredDate;
	}

	public void setDesiredDate(String desiredDate) {
		this.desiredDate = desiredDate;
	}

	public String getDesiredTime() {
		return desiredTime;
	}

	public void setDesiredTime(String desiredTime) {
		this.desiredTime = desiredTime;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getAdditionalRequests() {
		return additionalRequests;
	}

	public void setAdditionalRequests(String additionalRequests) {
		this.additionalRequests = additionalRequests;
	}

	public int getDesignId() {
		return designId;
	}

	public void setDesignId(int designId) {
		this.designId = designId;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public int getParticipationCount() {
		return participationCount;
	}

	public void setParticipationCount(int participationCount) {
		this.participationCount = participationCount;
	}

	public int getAuditionViewCount() {
		return auditionViewCount;
	}

	public void setAuditionViewCount(int auditionViewCount) {
		this.auditionViewCount = auditionViewCount;
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

	@Override
	public String toString() {
		return "JuAuditionApplicationVo [auditionApplicationId=" + auditionApplicationId + ", memberId=" + memberId
				+ ", userId=" + userId + ", userNickname=" + userNickname + ", auditionApplicationTitle="
				+ auditionApplicationTitle + ", auditionApplicationSize=" + auditionApplicationSize + ", expectedPrice="
				+ expectedPrice + ", cakeLettering=" + cakeLettering + ", plateLettering=" + plateLettering
				+ ", deliveryMethod=" + deliveryMethod + ", deliveryAddress=" + deliveryAddress + ", recipientName="
				+ recipientName + ", recipientPhone=" + recipientPhone + ", desiredDate=" + desiredDate
				+ ", desiredTime=" + desiredTime + ", region=" + region + ", additionalRequests=" + additionalRequests
				+ ", designId=" + designId + ", imageUrl=" + imageUrl + ", status=" + status + ", createdAt="
				+ createdAt + ", participationCount=" + participationCount + ", auditionViewCount=" + auditionViewCount
				+ ", totalCount=" + totalCount + ", totalPages=" + totalPages + ", currentPage=" + currentPage + "]";
	}

}

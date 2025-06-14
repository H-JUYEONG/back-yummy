package com.javaex.user.model;

public class JuAnniversaryVo {

	private int anniversaryId; // 기념일 고유 식별자 ID
	private int userId; // 유저 ID
	private String anniversaryName; // 기념일 이름
	private String anniversaryDate; // 기념일 날짜
	private boolean isNotificationEnabled; // 알림 활성화 여부
	private String notificationDate; // 알림 보낼 예정일(기념일 일주일 전)
	private String createdAt; // 기념일 정보 생성일

	private int smsId; // 알림 고유 식별자
	private String name;
	private String phone; // 수진자 전화번호
	private String message; // 발송된 메세지 내용
	private String status; // 발송 상태
	private String sentAt; // 알림 발송 일시

	public JuAnniversaryVo() {

	}

	public JuAnniversaryVo(int anniversaryId, int userId, String anniversaryName, String anniversaryDate,
			boolean isNotificationEnabled, String notificationDate, String createdAt, int smsId, String name,
			String phone, String message, String status, String sentAt) {
		this.anniversaryId = anniversaryId;
		this.userId = userId;
		this.anniversaryName = anniversaryName;
		this.anniversaryDate = anniversaryDate;
		this.isNotificationEnabled = isNotificationEnabled;
		this.notificationDate = notificationDate;
		this.createdAt = createdAt;
		this.smsId = smsId;
		this.name = name;
		this.phone = phone;
		this.message = message;
		this.status = status;
		this.sentAt = sentAt;
	}

	public int getAnniversaryId() {
		return anniversaryId;
	}

	public void setAnniversaryId(int anniversaryId) {
		this.anniversaryId = anniversaryId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getAnniversaryName() {
		return anniversaryName;
	}

	public void setAnniversaryName(String anniversaryName) {
		this.anniversaryName = anniversaryName;
	}

	public String getAnniversaryDate() {
		return anniversaryDate;
	}

	public void setAnniversaryDate(String anniversaryDate) {
		this.anniversaryDate = anniversaryDate;
	}

	public boolean isNotificationEnabled() {
		return isNotificationEnabled;
	}

	public void setNotificationEnabled(boolean isNotificationEnabled) {
		this.isNotificationEnabled = isNotificationEnabled;
	}

	public String getNotificationDate() {
		return notificationDate;
	}

	public void setNotificationDate(String notificationDate) {
		this.notificationDate = notificationDate;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public int getSmsId() {
		return smsId;
	}

	public void setSmsId(int smsId) {
		this.smsId = smsId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSentAt() {
		return sentAt;
	}

	public void setSentAt(String sentAt) {
		this.sentAt = sentAt;
	}

	@Override
	public String toString() {
		return "JuAnniversaryVo [anniversaryId=" + anniversaryId + ", userId=" + userId + ", anniversaryName="
				+ anniversaryName + ", anniversaryDate=" + anniversaryDate + ", isNotificationEnabled="
				+ isNotificationEnabled + ", notificationDate=" + notificationDate + ", createdAt=" + createdAt
				+ ", smsId=" + smsId + ", name=" + name + ", phone=" + phone + ", message=" + message + ", status="
				+ status + ", sentAt=" + sentAt + "]";
	}

}

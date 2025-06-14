package com.javaex.mypage.model;

import java.time.LocalDateTime;

public class DdrMypagePointHistoryVo {
	private Long historyId; // 포인트 히스토리 ID
	private Long memberId; // 회원 ID
	private String type; // 포인트 유형 (적립/사용)
	private String reason; // 적립/사용 사유
	private Integer pointAmount; // 포인트 값 (양수: 적립, 음수: 사용)
	private Long relatedDesignId; // 관련된 도안 ID (nullable)
	private LocalDateTime createdAt; // 이력 생성 시간
	private Integer totalPoint;

	public DdrMypagePointHistoryVo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DdrMypagePointHistoryVo(Long historyId, Long memberId, String type, String reason, Integer pointAmount,
			Long relatedDesignId, LocalDateTime createdAt, Integer totalPoint) {
		super();
		this.historyId = historyId;
		this.memberId = memberId;
		this.type = type;
		this.reason = reason;
		this.pointAmount = pointAmount;
		this.relatedDesignId = relatedDesignId;
		this.createdAt = createdAt;
		this.totalPoint = totalPoint;
	}

	public Integer getTotalPoint() {
		return totalPoint;
	}

	public void setTotalPoint(Integer totalPoint) {
		this.totalPoint = totalPoint;
	}

	public Long getHistoryId() {
		return historyId;
	}

	public void setHistoryId(Long historyId) {
		this.historyId = historyId;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Integer getPointAmount() {
		return pointAmount;
	}

	public void setPointAmount(Integer pointAmount) {
		this.pointAmount = pointAmount;
	}

	public Long getRelatedDesignId() {
		return relatedDesignId;
	}

	public void setRelatedDesignId(Long relatedDesignId) {
		this.relatedDesignId = relatedDesignId;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public String toString() {
		return "DdrMypagePointHistoryVo [historyId=" + historyId + ", memberId=" + memberId + ", type=" + type
				+ ", reason=" + reason + ", pointAmount=" + pointAmount + ", relatedDesignId=" + relatedDesignId
				+ ", createdAt=" + createdAt + ", totalPoint=" + totalPoint + "]";
	}

}
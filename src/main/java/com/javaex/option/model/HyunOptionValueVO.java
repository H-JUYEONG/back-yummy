package com.javaex.option.model;

import java.time.LocalDateTime;

public class HyunOptionValueVO {

	private int optionValueId;
	private int optionTypeId;
	private String optionTypeName; // 옵션 유형 이름 추가
	private int venderId;
	private String optionValueName;
	private String optionValueImageUrl;
	private LocalDateTime optionValueCreatedAt;
	private Boolean isSelected; // 추가된 필드

	// 기본 생성자
	public HyunOptionValueVO() {
	}

	public HyunOptionValueVO(int optionValueId, int optionTypeId, String optionTypeName, int venderId,
			String optionValueName, String optionValueImageUrl, LocalDateTime optionValueCreatedAt,
			Boolean isSelected) {
		super();
		this.optionValueId = optionValueId;
		this.optionTypeId = optionTypeId;
		this.optionTypeName = optionTypeName;
		this.venderId = venderId;
		this.optionValueName = optionValueName;
		this.optionValueImageUrl = optionValueImageUrl;
		this.optionValueCreatedAt = optionValueCreatedAt;
		this.isSelected = isSelected;
	}

	// Getters and Setters

	public int getOptionValueId() {
		return optionValueId;
	}

	public Boolean getIsSelected() {
		return isSelected;
	}

	public void setIsSelected(Boolean isSelected) {
		this.isSelected = isSelected;
	}

	public String getOptionTypeName() {
		return optionTypeName;
	}

	public void setOptionTypeName(String optionTypeName) {
		this.optionTypeName = optionTypeName;
	}

	public void setOptionValueId(int optionValueId) {
		this.optionValueId = optionValueId;
	}

	public int getOptionTypeId() {
		return optionTypeId;
	}

	public void setOptionTypeId(int optionTypeId) {
		this.optionTypeId = optionTypeId;
	}

	public int getVenderId() {
		return venderId;
	}

	public void setVenderId(int venderId) {
		this.venderId = venderId;
	}

	public String getOptionValueName() {
		return optionValueName;
	}

	public void setOptionValueName(String optionValueName) {
		this.optionValueName = optionValueName;
	}

	public String getOptionValueImageUrl() {
		return optionValueImageUrl;
	}

	public void setOptionValueImageUrl(String optionValueImageUrl) {
		this.optionValueImageUrl = optionValueImageUrl;
	}

	public LocalDateTime getOptionValueCreatedAt() {
		return optionValueCreatedAt;
	}

	public void setOptionValueCreatedAt(LocalDateTime optionValueCreatedAt) {
		this.optionValueCreatedAt = optionValueCreatedAt;
	}

	@Override
	public String toString() {
		return "HyunOptionValueVO [optionValueId=" + optionValueId + ", optionTypeId=" + optionTypeId
				+ ", optionTypeName=" + optionTypeName + ", venderId=" + venderId + ", optionValueName="
				+ optionValueName + ", optionValueImageUrl=" + optionValueImageUrl + ", optionValueCreatedAt="
				+ optionValueCreatedAt + ", isSelected=" + isSelected + "]";
	}


}

package com.javaex.option.model;

public class DdrOptionVo {
	private int optionValueId;
	private String optionValueName;
	private String optionValueImageUrl;
	private String optionTypeName;

	public DdrOptionVo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DdrOptionVo(int optionValueId, String optionValueName, String optionValueImageUrl, String optionTypeName) {
		super();
		this.optionValueId = optionValueId;
		this.optionValueName = optionValueName;
		this.optionValueImageUrl = optionValueImageUrl;
		this.optionTypeName = optionTypeName;
	}

	public int getOptionValueId() {
		return optionValueId;
	}

	public void setOptionValueId(int optionValueId) {
		this.optionValueId = optionValueId;
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

	public String getOptionTypeName() {
		return optionTypeName;
	}

	public void setOptionTypeName(String optionTypeName) {
		this.optionTypeName = optionTypeName;
	}

	@Override
	public String toString() {
		return "DdrOptionVo [optionValueId=" + optionValueId + ", optionValueName=" + optionValueName
				+ ", optionValueImageUrl=" + optionValueImageUrl + ", optionTypeName=" + optionTypeName + "]";
	}

}
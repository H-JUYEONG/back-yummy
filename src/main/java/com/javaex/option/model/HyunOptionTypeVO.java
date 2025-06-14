package com.javaex.option.model;

import java.util.List;

public class HyunOptionTypeVO {

	private int optionTypeId;
	private String optionTypeName;
	private List<HyunOptionValueVO> optionValues; // 옵션 값 목록

	public HyunOptionTypeVO() {
		super();
	}

	public HyunOptionTypeVO(int optionTypeId, String optionTypeName) {
		super();
		this.optionTypeId = optionTypeId;
		this.optionTypeName = optionTypeName;
	}

	public int getOptionTypeId() {
		return optionTypeId;
	}

	public void setOptionTypeId(int optionTypeId) {
		this.optionTypeId = optionTypeId;
	}

	public String getOptionTypeName() {
		return optionTypeName;
	}

	public void setOptionTypeName(String optionTypeName) {
		this.optionTypeName = optionTypeName;
	}

	public List<HyunOptionValueVO> getOptionValues() {
		return optionValues;
	}

	public void setOptionValues(List<HyunOptionValueVO> optionValues) {
		this.optionValues = optionValues;
	}

	@Override
	public String toString() {
		return "HyunOptionTypeVO [optionTypeId=" + optionTypeId + ", optionTypeName=" + optionTypeName + "]";
	}

}

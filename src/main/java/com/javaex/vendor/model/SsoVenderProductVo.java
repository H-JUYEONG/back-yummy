package com.javaex.vendor.model;

public class SsoVenderProductVo {
	
	//필드
	private int venderId;
	private int productId;
	private int optionTypeId;
	private int optionValueId;
	
	private String productName;
	private String productPrice;
	private String productURL;
	private String optionValueName;
	private String option_value_image_url;
	private int isVisible;		//상품 공개여부 0:미노출 1:노출
	
	
	//생성자
	public SsoVenderProductVo() {
		super();
	}
	
	public SsoVenderProductVo(int venderId, int optionValueId) {
		this.venderId = venderId;
		this.optionValueId = optionValueId;
	}
	
	
	
	public SsoVenderProductVo(int optionValueId, int venderId, String optionValueName, String option_value_image_url) {
		super();
		this.venderId = venderId;
		this.optionValueName = optionValueName;
		this.option_value_image_url = option_value_image_url;
		this.optionValueId = optionValueId;
	}



	public SsoVenderProductVo(int venderId, int productId, int optionTypeId, String productName, String productPrice,
			String productURL, String optionValueName, String option_value_image_url, int isVisible, int optionValueId) {
		super();
		this.venderId = venderId;
		this.productId = productId;
		this.optionTypeId = optionTypeId;
		this.productName = productName;
		this.productPrice = productPrice;
		this.productURL = productURL;
		this.optionValueName = optionValueName;
		this.option_value_image_url = option_value_image_url;
		this.isVisible = isVisible;
		this.optionValueId = optionValueId;
	}


	//메서드 gs

	public int getVenderId() {
		return venderId;
	}

	public void setVenderId(int venderId) {
		this.venderId = venderId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getOptionTypeId() {
		return optionTypeId;
	}

	public void setOptionTypeId(int optionTypeId) {
		this.optionTypeId = optionTypeId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(String productPrice) {
		this.productPrice = productPrice;
	}

	public String getProductURL() {
		return productURL;
	}

	public void setProductURL(String productURL) {
		this.productURL = productURL;
	}

	public String getOptionValueName() {
		return optionValueName;
	}

	public void setOptionValueName(String optionValueName) {
		this.optionValueName = optionValueName;
	}

	public String getOption_value_image_url() {
		return option_value_image_url;
	}

	public void setOptionURL(String option_value_image_url) {
		this.option_value_image_url = option_value_image_url;
	}
	
	
	public int getIsVisible() {
		return isVisible;
	}


	public void setIsVisible(int isVisible) {
		this.isVisible = isVisible;
	}
	public int getOptionValueId() {
		return optionValueId;
	}



	public void setOptionValueId(int optionValueId) {
		this.optionValueId = optionValueId;
	}



	public void setOption_value_image_url(String option_value_image_url) {
		this.option_value_image_url = option_value_image_url;
	}




	//메서드 일반

	@Override
	public String toString() {
		return "SsoVenderProductVo [venderId=" + venderId + ", productId=" + productId + ", optionTypeId="
				+ optionTypeId + ", optionValueId=" + optionValueId + ", productName=" + productName + ", productPrice="
				+ productPrice + ", productURL=" + productURL + ", optionValueName=" + optionValueName
				+ ", option_value_image_url=" + option_value_image_url + ", isVisible=" + isVisible + "]";
	}

	

}

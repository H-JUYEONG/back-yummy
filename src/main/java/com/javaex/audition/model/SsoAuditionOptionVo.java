package com.javaex.audition.model;

public class SsoAuditionOptionVo {
	
	//필드
	private int venderId;
	private int productId;
	private String auditionId;
	
	private String cakeURL;		//케이크 사진(제시상품)
	private String cakeName;	//케이크 이름
	private String productType;	//상품타입
	private String category;	//카테고리
	
	private String taste;		//맛
	private String flavorCream;	//크림맛
	private String creamColor;	//크림색상
	private String creamPosition;	//크림위치
	private String cakeColor;		//케이크 배경색상
	private String decorationType; 	//데코 종류
	private String decorationColor;	//데코 색상
	private String optionURL;	//옵션 사진
	private String size;		//사이즈
	

	
	//생성자
	public SsoAuditionOptionVo() {
		super();
	}
	
	public SsoAuditionOptionVo(int venderId, int productId) {
		this.venderId = venderId;
		this.productId = productId;
	}
	
	public SsoAuditionOptionVo(int venderId, int productId, String auditionId, String cakeURL, String cakeName,
			String productType, String category, String taste, String flavorCream, String creamColor,
			String creamPosition, String cakeColor, String decorationType, String decorationColor, String optionURL,
			String size) {
		super();
		this.venderId = venderId;
		this.productId = productId;
		this.auditionId = auditionId;
		this.cakeURL = cakeURL;
		this.cakeName = cakeName;
		this.productType = productType;
		this.category = category;
		this.taste = taste;
		this.flavorCream = flavorCream;
		this.creamColor = creamColor;
		this.creamPosition = creamPosition;
		this.cakeColor = cakeColor;
		this.decorationType = decorationType;
		this.decorationColor = decorationColor;
		this.optionURL = optionURL;
		this.size = size;
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

	public String getAuditionId() {
		return auditionId;
	}

	public void setAuditionId(String auditionId) {
		this.auditionId = auditionId;
	}

	public String getCakeURL() {
		return cakeURL;
	}

	public void setCakeURL(String cakeURL) {
		this.cakeURL = cakeURL;
	}

	public String getCakeName() {
		return cakeName;
	}

	public void setCakeName(String cakeName) {
		this.cakeName = cakeName;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getTaste() {
		return taste;
	}

	public void setTaste(String taste) {
		this.taste = taste;
	}

	public String getFlavorCream() {
		return flavorCream;
	}

	public void setFlavorCream(String flavorCream) {
		this.flavorCream = flavorCream;
	}

	public String getCreamColor() {
		return creamColor;
	}

	public void setCreamColor(String creamColor) {
		this.creamColor = creamColor;
	}

	public String getCreamPosition() {
		return creamPosition;
	}

	public void setCreamPosition(String creamPosition) {
		this.creamPosition = creamPosition;
	}

	public String getCakeColor() {
		return cakeColor;
	}

	public void setCakeColor(String cakeColor) {
		this.cakeColor = cakeColor;
	}

	public String getDecorationType() {
		return decorationType;
	}

	public void setDecorationType(String decorationType) {
		this.decorationType = decorationType;
	}

	public String getDecorationColor() {
		return decorationColor;
	}

	public void setDecorationColor(String decorationColor) {
		this.decorationColor = decorationColor;
	}

	public String getOptionURL() {
		return optionURL;
	}

	public void setOptionURL(String optionURL) {
		this.optionURL = optionURL;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	
	//메서드 일반
	

	@Override
	public String toString() {
		return "SsoAuditionOptionVo [venderId=" + venderId + ", productId=" + productId + ", auditionId=" + auditionId
				+ ", cakeURL=" + cakeURL + ", cakeName=" + cakeName + ", productType=" + productType + ", category="
				+ category + ", taste=" + taste + ", flavorCream=" + flavorCream + ", creamColor=" + creamColor
				+ ", creamPosition=" + creamPosition + ", cakeColor=" + cakeColor + ", decorationType=" + decorationType
				+ ", decorationColor=" + decorationColor + ", optionURL=" + optionURL + ", size=" + size + "]";
	}
	
}

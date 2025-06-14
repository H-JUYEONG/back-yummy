package com.javaex.audition.model;

public class SsoAuditionVo {
	
	//필드
	
	//  <주문내역>  db에서 받을때 값
	private int venderId;	//업체 no
	private int auditionId;	//오디션 no
	
	private String userName;	//주문자 이름
	private String deliveryMethod;	//수령방식
	private String deliveryDate;	//퀵 배송일자
	private String pickupDate;		//픽업 일자
	private String designId;	//요청도안번호 (도안 no값 또는 null)
	private String cakeDesignId;	//업체상품의 도안번호
	private String region;	//요청 구 (지역)
	private String distict;		//업체 구
	
	private String additionalRequests;	//유저 요청사항
	private String expectedPrice;		//희망가격
	private String auditionApplicationSize;		//사이즈
	private String lettering;			//레터링
	private String plateLettering;		//판레터링
	
	private String userimgURL;		//요청도안URL
	private String auditionTitle;	//신청이름
	
	//보낼때 값
	private String date;		//수령일자
	private String designStatus;	//해당주문건에 대한 업체측 상품 보유상태
	
	//상품관련
	private int productId;
	private String productName;
	private String productURL;
	private String selectProduct;	//상품 사용가능여부
	
	
	
	//생성자
	public SsoAuditionVo() {
		super();
	}
	
	public SsoAuditionVo(int venderId, int auditionId, String userName, String deliveryMethod, String deliveryDate,
			String pickupDate, String designId, String cakeDesignId, String region, String distict,
			String additionalRequests, String expectedPrice, String auditionApplicationSize, String userimgURL,
			String auditionTitle, String date, String designStatus) {
		super();
		this.venderId = venderId;
		this.auditionId = auditionId;
		this.userName = userName;
		this.deliveryMethod = deliveryMethod;
		this.deliveryDate = deliveryDate;
		this.pickupDate = pickupDate;
		this.designId = designId;
		this.cakeDesignId = cakeDesignId;
		this.region = region;
		this.distict = distict;
		this.additionalRequests = additionalRequests;
		this.expectedPrice = expectedPrice;
		this.auditionApplicationSize = auditionApplicationSize;
		this.userimgURL = userimgURL;
		this.auditionTitle = auditionTitle;
		this.date = date;
		this.designStatus = designStatus;
	}
	
	

	public SsoAuditionVo(int venderId, int auditionId, String userName, String deliveryMethod, String deliveryDate,
			String pickupDate, String designId, String cakeDesignId, String region, String distict,
			String additionalRequests, String expectedPrice, String auditionApplicationSize, String userimgURL,
			String auditionTitle, String date, String designStatus, int productId, String productName,
			String productURL, String selectProduct) {
		super();
		this.venderId = venderId;
		this.auditionId = auditionId;
		this.userName = userName;
		this.deliveryMethod = deliveryMethod;
		this.deliveryDate = deliveryDate;
		this.pickupDate = pickupDate;
		this.designId = designId;
		this.cakeDesignId = cakeDesignId;
		this.region = region;
		this.distict = distict;
		this.additionalRequests = additionalRequests;
		this.expectedPrice = expectedPrice;
		this.auditionApplicationSize = auditionApplicationSize;
		this.userimgURL = userimgURL;
		this.auditionTitle = auditionTitle;
		this.date = date;
		this.designStatus = designStatus;
		this.productId = productId;
		this.productName = productName;
		this.productURL = productURL;
		this.selectProduct = selectProduct;
	}

	public SsoAuditionVo(int venderId, int auditionId, String userName, String deliveryMethod, String deliveryDate,
			String pickupDate, String designId, String cakeDesignId, String region, String distict,
			String additionalRequests, String expectedPrice, String auditionApplicationSize, String lettering,
			String plateLettering, String userimgURL, String auditionTitle, String date, String designStatus,
			int productId, String productName, String productURL, String selectProduct) {
		super();
		this.venderId = venderId;
		this.auditionId = auditionId;
		this.userName = userName;
		this.deliveryMethod = deliveryMethod;
		this.deliveryDate = deliveryDate;
		this.pickupDate = pickupDate;
		this.designId = designId;
		this.cakeDesignId = cakeDesignId;
		this.region = region;
		this.distict = distict;
		this.additionalRequests = additionalRequests;
		this.expectedPrice = expectedPrice;
		this.auditionApplicationSize = auditionApplicationSize;
		this.lettering = lettering;
		this.plateLettering = plateLettering;
		this.userimgURL = userimgURL;
		this.auditionTitle = auditionTitle;
		this.date = date;
		this.designStatus = designStatus;
		this.productId = productId;
		this.productName = productName;
		this.productURL = productURL;
		this.selectProduct = selectProduct;
	}

	//메서드gs
	
	public int getVenderId() {
		return venderId;
	}

	public void setVenderId(int venderId) {
		this.venderId = venderId;
	}

	public int getAuditionId() {
		return auditionId;
	}

	public void setAuditionId(int auditionId) {
		this.auditionId = auditionId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getDeliveryMethod() {
		return deliveryMethod;
	}

	public void setDeliveryMethod(String deliveryMethod) {
		this.deliveryMethod = deliveryMethod;
	}

	public String getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public String getPickupDate() {
		return pickupDate;
	}

	public void setPickupDate(String pickupDate) {
		this.pickupDate = pickupDate;
	}

	public String getDesignId() {
		return designId;
	}

	public void setDesignId(String designId) {
		this.designId = designId;
	}

	public String getCakeDesignId() {
		return cakeDesignId;
	}

	public void setCakeDesignId(String cakeDesignId) {
		this.cakeDesignId = cakeDesignId;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDesignStatus() {
		return designStatus;
	}

	public void setDesignStatus(String designStatus) {
		this.designStatus = designStatus;
	}
	public String getAdditionalRequests() {
		return additionalRequests;
	}

	public void setAdditionalRequests(String additionalRequests) {
		this.additionalRequests = additionalRequests;
	}
	
	public String getExpectedPrice() {
		return expectedPrice;
	}

	public void setExpectedPrice(String expectedPrice) {
		this.expectedPrice = expectedPrice;
	}

	public String getAuditionApplicationSize() {
		return auditionApplicationSize;
	}

	public void setAuditionApplicationSize(String auditionApplicationSize) {
		this.auditionApplicationSize = auditionApplicationSize;
	}
	public String getUserimgURL() {
		return userimgURL;
	}

	public void setUserimgURL(String userimgURL) {
		this.userimgURL = userimgURL;
	}
	
	public String getAuditionTitle() {
		return auditionTitle;
	}

	public void setAuditionTitle(String auditionTitle) {
		this.auditionTitle = auditionTitle;
	}
	
	public String getDistict() {
		return distict;
	}

	public void setDistict(String distict) {
		this.distict = distict;
	}
	
	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductURL() {
		return productURL;
	}

	public void setProductURL(String productURL) {
		this.productURL = productURL;
	}
	
	public String getSelectProduct() {
		return selectProduct;
	}

	public void setSelectProduct(String selectProduct) {
		this.selectProduct = selectProduct;
	}
	public String getLettering() {
		return lettering;
	}

	public void setLettering(String lettering) {
		this.lettering = lettering;
	}

	public String getPlateLettering() {
		return plateLettering;
	}

	public void setPlateLettering(String plateLettering) {
		this.plateLettering = plateLettering;
	}

	
	
	@Override
	public String toString() {
		return "SsoAuditionVo [venderId=" + venderId + ", auditionId=" + auditionId + ", userName=" + userName
				+ ", deliveryMethod=" + deliveryMethod + ", deliveryDate=" + deliveryDate + ", pickupDate=" + pickupDate
				+ ", designId=" + designId + ", cakeDesignId=" + cakeDesignId + ", region=" + region + ", distict="
				+ distict + ", additionalRequests=" + additionalRequests + ", expectedPrice=" + expectedPrice
				+ ", auditionApplicationSize=" + auditionApplicationSize + ", lettering=" + lettering
				+ ", plateLettering=" + plateLettering + ", userimgURL=" + userimgURL + ", auditionTitle="
				+ auditionTitle + ", date=" + date + ", designStatus=" + designStatus + ", productId=" + productId
				+ ", productName=" + productName + ", productURL=" + productURL + ", selectProduct=" + selectProduct
				+ "]";
	}



	
	
	
	
}

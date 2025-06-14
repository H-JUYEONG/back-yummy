package com.javaex.mypage.model;

public class DdrMypageWishlistVo {
	 private int productWishlistId;
	    private int productId;
	    private int memberId;
	    private String productName;
	    private double productPrice;
	    private String productImage1Url;
	    private String style;  // 스타일 카테고리
	    private String target; // 대상 카테고리
	    private String searchKeyword; // 검색어
	    private int page;
	    private int size;
	    private int offset;
		public DdrMypageWishlistVo() {
			super();
			// TODO Auto-generated constructor stub
		}
		public DdrMypageWishlistVo(int productWishlistId, int productId, int memberId, String productName,
				double productPrice, String productImage1Url, String style, String target, String searchKeyword,
				int page, int size, int offset) {
			super();
			this.productWishlistId = productWishlistId;
			this.productId = productId;
			this.memberId = memberId;
			this.productName = productName;
			this.productPrice = productPrice;
			this.productImage1Url = productImage1Url;
			this.style = style;
			this.target = target;
			this.searchKeyword = searchKeyword;
			this.page = page;
			this.size = size;
			this.offset = offset;
		}
		public int getProductWishlistId() {
			return productWishlistId;
		}
		public void setProductWishlistId(int productWishlistId) {
			this.productWishlistId = productWishlistId;
		}
		public int getProductId() {
			return productId;
		}
		public void setProductId(int productId) {
			this.productId = productId;
		}
		public int getMemberId() {
			return memberId;
		}
		public void setMemberId(int memberId) {
			this.memberId = memberId;
		}
		public String getProductName() {
			return productName;
		}
		public void setProductName(String productName) {
			this.productName = productName;
		}
		public double getProductPrice() {
			return productPrice;
		}
		public void setProductPrice(double productPrice) {
			this.productPrice = productPrice;
		}
		public String getProductImage1Url() {
			return productImage1Url;
		}
		public void setProductImage1Url(String productImage1Url) {
			this.productImage1Url = productImage1Url;
		}
		public String getStyle() {
			return style;
		}
		public void setStyle(String style) {
			this.style = style;
		}
		public String getTarget() {
			return target;
		}
		public void setTarget(String target) {
			this.target = target;
		}
		public String getSearchKeyword() {
			return searchKeyword;
		}
		public void setSearchKeyword(String searchKeyword) {
			this.searchKeyword = searchKeyword;
		}
		public int getPage() {
			return page;
		}
		public void setPage(int page) {
			this.page = page;
		}
		public int getSize() {
			return size;
		}
		public void setSize(int size) {
			this.size = size;
		}
		public int getOffset() {
			return offset;
		}
		public void setOffset(int offset) {
			this.offset = offset;
		}
		@Override
		public String toString() {
			return "DdrMypageWishlistVo [productWishlistId=" + productWishlistId + ", productId=" + productId
					+ ", memberId=" + memberId + ", productName=" + productName + ", productPrice=" + productPrice
					+ ", productImage1Url=" + productImage1Url + ", style=" + style + ", target=" + target
					+ ", searchKeyword=" + searchKeyword + ", page=" + page + ", size=" + size + ", offset=" + offset
					+ "]";
		}
	
		
}
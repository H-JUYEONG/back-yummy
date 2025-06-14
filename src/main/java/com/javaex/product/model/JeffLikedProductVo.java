package com.javaex.product.model;



public class JeffLikedProductVo {
    private int productWishlistId;
    private int productId;
    private int memberId;
    private String productName;
    private String productImageUrl;
    private String searchKeyword; // 검색어
    private int page;
    private int size;
    private int offset;

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

    public String getProductImageUrl() {
        return productImageUrl;
    }

    public void setProductImageUrl(String productImageUrl) {
        this.productImageUrl = productImageUrl;
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
        return "JeffLikedProductVo{" +
                "productWishlistId=" + productWishlistId +
                ", productId=" + productId +
                ", memberId=" + memberId +
                ", productName='" + productName + '\'' +
                ", productImageUrl='" + productImageUrl + '\'' +
                ", searchKeyword='" + searchKeyword + '\'' +
                ", page=" + page +
                ", size=" + size +
                ", offset=" + offset +
                '}';
    }
}

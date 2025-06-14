package com.javaex.cakedesign.model;

public class JeffLikedDesignVo {
    private int cake_design_wishlist_id;
    private int cake_design_id;
    private int memberId;
    private String cake_design_title; // New field for cake design title
    private String cake_design_image_url;
    private String searchKeyword; // 검색어
    private int page;
    private int size;
    private int offset;

    // Getters and Setters
    public int getCake_design_wishlist_id() {
        return cake_design_wishlist_id;
    }

    public void setCake_design_wishlist_id(int cake_design_wishlist_id) {
        this.cake_design_wishlist_id = cake_design_wishlist_id;
    }

    public int getCake_design_id() {
        return cake_design_id;
    }

    public void setCake_design_id(int cake_design_id) {
        this.cake_design_id = cake_design_id;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public String getCake_design_title() {
        return cake_design_title;
    }

    public void setCake_design_title(String cake_design_title) {
        this.cake_design_title = cake_design_title;
    }

    public String getCake_design_image_url() {
        return cake_design_image_url;
    }

    public void setCake_design_image_url(String cake_design_image_url) {
        this.cake_design_image_url = cake_design_image_url;
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
        return "JeffLikedDesignVo {" +
                "cake_design_wishlist_id=" + cake_design_wishlist_id +
                ", cake_design_id=" + cake_design_id +
                ", memberId=" + memberId +
                ", cake_design_title='" + cake_design_title + '\'' +
                ", cake_design_image_url='" + cake_design_image_url + '\'' +
                ", searchKeyword='" + searchKeyword + '\'' +
                ", page=" + page +
                ", size=" + size +
                ", offset=" + offset +
                '}';
    }
}

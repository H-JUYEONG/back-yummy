package com.javaex.review.model;

import java.sql.Timestamp;

public class JeffReviewVo {

    // Fields from the Product table
    private int productId;
    private String productName;

    // Fields from the Review table
    private int reviewId;
    private Integer reviewUserId; // Nullable field
    private Integer reviewOrderId; // Nullable field
    private Timestamp reviewCreatedAt; // Timestamp for review creation

    // Default Constructor
    public JeffReviewVo() {}

    // Parameterized Constructor
    public JeffReviewVo(int productId, String productName, int reviewId, Integer reviewUserId, Integer reviewOrderId, Timestamp reviewCreatedAt) {
        this.productId = productId;
        this.productName = productName;
        this.reviewId = reviewId;
        this.reviewUserId = reviewUserId;
        this.reviewOrderId = reviewOrderId;
        this.reviewCreatedAt = reviewCreatedAt;
    }

    // Getters and Setters
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

    public int getReviewId() {
        return reviewId;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    public Integer getReviewUserId() {
        return reviewUserId;
    }

    public void setReviewUserId(Integer reviewUserId) {
        this.reviewUserId = reviewUserId;
    }

    public Integer getReviewOrderId() {
        return reviewOrderId;
    }

    public void setReviewOrderId(Integer reviewOrderId) {
        this.reviewOrderId = reviewOrderId;
    }

    public Timestamp getReviewCreatedAt() {
        return reviewCreatedAt;
    }

    public void setReviewCreatedAt(Timestamp reviewCreatedAt) {
        this.reviewCreatedAt = reviewCreatedAt;
    }

    // toString Method
    @Override
    public String toString() {
        return "JeffReviewVo{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", reviewId=" + reviewId +
                ", reviewUserId=" + reviewUserId +
                ", reviewOrderId=" + reviewOrderId +
                ", reviewCreatedAt=" + reviewCreatedAt +
                '}';
    }
}


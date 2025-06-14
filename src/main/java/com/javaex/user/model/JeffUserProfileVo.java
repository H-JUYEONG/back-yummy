package com.javaex.user.model;

public class JeffUserProfileVo {
    private int memberId;                // Unique member ID (from Member table)
    private String email;                // User's email
    private String phoneNumber;          // User's phone number
    private String name;                 // User's name
    private String userNickname;         // User's nickname (from User table)
    private String userProfileImageUrl;  // Profile image URL
    private String passwordHash;         // User's password hash

    // Getters and Setters
    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserNickname() {
        return userNickname;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }

    public String getUserProfileImageUrl() {
        return userProfileImageUrl;
    }

    public void setUserProfileImageUrl(String userProfileImageUrl) {
        this.userProfileImageUrl = userProfileImageUrl;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    @Override
    public String toString() {
        return "JeffUserProfileVo{" +
                "memberId=" + memberId +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", name='" + name + '\'' +
                ", userNickname='" + userNickname + '\'' +
                ", userProfileImageUrl='" + userProfileImageUrl + '\'' +
                ", passwordHash='" + passwordHash + '\'' +
                '}';
    }
}

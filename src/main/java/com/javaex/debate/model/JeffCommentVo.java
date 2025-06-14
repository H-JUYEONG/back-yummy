package com.javaex.debate.model;

import java.sql.Timestamp;

public class JeffCommentVo {
    private int debate_comment_id; // 댓글 고유 식별자
    private int debate_id; // 토론 고유 식별자
    private int member_id; // 작성자 회원 ID
    private String user_nickname; // 작성자 닉네임
    private String user_profile_image_url; // 작성자 프로필 이미지 URL
    private String debate_comment_content; // 댓글 내용
    private Timestamp debate_comment_created_at; // 댓글 생성일

    // Default Constructor
    public JeffCommentVo() {}

    // Parameterized Constructor
    public JeffCommentVo(int debate_comment_id, int debate_id, int member_id, String user_nickname, String user_profile_image_url, String debate_comment_content, Timestamp debate_comment_created_at) {
        this.debate_comment_id = debate_comment_id;
        this.debate_id = debate_id;
        this.member_id = member_id;
        this.user_nickname = user_nickname;
        this.user_profile_image_url = user_profile_image_url;
        this.debate_comment_content = debate_comment_content;
        this.debate_comment_created_at = debate_comment_created_at;
    }

    // Getters and Setters
    public int getDebate_comment_id() {
        return debate_comment_id;
    }

    public void setDebate_comment_id(int debate_comment_id) {
        this.debate_comment_id = debate_comment_id;
    }

    public int getDebate_id() {
        return debate_id;
    }

    public void setDebate_id(int debate_id) {
        this.debate_id = debate_id;
    }

    public int getMember_id() {
        return member_id;
    }

    public void setMember_id(int member_id) {
        this.member_id = member_id;
    }

    public String getUser_nickname() {
        return user_nickname;
    }

    public void setUser_nickname(String user_nickname) {
        this.user_nickname = user_nickname;
    }

    public String getUser_profile_image_url() {
        return user_profile_image_url;
    }

    public void setUser_profile_image_url(String user_profile_image_url) {
        this.user_profile_image_url = user_profile_image_url;
    }

    public String getDebate_comment_content() {
        return debate_comment_content;
    }

    public void setDebate_comment_content(String debate_comment_content) {
        this.debate_comment_content = debate_comment_content;
    }

    public Timestamp getDebate_comment_created_at() {
        return debate_comment_created_at;
    }

    public void setDebate_comment_created_at(Timestamp debate_comment_created_at) {
        this.debate_comment_created_at = debate_comment_created_at;
    }

    // toString Method
    @Override
    public String toString() {
        return "JeffCommentVo{" +
                "debate_comment_id=" + debate_comment_id +
                ", debate_id=" + debate_id +
                ", member_id=" + member_id +
                ", user_nickname='" + user_nickname + '\'' +
                ", user_profile_image_url='" + user_profile_image_url + '\'' +
                ", debate_comment_content='" + debate_comment_content + '\'' +
                ", debate_comment_created_at=" + debate_comment_created_at +
                '}';
    }
}

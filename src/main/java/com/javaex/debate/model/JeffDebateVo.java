package com.javaex.debate.model;

import java.sql.Timestamp;

public class JeffDebateVo {
    private int debate_id; // 토론 고유 식별자
    private int member_id; // 토론을 작성한 회원 ID
    private String user_nickname;
    private String debate_title; // 토론 제목
    private String debate_category; // 도안, 업체 토론
    private String debate_left_item_type; // 좌측 비교 항목의 유형 (이미지, 도안, 상품)
    private Integer debate_left_item_id; // 좌측 항목의 ID (도안ID, 상품ID 등), 이미지일 경우 NULL
    private String debate_left_image_url; // 좌측에 업로드된 이미지 URL (항목 유형이 'Image'인 경우 사용)
    private String debate_right_item_type; // 우측 비교 항목의 유형 (이미지, 도안, 상품)
    private Integer debate_right_item_id; // 우측 항목의 ID (도안ID, 상품ID 등), 이미지일 경우 NULL
    private String debate_right_image_url; // 우측에 업로드된 이미지 URL (항목 유형이 'Image'인 경우 사용)
    private String debate_content; // 토론 내용
    private int debate_view_count; // 조회수
    private Timestamp debate_created_at; // 토론 생성일

    // Default Constructor
    public JeffDebateVo() {
    }

    public String getUser_nickname() {
		return user_nickname;
	}

	public void setUser_nickname(String user_nickname) {
		this.user_nickname = user_nickname;
	}

	// Getters and Setters
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

    public String getDebate_title() {
        return debate_title;
    }

    public void setDebate_title(String debate_title) {
        this.debate_title = debate_title;
    }

    public String getDebate_category() {
        return debate_category;
    }

    public void setDebate_category(String debate_category) {
        this.debate_category = debate_category;
    }

    public String getDebate_left_item_type() {
        return debate_left_item_type;
    }

    public void setDebate_left_item_type(String debate_left_item_type) {
        this.debate_left_item_type = debate_left_item_type;
    }

    public Integer getDebate_left_item_id() {
        return debate_left_item_id;
    }

    public void setDebate_left_item_id(Integer debate_left_item_id) {
        this.debate_left_item_id = debate_left_item_id;
    }

    public String getDebate_left_image_url() {
        return debate_left_image_url;
    }

    public void setDebate_left_image_url(String debate_left_image_url) {
        this.debate_left_image_url = debate_left_image_url;
    }

    public String getDebate_right_item_type() {
        return debate_right_item_type;
    }

    public void setDebate_right_item_type(String debate_right_item_type) {
        this.debate_right_item_type = debate_right_item_type;
    }

    public Integer getDebate_right_item_id() {
        return debate_right_item_id;
    }

    public void setDebate_right_item_id(Integer debate_right_item_id) {
        this.debate_right_item_id = debate_right_item_id;
    }

    public String getDebate_right_image_url() {
        return debate_right_image_url;
    }

    public void setDebate_right_image_url(String debate_right_image_url) {
        this.debate_right_image_url = debate_right_image_url;
    }

    public String getDebate_content() {
        return debate_content;
    }

    public void setDebate_content(String debate_content) {
        this.debate_content = debate_content;
    }

    public int getDebate_view_count() {
        return debate_view_count;
    }

    public void setDebate_view_count(int debate_view_count) {
        this.debate_view_count = debate_view_count;
    }

    public Timestamp getDebate_created_at() {
        return debate_created_at;
    }

    public void setDebate_created_at(Timestamp debate_created_at) {
        this.debate_created_at = debate_created_at;
    }

    @Override
    public String toString() {
        return "JeffDebateVo{" +
                "debate_id=" + debate_id +
                ", member_id=" + member_id +
                ", debate_title='" + debate_title + '\'' +
                ", debate_category='" + debate_category + '\'' +
                ", debate_left_item_type='" + debate_left_item_type + '\'' +
                ", debate_left_item_id=" + debate_left_item_id +
                ", debate_left_image_url='" + debate_left_image_url + '\'' +
                ", debate_right_item_type='" + debate_right_item_type + '\'' +
                ", debate_right_item_id=" + debate_right_item_id +
                ", debate_right_image_url='" + debate_right_image_url + '\'' +
                ", debate_content='" + debate_content + '\'' +
                ", debate_view_count=" + debate_view_count +
                ", debate_created_at=" + debate_created_at +
                '}';
    }
}

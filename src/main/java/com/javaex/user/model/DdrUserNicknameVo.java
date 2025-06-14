package com.javaex.user.model;

public class DdrUserNicknameVo {
	private int userId;
	private String userNickname;

	public DdrUserNicknameVo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DdrUserNicknameVo(int userId, String userNickname) {
		super();
		this.userId = userId;
		this.userNickname = userNickname;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserNickname() {
		return userNickname;
	}

	public void setUserNickname(String userNickname) {
		this.userNickname = userNickname;
	}

	@Override
	public String toString() {
		return "DdrUserNicknameVo [userId=" + userId + ", userNickname=" + userNickname + "]";
	}

}

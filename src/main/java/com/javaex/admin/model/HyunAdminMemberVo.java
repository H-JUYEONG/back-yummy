package com.javaex.admin.model;

public class HyunAdminMemberVo {

	private String uid;
	private String name;
	private String email;
	private String joinType;
	private String joinDate;
	private String status;

	public HyunAdminMemberVo() {
		super();
	}

	public HyunAdminMemberVo(String uid, String name, String email, String joinType, String joinDate, String status) {
		super();
		this.uid = uid;
		this.name = name;
		this.email = email;
		this.joinType = joinType;
		this.joinDate = joinDate;
		this.status = status;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getJoinType() {
		return joinType;
	}

	public void setJoinType(String joinType) {
		this.joinType = joinType;
	}

	public String getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "HyunAdminMemberVo [uid=" + uid + ", name=" + name + ", email=" + email + ", joinType=" + joinType
				+ ", joinDate=" + joinDate + ", status=" + status + "]";
	}

}

package com.javaex.user.model;

public class JuLoginVo {

	private int member_id;
	private int user_id;
	private int vender_id;
	private String type;
	private String email;
	private String password_hash;
	private String name;
	private String user_provider;
	private boolean is_active;

	public JuLoginVo() {

	}

	public JuLoginVo(int member_id, int user_id, int vender_id, String type, String email, String password_hash,
			String name, String user_provider, boolean is_active) {
		this.member_id = member_id;
		this.user_id = user_id;
		this.vender_id = vender_id;
		this.type = type;
		this.email = email;
		this.password_hash = password_hash;
		this.name = name;
		this.user_provider = user_provider;
		this.is_active = is_active;
	}

	public int getMember_id() {
		return member_id;
	}

	public void setMember_id(int member_id) {
		this.member_id = member_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getVender_id() {
		return vender_id;
	}

	public void setVender_id(int vender_id) {
		this.vender_id = vender_id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword_hash() {
		return password_hash;
	}

	public void setPassword_hash(String password_hash) {
		this.password_hash = password_hash;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUser_provider() {
		return user_provider;
	}

	public void setUser_provider(String user_provider) {
		this.user_provider = user_provider;
	}

	public boolean isIs_active() {
		return is_active;
	}

	public void setIs_active(boolean is_active) {
		this.is_active = is_active;
	}

	@Override
	public String toString() {
		return "JuLoginVo [member_id=" + member_id + ", user_id=" + user_id + ", vender_id=" + vender_id + ", type="
				+ type + ", email=" + email + ", password_hash=" + password_hash + ", name=" + name + ", user_provider="
				+ user_provider + ", is_active=" + is_active + "]";
	}

}

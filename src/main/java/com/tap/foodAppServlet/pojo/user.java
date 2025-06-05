package com.tap.foodAppServlet.pojo;

public class user {
	private int u_id;
	private String u_name;
	private String email;
	private int mobile;
	private String password;
	private String address;
	public int getU_id() {
		return u_id;
	}
	public void setU_id(int u_id) {
		this.u_id = u_id;
	}
	public String getU_name() {
		return u_name;
	}
	public void setU_name(String u_name) {
		this.u_name = u_name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getMobile() {
		return mobile;
	}
	public void setMobile(int mobile) {
		this.mobile = mobile;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public user(int u_id, String u_name, String email, int mobile, String password, String address) {
		super();
		this.u_id = u_id;
		this.u_name = u_name;
		this.email = email;
		this.mobile = mobile;
		this.password = password;
		this.address = address;
	}
	public user() {
		super();
	}
	public user(String u_name, String email, int mobile, String password, String address) {
		super();
		this.u_name = u_name;
		this.email = email;
		this.mobile = mobile;
		this.password = password;
		this.address = address;
	}
	@Override
	public String toString() {
		return "user [u_id=" + u_id + ", u_name=" + u_name + ", email=" + email + ", mobile=" + mobile + ", password="
				+ password + ", address=" + address + "]";
	}
	
	


}

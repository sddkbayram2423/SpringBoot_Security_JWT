package com.bayram.model;

public class RequestLogin {
	
	private String username;
	private String password;
	public RequestLogin() {
	}
	
	
	public RequestLogin(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}


	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	

}

package com.web.jdbc;

public class shopuser {
	
	private String name;
	private String password;
	private String email;
	
	public shopuser(String name,String password,String email) {
		this.name=name;
		this.password=password;
		this.email=email;
	}
	
	public shopuser(String name,String password) {
		this.name=name;
		this.password=password;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public String toString() {
		return "user [Name=" + name + ", Password " + password + ", email=" + email + "]";
	}

}

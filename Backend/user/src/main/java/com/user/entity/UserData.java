package com.user.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("UserData")
public class UserData {
	
	@Id
	String userId;
	String userName;
	String email;
	String password;
    String phone;
	String role;

	public UserData() {
	};

	public UserData(String userId,String userName, String email, String password, String phone, String role) {
	super();
	this.userId = userId;
	this.userName = userName;
	this.email = email;
	this.password = password;
	this.phone = phone;
	this.role = role;
}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

//	public int getAge() {
//		return age;
//	}
//
//	public void setAge(int age) {
//		this.age = age;
//	}

	public String getRole() {
		return role;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setRole(String role) {
		this.role = role;
	}

}

package com.mobilapi.model;

public class UserDetailsResponseModel {
	private String userId;
	private String firstName;
	private String lastname;
	private String email;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public UserDetailsResponseModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserDetailsResponseModel(String userId, String firstName, String lastname, String email) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastname = lastname;
		this.email = email;
	}
}
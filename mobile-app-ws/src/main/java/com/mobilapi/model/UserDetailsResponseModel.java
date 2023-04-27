package com.mobilapi.model;

public class UserDetailsResponseModel {
	private String userId;
	private String firstName;
	private String lastName;
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
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastname) {
		this.lastName = lastname;
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
		this.lastName = lastname;
		this.email = email;
	}
}
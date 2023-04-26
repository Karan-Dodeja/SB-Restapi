package com.mobilapi.dto;

import java.io.Serializable;

public class UserDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3230872982535777265L;
	private long id;
	private String userid;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String encryptedPassword;
	private String emailVerificationToken;
	private Boolean emailverificationStatus=false;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
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
	public void setLastName(String lastName) {
		this.lastName = lastName;
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
	public String getEncryptedPassword() {
		return encryptedPassword;
	}
	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}
	public String getEmailVerificationToken() {
		return emailVerificationToken;
	}
	public void setEmailVerificationToken(String emailVerificationToken) {
		this.emailVerificationToken = emailVerificationToken;
	}
	public Boolean getEmailverificationStatus() {
		return emailverificationStatus;
	}
	public void setEmailverificationStatus(Boolean emailverificationStatus) {
		this.emailverificationStatus = emailverificationStatus;
	}
	public UserDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserDto(long id, String userid, String firstName, String lastName, String email, String password,
			String encryptedPassword, String emailVerificationToken, Boolean emailverificationStatus) {
		super();
		this.id = id;
		this.userid = userid;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.encryptedPassword = encryptedPassword;
		this.emailVerificationToken = emailVerificationToken;
		this.emailverificationStatus = emailverificationStatus;
	}
}

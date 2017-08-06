package com.domain;

import java.util.Date;

public class Officer {
	private int officerId;
	private int titleId;
	private String firstName;
	private String surname;
	private String designation;
	private String mobileNo;
	private int officerTypeId;
	private String userName;
	private String password;
	private String status;
	private Date createdDate;
	private int createdUser;
	public int getTitleId() {
		return titleId;
	}
	public void setTitleId(int titleId) {
		this.titleId = titleId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public int getOfficerTypeId() {
		return officerTypeId;
	}
	public void setOfficerTypeId(int officerTypeId) {
		this.officerTypeId = officerTypeId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public int getCreatedUser() {
		return createdUser;
	}
	public void setCreatedUser(int createdUser) {
		this.createdUser = createdUser;
	}
	public int getOfficerId() {
		return officerId;
	}
	public void setOfficerId(int officerId) {
		this.officerId = officerId;
	}
	
}

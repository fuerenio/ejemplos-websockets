package com.notasprogramacion.back.rest.model.dto;


import java.io.Serializable;

public class UserRole implements Serializable {

	private Integer userRoleId;

	private User user;

	private String role;

	public UserRole() {
		
	}

	public UserRole(Integer userRoleId, User user, String role) {
		this.userRoleId = userRoleId;
		this.user = user;
		this.role = role;
	}

	public Integer getUserRoleId() {
		return userRoleId;
	}

	public void setUserRoleId(Integer userRoleId) {
		this.userRoleId = userRoleId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "UserRole [userRoleId=" + userRoleId + ", user=" + user + ", role=" + role + "]";
	}

}

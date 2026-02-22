package com.alumni.management.user.dto;

public class LoginResponseDto {

	private String token;
	private String name;
	private String role;

	public LoginResponseDto(String token, String name, String role) {
		this.token = token;
		this.name = name;
		this.role = role;
	}

	public String getToken() {
		return token;
	}

	public String getName() {
		return name;
	}

	public String getRole() {
		return role;
	}
}

//This file use to display data to user and Userrepository file use to insert data
//motive is to not display password to the user thats why we create this file
//Thats why we only use getters not setters
//DTOs cannot talk to the database.
package com.alumni.management.user.dto;

public class UserResponseDto {

	private Long id;
	private String name;
	private String email;
	private String roleName;

	public UserResponseDto(Long id, String name, String email, String roleName) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.roleName = roleName;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getRoleName() {
		return roleName;
	}

}

package com.alumni.management.user.dto;

public class UserResponseDto {

	private Long id;
	private String name;
	private String email;
	private String roleName;
	private String department;

	public UserResponseDto(Long id, String name, String email, String roleName) {
		this(id, name, email, roleName, "MCA");
	}

	public UserResponseDto(Long id, String name, String email, String roleName, String department) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.roleName = roleName;
		this.department = department != null && !department.isEmpty() ? department : "MCA";
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

	public String getDepartment() {
		return department;
	}
}

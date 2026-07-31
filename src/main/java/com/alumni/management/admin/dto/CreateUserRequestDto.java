package com.alumni.management.admin.dto;

public class CreateUserRequestDto {
    private String name;
    private String email;
    private String password;
    private String roleName;
    private String department;

    public CreateUserRequestDto() {}

    public CreateUserRequestDto(String name, String email, String password, String roleName, String department) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.roleName = roleName;
        this.department = department;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}

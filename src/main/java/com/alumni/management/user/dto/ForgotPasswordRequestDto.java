package com.alumni.management.user.dto;

public class ForgotPasswordRequestDto {
    private String email;

    public ForgotPasswordRequestDto() {}

    public ForgotPasswordRequestDto(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

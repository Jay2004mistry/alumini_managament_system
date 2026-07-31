package com.alumni.management.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alumni.management.admin.dto.CreateUserRequestDto;
import com.alumni.management.admin.service.AdminService;
import com.alumni.management.user.dto.UserResponseDto;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

	@Autowired
	AdminService adminService;

	@GetMapping("/users")
	public List<UserResponseDto> getAllUser(){
		return adminService.getAllUsers();
	}

	@GetMapping("/role/{roleName}")
	public List<UserResponseDto> getUserByRole(@PathVariable String roleName){
		return adminService.getUserByRole(roleName);
	}

	@DeleteMapping("/users/{id}")
	public String deleteUser(@PathVariable Long id) {
		return adminService.deleteUser(id);
	}

	@PostMapping("/users")
	public UserResponseDto createUser(@RequestBody CreateUserRequestDto request) {
		return adminService.createUserByAdmin(request);
	}
}

package com.alumni.management.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alumni.management.user.dto.UserResponseDto;

import com.alumni.management.admin.service.AdminService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/admin")
//@RequiredArgsConstructor help to create constructor automatically
@RequiredArgsConstructor
public class AdminController {
	
	@Autowired
	AdminService adminService;
	
	
	@GetMapping("/users")
	public List<UserResponseDto> getAllUser(){
		return adminService.getAllUsers();
	}
	
	@GetMapping("role/{roleName}")
	public List<UserResponseDto> getUserByRole(@PathVariable String roleName){
		return adminService.getUserByRole(roleName);
	}
	
	

}

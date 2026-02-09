package com.alumni.management.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alumni.management.user.dto.LoginRequestDto;
import com.alumni.management.user.dto.UserResponseDto;
import com.alumni.management.user.entity.User;
import com.alumni.management.user.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	UserService userService;

	@PostMapping
	public User createUser(@RequestBody User user) {

//		Send the user data to the service layer and then 
//		service layer will save that data by checking condition
		return userService.createUser(user);
	}

	@GetMapping()
	public List<UserResponseDto> getAllUsers() {
		return userService.getAllUsers();
	}

	@GetMapping("/{id}")
	public UserResponseDto getUserById(@PathVariable Long id) {
		return userService.getUserById(id);
	}

	@DeleteMapping("/{id}")
	public String deleteUser(@PathVariable Long id) {
		userService.deleteUser(id);
		return "Delete Successfully";
	}

	@PutMapping("/{id}")
	public User updateUserData(@PathVariable Long id, @RequestBody User user) {
		return userService.updateUserData(id, user);

	}
	
//	here we use userResponseDto because we have to send JSON formate to frontend.
//	First we use String But we change with UerResponseDto ti get all data in JSON formate
	@PostMapping("/login")
	public UserResponseDto login(@RequestBody LoginRequestDto request) {

	    User user = userService.login(request); // must return User

	    return new UserResponseDto(
	        user.getId(),
	        user.getName(),
	        user.getEmail(),
	        user.getRole().getRoleName()
	    );
	}


}

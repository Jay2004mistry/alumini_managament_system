package com.alumni.management.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.alumni.management.user.dto.LoginRequestDto;
import com.alumni.management.user.dto.UserResponseDto;
import com.alumni.management.user.entity.User;
import com.alumni.management.user.service.UserService;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
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

	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody LoginRequestDto request) {

		User user = userService.login(request);

		if (user == null) {
			return ResponseEntity
					.status(HttpStatus.UNAUTHORIZED)
					.body("INVALID_CREDENTIALS");
		}

		return ResponseEntity.ok("SUCCESS");
	}

}

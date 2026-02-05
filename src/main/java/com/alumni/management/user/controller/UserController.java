package com.alumni.management.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alumni.management.role.entity.Role;
import com.alumni.management.role.repository.RoleRepository;
import com.alumni.management.user.entity.User;
import com.alumni.management.user.repository.UserRepository;


@RestController
@RequestMapping("api/users")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;
	
	@PostMapping
	public User createUser(@RequestBody User user)
	{
		

//		below 2 line is just for chekking the role if is it availble or not
		Role role=roleRepository.findByRoleName(user.getRole().getRoleName())
		.orElseThrow(()-> new RuntimeException("Role not found"));
		
//	If role exist set that role	
		user.setRole(role);

//Set that user with role in DB

        return userRepository.save(user);
		
	}
}

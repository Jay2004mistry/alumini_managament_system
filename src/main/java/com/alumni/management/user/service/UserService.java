package com.alumni.management.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.alumni.management.role.entity.Role;
import com.alumni.management.role.repository.RoleRepository;
import com.alumni.management.user.entity.User;
import com.alumni.management.user.repository.UserRepository;



@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	@Autowired
	RoleRepository roleRepository;
	
	
	public User createUser(User user) {
		
//below 2 line is just for chekking the role if is it availble or not

		Role role=roleRepository.findByRoleName(user.getRole().getRoleName()).
		orElseThrow(()->	new ResponseStatusException(HttpStatus.NOT_FOUND,"User not found"));

	
//		If role exist set that role	
			user.setRole(role);
			
//Set that user with role in DB
			return userRepository.save(user);
	}


	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}


	public User getUserById(Long id) {
		return userRepository.findById(id).orElseThrow(()->
		new ResponseStatusException(HttpStatus.NOT_FOUND,"User not found"));
	}


	public void deleteUser(Long id) {
		if(!userRepository.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"User not found");
		}
		userRepository.deleteById(id);
	}


	public User updateUserData(Long id, User user) {
		User existingUser=userRepository.findById(id).
		orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"User not found"));
		
		existingUser.setName(user.getName());
		existingUser.setEmail(user.getEmail());
		existingUser.setPassword(user.getPassword());
		
		// check role exist if yes then ready to update
	    if (user.getRole() != null) {
	        Role role = roleRepository
	                .findByRoleName(user.getRole().getRoleName())
	                .orElseThrow(() ->
	                    new ResponseStatusException(HttpStatus.NOT_FOUND, "Role not found"));
	        existingUser.setRole(role);
	    }
	    
	    return userRepository.save(existingUser);
		
	}
}

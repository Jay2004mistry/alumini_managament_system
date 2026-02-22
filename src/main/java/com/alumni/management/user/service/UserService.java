package com.alumni.management.user.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.alumni.management.exception.ResourceNotFoundException;
import com.alumni.management.role.entity.Role;
import com.alumni.management.role.repository.RoleRepository;
import com.alumni.management.security.JwtUtil;
import com.alumni.management.user.dto.LoginRequestDto;
import com.alumni.management.user.dto.LoginResponseDto;
import com.alumni.management.user.dto.UserResponseDto;
import com.alumni.management.user.entity.User;
import com.alumni.management.user.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	JwtUtil jwtUtil;

	public User createUser(User user) {

//		below 2 line is just for chekking the role if is it availble or not

		Role role = roleRepository.findByRoleName(user.getRole().getRoleName())
				.orElseThrow(() -> new ResourceNotFoundException("Role not found"));

//		If role exist set that role	
		user.setRole(role);

//		Set password by doing encrypted before pass to save() function
		user.setPassword(passwordEncoder.encode(user.getPassword()));

//		Set that user with role in DB
		return userRepository.save(user);
	}

//	We write list<userResponseDTO> because it give list of user dto(Dummy entity) not entity(DB)
	public List<UserResponseDto> getAllUsers() {
		List<User> users = userRepository.findAll();
		// stream() process users one by one

		return users.stream().map(user -> new UserResponseDto(
//				Convert each User → DTO (map)
				user.getId(), user.getName(), user.getEmail(), user.getRole().getRoleName()))
//				Collect results into a list
				.collect(Collectors.toList());
	}

//	We use dto class to secure password
	public UserResponseDto getUserById(Long id) {

		User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));

		return new UserResponseDto(user.getId(), user.getName(), user.getEmail(), user.getRole().getRoleName());
	}

	public void deleteUser(Long id) {
		if (!userRepository.existsById(id)) {
			throw new ResourceNotFoundException("User not found");
		}
		userRepository.deleteById(id);
	}

	public User updateUserData(Long id, User user) {
		User existingUser = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User not found"));
		existingUser.setName(user.getName());
		existingUser.setEmail(user.getEmail());
		existingUser.setPassword(user.getPassword());

		// check role exist if yes then ready to update
		if (user.getRole() != null) {
			Role role = roleRepository.findByRoleName(user.getRole().getRoleName())
					.orElseThrow(() -> new ResourceNotFoundException("User not found"));
			existingUser.setRole(role);
		}

		return userRepository.save(existingUser);

	}

	public LoginResponseDto login(LoginRequestDto request) {

		User user = userRepository.findByEmail(request.getEmail())
				.orElseThrow(() -> new ResourceNotFoundException("Invalid email or password"));

		if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
			throw new ResourceNotFoundException("Invalid email or password");
		}
	    String token = jwtUtil.generateToken(user.getEmail());

	    return new LoginResponseDto(
	    	    token,
	    	    user.getName(),
	    	    user.getRole().getRoleName()
	    	);	}

}

package com.alumni.management.admin.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alumni.management.exception.ResourceNotFoundException;
import com.alumni.management.user.dto.UserResponseDto;
import com.alumni.management.user.entity.User;
import com.alumni.management.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminService {

	@Autowired
	UserRepository userRepository;

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

	

	public List<UserResponseDto> getUserByRole(String roleName) {
		List<User> users= userRepository.findByRole_RoleName(roleName);
		
		if(users.isEmpty()) {
			throw new ResourceNotFoundException("No user found with role"+ roleName);
		}
		return users.stream().map(user -> new UserResponseDto(user.getId(), user.getName(), user.getEmail(),user.getRole().getRoleName())).
				collect(Collectors.toList());
	}

}

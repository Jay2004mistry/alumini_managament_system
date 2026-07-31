package com.alumni.management.admin.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.alumni.management.admin.dto.CreateUserRequestDto;
import com.alumni.management.alumni.entity.AlumniProfile;
import com.alumni.management.alumni.repository.AlumniProfileRepository;
import com.alumni.management.exception.ResourceNotFoundException;
import com.alumni.management.faculty.entity.FacultyProfile;
import com.alumni.management.faculty.repository.FacultyRepository;
import com.alumni.management.role.entity.Role;
import com.alumni.management.role.repository.RoleRepository;
import com.alumni.management.user.dto.UserResponseDto;
import com.alumni.management.user.entity.User;
import com.alumni.management.user.repository.UserRepository;

@Service
public class AdminService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired(required = false)
	private AlumniProfileRepository alumniProfileRepository;

	@Autowired(required = false)
	private FacultyRepository facultyRepository;

	private String getUserDepartment(User user) {
		String role = user.getRole() != null ? user.getRole().getRoleName().toUpperCase() : "";
		if ("ALUMNI".equals(role) && alumniProfileRepository != null) {
			Optional<AlumniProfile> ap = alumniProfileRepository.findByUserId(user.getId());
			if (ap.isPresent() && ap.get().getDepartment() != null && !ap.get().getDepartment().isEmpty()) {
				return ap.get().getDepartment();
			}
		} else if ("FACULTY".equals(role) && facultyRepository != null) {
			Optional<FacultyProfile> fp = facultyRepository.findByUserId(user.getId());
			if (fp.isPresent() && fp.get().getDepartment() != null && !fp.get().getDepartment().isEmpty()) {
				return fp.get().getDepartment();
			}
		}
		return "MCA"; // Default department
	}

	public List<UserResponseDto> getAllUsers() {
		List<User> users = userRepository.findAll();
		return users.stream().map(user -> new UserResponseDto(
				user.getId(),
				user.getName(),
				user.getEmail(),
				user.getRole() != null ? user.getRole().getRoleName() : "USER",
				getUserDepartment(user)
		)).collect(Collectors.toList());
	}

	public List<UserResponseDto> getUserByRole(String roleName) {
		List<User> users = userRepository.findByRole_RoleName(roleName);
		if (users.isEmpty()) {
			return List.of();
		}
		return users.stream().map(user -> new UserResponseDto(
				user.getId(),
				user.getName(),
				user.getEmail(),
				user.getRole() != null ? user.getRole().getRoleName() : "USER",
				getUserDepartment(user)
		)).collect(Collectors.toList());
	}

	public String deleteUser(Long userId) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found with id " + userId));
		userRepository.delete(user);
		return "User deleted successfully";
	}

	public UserResponseDto createUserByAdmin(CreateUserRequestDto request) {
		String roleStr = request.getRoleName() != null ? request.getRoleName().toUpperCase() : "USER";
		Role role = roleRepository.findByRoleName(roleStr)
				.orElseThrow(() -> new ResourceNotFoundException("Role " + roleStr + " not found"));

		User user = new User();
		user.setName(request.getName());
		user.setEmail(request.getEmail());
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		user.setRole(role);

		User savedUser = userRepository.save(user);

		String dept = request.getDepartment() != null && !request.getDepartment().isEmpty() ? request.getDepartment() : "MCA";

		// Initialize profile if ALUMNI or FACULTY
		if ("ALUMNI".equals(roleStr) && alumniProfileRepository != null) {
			AlumniProfile ap = new AlumniProfile();
			ap.setUser(savedUser);
			ap.setDepartment(dept);
			alumniProfileRepository.save(ap);
		} else if ("FACULTY".equals(roleStr) && facultyRepository != null) {
			FacultyProfile fp = new FacultyProfile();
			fp.setUser(savedUser);
			fp.setDepartment(dept);
			facultyRepository.save(fp);
		}

		return new UserResponseDto(savedUser.getId(), savedUser.getName(), savedUser.getEmail(), savedUser.getRole().getRoleName(), dept);
	}
}

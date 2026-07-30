package com.alumni.management.profileview.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alumni.management.profileview.entity.ProfileView;
import com.alumni.management.profileview.repository.ProfileViewRepository;
import com.alumni.management.user.entity.User;
import com.alumni.management.user.repository.UserRepository;

@RestController
@RequestMapping("/api/profile-views")
public class ProfileViewController {

	@Autowired
	private ProfileViewRepository profileViewRepository;

	@Autowired
	private UserRepository userRepository;

	private User getCurrentUser() {
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		return userRepository.findByEmail(email)
				.orElseThrow(() -> new RuntimeException("Logged in user not found"));
	}

	@PostMapping("/log")
	public String logProfileView(@RequestParam String ownerEmail) {
		User viewer = getCurrentUser();
		if (viewer.getEmail().equalsIgnoreCase(ownerEmail)) {
			return "Self view ignored";
		}

		ProfileView view = new ProfileView();
		view.setViewerEmail(viewer.getEmail());
		view.setViewerName(viewer.getName());
		view.setOwnerEmail(ownerEmail);
		view.setTimestamp(LocalDateTime.now());
		profileViewRepository.save(view);

		return "Profile view logged successfully";
	}

	@GetMapping
	public List<ProfileView> getMyProfileViews() {
		User user = getCurrentUser();
		return profileViewRepository.findByOwnerEmailOrderByTimestampDesc(user.getEmail());
	}

}

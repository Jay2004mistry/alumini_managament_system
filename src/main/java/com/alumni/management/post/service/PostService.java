package com.alumni.management.post.service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.alumni.management.alumni.repository.AlumniProfileRepository;
import com.alumni.management.exception.ResourceNotFoundException;
import com.alumni.management.faculty.repository.FacultyRepository;
import com.alumni.management.post.dto.PostDto;
import com.alumni.management.post.entity.Post;
import com.alumni.management.post.repository.PostRepository;
import com.alumni.management.user.entity.User;
import com.alumni.management.user.repository.UserRepository;

@Service
public class PostService {

	@Autowired
	private PostRepository postRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AlumniProfileRepository alumniProfileRepository;

	@Autowired
	private FacultyRepository facultyRepository;

	private User getCurrentUser() {
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		return userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
	}

	private PostDto convertToDto(Post post) {
		String department = "N/A";
		String designation = "N/A";

		if (post.getUser() != null) {
			Long userId = post.getUser().getId();
			if (post.getUser().getRole() != null) {
				String roleName = post.getUser().getRole().getRoleName();
				if ("ALUMNI".equals(roleName)) {
					java.util.Optional<com.alumni.management.alumni.entity.AlumniProfile> alumniProfileOpt = alumniProfileRepository.findByUserId(userId);
					if (alumniProfileOpt.isPresent()) {
						com.alumni.management.alumni.entity.AlumniProfile profile = alumniProfileOpt.get();
						if (profile.getDepartment() != null) department = profile.getDepartment();
						if (profile.getBatchYear() != null) designation = "Class of " + profile.getBatchYear();
					}
				} else if ("FACULTY".equals(roleName)) {
					java.util.Optional<com.alumni.management.faculty.entity.FacultyProfile> facultyProfileOpt = facultyRepository.findByUserId(userId);
					if (facultyProfileOpt.isPresent()) {
						com.alumni.management.faculty.entity.FacultyProfile profile = facultyProfileOpt.get();
						if (profile.getDepartment() != null) department = profile.getDepartment();
						if (profile.getDesignation() != null) designation = profile.getDesignation();
					}
				} else if ("ADMIN".equals(roleName)) {
					department = "Administration";
					designation = "Staff";
				}
			}
		}

		return new PostDto(
				post.getId(),
				post.getContent(),
				post.getImageUrl(),
				post.getCreatedAt(),
				post.getUser().getId(),
				post.getUser().getName(),
				post.getUser().getEmail(),
				post.getUser().getRole() != null ? post.getUser().getRole().getRoleName() : "USER",
				department,
				designation
		);
	}

	public PostDto createPost(String content, MultipartFile image) {
		User user = getCurrentUser();

		Post post = new Post();
		post.setContent(content);
		post.setCreatedAt(LocalDateTime.now());
		post.setUser(user);

		if (image != null && !image.isEmpty()) {
			try {
				String uploadDir = "uploads/posts/";
				File directory = new File(uploadDir);
				if (!directory.exists()) {
					directory.mkdirs();
				}

				String fileName = System.currentTimeMillis() + "_" + image.getOriginalFilename();
				File destFile = new File(directory.getAbsolutePath() + File.separator + fileName);
				image.transferTo(destFile);

				post.setImageUrl("/uploads/posts/" + fileName);
			} catch (IOException e) {
				throw new RuntimeException("Failed to upload post image", e);
			}
		}

		Post saved = postRepository.save(post);
		return convertToDto(saved);
	}

	public List<PostDto> getAllPosts() {
		return postRepository.findAllByOrderByCreatedAtDesc().stream()
				.map(this::convertToDto)
				.collect(Collectors.toList());
	}

	public List<PostDto> getMyPosts() {
		User user = getCurrentUser();
		return postRepository.findByUserIdOrderByCreatedAtDesc(user.getId()).stream()
				.map(this::convertToDto)
				.collect(Collectors.toList());
	}

	public String deletePost(Long id) {
		User user = getCurrentUser();
		Post post = postRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Post not found"));

		// Allow author or admin to delete post
		if (!post.getUser().getId().equals(user.getId()) && !"ADMIN".equals(user.getRole().getRoleName())) {
			throw new RuntimeException("Unauthorized to delete this post");
		}

		postRepository.delete(post);
		return "Post deleted successfully";
	}
}

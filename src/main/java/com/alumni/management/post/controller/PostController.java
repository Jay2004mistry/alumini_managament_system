package com.alumni.management.post.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alumni.management.post.dto.PostDto;
import com.alumni.management.post.service.PostService;

@RestController
@RequestMapping("/api/posts")
@CrossOrigin
public class PostController {

	@Autowired
	private PostService postService;

	@PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public PostDto createPost(
			@RequestParam("content") String content,
			@RequestParam(value = "image", required = false) MultipartFile image) {
		return postService.createPost(content, image);
	}

	@GetMapping
	public List<PostDto> getAllPosts() {
		return postService.getAllPosts();
	}

	@GetMapping("/my")
	public List<PostDto> getMyPosts() {
		return postService.getMyPosts();
	}

	@DeleteMapping("/{id}")
	public String deletePost(@PathVariable Long id) {
		return postService.deletePost(id);
	}
}

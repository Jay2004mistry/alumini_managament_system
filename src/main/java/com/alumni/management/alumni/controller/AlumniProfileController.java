package com.alumni.management.alumni.controller;

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

import com.alumni.management.alumni.dto.AlumniProfileDto;
import com.alumni.management.alumni.entity.AlumniProfile;
import com.alumni.management.alumni.service.AlumniProfileService;

@RestController
@RequestMapping("/api/alumni")
public class AlumniProfileController {

	@Autowired
	AlumniProfileService alumniProfileService;

	@PostMapping("/profile/{userId}")
	public String createProfile(@PathVariable Long userId, @RequestBody AlumniProfile profile) {
		return alumniProfileService.createProfile(userId, profile);
	}

	@GetMapping("/profile/{userId}")
	public AlumniProfileDto getProfile(@PathVariable Long userId) {
		return alumniProfileService.getProfileByUserId(userId);
	}

	@PutMapping("/profile/{userId}")
	public String updateProfile(@PathVariable Long userId, @RequestBody AlumniProfile profile) {
		return alumniProfileService.updateProfile(userId, profile);
	}

	@DeleteMapping("/profile/{userId}")
	public String deleteProfile(@PathVariable Long userId) {
		return alumniProfileService.deleteProfile(userId);
	}
	
	@GetMapping("/search/name/{name}")
	public List<AlumniProfileDto> searchByName(@PathVariable String name) {
		return alumniProfileService.searchByName(name);
	}
	
	@GetMapping("/search/batchYear/{batchYear}")
	public List<AlumniProfileDto> searchByBatchYear(@PathVariable Integer batchYear) {
		return alumniProfileService.searchByBatchYear(batchYear);
	}
	
	@GetMapping("/search/department/{department}")
	public List<AlumniProfileDto> searchByDepartment(@PathVariable String department) {
		return alumniProfileService.searchByDepartment(department);
	}
	
	
	
	
	
	
}

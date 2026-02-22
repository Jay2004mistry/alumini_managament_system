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

	@PostMapping("/profile")
	public String createProfile(@RequestBody AlumniProfile profile) {
		return alumniProfileService.createProfile(profile);
	}

	@GetMapping("/profile")
	public AlumniProfileDto getProfile() {
		return alumniProfileService.getProfileByUserId();
	}

	@PutMapping("/profile")
	public String updateProfile(@RequestBody AlumniProfile profile) {
		return alumniProfileService.updateProfile(profile);
	}

	@DeleteMapping("/profile")
	public String deleteProfile() {
		return alumniProfileService.deleteProfile();
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

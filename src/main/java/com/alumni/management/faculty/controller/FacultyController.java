package com.alumni.management.faculty.controller;

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
import com.alumni.management.faculty.dto.FacultyProfileDto;
import com.alumni.management.faculty.entity.FacultyProfile;
import com.alumni.management.faculty.repository.FacultyRepository;
import com.alumni.management.faculty.service.FacultyService;

@RestController
@RequestMapping("api/faculty")
public class FacultyController {

	

	@Autowired
	private FacultyService facultyService;


  
	
	@PostMapping("profile")
	public String createFacultyProfile(@RequestBody FacultyProfile facultyProfile) {
		return facultyService.createFacultyProfile(facultyProfile);
	}
	
	@GetMapping("profile")
	public FacultyProfileDto getFacultyProfile() {
		return facultyService.getFacultyProfile();
	}
	@PutMapping("profile")
	public String updateFacultyProfile(@RequestBody FacultyProfile facultyProfile) {
		return facultyService.updateFacultyProfile(facultyProfile);
	}
	@DeleteMapping("profile")
	public String deleteFacultyProfile() {
		return facultyService.deleteFacultyProfile();
		
	}

}

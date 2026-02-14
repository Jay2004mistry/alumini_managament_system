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

    private final FacultyRepository facultyRepository;
	

	@Autowired
	FacultyService facultyService;


    FacultyController(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }
	
	
	@PostMapping("profile/{userId}")
	public String createFacultyProfile(@PathVariable Long userId, @RequestBody FacultyProfile facultyProfile) {
		return facultyService.createFacultyProfile(userId,facultyProfile);
	}
	
	@GetMapping("profile/{userId}")
	public FacultyProfileDto getFacultyProfile(@PathVariable Long userId) {
		return facultyService.getFacultyProfile(userId);
	}
	@PutMapping("profile/{userId}")
	public String updateFacultyProfile(@PathVariable Long userId, @RequestBody FacultyProfile facultyProfile) {
		return facultyService.updateFacultyProfile(userId,facultyProfile);
	}
	@DeleteMapping("profile/{userId}")
	public String deleteFacultyProfile(@PathVariable Long userId) {
		return facultyService.deleteFacultyProfile(userId);
		
	}

}

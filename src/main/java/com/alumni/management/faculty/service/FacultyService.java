package com.alumni.management.faculty.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alumni.management.alumni.dto.AlumniProfileDto;
import com.alumni.management.alumni.entity.AlumniProfile;
import com.alumni.management.alumni.repository.AlumniProfileRepository;
import com.alumni.management.exception.ResourceNotFoundException;
import com.alumni.management.faculty.dto.FacultyProfileDto;
import com.alumni.management.faculty.entity.FacultyProfile;
import com.alumni.management.faculty.repository.FacultyRepository;
import com.alumni.management.user.entity.User;
import com.alumni.management.user.repository.UserRepository;
import com.alumni.management.user.service.UserService;

@Service
public class FacultyService {

	@Autowired
	FacultyRepository facultyRepository;

	@Autowired
	UserRepository userRepository;

	public String createFacultyProfile(Long userId, FacultyProfile facultyProfile) {
		User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));

		if (!"FACULTY".equals(user.getRole().getRoleName())) {
			throw new RuntimeException("Only faculty can create profile");
		}
		FacultyProfile profile = facultyRepository.findByUserId(userId).orElse(new FacultyProfile());
		profile.setUser(user);
		profile.setDepartment(facultyProfile.getDepartment());
		profile.setDesignation(facultyProfile.getDesignation());
		profile.setQualification(facultyProfile.getQualification());
		profile.setSpecialization(facultyProfile.getSpecialization());
		profile.setExperienceYears(facultyProfile.getExperienceYears());

		profile.setEmail(facultyProfile.getEmail());
		profile.setContactNumber(facultyProfile.getContactNumber());

		profile.setResearchInterests(facultyProfile.getResearchInterests());
		profile.setBio(facultyProfile.getBio());
		profile.setLinkedInUrl(facultyProfile.getLinkedInUrl());
		facultyRepository.save(profile);
		return "Profile add successfully";
	}

	public FacultyProfileDto getFacultyProfile(Long userId) {
		FacultyProfile profile = facultyRepository.findByUserId(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found"));

		return new FacultyProfileDto(profile.getDepartment(), profile.getDesignation(), profile.getQualification(),
				profile.getSpecialization(), profile.getExperienceYears(), profile.getEmail(),
				profile.getContactNumber(), profile.getResearchInterests(), profile.getBio(), profile.getLinkedInUrl());
	}

	public String updateFacultyProfile(Long userId, FacultyProfile facultyProfile) {
		FacultyProfile profile = facultyRepository.findByUserId(userId).orElseThrow(
				()->new ResourceNotFoundException("Faculty not found"));
		profile.setDepartment(facultyProfile.getDepartment());
		profile.setDesignation(facultyProfile.getDesignation());
		profile.setQualification(facultyProfile.getQualification());
		profile.setSpecialization(facultyProfile.getSpecialization());
		profile.setExperienceYears(facultyProfile.getExperienceYears());

		profile.setEmail(facultyProfile.getEmail());
		profile.setContactNumber(facultyProfile.getContactNumber());

		profile.setResearchInterests(facultyProfile.getResearchInterests());
		profile.setBio(facultyProfile.getBio());
		profile.setLinkedInUrl(facultyProfile.getLinkedInUrl());
		facultyRepository.save(profile);
		return "Profile Update successfully";
	}

	public String deleteFacultyProfile(Long userId) {
		FacultyProfile facultyProfile=facultyRepository.findByUserId(userId).orElseThrow(
				()->new ResourceNotFoundException("No faculty profile has been found"));
		facultyRepository.delete(facultyProfile);
		return "Faculty profile delete successfully";
	}

}

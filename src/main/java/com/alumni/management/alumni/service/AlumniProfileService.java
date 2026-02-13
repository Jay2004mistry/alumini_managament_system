package com.alumni.management.alumni.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alumni.management.alumni.dto.AlumniProfileDto;
import com.alumni.management.alumni.entity.AlumniProfile;
import com.alumni.management.alumni.repository.AlumniProfileRepository;
import com.alumni.management.exception.ResourceNotFoundException;
import com.alumni.management.user.entity.User;
import com.alumni.management.user.repository.UserRepository;

@Service
public class AlumniProfileService {

	@Autowired
	AlumniProfileRepository profileRepository;

	@Autowired
	UserRepository UserRepository;

//	Help to not write this everytime just call it using map(this::converToDto)
	private AlumniProfileDto convertToDto(AlumniProfile alumniProfile) {

		return new AlumniProfileDto(alumniProfile.getBatchYear(), alumniProfile.getDegree(),
				alumniProfile.getDepartment(), alumniProfile.getDesignation(), alumniProfile.getCompanyName(),
				alumniProfile.getIndustry(), alumniProfile.getSkills(), alumniProfile.getWorkExperience(),
				alumniProfile.getLinkedInUrl(), alumniProfile.getGithubUrl(), alumniProfile.getContactNumber(),
				alumniProfile.getCurrentCity());
	}

	public String createProfile(Long userId, AlumniProfile profile) {

		User user = UserRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));

		if (!"ALUMNI".equals(user.getRole().getRoleName())) {
			throw new RuntimeException("Only alumni can create the profile");
		}

		AlumniProfile alumniProfile = profileRepository.findByUserId(userId).orElse(new AlumniProfile());

		alumniProfile.setUser(user);
		alumniProfile.setBatchYear(profile.getBatchYear());
		alumniProfile.setDegree(profile.getDegree());
		alumniProfile.setDepartment(profile.getDepartment());

		alumniProfile.setDesignation(profile.getDesignation());
		alumniProfile.setCompanyName(profile.getCompanyName());
		alumniProfile.setIndustry(profile.getIndustry());
		alumniProfile.setSkills(profile.getSkills());
		alumniProfile.setWorkExperience(profile.getWorkExperience());

		alumniProfile.setLinkedInUrl(profile.getLinkedInUrl());
		alumniProfile.setGithubUrl(profile.getGithubUrl());
		alumniProfile.setContactNumber(profile.getContactNumber());
		alumniProfile.setCurrentCity(profile.getCurrentCity());

		profileRepository.save(alumniProfile);

		return "Profile saved successfully";
	}

	public AlumniProfileDto getProfileByUserId(Long userId) {
		AlumniProfile alumniProfile = profileRepository.findByUserId(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found"));
		return new AlumniProfileDto(alumniProfile.getBatchYear(), alumniProfile.getDegree(),
				alumniProfile.getDepartment(), alumniProfile.getDesignation(), alumniProfile.getCompanyName(),
				alumniProfile.getIndustry(), alumniProfile.getSkills(), alumniProfile.getWorkExperience(),
				alumniProfile.getLinkedInUrl(), alumniProfile.getGithubUrl(), alumniProfile.getContactNumber(),
				alumniProfile.getCurrentCity());
	}

	public String updateProfile(Long userId, AlumniProfile profile) {
		AlumniProfile alumniProfile = profileRepository.findByUserId(userId).orElse(new AlumniProfile());

		alumniProfile.setBatchYear(profile.getBatchYear());
		alumniProfile.setDegree(profile.getDegree());
		alumniProfile.setDepartment(profile.getDepartment());

		alumniProfile.setDesignation(profile.getDesignation());
		alumniProfile.setCompanyName(profile.getCompanyName());
		alumniProfile.setIndustry(profile.getIndustry());
		alumniProfile.setSkills(profile.getSkills());
		alumniProfile.setWorkExperience(profile.getWorkExperience());

		alumniProfile.setLinkedInUrl(profile.getLinkedInUrl());
		alumniProfile.setGithubUrl(profile.getGithubUrl());
		alumniProfile.setContactNumber(profile.getContactNumber());
		alumniProfile.setCurrentCity(profile.getCurrentCity());

		profileRepository.save(alumniProfile);

		return "Profile update successfully";

	}

	public String deleteProfile(Long userId) {
		AlumniProfile alumniProfile = profileRepository.findByUserId(userId)
				.orElseThrow(() -> new ResourceNotFoundException("user not found"));
		profileRepository.delete(alumniProfile);

		return "Delete the user profile successfully";
	}

	public List<AlumniProfileDto> searchByName(String name) {

		List<AlumniProfile> alumniProfiles = profileRepository.findByUser_Name(name);

		return alumniProfiles.stream().map(this::convertToDto).collect(Collectors.toList());
	}

	public List<AlumniProfileDto> searchByBatchYear(Integer batchYear) {
		List<AlumniProfile> alumniProfile = profileRepository.findByBatchYear(batchYear);

		return alumniProfile.stream().map(this::convertToDto).collect(Collectors.toList());
	}

	public List<AlumniProfileDto> searchByDepartment(String department) {
		List<AlumniProfile> alumniProfile = profileRepository.findByDepartment(department);
		return alumniProfile.stream().map(this::convertToDto).collect(Collectors.toList());
	}

}

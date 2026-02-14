package com.alumni.management.faculty.dto;

import com.alumni.management.user.entity.User;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

public class FacultyProfileDto {

	private String department;
	private String designation;
	private String qualification;
	private String specialization;
	private Integer experienceYears;

	private String email;
	private String contactNumber;

	private String researchInterests;
	private String bio;
	private String linkedInUrl;

	public FacultyProfileDto(String department, String designation, String qualification, String specialization,
			Integer experienceYears, String email, String contactNumber, String researchInterests, String bio,
			String linkedInUrl) {
		super();
		this.department = department;
		this.designation = designation;
		this.qualification = qualification;
		this.specialization = specialization;
		this.experienceYears = experienceYears;
		this.email = email;
		this.contactNumber = contactNumber;
		this.researchInterests = researchInterests;
		this.bio = bio;
		this.linkedInUrl = linkedInUrl;
	}

	public FacultyProfileDto() {
		super();
	}

	public String getDepartment() {
		return department;
	}

	public String getDesignation() {
		return designation;
	}

	public String getQualification() {
		return qualification;
	}

	public String getSpecialization() {
		return specialization;
	}

	public Integer getExperienceYears() {
		return experienceYears;
	}

	public String getEmail() {
		return email;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public String getResearchInterests() {
		return researchInterests;
	}

	public String getBio() {
		return bio;
	}

	public String getLinkedInUrl() {
		return linkedInUrl;
	}

}
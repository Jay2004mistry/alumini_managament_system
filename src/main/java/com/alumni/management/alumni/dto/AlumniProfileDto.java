package com.alumni.management.alumni.dto;

import com.alumni.management.alumni.entity.AlumniProfile;

public class AlumniProfileDto {

	private Long userId;
	private String userName;
	private Integer batchYear;
	private String degree;
	private String department;
	private String designation;
	private String companyName;
	private String industry;
	private String skills;
	private Double workExperience;
	private String linkedInUrl;
	private String githubUrl;
	private String contactNumber;
	private String currentCity;

	// Update constructor to include userName
	public AlumniProfileDto(Long userId, String userName, Integer batchYear, String degree, String department,
			String designation, String companyName, String industry, String skills, Double workExperience,
			String linkedInUrl, String githubUrl, String contactNumber, String currentCity) {
		super();
		this.userId = userId;
		this.userName = userName; 
		this.batchYear = batchYear;
		this.degree = degree;
		this.department = department;
		this.designation = designation;
		this.companyName = companyName;
		this.industry = industry;
		this.skills = skills;
		this.workExperience = workExperience;
		this.linkedInUrl = linkedInUrl;
		this.githubUrl = githubUrl;
		this.contactNumber = contactNumber;
		this.currentCity = currentCity;
	}

	public AlumniProfileDto() {
		super();
	}

	public Long getUserId() {
		return userId;
	}

	public String getUserName() {
		return userName;
	}

	public Integer getBatchYear() {
		return batchYear;
	}

	public String getDegree() {
		return degree;
	}

	public String getDepartment() {
		return department;
	}

	public String getDesignation() {
		return designation;
	}

	public String getCompanyName() {
		return companyName;
	}

	public String getIndustry() {
		return industry;
	}

	public String getSkills() {
		return skills;
	}

	public Double getWorkExperience() {
		return workExperience;
	}

	public String getLinkedInUrl() {
		return linkedInUrl;
	}

	public String getGithubUrl() {
		return githubUrl;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public String getCurrentCity() {
		return currentCity;
	}

}
package com.alumni.management.alumni.dto;

import com.alumni.management.alumni.entity.AlumniProfile;

public class AlumniProfileDto {

	public Integer batchYear;
	public String degree;
	public String department;

	public String designation;
	public String companyName;
	public String industry;
	public String skills;
	public Double workExperience;

	public String linkedInUrl;
	public String githubUrl;
	public String contactNumber;
	public String currentCity;

	public AlumniProfileDto(Integer batchYear, String degree, String department, String designation, String companyName,
			String industry, String skills, Double workExperience, String linkedInUrl, String githubUrl,
			String contactNumber, String currentCity) {
		super();
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

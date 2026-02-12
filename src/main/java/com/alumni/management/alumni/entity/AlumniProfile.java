package com.alumni.management.alumni.entity;

import com.alumni.management.user.entity.User;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class AlumniProfile {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
//	@joinColum create user_id column in alumniprofile table and oneToOne indicate it is foreign key
	@OneToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
	 //  Academic Details
    private Integer batchYear;
    private String degree;
    private String department;

    //  Professional Details
    private String designation;
    private String companyName;
    private String industry;
    private String skills;
    private Double workExperience;

    // Contact & Social
    private String linkedInUrl;
    private String githubUrl;
    private String contactNumber;
    private String currentCity;
	
    
    public AlumniProfile(Long id, User user, Integer batchYear, String degree, String department, String designation,
			String companyName, String industry, String skills, Double workExperience, String linkedInUrl,
			String githubUrl, String contactNumber, String currentCity) {
		super();
		this.id = id;
		this.user = user;
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


	public AlumniProfile() {
		// TODO Auto-generated constructor stub
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public Integer getBatchYear() {
		return batchYear;
	}


	public void setBatchYear(Integer batchYear) {
		this.batchYear = batchYear;
	}


	public String getDegree() {
		return degree;
	}


	public void setDegree(String degree) {
		this.degree = degree;
	}


	public String getDepartment() {
		return department;
	}


	public void setDepartment(String department) {
		this.department = department;
	}


	public String getDesignation() {
		return designation;
	}


	public void setDesignation(String designation) {
		this.designation = designation;
	}


	public String getCompanyName() {
		return companyName;
	}


	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}


	public String getIndustry() {
		return industry;
	}


	public void setIndustry(String industry) {
		this.industry = industry;
	}


	public String getSkills() {
		return skills;
	}


	public void setSkills(String skills) {
		this.skills = skills;
	}


	public Double getWorkExperience() {
		return workExperience;
	}


	public void setWorkExperience(Double workExperience) {
		this.workExperience = workExperience;
	}


	public String getLinkedInUrl() {
		return linkedInUrl;
	}


	public void setLinkedInUrl(String linkedInUrl) {
		this.linkedInUrl = linkedInUrl;
	}


	public String getGithubUrl() {
		return githubUrl;
	}


	public void setGithubUrl(String githubUrl) {
		this.githubUrl = githubUrl;
	}


	public String getContactNumber() {
		return contactNumber;
	}


	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}


	public String getCurrentCity() {
		return currentCity;
	}


	public void setCurrentCity(String currentCity) {
		this.currentCity = currentCity;
	}
    
    
    
    

}

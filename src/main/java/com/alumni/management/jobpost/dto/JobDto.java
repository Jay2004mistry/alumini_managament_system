package com.alumni.management.jobpost.dto;

import java.time.LocalDate;

public class JobDto {
	
	private long userId;
	private String userName;
	private String companyName;
	private String jobTitle;
	private String location;
	private String salary;
	private String jobDescription;

	private String skillsRequired;
	private String experienceRequired;
	private String joiningType;
	private String jobType;

	private LocalDate lastDateToApply;

	public JobDto(long userId, String userName, String companyName, String jobTitle, String location, String salary,
			String jobDescription, String skillsRequired, String experienceRequired, String joiningType, String jobType,
			LocalDate lastDateToApply) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.companyName = companyName;
		this.jobTitle = jobTitle;
		this.location = location;
		this.salary = salary;
		this.jobDescription = jobDescription;
		this.skillsRequired = skillsRequired;
		this.experienceRequired = experienceRequired;
		this.joiningType = joiningType;
		this.jobType = jobType;
		this.lastDateToApply = lastDateToApply;
	}

	public JobDto() {
		super();
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	public String getJobDescription() {
		return jobDescription;
	}

	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}

	public String getSkillsRequired() {
		return skillsRequired;
	}

	public void setSkillsRequired(String skillsRequired) {
		this.skillsRequired = skillsRequired;
	}

	public String getExperienceRequired() {
		return experienceRequired;
	}

	public void setExperienceRequired(String experienceRequired) {
		this.experienceRequired = experienceRequired;
	}

	public String getJoiningType() {
		return joiningType;
	}

	public void setJoiningType(String joiningType) {
		this.joiningType = joiningType;
	}

	public String getJobType() {
		return jobType;
	}

	public void setJobType(String jobType) {
		this.jobType = jobType;
	}

	public LocalDate getLastDateToApply() {
		return lastDateToApply;
	}

	public void setLastDateToApply(LocalDate lastDateToApply) {
		this.lastDateToApply = lastDateToApply;
	}
	
	
	
	

}

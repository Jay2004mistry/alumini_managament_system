package com.alumni.management.faculty.dto;

public class FacultyProfileDto {

	private Long userId;
	private String userName;
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

	// New fields to match the frontend UI
	private String teachingExperience;
	private String industryExperience;
	private String publicationsCount;
	private String certifications;
	private String achievements;
	private String skills;
	private String studentsGuided;
	private String projectsSupervised;

	public FacultyProfileDto(Long userId, String userName, String department, String designation, String qualification, String specialization,
			Integer experienceYears, String email, String contactNumber, String researchInterests, String bio,
			String linkedInUrl, String teachingExperience, String industryExperience, String publicationsCount,
			String certifications, String achievements, String skills, String studentsGuided, String projectsSupervised) {
		super();
		this.userId = userId;
		this.userName = userName;
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
		this.teachingExperience = teachingExperience;
		this.industryExperience = industryExperience;
		this.publicationsCount = publicationsCount;
		this.certifications = certifications;
		this.achievements = achievements;
		this.skills = skills;
		this.studentsGuided = studentsGuided;
		this.projectsSupervised = projectsSupervised;
	}

	public FacultyProfileDto() {
		super();
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	public void setDepartment(String department) {
		this.department = department;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}

	public void setExperienceYears(Integer experienceYears) {
		this.experienceYears = experienceYears;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public void setResearchInterests(String researchInterests) {
		this.researchInterests = researchInterests;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public void setLinkedInUrl(String linkedInUrl) {
		this.linkedInUrl = linkedInUrl;
	}

	public void setTeachingExperience(String teachingExperience) {
		this.teachingExperience = teachingExperience;
	}

	public void setIndustryExperience(String industryExperience) {
		this.industryExperience = industryExperience;
	}

	public void setPublicationsCount(String publicationsCount) {
		this.publicationsCount = publicationsCount;
	}

	public void setCertifications(String certifications) {
		this.certifications = certifications;
	}

	public void setAchievements(String achievements) {
		this.achievements = achievements;
	}

	public void setSkills(String skills) {
		this.skills = skills;
	}

	public void setStudentsGuided(String studentsGuided) {
		this.studentsGuided = studentsGuided;
	}

	public void setProjectsSupervised(String projectsSupervised) {
		this.projectsSupervised = projectsSupervised;
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

	public String getTeachingExperience() {
		return teachingExperience;
	}

	public String getIndustryExperience() {
		return industryExperience;
	}

	public String getPublicationsCount() {
		return publicationsCount;
	}

	public String getCertifications() {
		return certifications;
	}

	public String getAchievements() {
		return achievements;
	}

	public String getSkills() {
		return skills;
	}

	public String getStudentsGuided() {
		return studentsGuided;
	}

	public String getProjectsSupervised() {
		return projectsSupervised;
	}

	private String profilePictureUrl;

	public String getProfilePictureUrl() {
		return profilePictureUrl;
	}

	public void setProfilePictureUrl(String profilePictureUrl) {
		this.profilePictureUrl = profilePictureUrl;
	}

}
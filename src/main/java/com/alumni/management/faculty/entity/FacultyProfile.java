package com.alumni.management.faculty.entity;

import com.alumni.management.user.entity.User;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class FacultyProfile {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

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

	@OneToOne
	@JoinColumn(name = "user_id", nullable = false, unique = true)
	private User user;

	public FacultyProfile(Long id, String department, String designation, String qualification,
			String specialization, Integer experienceYears, String email, String contactNumber,
			String researchInterests, String bio, String linkedInUrl, String teachingExperience,
			String industryExperience, String publicationsCount, String certifications, String achievements,
			String skills, String studentsGuided, String projectsSupervised, User user) {
		super();
		this.id = id;
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
		this.user = user;
	}

	public FacultyProfile() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public String getSpecialization() {
		return specialization;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}

	public Integer getExperienceYears() {
		return experienceYears;
	}

	public void setExperienceYears(Integer experienceYears) {
		this.experienceYears = experienceYears;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getResearchInterests() {
		return researchInterests;
	}

	public void setResearchInterests(String researchInterests) {
		this.researchInterests = researchInterests;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public String getLinkedInUrl() {
		return linkedInUrl;
	}

	public void setLinkedInUrl(String linkedInUrl) {
		this.linkedInUrl = linkedInUrl;
	}

	public String getTeachingExperience() {
		return teachingExperience;
	}

	public void setTeachingExperience(String teachingExperience) {
		this.teachingExperience = teachingExperience;
	}

	public String getIndustryExperience() {
		return industryExperience;
	}

	public void setIndustryExperience(String industryExperience) {
		this.industryExperience = industryExperience;
	}

	public String getPublicationsCount() {
		return publicationsCount;
	}

	public void setPublicationsCount(String publicationsCount) {
		this.publicationsCount = publicationsCount;
	}

	public String getCertifications() {
		return certifications;
	}

	public void setCertifications(String certifications) {
		this.certifications = certifications;
	}

	public String getAchievements() {
		return achievements;
	}

	public void setAchievements(String achievements) {
		this.achievements = achievements;
	}

	public String getSkills() {
		return skills;
	}

	public void setSkills(String skills) {
		this.skills = skills;
	}

	public String getStudentsGuided() {
		return studentsGuided;
	}

	public void setStudentsGuided(String studentsGuided) {
		this.studentsGuided = studentsGuided;
	}

	public String getProjectsSupervised() {
		return projectsSupervised;
	}

	public void setProjectsSupervised(String projectsSupervised) {
		this.projectsSupervised = projectsSupervised;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}

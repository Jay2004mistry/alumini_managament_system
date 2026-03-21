package com.alumni.management.jobpost.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.alumni.management.jobpost.dto.JobDto;
import com.alumni.management.jobpost.entity.Job;
import com.alumni.management.jobpost.repository.JobRepository;
import com.alumni.management.user.entity.User;
import com.alumni.management.user.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class JobService {

	@Autowired
	JobRepository jobRepository;
	@Autowired
	UserRepository userRepository;

//	jwt authentication
	private User getCurrUser() {
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		return userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
	}

//	onvert he job entity into dto for use
	private JobDto convertToDto(Job job) {
		return new JobDto(job.getUser().getId(), job.getUser().getName(), job.getCompanyName(), job.getJobTitle(),
				job.getLocation(), job.getSalary(), job.getJobDescription(), job.getSkillsRequired(),
				job.getExperienceRequired(), job.getJoiningType(), job.getJobType(), job.getLastDateToApply());
	}

	/**
	 * transactional
	 * Ensures "All or Nothing" database operations.
	 * If any part of this method fails or throws an Exception, 
	 * Spring will automatically ROLLBACK (cancel) all database changes 
	 * made during this process to maintain data integrity.
	 */
	@Transactional
	public String createJobpost(Job job) {
		User user=getCurrUser();
		// Check if user has permission to post jobs (Alumni, Faculty, or Admin)
String userRole=user.getRole().getRoleName();
if (!"ALUMNI".equals(userRole) && !"FACULTY".equals(userRole) && !"ADMIN".equals(userRole)) {
	throw new RuntimeException("Only alumni, faculty, and admin can create job posts");
}
//Set the user to the job post
job.setUser(user);
jobRepository.save(job);
return "Job post created successfully by "+user.getName();
	}

	public List<JobDto> getAllJobs() {
		// TODO Auto-generated method stub
		return null;
	}

	public String updateJob(Long id, JobDto jobDto) {
		// TODO Auto-generated method stub
		return null;
	}

	public String deleteJob(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}

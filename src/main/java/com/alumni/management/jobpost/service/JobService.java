package com.alumni.management.jobpost.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.alumni.management.exception.ResourceNotFoundException;
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
	 * transactional Ensures "All or Nothing" database operations. If any part of
	 * this method fails or throws an Exception, Spring will automatically ROLLBACK
	 * (cancel) all database changes made during this process to maintain data
	 * integrity.
	 */
	@Transactional
	public String createJobpost(Job job) {
		User user = getCurrUser();
		// Check if user has permission to post jobs (Alumni, Faculty, or Admin)
		String userRole = user.getRole().getRoleName();
		if (!"ALUMNI".equals(userRole) && !"FACULTY".equals(userRole) && !"ADMIN".equals(userRole)) {
			throw new RuntimeException("Only alumni, faculty, and admin can create job posts");
		}
//Set the user to the job post
		job.setUser(user);
		jobRepository.save(job);
		return "Job post created successfully by " + user.getName();
	}

	public List<JobDto> getAllJobs() {
		List<Job> jobs = jobRepository.findAll();
		return jobs.stream().map(this::convertToDto).collect(Collectors.toList());
	}

	@Transactional
	public String updateJob(Long id, JobDto jobDto) {
		User currentUser = getCurrUser();

		Job existingJob = jobRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Job not found with id: " + id));

		// Check if current user is the owner of the job or admin
		String userRole = currentUser.getRole().getRoleName();
		if (!existingJob.getUser().getId().equals(currentUser.getId()) && !"ADMIN".equals(userRole)) {
			throw new RuntimeException("You are not authorized to update this job post");
		}

		// Update job details
		existingJob.setCompanyName(jobDto.getCompanyName());
		existingJob.setJobTitle(jobDto.getJobTitle());
		existingJob.setLocation(jobDto.getLocation());
		existingJob.setSalary(jobDto.getSalary());
		existingJob.setJobDescription(jobDto.getJobDescription());
		existingJob.setSkillsRequired(jobDto.getSkillsRequired());
		existingJob.setExperienceRequired(jobDto.getExperienceRequired());
		existingJob.setJoiningType(jobDto.getJoiningType());
		existingJob.setJobType(jobDto.getJobType());
		existingJob.setLastDateToApply(jobDto.getLastDateToApply());

		jobRepository.save(existingJob);
		return "Job post updated successfully with ID: " + id;
	}

	@Transactional
	public String deleteJob(Long id) {
		User currentUser = getCurrUser();

		Job job = jobRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Job not found with id: " + id));

		// Check if current user is the owner of the job or admin
		String userRole = currentUser.getRole().getRoleName();
		if (!job.getUser().getId().equals(currentUser.getId()) && !"ADMIN".equals(userRole)) {
			throw new RuntimeException("You are not authorized to delete this job post");
		}

		jobRepository.delete(job);
		return "Job post deleted successfully with ID: " + id;
	}

}

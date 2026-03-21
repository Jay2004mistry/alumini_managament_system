package com.alumni.management.jobpost.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alumni.management.jobpost.entity.Job;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {
	
	List<Job> findByUserId(Long userId);
	
	// Remove this line - you cannot search by userName directly in Job repository
	// List<Job> findByUserName(Long userName);
	
	// Corrected: companyName instead of company
	List<Job> findByCompanyName(String companyName);
	
	// Corrected: jobTitle instead of jobTitile
	List<Job> findByJobTitle(String jobTitle);
	
	// Corrected: location parameter type should be String, not Long
	List<Job> findByLocation(String location);
	
	List<Job> findByJobType(String jobType);
}
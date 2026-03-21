package com.alumni.management.jobpost.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alumni.management.jobpost.entity.Job;

public interface JobRepository extends JpaRepository<Job, Long> {
	
	List<Job> findByUserId(Long userId);
	List<Job> findByUserName(Long userName);
	List<Job> findByCompany(Long companyName);
	List<Job> findByJobTitile(Long jobTitle);
	List<Job> findByLocation(Long location);
    List<Job> findByJobType(String jobType);


}

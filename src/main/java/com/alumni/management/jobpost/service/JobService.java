package com.alumni.management.jobpost.service;

import java.util.List;

import com.alumni.management.jobpost.dto.JobDto;
import com.alumni.management.jobpost.entity.Job;

import jakarta.transaction.Transactional;

public class JobService {

	/**
	 * transactional
	 * Ensures "All or Nothing" database operations.
	 * If any part of this method fails or throws an Exception, 
	 * Spring will automatically ROLLBACK (cancel) all database changes 
	 * made during this process to maintain data integrity.
	 */
	@Transactional
	public String createJobpost(Job job) {
		// TODO Auto-generated method stub
		return null;
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

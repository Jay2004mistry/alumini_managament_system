package com.alumni.management.faculty.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alumni.management.faculty.entity.FacultyProfile;

public interface FacultyRepository extends JpaRepository<FacultyProfile, Long> {

	
	Optional<FacultyProfile> findByUserId(Long userId);
}

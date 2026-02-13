package com.alumni.management.alumni.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alumni.management.alumni.entity.AlumniProfile;

public interface AlumniProfileRepository extends JpaRepository<AlumniProfile, Long> {

	Optional<AlumniProfile> findByUserId(Long userId);
	List<AlumniProfile> findByUser_Name(String name);
	List<AlumniProfile> findByBatchYear(Integer batchYear);
	List<AlumniProfile> findByDepartment(String department);
	

}

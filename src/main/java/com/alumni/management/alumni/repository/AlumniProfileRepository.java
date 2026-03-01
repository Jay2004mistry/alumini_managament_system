package com.alumni.management.alumni.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.alumni.management.alumni.entity.AlumniProfile;

public interface AlumniProfileRepository extends JpaRepository<AlumniProfile, Long> {

	Optional<AlumniProfile> findByUserId(Long userId);

//	we did this below because user name is comming from user table it is not in alumani profile table 
	@Query("SELECT ap FROM AlumniProfile ap WHERE ap.user.name = :name")
	List<AlumniProfile> findByName(@Param("name") String name);

	List<AlumniProfile> findByBatchYear(Integer batchYear);

	List<AlumniProfile> findByDepartment(String department);

}

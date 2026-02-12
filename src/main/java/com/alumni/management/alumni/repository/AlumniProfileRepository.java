package com.alumni.management.alumni.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alumni.management.alumni.entity.AlumniProfile;

public interface AlumniProfileRepository extends JpaRepository<AlumniProfile, Long> {

	Optional<AlumniProfile> findByUserId(Long userId);

}

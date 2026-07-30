package com.alumni.management.profileview.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alumni.management.profileview.entity.ProfileView;

@Repository
public interface ProfileViewRepository extends JpaRepository<ProfileView, Long> {

	List<ProfileView> findByOwnerEmailOrderByTimestampDesc(String ownerEmail);

	boolean existsByViewerEmailAndOwnerEmail(String viewerEmail, String ownerEmail);

}

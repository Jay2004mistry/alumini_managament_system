package com.alumni.management.event.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alumni.management.event.entity.Event;

public interface EventRepository extends JpaRepository<Event, Long> {
	
	List<Event> findByStatus(String status);
	List<Event> findByCreatedBy_Id(Long userId);

}

package com.alumni.management.event.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.alumni.management.event.entity.Event;
import com.alumni.management.event.repository.EventRepository;
import com.alumni.management.exception.ResourceNotFoundException;
import com.alumni.management.user.entity.User;
import com.alumni.management.user.repository.UserRepository;

@Service
public class EventService {

	@Autowired
	EventRepository eventRepository;

	@Autowired
	UserRepository userRepository;

	
//	jwt authentication for token genration
	
	private User getCurrentUser() {
		String email=SecurityContextHolder.getContext().getAuthentication().getName();
		return userRepository.findByEmail(email).orElseThrow(
				()-> new RuntimeException("User not found"));
	}
	
	public String createEvent(Event event) {
User user=getCurrentUser();
		Event event2 = new Event();
		event2.setTitle(event.getTitle());
		event2.setDescription(event.getDescription());
		event2.setLocation(event.getLocation());
		event2.setEventDate(event.getEventDate());

		event2.setStatus("PENDING");
		event2.setCreatedAt(LocalDateTime.now());
		event2.setCreatedBy(user);

		eventRepository.save(event2);

		return "Event submitted for approval";

	}

	public List<Event> getApprovedEvents() {
		return eventRepository.findByStatus("APPROVED");
	}

	public List<Event> getMyEvents() {

		User user=getCurrentUser();
		userRepository.findById(user.getId()).orElseThrow(() -> new ResourceNotFoundException("User not found"));

		return eventRepository.findByCreatedBy_Id(user.getId());
	}

	public List<Event> getPendingEvents() {
		return eventRepository.findByStatus("PENDING");
	}

	public String approveEvent(Long eventId) {

		Event event = eventRepository.findById(eventId)
				.orElseThrow(() -> new ResourceNotFoundException("Event not found"));

		event.setStatus("APPROVED");
		eventRepository.save(event);

		return "Event approved successfully";
	}

	public String rejectEvent(Long eventId) {

		Event event = eventRepository.findById(eventId)
				.orElseThrow(() -> new ResourceNotFoundException("Event not found"));

		event.setStatus("REJECTED");
		eventRepository.save(event);

		return "Event rejected successfully";
	}

}

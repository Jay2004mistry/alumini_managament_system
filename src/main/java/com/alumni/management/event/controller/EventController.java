package com.alumni.management.event.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alumni.management.event.entity.Event;
import com.alumni.management.event.service.EventService;

@RestController
@RequestMapping("api/events")
public class EventController {

	@Autowired
	private EventService eventService;

	@PostMapping("/{userId}")

	public String createEvent(@PathVariable Long userId, @RequestBody Event event) {

		return eventService.createEvent(userId, event);
	}
//get approved event(publiclly)
	@GetMapping
	public List<Event> getApprovedEvents() {
		return eventService.getApprovedEvents();
	}
//My event
	@GetMapping("/my/{userId}")
	public List<Event> getMyEvents(@PathVariable Long userId) {
		return eventService.getMyEvents(userId);
	}

//	Now only admin can access below 3
	@GetMapping("/admin/pending")
	public List<Event> getPendingEvents() {
		return eventService.getPendingEvents();
	}

	@PutMapping("/admin/approve/{eventId}")
	public String approveEvent(@PathVariable Long eventId) {
		return eventService.approveEvent(eventId);
	}

	@PutMapping("/admin/reject/{eventId}")
	public String rejectEvent(@PathVariable Long eventId) {
		return eventService.rejectEvent(eventId);
	}

}

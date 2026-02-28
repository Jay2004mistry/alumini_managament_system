package com.alumni.management.event.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class EventDto {
	private String title;
	private String description;
	private String location;

	private LocalDate eventDate;

	private String status; // PENDING, APPROVED, REJECTED

	private LocalDateTime createdAt;
//	Add this new 1 if code not work remove it
	private String imageUrl;

	public EventDto(String title, String description, String location, LocalDate eventDate, String status,
			LocalDateTime createdAt, String imageUrl) {
		super();
		this.title = title;
		this.description = description;
		this.location = location;
		this.eventDate = eventDate;
		this.status = status;
		this.createdAt = createdAt;
		this.imageUrl = imageUrl;
	}

	public EventDto() {
		super();
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public String getLocation() {
		return location;
	}

	public LocalDate getEventDate() {
		return eventDate;
	}

	public String getStatus() {
		return status;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public String getImageUrl() {
		return imageUrl;
	}
	

}

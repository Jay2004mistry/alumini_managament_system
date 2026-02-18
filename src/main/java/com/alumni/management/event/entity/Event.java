package com.alumni.management.event.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.alumni.management.user.entity.User;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Event {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	private String title;
	private String description;
	private String location;

	private LocalDate eventDate;

	private String status; // PENDING, APPROVED, REJECTED

	private LocalDateTime createdAt;

	@ManyToOne
	@JoinColumn(name = "created_by")
	private User createdBy;

	public Event(Long id, String title, String description, String location, LocalDate eventDate, String status,
			LocalDateTime createdAt, User createdBy) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.location = location;
		this.eventDate = eventDate;
		this.status = status;
		this.createdAt = createdAt;
		this.createdBy = createdBy;
	}
	
	

	public Event() {
		super();
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public LocalDate getEventDate() {
		return eventDate;
	}

	public void setEventDate(LocalDate eventDate) {
		this.eventDate = eventDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

}

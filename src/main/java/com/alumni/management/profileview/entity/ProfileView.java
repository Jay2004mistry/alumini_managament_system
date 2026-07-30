package com.alumni.management.profileview.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "profile_views")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProfileView {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String viewerEmail;
	private String viewerName;
	private String ownerEmail;
	private LocalDateTime timestamp;

}

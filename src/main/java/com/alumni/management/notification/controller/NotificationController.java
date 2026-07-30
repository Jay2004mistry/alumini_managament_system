package com.alumni.management.notification.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alumni.management.notification.entity.Notification;
import com.alumni.management.notification.repository.NotificationRepository;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

	@Autowired
	private NotificationRepository notificationRepository;

	@GetMapping
	public List<Notification> getAllNotifications() {
		return notificationRepository.findAllByOrderByTimestampDesc();
	}

	@PostMapping("/mark-read")
	public String markAllAsRead() {
		List<Notification> all = notificationRepository.findAll();
		for (Notification notif : all) {
			notif.setRead(true);
			notificationRepository.save(notif);
		}
		return "All notifications marked as read";
	}

	@GetMapping("/unread-count")
	public long getUnreadCount() {
		return notificationRepository.countByIsReadFalse();
	}

}

package com.alumni.management.chat.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alumni.management.chat.entity.ChatMessage;
import com.alumni.management.chat.repository.ChatMessageRepository;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

	@Autowired
	private SimpMessagingTemplate messagingTemplate;

	@Autowired
	private ChatMessageRepository chatMessageRepository;

	private String getCurrentUserEmail() {
		return SecurityContextHolder.getContext().getAuthentication().getName();
	}

	@MessageMapping("/chat.send")
	public void sendMessage(@Payload ChatMessage chatMessage) {
		chatMessage.setTimestamp(LocalDateTime.now());
		chatMessage.setRead(false);
		
		// If sender isn't explicitly set, default to sender authentication name
		if (chatMessage.getSender() == null || chatMessage.getSender().isEmpty()) {
			chatMessage.setSender(getCurrentUserEmail());
		}

		ChatMessage saved = chatMessageRepository.save(chatMessage);

		// Send to the receiver dynamically
		messagingTemplate.convertAndSendToUser(
				chatMessage.getReceiver(),
				"/queue/messages",
				saved
		);
		// Echo back to the sender so their chat updates instantly
		messagingTemplate.convertAndSendToUser(
				chatMessage.getSender(),
				"/queue/messages",
				saved
		);
	}

	@GetMapping("/history/{receiverEmail}")
	public List<ChatMessage> getChatHistory(@PathVariable String receiverEmail) {
		String myEmail = getCurrentUserEmail();
		
		// Mark messages from receiver to me as read
		List<ChatMessage> unread = chatMessageRepository.findBySenderAndReceiverAndIsReadFalse(receiverEmail, myEmail);
		for (ChatMessage msg : unread) {
			msg.setRead(true);
			chatMessageRepository.save(msg);
		}

		return chatMessageRepository.findChatHistory(myEmail, receiverEmail);
	}

	@GetMapping("/unread")
	public Map<String, Integer> getUnreadCounts() {
		String myEmail = getCurrentUserEmail();
		List<ChatMessage> unreadMessages = chatMessageRepository.findByReceiverAndIsReadFalse(myEmail);
		
		Map<String, Integer> counts = new HashMap<>();
		for (ChatMessage msg : unreadMessages) {
			counts.put(msg.getSender(), counts.getOrDefault(msg.getSender(), 0) + 1);
		}
		return counts;
	}

	@GetMapping("/conversations")
	public List<ChatMessage> getConversations() {
		String myEmail = getCurrentUserEmail();
		List<ChatMessage> allMsgs = chatMessageRepository.findConversations(myEmail);

		List<ChatMessage> conversations = new ArrayList<>();
		Set<String> processedUsers = new HashSet<>();

		for (ChatMessage msg : allMsgs) {
			String otherUser = msg.getSender().equals(myEmail) ? msg.getReceiver() : msg.getSender();
			if (!processedUsers.contains(otherUser)) {
				processedUsers.add(otherUser);
				conversations.add(msg);
			}
		}
		return conversations;
	}
}

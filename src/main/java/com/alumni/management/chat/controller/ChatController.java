package com.alumni.management.chat.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alumni.management.chat.entity.ChatMessage;
import com.alumni.management.chat.repository.ChatMessageRepository;

import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@RequestMapping("/api/chat")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ChatController {

	@Autowired
	private SimpMessagingTemplate messagingTemplate;

	@Autowired
	private ChatMessageRepository chatMessageRepository;

	private String getCurrentUserEmail() {
		String auth = SecurityContextHolder.getContext().getAuthentication().getName();
		return auth != null ? auth.trim().toLowerCase() : "";
	}

	@MessageMapping("/chat.send")
	public void sendMessage(@Payload ChatMessage chatMessage) {
		chatMessage.setTimestamp(LocalDateTime.now());
		chatMessage.setRead(false);
		
		String sender = (chatMessage.getSender() != null && !chatMessage.getSender().trim().isEmpty())
				? chatMessage.getSender().trim().toLowerCase()
				: getCurrentUserEmail();
		String receiver = chatMessage.getReceiver() != null ? chatMessage.getReceiver().trim().toLowerCase() : "";

		chatMessage.setSender(sender);
		chatMessage.setReceiver(receiver);

		ChatMessage saved = chatMessageRepository.save(chatMessage);

		try {
			messagingTemplate.convertAndSendToUser(receiver, "/queue/messages", saved);
			messagingTemplate.convertAndSendToUser(sender, "/queue/messages", saved);
		} catch (Exception e) {
			// Log WebSocket error silently
		}
	}

	@PostMapping("/send")
	public ChatMessage sendRestMessage(@RequestBody ChatMessage chatMessage) {
		chatMessage.setTimestamp(LocalDateTime.now());
		chatMessage.setRead(false);

		String sender = (chatMessage.getSender() != null && !chatMessage.getSender().trim().isEmpty())
				? chatMessage.getSender().trim().toLowerCase()
				: getCurrentUserEmail();
		String receiver = chatMessage.getReceiver() != null ? chatMessage.getReceiver().trim().toLowerCase() : "";

		chatMessage.setSender(sender);
		chatMessage.setReceiver(receiver);

		ChatMessage saved = chatMessageRepository.save(chatMessage);

		try {
			messagingTemplate.convertAndSendToUser(receiver, "/queue/messages", saved);
			messagingTemplate.convertAndSendToUser(sender, "/queue/messages", saved);
		} catch (Exception e) {
			// Log WebSocket error silently
		}

		return saved;
	}

	@GetMapping("/history/{receiverEmail}")
	public List<ChatMessage> getChatHistory(@PathVariable String receiverEmail) {
		String myEmail = getCurrentUserEmail();
		String targetEmail = receiverEmail != null ? receiverEmail.trim().toLowerCase() : "";
		
		// Mark all unread messages from target to me as READ
		List<ChatMessage> unread = chatMessageRepository.findBySenderIgnoreCaseAndReceiverIgnoreCaseAndIsReadFalse(targetEmail, myEmail);
		if (!unread.isEmpty()) {
			for (ChatMessage msg : unread) {
				msg.setRead(true);
			}
			chatMessageRepository.saveAll(unread);
		}

		// Fetch messages in both directions
		List<ChatMessage> sentByMe = chatMessageRepository.findBySenderIgnoreCaseAndReceiverIgnoreCase(myEmail, targetEmail);
		List<ChatMessage> sentToMe = chatMessageRepository.findBySenderIgnoreCaseAndReceiverIgnoreCase(targetEmail, myEmail);

		List<ChatMessage> all = new ArrayList<>(sentByMe);
		all.addAll(sentToMe);

		// Deduplicate by ID and sort chronologically by timestamp
		Map<String, ChatMessage> map = new HashMap<>();
		for (ChatMessage msg : all) {
			if (msg.getId() != null) {
				map.put(msg.getId(), msg);
			}
		}

		return map.values().stream()
				.sorted(Comparator.comparing(ChatMessage::getTimestamp, Comparator.nullsFirst(Comparator.naturalOrder())))
				.collect(Collectors.toList());
	}

	@GetMapping("/unread")
	public Map<String, Integer> getUnreadCounts() {
		String myEmail = getCurrentUserEmail();
		List<ChatMessage> unreadMessages = chatMessageRepository.findByReceiverIgnoreCaseAndIsReadFalse(myEmail);
		
		Map<String, Integer> counts = new HashMap<>();
		for (ChatMessage msg : unreadMessages) {
			if (msg.getSender() != null) {
				String senderLower = msg.getSender().trim().toLowerCase();
				counts.put(senderLower, counts.getOrDefault(senderLower, 0) + 1);
			}
		}
		return counts;
	}

	@GetMapping("/conversations")
	public List<ChatMessage> getConversations() {
		String myEmail = getCurrentUserEmail();
		List<ChatMessage> allMsgs = chatMessageRepository.findBySenderIgnoreCaseOrReceiverIgnoreCase(myEmail, myEmail);

		allMsgs.sort(Comparator.comparing(ChatMessage::getTimestamp, Comparator.nullsFirst(Comparator.reverseOrder())));

		List<ChatMessage> conversations = new ArrayList<>();
		Set<String> processedUsers = new HashSet<>();

		for (ChatMessage msg : allMsgs) {
			String sender = msg.getSender() != null ? msg.getSender().trim().toLowerCase() : "";
			String receiver = msg.getReceiver() != null ? msg.getReceiver().trim().toLowerCase() : "";

			String otherUser = sender.equals(myEmail) ? receiver : sender;
			if (!otherUser.isEmpty() && !processedUsers.contains(otherUser)) {
				processedUsers.add(otherUser);
				conversations.add(msg);
			}
		}
		return conversations;
	}
}

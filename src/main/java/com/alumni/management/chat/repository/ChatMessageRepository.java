package com.alumni.management.chat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.alumni.management.chat.entity.ChatMessage;

@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {

	@Query("SELECT m FROM ChatMessage m WHERE (m.sender = :user1 AND m.receiver = :user2) OR (m.sender = :user2 AND m.receiver = :user1) ORDER BY m.timestamp ASC")
	List<ChatMessage> findChatHistory(@Param("user1") String user1, @Param("user2") String user2);

	@Query("SELECT m FROM ChatMessage m WHERE m.sender = :email OR m.receiver = :email ORDER BY m.timestamp DESC")
	List<ChatMessage> findConversations(@Param("email") String email);

	List<ChatMessage> findByReceiverAndIsReadFalse(String receiver);

	List<ChatMessage> findBySenderAndReceiverAndIsReadFalse(String sender, String receiver);

}

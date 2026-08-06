package com.alumni.management.chat.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.alumni.management.chat.entity.ChatMessage;

@Repository
public interface ChatMessageRepository extends MongoRepository<ChatMessage, String> {

	List<ChatMessage> findBySenderIgnoreCaseAndReceiverIgnoreCase(String sender, String receiver);

	List<ChatMessage> findBySenderIgnoreCaseOrReceiverIgnoreCase(String sender, String receiver);

	List<ChatMessage> findByReceiverIgnoreCaseAndIsReadFalse(String receiver);

	List<ChatMessage> findBySenderIgnoreCaseAndReceiverIgnoreCaseAndIsReadFalse(String sender, String receiver);

}

package com.alumni.management.chat.config;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import com.alumni.management.chat.dto.PresenceEvent;

@Component
public class PresenceEventListener {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    private static final Map<String, Integer> onlineUserSessions = new ConcurrentHashMap<>();
    private static final Map<String, LocalDateTime> userLastSeen = new ConcurrentHashMap<>();

    @EventListener
    public void handleWebSocketConnectListener(SessionConnectedEvent event) {
        Principal user = event.getUser();
        if (user != null && user.getName() != null) {
            String email = user.getName().trim().toLowerCase();
            int count = onlineUserSessions.merge(email, 1, Integer::sum);
            if (count == 1) {
                PresenceEvent presence = new PresenceEvent(email, "ONLINE", LocalDateTime.now());
                messagingTemplate.convertAndSend("/topic/presence", presence);
            }
        }
    }

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        Principal user = event.getUser();
        if (user != null && user.getName() != null) {
            String email = user.getName().trim().toLowerCase();
            onlineUserSessions.computeIfPresent(email, (k, count) -> {
                int newCount = count - 1;
                if (newCount <= 0) {
                    LocalDateTime now = LocalDateTime.now();
                    userLastSeen.put(email, now);
                    PresenceEvent presence = new PresenceEvent(email, "OFFLINE", now);
                    messagingTemplate.convertAndSend("/topic/presence", presence);
                    return null;
                }
                return newCount;
            });
        }
    }

    public static boolean isUserOnline(String email) {
        if (email == null) return false;
        return onlineUserSessions.getOrDefault(email.trim().toLowerCase(), 0) > 0;
    }

    public static LocalDateTime getLastSeen(String email) {
        if (email == null) return null;
        return userLastSeen.get(email.trim().toLowerCase());
    }
}

package com.alumni.management.chat.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TypingEvent {
    private String sender;
    private String receiver;
    private boolean isTyping;
}

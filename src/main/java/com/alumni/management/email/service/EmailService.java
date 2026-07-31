package com.alumni.management.email.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired(required = false)
    private JavaMailSender mailSender;

    @Value("${spring.mail.username:your_email@gmail.com}")
    private String fromEmail;

    public void sendOtpEmail(String toEmail, String otp) {
        if (mailSender == null) {
            System.out.println("⚠️ JavaMailSender bean is not configured properly.");
            return;
        }

        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(fromEmail);
            message.setTo(toEmail);
            message.setSubject("GLS Connect - Password Reset OTP");
            message.setText("Dear User,\n\n"
                    + "Your One-Time Password (OTP) for resetting your password on GLS Connect is:\n\n"
                    + "🔑 " + otp + "\n\n"
                    + "This OTP is valid for 15 minutes. Please do not share this OTP with anyone.\n\n"
                    + "Best regards,\n"
                    + "GLS Connect Team");

            mailSender.send(message);
            System.out.println("✅ Real OTP Email sent successfully to " + toEmail);
        } catch (Exception e) {
            System.err.println("❌ Failed to send OTP email to " + toEmail + ": " + e.getMessage());
            // Fallback log so execution doesn't crash if SMTP credentials aren't set yet
            System.out.println("🔑 Fallback Logged OTP for " + toEmail + ": " + otp);
        }
    }
}

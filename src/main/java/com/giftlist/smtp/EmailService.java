package com.giftlist.smtp;

import jakarta.mail.MessagingException;
import org.springframework.http.ResponseEntity;
import org.thymeleaf.context.Context;

public interface EmailService {
    String sendSimpleMail(EmailRequest details);
    String sendMailWithAttachment(EmailRequest details);
    ResponseEntity<String> sendHTMLEmail(String to, String subject, String template, Context context) throws MessagingException;
}

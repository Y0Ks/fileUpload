package com.sorawee.fileUpload.notification.service;

public interface EmailService {
    boolean sentEmail(String recipient, String subject, String content);
}

package com.sorawee.fileUpload.notification.service;

import jakarta.mail.Message;
import jakarta.mail.Multipart;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Properties;
@Service
public class EmailServiceImpl implements EmailService {
    private static final Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);

    public boolean sentEmail(String recipient, String subject, String content) {
        try {
            Session session = Session.getInstance(getPropertiesSMTP());

            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress("yok.sorawee@gmail.com"));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            message.setSubject(subject);

            Multipart multipart = new MimeMultipart();
            // Body
            MimeBodyPart textBodyPart = new MimeBodyPart();
            textBodyPart.setContent(content, "text/html;charset=utf-8");

            multipart.addBodyPart(textBodyPart);

            message.setContent(multipart);
            Transport.send(message);
            logger.info("Email sent successfully to " + recipient + " with content :" + content);
        } catch (Exception e) {
            logger.error("Error in transferFile : " + e);
            return false;
        }
        return true;
    }

    private Properties getPropertiesSMTP() {

        Properties properties = new Properties();
        properties.setProperty("mail.transport.protocol", "smtp");
        properties.setProperty("mail.smtp.auth", "auth");
        properties.setProperty("mail.smtp.host", "host");
        properties.setProperty("mail.smtp.port", "port");
        return properties;

    }
}

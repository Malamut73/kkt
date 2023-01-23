package com.tehnology.kkt.services;

import com.tehnology.kkt.models.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    private final JavaMailSender mailSender;

    public MailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Value("${mail.server.username}")
    private String username;

    private void sendMail(String email, String subject, String content){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(username);
        message.setTo(email);
        message.setSubject(subject);
        message.setText(content);
        mailSender.send(message);
    }

    public void sendPassword(String pass, User user) {
        String subject = "Пароль от аккаунта";
        String content = "Ваш логин от аккаунат: " + user.getEmail() + ".\n"
                + "Ваш пароль от аккаунта: " + pass + ".";
        sendMail(user.getEmail(), subject, content);
    }
}

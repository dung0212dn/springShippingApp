/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dvtd.service.impl;

import com.dvtd.service.EmailSenderService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 *
 * @author ACER
 */
@Service
public class EmailSenderImpl implements EmailSenderService {

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public boolean sendEmail(String to, String subject, String message) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("dung0212dny@gmail.com");
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(message);
        this.mailSender.send(simpleMailMessage);

        return true;
    }

    @Override
    public boolean sendEmailMultiRecepient(List<String> recipients, String subject, String message) {
        recipients.forEach(recipient -> {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom("dung0212dny@gmail.com");
            simpleMailMessage.setTo(recipient);
            simpleMailMessage.setSubject(subject);
            simpleMailMessage.setText(message);
            this.mailSender.send(simpleMailMessage);
        });
        return true;
    }

}

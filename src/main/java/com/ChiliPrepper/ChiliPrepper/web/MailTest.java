package com.ChiliPrepper.ChiliPrepper.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

/**
 * Created by Andreas on 13.03.2017.
 */

@Component
public class MailTest {



    @Autowired
    private JavaMailSender mailSender;

    public void send() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("nordbyandreas@hotmail.com");
        message.setTo("nordbyandreas@hotmail.com");
        message.setSubject("Testsss");
        mailSender.send(message);
    }

}

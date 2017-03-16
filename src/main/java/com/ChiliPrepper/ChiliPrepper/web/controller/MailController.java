package com.ChiliPrepper.ChiliPrepper.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Andreas on 15.03.2017.
 */

@RestController
public class MailController {


    @RequestMapping("/sendMail")
    public void sendMail() {

        String from = "chiliprepper.bot";
        String pass = "ChiliPrepperPassword";
        String[] to = { "nordbyandreas@hotmail.com" }; // list of recipient email addresses
        String subject = "Java send mail example";
        String body = "Welcome to JavaMail!";

        MailSenderTest mailSenderTest = new MailSenderTest();

        mailSenderTest.sendFromGMail(from, pass, to, subject, body);
    }



}

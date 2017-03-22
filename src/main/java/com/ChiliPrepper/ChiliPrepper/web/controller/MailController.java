package com.ChiliPrepper.ChiliPrepper.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Andreas on 15.03.2017.
 */

@RestController
public class MailController {

    //Vi burde nok prøve å utvide URL (kanskje?) på en slik måte at vi kan legge til bruker (altså personen det skal
    //sendes e-post til) som en parameter i sendMail-metoden.
    @RequestMapping("/sendMail")
    public void sendMail() {

        String from = "chiliprepper.bot";
        String pass = "ChiliPrepperPassword";
        String[] to = { "chiliprepper.bot@gmail.com" }; // list of recipient email addresses
        String subject = "Java send mail example";
        String body = "Welcome to JavaMail!";

        BotMailSender botMailSender = new BotMailSender();

        botMailSender.sendFromGMail(to, subject, body);
    }



}

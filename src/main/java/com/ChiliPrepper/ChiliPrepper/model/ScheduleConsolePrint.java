package com.ChiliPrepper.ChiliPrepper.model;

/**
 * Created by Christer on 16.03.2017.
 */

import java.text.SimpleDateFormat;
import java.util.Date;

import com.ChiliPrepper.ChiliPrepper.web.controller.MailController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduleConsolePrint {

    private static final Logger log = LoggerFactory.getLogger(ScheduleConsolePrint.class);
    private MailController mailController = new MailController();
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = 20000)
    public void reportCurrentTime() {
        log.info("Mail sent.");
        mailController.sendMail();
    }
}

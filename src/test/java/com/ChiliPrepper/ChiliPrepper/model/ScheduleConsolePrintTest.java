package com.ChiliPrepper.ChiliPrepper.model;

import com.ChiliPrepper.ChiliPrepper.web.controller.MailController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;

import static org.junit.Assert.*;

/**
 * Created by dagki on 22/03/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class ScheduleConsolePrintTest {

    @Mock
    private Logger logger;
    @Mock
    private MailController mailController;
    @Mock
    private SimpleDateFormat simpleDateFormat;
    @InjectMocks
    private ScheduleConsolePrint scheduleConsolePrint;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void reportCurrentTime() throws Exception {

    }

}
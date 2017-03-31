package com.ChiliPrepper.ChiliPrepper.web;

import com.sun.net.httpserver.Authenticator;
import org.junit.Before;
import org.junit.Test;

import static com.ChiliPrepper.ChiliPrepper.web.FlashMessage.*;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.*;

/**
 * Created by dagki on 29/03/2017.
 */
public class FlashMessageTest {
    FlashMessage flashMessage;

    @Before
    public void setUp() throws Exception {
        flashMessage = new FlashMessage(null, null);
    }

    @Test
    public void getMessage() throws Exception {
        String message = "message";
        assertNull("Question ID should be null", flashMessage.getMessage());
        flashMessage.setMessage(message);
        assertEquals("Question ID should be set to the assigned value", message, flashMessage.getMessage());
    }

    @Test
    public void setMessage() throws Exception {
        assertThat(Status.valueOf("INFO"), is(notNullValue()));
        assertThat(Status.valueOf("FAILURE"), is(notNullValue()));
        assertThat(Status.valueOf("SUCCESS"), is(notNullValue()));
    }

    @Test
    public void getStatus() throws Exception {
        FlashMessage.Status statusInfo = Status.INFO;
        FlashMessage.Status statusFailure = Status.FAILURE;
        FlashMessage.Status statusSuccess = Status.SUCCESS;
        assertNull("Question ID should be null", flashMessage.getStatus());
        flashMessage.setStatus(statusInfo);
        assertEquals("Question ID should be set to the assigned value", statusInfo, flashMessage.getStatus());
        flashMessage.setStatus(statusFailure);
        assertEquals("Question ID should be set to the assigned value", statusFailure, flashMessage.getStatus());
        flashMessage.setStatus(statusSuccess);
        assertEquals("Question ID should be set to the assigned value", statusSuccess, flashMessage.getStatus());
    }
}
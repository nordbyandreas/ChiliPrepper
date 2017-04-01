package com.ChiliPrepper.ChiliPrepper.web;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static com.ChiliPrepper.ChiliPrepper.web.FlashMessage.*;

/**
 * Created by dagki on 29/03/2017.
 */

public class FlashMessageTest {
    FlashMessage flashMessage;
    FlashMessage.Status statusInfo = Status.INFO;
    FlashMessage.Status statusFailure = Status.FAILURE;
    FlashMessage.Status statusSuccess = Status.SUCCESS;

    @Before
    public void setUp() throws Exception {
        statusInfo = Status.INFO;
        statusFailure = Status.FAILURE;
        statusSuccess = Status.SUCCESS;
        flashMessage = new FlashMessage(null, null);
    }

    /**Confirms that the statuses INFO, FAILURE and SUCCESS exists.
     * */
    @Test
    public void Status() throws Exception {
        assertThat("Status INFO should exist", Status.valueOf("INFO"), is(notNullValue()));
        assertThat("Status FAILURE should exist", Status.valueOf("FAILURE"), is(notNullValue()));
        assertThat("Status STATUS should exist", Status.valueOf("SUCCESS"), is(notNullValue()));
    }

    /**First confirms that the flash message's message ain't assigned,
     * then assigns the message
     * and concludes by confirming that the message is assigned to the flash message.*/
    @Test
    public void getAndSetMessage() throws Exception {
        String message = "message";
        assertNull("Flash message ain't assigned, and should return: null", flashMessage.getMessage());
        flashMessage.setMessage(message);
        assertEquals("Flash message is assigned, and should return: message (String)", message, flashMessage.getMessage());
    }

    /**First confirms that the flash message's status ain't assigned,
     * then assigns the INFO status and confirms that it's assigned to the flash message,
     * then assigns the FAILURE status and confirms that it's assigned to the flash message,
     * and concludes by then assigning the SUCCESS status and confirms that it's assigned to the flash message,*/
    @Test
    public void getAndSetStatus() throws Exception {
        assertNull("Flash status ain't assigned, and should return: null", flashMessage.getStatus());
        flashMessage.setStatus(statusInfo);
        assertEquals("Flash message is assigned, and should return: INFO (Status)", statusInfo, flashMessage.getStatus());
        flashMessage.setStatus(statusFailure);
        assertEquals("Flash message is assigned, and should return: FAILURE (Status)", statusFailure, flashMessage.getStatus());
        flashMessage.setStatus(statusSuccess);
        assertEquals("Flash message is assigned, and should return: SUCCESS (Status)", statusSuccess, flashMessage.getStatus());
    }
}
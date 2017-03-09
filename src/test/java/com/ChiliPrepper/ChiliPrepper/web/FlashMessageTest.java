package com.ChiliPrepper.ChiliPrepper.web;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by dagki on 09/03/2017.
 */
public class FlashMessageTest {
    String message;
    FlashMessage.Status status;
    FlashMessage flashMessage;

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void flashMessage() throws Exception {
        assertNull("Flash message should be null before ", flashMessage.getMessage());

    }

    @Test
    public void getStatus() throws Exception {

    }

    @Test
    public void setStatus() throws Exception {

    }

}
package com.ChiliPrepper.ChiliPrepper.web.controller;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.Assert.*;

/**
 * Created by dagki on 03/03/2017.
 */
public class LoginControllerTest {
    private MockMvc mockMvc;
    private LoginController controller;

    @Before
    public void setUp() throws Exception {
        controller = new LoginController();
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void loginForm() throws Exception {

    }

    @Test
    public void accessDenied() throws Exception {

    }

    @Test
    public void logout() throws Exception {

    }

}
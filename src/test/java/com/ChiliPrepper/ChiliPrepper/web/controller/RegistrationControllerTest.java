package com.ChiliPrepper.ChiliPrepper.web.controller;


import com.ChiliPrepper.ChiliPrepper.model.User;
import com.ChiliPrepper.ChiliPrepper.service.UserService;
import org.junit.Test;
import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * Created by dagki on 04/03/2017.
 */
public class RegistrationControllerTest {
    private MockMvc mockMvc;

    @InjectMocks
    private RegistrationController controller;

    @Mock
    private UserService service;

    @Before
    public void setUp() throws Exception {
        controller = new RegistrationController();
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void regForm() throws Exception {
        mockMvc.perform(get("/register.html")).andExpect(view().name("registration"));
    }
    /*
    @Test
    public void regUser() throws Exception {
        mockMvc.perform(post("/register.html")).andExpect(redirectedUrl("redirect:/login"));
    }
    */
}
package com.ChiliPrepper.ChiliPrepper.web.controller;


import com.ChiliPrepper.ChiliPrepper.model.User;
import com.ChiliPrepper.ChiliPrepper.service.UserService;
import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by dagki on 04/03/2017.
 */

public class RegistrationControllerTest {
    private MockMvc mockMvc;

    @InjectMocks
    private RegistrationController controller;

    @Mock
    private UserService service;

    @Mock
    private User user = new User();

    @Mock
    private Model model;

    @Before
    public void setUp() throws Exception {
        controller = new RegistrationController();
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void regForm() throws Exception {
        mockMvc.perform(get("/register.html"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("user"))
                .andExpect(view().name("registration"));

    }

    @Test
    public void regUser() throws Exception {
        mockMvc.perform(post("/register.html"))
                .andExpect(status().isOk())
                /*.andExpect(view().name("redirect:/login"))*/;
    }

}
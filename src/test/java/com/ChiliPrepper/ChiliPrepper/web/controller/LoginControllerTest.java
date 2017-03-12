package com.ChiliPrepper.ChiliPrepper.web.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

/**
 * Created by dagki on 03/03/2017.
 */

@RunWith(MockitoJUnitRunner.class)
public class LoginControllerTest {
    private MockMvc mockMvc;

    @InjectMocks
    private LoginController controller = new LoginController();

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void loginForm() throws Exception {
        mockMvc.perform(get("/login.html"))
            .andExpect(status().isOk())
            .andExpect(model().attributeExists("user"))
            .andExpect(view().name("login"));
    }

    @Test
    public void accessDenied() throws Exception {
        mockMvc.perform(get("/access_denied.html"))
                .andExpect(status().isOk())
                .andExpect(view().name("access_denied"));
    }

    @Test
    public void logout() throws Exception {
        mockMvc.perform(get("/logout.html"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login"));
    }

}
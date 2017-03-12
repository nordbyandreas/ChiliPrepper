package com.ChiliPrepper.ChiliPrepper.web.controller;

import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;

import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

/**
 * Created by dagki on 03/03/2017.
 */

@RunWith(MockitoJUnitRunner.class)
public class ProfileControllerTest {
    private MockMvc mockMvc;
    private ProfileController controller;

    @Before
    public void setup() {
        controller = new ProfileController();
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void profileShouldRenderProfileView() throws Exception {
        mockMvc.perform(get("/profile.html"))
                .andExpect(status().isOk())
                .andExpect(view().name("profile"));
    }

    @Test
    public void aboutShouldRenderAboutView() throws Exception {
        mockMvc.perform(get("/about.html"))
                .andExpect(status().isOk())
                .andExpect(view().name("about"));
    }

}
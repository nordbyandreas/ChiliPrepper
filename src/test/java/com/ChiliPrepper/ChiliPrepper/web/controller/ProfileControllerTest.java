package com.ChiliPrepper.ChiliPrepper.web.controller;

import com.ChiliPrepper.ChiliPrepper.model.User;
import com.ChiliPrepper.ChiliPrepper.service.UserService;
import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.security.Principal;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

/**
 * Created by dagki on 03/03/2017.
 */

@RunWith(MockitoJUnitRunner.class)
public class ProfileControllerTest {
    private MockMvc mockMvc;

    @Mock
    UserService userService;

    @Mock
    User user;

    @Mock
    Principal principal;

    @InjectMocks
    private ProfileController controller;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void profileShouldRenderProfileView() throws Exception {
        String username = "username";
        when(principal.getName()).thenReturn(username);
        when(userService.findByUsername(username)).thenReturn(user);
        mockMvc.perform(get("/profile.html")
                .principal(principal))
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
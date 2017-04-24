package com.ChiliPrepper.ChiliPrepper.web.controller;

import org.junit.Test;
import org.junit.Before;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.security.core.context.SecurityContextHolder;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

/**
 * Created by Dag Kirstihagen 03/03/2017.
 *
 * The mapping methods in the controller class will confirm that:
 * 1. All model- and flash attributes are invoked
 * 2. All save and delete calls on service objects are invoked
 * 3. The request status correspond to the expected outcome
 * 4. The view name or redirected url correspond to the expected outcome
 */

@RunWith(MockitoJUnitRunner.class)
public class LoginControllerTest {
    private MockMvc mockMvc;

    @Mock
    Authentication authentication;

    @Mock
    SecurityContext securityContext;

    @InjectMocks
    private LoginController controller;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void renderLoginView() throws Exception {
        mockMvc.perform(get("/login.html"))

                .andExpect(model().attributeExists("user"))

                .andExpect(status().isOk())
                .andExpect(view().name("login"));
    }

    @Test
    public void renderAccessDeniedView() throws Exception {
        mockMvc.perform(get("/access_denied.html"))

                .andExpect(status().isOk())
                .andExpect(view().name("access_denied"));
    }

}
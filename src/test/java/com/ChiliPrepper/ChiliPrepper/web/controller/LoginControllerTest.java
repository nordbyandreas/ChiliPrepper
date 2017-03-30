package com.ChiliPrepper.ChiliPrepper.web.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestFilter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.mockito.Mockito.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by dagki on 03/03/2017.
 */

@RunWith(MockitoJUnitRunner.class)
@EnableAutoConfiguration(exclude = { SecurityAutoConfiguration.class})
public class LoginControllerTest {
    private MockMvc mockMvc;



    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private SecurityContextHolder securityContextHolder = new SecurityContextHolder();

    @Mock
    HttpSession httpSession;

    @Mock
    SecurityContext securityContext = new SecurityContextImpl();

    @Mock
    Authentication authentication;

    @InjectMocks
    private LoginController controller;
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void loginForm() throws Exception {
        mockMvc.perform(get("/login.html"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"))
                .andExpect(model().attributeExists("user"));
    }

    /*@Test
    public void logindsForm() throws Exception {
        when(request.getSession()).thenReturn(httpSession);
        when(httpSession.getAttribute("flash")).thenReturn(null);
        mockMvc.perform(get("/login.html"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"))
                .andExpect(model().attributeExists("user"));
    }*/

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
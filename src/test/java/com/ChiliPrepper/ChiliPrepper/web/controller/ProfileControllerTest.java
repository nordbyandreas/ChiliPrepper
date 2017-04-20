package com.ChiliPrepper.ChiliPrepper.web.controller;

import org.junit.Test;
import org.mockito.Mock;
import org.junit.Before;
import java.security.Principal;
import org.mockito.InjectMocks;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import com.ChiliPrepper.ChiliPrepper.model.User;
import com.ChiliPrepper.ChiliPrepper.web.FlashMessage;
import com.ChiliPrepper.ChiliPrepper.service.UserService;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.hamcrest.Matchers.hasProperty;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

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
public class ProfileControllerTest {
    private MockMvc mockMvc;

    @Mock
    private User user;

    @Mock
    private Principal principal;

    @Mock
    private UserService userService;

    @InjectMocks
    private ProfileController controller;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void profile_RendersProfileView() throws Exception {
        //Finds the logged in user
        when(principal.getName()).thenReturn("username");
        when(userService.findByUsername("username")).thenReturn(user);

        mockMvc.perform(get("/profile.html")
                .principal(principal))

                .andExpect(model().attribute("user", user))

                .andExpect(status().isOk())
                .andExpect(view().name("profile"));
    }



    @Test
    public void about_RendersAboutView() throws Exception {
        mockMvc.perform(get("/about.html"))

                .andExpect(status().isOk())
                .andExpect(view().name("about"));
    }



    @Test
    public void saveBotDetails_RedirectsToProfile() throws Exception {
        mockMvc.perform(post("/saveBotDetails")
                .flashAttr("user", user))

                .andExpect(flash().attributeExists("flash"))
                .andExpect(flash().attribute("flash", hasProperty("message", is("Bot preferences saved. "))))
                .andExpect(flash().attribute("flash", hasProperty("status", is(FlashMessage.Status.SUCCESS))))

                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/profile"));

        verify(userService).save(user);
    }

}
package com.ChiliPrepper.ChiliPrepper.web.controller;

import org.junit.Test;
import org.mockito.Mock;
import org.junit.Before;
import org.hamcrest.Matchers;
import org.mockito.InjectMocks;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import com.ChiliPrepper.ChiliPrepper.model.User;
import org.springframework.test.web.servlet.MockMvc;
import com.ChiliPrepper.ChiliPrepper.web.FlashMessage;
import com.ChiliPrepper.ChiliPrepper.service.UserService;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

/**
 * Created by Dag Kirstihagen 04/03/2017.
 *
 * The mapping methods in the controller class will confirm that:
 * 1. All model- and flash attributes are invoked
 * 2. All save and delete calls on service objects are invoked
 * 3. The request status correspond to the expected outcome
 * 4. The view name or redirected url correspond to the expected outcome
 */

@RunWith(MockitoJUnitRunner.class)
public class RegistrationControllerTest {
    private MockMvc mockMvc;

    @Mock
    private User user;

    @Mock
    private UserService userService;

    @InjectMocks
    private RegistrationController controller;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void renderRegistrationFormView() throws Exception {
        mockMvc.perform(get("/register"))

                .andExpect(model().attribute("user", instanceOf(User.class)))

                .andExpect(status().isOk())
                .andExpect(view().name("registration"));
    }



    @Test
    public void registerUser_SuccessfullyCreatesUser_RedirectsToLogin() throws Exception {
        //Email and username are entered and aren't occupied, and should therefore successfully create a new user
        when(user.getEmail()).thenReturn("username@domain.com");
        when(user.getUsername()).thenReturn("username");
        when(userService.findByUsername("username")).thenReturn(null);
        when(user.getPassword()).thenReturn("password");

        mockMvc.perform(post("/register")
                .flashAttr("user", user))

                .andExpect(flash().attributeExists("flash"))
                .andExpect(flash().attribute("flash", hasProperty("message", Matchers.is("Success!  Welcome to ChiliPrepper, log in to start !"))))
                .andExpect(flash().attribute("flash", hasProperty("status", Matchers.is(FlashMessage.Status.SUCCESS))))

                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login"));

        verify(userService).save(user);
    }

    @Test
    public void registerUser_EmailNotEntered_RedirectsToRegister() throws Exception {
        //The email field is empty, which will cause a failure when trying to create the account
        when(user.getEmail()).thenReturn("");

        mockMvc.perform(post("/register")
                .flashAttr("user", user))

                .andExpect(flash().attributeExists("flash"))
                .andExpect(flash().attribute("flash", hasProperty("message", Matchers.is("Registration failed! You must include a correct email!"))))
                .andExpect(flash().attribute("flash", hasProperty("status", Matchers.is(FlashMessage.Status.FAILURE))))

                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/register"));
    }

    @Test
    public void registerUser_UsernameAlreadyTaken_RedirectsToRegister() throws Exception {
        //The user have entered its email and username in order to create the account
        when(user.getEmail()).thenReturn("username@domain.com");
        when(user.getUsername()).thenReturn("username");

        //The username is already occupied, which will cause a failure when trying to create an account
        when(userService.findByUsername("username")).thenReturn(new User());

        mockMvc.perform(post("/register")
                .flashAttr("user", user))

                .andExpect(flash().attributeExists("flash"))
                .andExpect(flash().attribute("flash", hasProperty("message", Matchers.is("Registration failed! The username is already taken!"))))
                .andExpect(flash().attribute("flash", hasProperty("status", Matchers.is(FlashMessage.Status.FAILURE))))

                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/register"));
    }

    @Test
    public void registerUser_TooShortPassword_RedirectsToRegister() throws Exception {
        //The user have entered its email and username in order to create the account
        when(user.getEmail()).thenReturn("username@domain.com");
        when(user.getUsername()).thenReturn("username");
        when(userService.findByUsername("username")).thenReturn(null);

        //The password is shorter than 6 characters, which will cause a failure when trying to create an account
        when(user.getPassword()).thenReturn("short");

        mockMvc.perform(post("/register")
                .flashAttr("user", user))

                .andExpect(flash().attributeExists("flash"))
                .andExpect(flash().attribute("flash", hasProperty("message", Matchers.is("Registration failed! Password must be at least 6 characters."))))
                .andExpect(flash().attribute("flash", hasProperty("status", Matchers.is(FlashMessage.Status.FAILURE))))

                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/register"));
    }

}
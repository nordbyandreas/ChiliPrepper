package com.ChiliPrepper.ChiliPrepper.web.controller;

import com.ChiliPrepper.ChiliPrepper.model.Course;
import com.ChiliPrepper.ChiliPrepper.model.Role;
import com.ChiliPrepper.ChiliPrepper.model.User;
import com.ChiliPrepper.ChiliPrepper.service.CourseService;
import com.ChiliPrepper.ChiliPrepper.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import sun.security.acl.PrincipalImpl;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.List;



import static org.junit.Assert.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.mockito.Mockito.*;


/**
 * Created by dagki on 04/03/2017.
 */


@RunWith(MockitoJUnitRunner.class)
public class CourseControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private CourseController controller;

    @Mock
    private UserService userService;

    @Mock
    private CourseService courseService;

    @Mock
    private Principal principal;

    @Mock
    private UsernamePasswordAuthenticationToken upat;

    @Mock
    Iterable<Course> myCourses;

    @Mock
    Set<Course> regCourses;

    @Mock
    User user;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        user.setUsername("Unit test");
    }

    @Test
    public void index_shouldRenderIndexView() throws Exception {
        when(courseService.findAll()).thenReturn(myCourses);
        when((User)upat.getPrincipal()).thenReturn(user);
        when(user.getRegCourses()).thenReturn(regCourses);
        when(user.getUsername()).thenReturn("Unit test");
        when(userService.findByUsername(user.getUsername())).thenReturn(user);

        //mockMvc.perform(get("/")).andExpect(status().isOk());
    }


    @Test
    public void course() throws Exception {
        //mockMvc.perform(get("/courses/{courseId}"));

    }

    @Test
    public void addCourse() throws Exception {
    /*
            mockMvc.perform(post("/addCourse"))
                .andExpect(model().attributeExists("course"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));

*/

    }

    @Test
    public void regCourse() throws Exception {
        //mockMvc.perform(post("/addCourse").param("accessCode", "accessCode"));
    }

}
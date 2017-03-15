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
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.List;


import static org.junit.Assert.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


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

    @Autowired
    private WebApplicationContext wac;


    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }



    @Test
    public void index_shouldRenderIndexView() throws Exception {

    }


    @Test
    public void course() throws Exception {

    }

    @Test
    public void addCourse() throws Exception {

    }

    @Test
    public void regCourse() throws Exception {

    }

}
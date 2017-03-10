package com.ChiliPrepper.ChiliPrepper.web.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by dagki on 04/03/2017.
 */
public class CourseControllerTest {
    private MockMvc mockMvc;
    private CourseController controller;

    @Before
    public void setUp() throws Exception {
        controller = new CourseController();
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void index() throws Exception {

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
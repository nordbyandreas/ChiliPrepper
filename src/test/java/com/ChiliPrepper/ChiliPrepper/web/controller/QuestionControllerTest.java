package com.ChiliPrepper.ChiliPrepper.web.controller;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.Assert.*;

/**
 * Created by dagki on 04/03/2017.
 */
public class QuestionControllerTest {
    private MockMvc mockMvc;
    private QuestionController controller;

    @Before
    public void setUp() throws Exception {
        controller = new QuestionController();
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void addQuestion() throws Exception {

    }

    @Test
    public void question() throws Exception {

    }

}
package com.ChiliPrepper.ChiliPrepper.web.controller;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.Assert.*;

/**
 * Created by dagki on 04/03/2017.
 */
public class QuizControllerTest {
    private MockMvc mockMvc;
    private QuizController controller;

    @Before
    public void setUp() throws Exception {
        controller = new QuizController();
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void addQuiz() throws Exception {

    }

    @Test
    public void quiz() throws Exception {

    }

    @Test
    public void publishQuiz() throws Exception {

    }

}
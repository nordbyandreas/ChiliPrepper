package com.ChiliPrepper.ChiliPrepper.web.controller;

import com.ChiliPrepper.ChiliPrepper.model.*;
import com.ChiliPrepper.ChiliPrepper.service.AlternativeService;
import com.ChiliPrepper.ChiliPrepper.service.QuestionService;
import com.ChiliPrepper.ChiliPrepper.service.QuizService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by dagki on 04/03/2017.
 */
public class QuestionControllerTest {
    private MockMvc mockMvc;

    @InjectMocks
    private QuestionController controller;

    @Mock
    QuestionService questionService;

    @Mock
    QuizService quizService;

    @Mock
    AlternativeService alternativeService;

    @Mock
    Question question;

    @Mock
    Quiz quiz;

    @Mock
    Course course;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void addQuestion() throws Exception {
        when(quizService.findOne(1L)).thenReturn(quiz);
        when(course.getId()).thenReturn(1L);
        when(quiz.getCourse()).thenReturn(course);

        mockMvc.perform(post("/addQuestion")
                .param("quizId", "1")
                .param("alt1", "Unit test")
                .param("alt2", "Acceptance test")
                .param("alt3", "Integration test"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/courses/1/1"));
        verify(quizService).findOne(1L);
        verify(questionService).save(any(Question.class));
        verify(alternativeService, times(3)).save(any(Alternative.class));
    }


    @Test
    public void question() throws Exception {
        when(questionService.findOne(1L)).thenReturn(question);
        mockMvc.perform(get
                ("/courses/{courseId}/{quizId}/{questionId}", 1L, 1L, 1L))
                .andExpect(status().isOk())
                .andExpect(view().name("question"))
                .andExpect(model().attributeExists("question"));
        verify(questionService).findOne(1L);
    }

}
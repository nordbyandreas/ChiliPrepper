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
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.ArrayList;
import java.util.Arrays;

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

    @Mock
    Iterable<Alternative> alternativeIterable;

    @Mock
    Alternative a1;

    @Mock
    Alternative a2;

    @Mock
    Alternative a3;

    @Before
    public void setUp() throws Exception {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/jsp/view/");
        viewResolver.setSuffix(".jsp");
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).setViewResolvers(viewResolver).build();
    }

    @Test
    public void addQuestion() throws Exception {
        when(course.getId()).thenReturn(1L);
        when(quiz.getCourse()).thenReturn(course);
        when(quizService.findOne(1L)).thenReturn(quiz);

        mockMvc.perform(post
                ("/addQuestion")
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

    @Test
    public void editQuestion() throws Exception {
        alternativeIterable = new ArrayList<>(Arrays.asList(a1, a2, a3));

        when(quiz.getId()).thenReturn(1L);
        when(course.getId()).thenReturn(1L);
        when(quiz.getCourse()).thenReturn(course);
        when(question.getQuiz()).thenReturn(quiz);
        when(questionService.findOne(1L)).thenReturn(question);
        when(alternativeService.findAllByQuestion_Id(1L)).thenReturn(alternativeIterable);

        mockMvc.perform(get
                ("/courses/{courseId}/{quizId}/{questionId}/editQuestion", 1L, 1L, 1L)
                .param("questionId", "1"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("alt1"))
                .andExpect(model().attributeExists("alt2"))
                .andExpect(model().attributeExists("alt3"))
                .andExpect(model().attributeExists("quizId"))
                .andExpect(model().attributeExists("courseId"))
                .andExpect(model().attributeExists("question"))
                .andExpect(view().name("editQuestion"));

        verify(questionService).findOne(1L);
        verify(alternativeService).findAllByQuestion_Id(1L);
    }

    @Test
    public void saveEditQuestion() throws Exception {
        when(quiz.getId()).thenReturn(1L);
        when(course.getId()).thenReturn(1L);
        when(question.getId()).thenReturn(1L);
        when(quiz.getCourse()).thenReturn(course);
        when(quizService.findOne(1L)).thenReturn(quiz);
        when(alternativeService.findOne(1L)).thenReturn(a1);
        when(alternativeService.findOne(2L)).thenReturn(a2);
        when(alternativeService.findOne(3L)).thenReturn(a3);
        when(questionService.findOne(1L)).thenReturn(question);

        mockMvc.perform(get("/courses/{courseId}/{quizId}/{questionId}/editQuestion/saveEditQuestion", 1L, 1L, 1L)
                .param("quizId", "1")
                .param("alt1Id", "1")
                .param("alt2Id", "2")
                .param("alt3Id", "3")
                .param("questionId", "1")
                .param("topic", "Testing")
                .param("alt1", "Unit test")
                .param("alt2", "Acceptance test")
                .param("alt3", "Integration test")
                .param("correctAnswer", "Unit test")
                .param("theQuestion", "What are this test?"))

                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/courses/1/1/1/editQuestion?questionId=1"));

        verify(alternativeService).findOne(1L);
        verify(alternativeService).findOne(2L);
        verify(alternativeService).findOne(3L);
        verify(questionService).save(any(Question.class));
        verify(alternativeService, times(3)).save(any(Alternative.class));
    }

    @Test
    public void deleteQuestion() throws Exception {
        when(quiz.getId()).thenReturn(1L);
        when(course.getId()).thenReturn(1L);
        when(quiz.getCourse()).thenReturn(course);
        when(question.getQuiz()).thenReturn(quiz);
        when(questionService.findOne(1L)).thenReturn(question);

        mockMvc.perform(get
                ("/deleteQuestion")
                .param("questionId", "1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl(("/courses/1/1/editQuiz?quizId=1")));

        verify(questionService).delete(question);
    }
}
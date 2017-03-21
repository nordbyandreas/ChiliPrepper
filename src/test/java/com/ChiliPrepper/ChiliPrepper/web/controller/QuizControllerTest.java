package com.ChiliPrepper.ChiliPrepper.web.controller;

import com.ChiliPrepper.ChiliPrepper.model.Course;
import com.ChiliPrepper.ChiliPrepper.model.Question;
import com.ChiliPrepper.ChiliPrepper.model.Quiz;
import com.ChiliPrepper.ChiliPrepper.service.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.Assert.*;

/**
 * Created by dagki on 04/03/2017.
 */
public class QuizControllerTest {
    private MockMvc mockMvc;

    @InjectMocks
    private QuizController controller;

    @Mock
    private UserService userService;

    @Mock
    private QuizService quizService;

    @Mock
    private QuestionService questionService;

    @Mock
    private CourseService courseService = new CourseServiceImpl();

    @Mock
    private AnswerService answerService;

    @Mock
    private AlternativeService alternativeService;

    @Mock
    private Course course;

    @Mock
    private Quiz quiz;

    @Mock
    private Iterable<Question> questions;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

    }

    @Test
    public void addQuiz_shouldRedirectToCourseSite() throws Exception {
        when(courseService.findOne(1L)).thenReturn(course);
        when(course.getId()).thenReturn(1L);

        mockMvc.perform(post("/addQuiz")
                .param("courseId", "1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/courses" + course.getId()));

        verify(quizService).save(any(Quiz.class));
    }


    @Test
    public void quiz() throws Exception {
        when(quizService.findOne(1L)).thenReturn(quiz);
        when(courseService.findOne(1L)).thenReturn(course);
        when(questionService.findAllByQuiz_Id(1L)).thenReturn(questions);

        mockMvc.perform(get("/courses/{courseId}/{quizId}", 1L, 1L))
                .andExpect(status().isOk())
                .andExpect(view().name("quiz"))
                .andExpect(model().attributeExists("quiz"))
                .andExpect(model().attributeExists("course"))
                .andExpect(model().attributeExists("questions"))
                .andExpect(model().attributeExists("newQuestion"));

        verify(quizService).findOne(any(Long.class));
        verify(courseService).findOne(any(Long.class));
        verify(questionService).findAllByQuiz_Id(any(Long.class));
    }

    @Test
    public void quizzer() throws Exception {
        /*questions.;

        when(quizService.findOne(1L)).thenReturn(quiz);
        when(courseService.findOne(1L)).thenReturn(course);
        when(questionService.findAllByQuiz_Id(1L)).thenReturn(questions);

        mockMvc.perform(get("/courses/{courseId}/{quizId}/quiz", 1L, 1L))
                .andExpect(status().isOk())
                .andExpect(view().name("quizEvent"));*/
    }

    @Test
    public void publishQuiz() throws Exception {
        when(course.getId()).thenReturn(1L);
        when(quiz.getCourse()).thenReturn(course);
        when(quizService.findOne(1L)).thenReturn(quiz);

        mockMvc.perform(get("/publishQuiz")
                .param("quizId", "1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/courses/" + course.getId()));

        verify(quiz).setPublished(true);
        verify(quizService).save(any(Quiz.class));
    }

    @Test
    public void quizCharter() throws Exception {
        /*when(questionService.findAllByQuiz_Id(1L)).thenReturn(questions);
        when(quizService.findOne(1L).getQuizName()).thenReturn("Unit test quiz");

        mockMvc.perform(get("/quizCChart/{quizId}", 1L))
                .andExpect(status().isOk())
                .andExpect(view().name("/courses/1"));*/
    }


    @Test
    public void quizChart() throws Exception {
        /*when(questionService.findAllByQuiz_Id(1L)).thenReturn(questions);
        when(quizService.findOne(1L).getQuizName()).thenReturn("Unit test quiz");

        mockMvc.perform(get("/quizCChart/{quizId}", 1L))
                .andExpect(status().isOk())
                .andExpect(view().n("/courses/1"));
    */}

}
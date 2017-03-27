package com.ChiliPrepper.ChiliPrepper.web.controller;

import com.ChiliPrepper.ChiliPrepper.model.*;
import com.ChiliPrepper.ChiliPrepper.service.*;
import com.sun.security.auth.UserPrincipal;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;

import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
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
import java.util.*;

import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import sun.security.acl.PrincipalImpl;

import javax.annotation.Resource;

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
    Iterable<Course> myCourses;

    @Mock
    Set<Course> regCourses;

    @Mock
    Course course;

    @Mock
    User user;

    @Mock
    User creator;

    @Mock
    Principal principal;

    @Mock
    QuizService quizService;

    @Mock
    AnswerService answerService;

    @Mock
    QuestionService questionService;

    @Mock
    Question questionOne;

    @Mock
    Question questionTwo;

    @Mock
    Quiz quizOne;

    @Mock
    Quiz quizTwo;

    @Mock
    Answer answerOne;

    @Mock
    Answer answerTwo;

    @Mock
    Answer answerThree;

    @Mock
    Answer answerFour;

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        when(principal.getName()).thenReturn("Test user");
        when(userService.findByUsername("Test user")).thenReturn(user);
    }

    @Test
    public void index_shouldRenderIndexView() throws Exception {
        when(user.getRegCourses()).thenReturn(regCourses);
        when(courseService.findAll()).thenReturn(myCourses);
        when(courseService.findAllForCreator()).thenReturn(new ArrayList<>(Arrays.asList(course)));

        mockMvc.perform(get("/")
                .principal(principal))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attributeExists("course", "myCourses", "regCourses"));

        verify(user).getRegCourses();
        verify(courseService).findAllForCreator();
    }

    @Test
    public void course() throws Exception {

        Iterable<Quiz> quizzes= new ArrayList<>(Arrays.asList(quizOne, quizTwo));

        Iterable<Answer> answers= new ArrayList<>(Arrays.asList(answerOne, answerTwo));
        Iterable<Answer> totalAnswers = new ArrayList<>(Arrays.asList(answerOne, answerTwo, answerThree, answerFour));


        when(questionService.findAllByQuiz_Id(1L)).thenReturn(new ArrayList(Arrays.asList(questionOne)));
        when(questionService.findAllByQuiz_Id(2L)).thenReturn(new ArrayList(Arrays.asList(questionTwo)));
        when(quizOne.getId()).thenReturn(1L);
        when(quizTwo.getId()).thenReturn(2L);
        when(answerOne.isCorrect()).thenReturn(true);
        when(answerTwo.isCorrect()).thenReturn(false);
        when(user.getId()).thenReturn(1L);
        when(courseService.findOne(1L)).thenReturn(course);
        when(answerService.findAllByCourse_IdAndUser_Id(1L, 1L)).thenReturn(answers);
        when(course.getCreator()).thenReturn(creator);
        when(quizService.findAllByCourse_id(1L)).thenReturn(quizzes);
        when(answerService.findAllByCourse_Id(1L)).thenReturn(totalAnswers);


        mockMvc.perform(get("/courses/{courseId}", 1L)
                .principal(principal))
                .andExpect(view().name("course"));

    }

    @Test
    public void addCourse() throws Exception {

        mockMvc.perform(post("/addCourse")
                .principal(principal))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));


    }

    @Test
    public void regCourse() throws Exception {
        Set<User> users = new HashSet<User>();
        when(courseService.findByAccessCode("accessCode")).thenReturn(course);
        when(course.getRegUsers()).thenReturn(users);
        when(user.getRegCourses()).thenReturn(regCourses);
        mockMvc.perform(post("/regCourse").principal(principal).param("accessCode", "accessCode")).andExpect(redirectedUrl("/"));
    }


}
package com.ChiliPrepper.ChiliPrepper.web.controller;

import java.util.*;

import org.hibernate.mapping.*;
import org.mockito.*;
import org.junit.Test;
import org.junit.Before;
import java.util.Arrays;
import java.security.Principal;
import java.util.Set;

import org.junit.runner.RunWith;
import com.ChiliPrepper.ChiliPrepper.model.*;
import com.ChiliPrepper.ChiliPrepper.service.*;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
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
 *
 * For the other methods:
 * The tests confirms that the appropriate values are returned
 * when given input that will trigger any if/else statement within the methods.
 */

@RunWith(MockitoJUnitRunner.class)
public class CourseControllerTest {
    private MockMvc mockMvc;

    @Mock
    private User user;

    @Mock
    private User creator;

    @Mock
    private Quiz quizOne;

    @Mock
    private Quiz quizTwo;

    @Mock
    private Course course;

    @Mock
    private Answer answerOne;

    @Mock
    private Answer answerTwo;

    @Mock
    private Answer answerThree;

    @Mock
    private Answer answerFour;

    @Mock
    private Question questionOne;

    @Mock
    private Question questionTwo;

    @Mock
    private Principal principal;

    @Mock
    private UserService userService;

    @Mock
    private QuizService quizService;

    @Mock
    private CourseService courseService;

    @Mock
    private AnswerService answerService;

    @Mock
    private QuestionService questionService;

    @InjectMocks
    private CourseController controller;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void index_shouldRenderIndexView() throws Exception {
        when(principal.getName()).thenReturn("user");
        when(userService.findByUsername("user")).thenReturn(user);
        when(user.getRegCourses()).thenReturn(new HashSet<>(Collections.singletonList(course)));
        when(courseService.findAll()).thenReturn(new ArrayList<>(Collections.singletonList(course)));
        when(courseService.findAllForCreator()).thenReturn(new ArrayList<>(Collections.singletonList(course)));

        mockMvc.perform(get("/")
                .principal(principal))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attributeExists("course", "myCourses", "regCourses"));

        verify(courseService).findAllForCreator();
    }

    @Test
    public void course() throws Exception {
        when(principal.getName()).thenReturn("user");
        when(userService.findByUsername("user")).thenReturn(user);

        Iterable<Quiz> quizzes= new ArrayList<>(Arrays.asList(quizOne, quizTwo));
        Iterable<Answer> answers= new ArrayList<>(Arrays.asList(answerOne, answerTwo));
        Iterable<Answer> totalAnswers = new ArrayList<>(Arrays.asList(answerOne, answerTwo, answerThree, answerFour));


        when(questionService.findAllByQuiz_Id(1L)).thenReturn(new ArrayList<>(Collections.singletonList(questionOne)));
        when(questionService.findAllByQuiz_Id(2L)).thenReturn(new ArrayList<>(Collections.singletonList(questionTwo)));
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
        when(principal.getName()).thenReturn("user");
        when(userService.findByUsername("user")).thenReturn(user);

        mockMvc.perform(post("/addCourse")
                .principal(principal))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

    @Test
    public void regCourse() throws Exception {
        when(principal.getName()).thenReturn("user");
        when(userService.findByUsername("user")).thenReturn(user);
        when(courseService.findByAccessCode("accessCode")).thenReturn(course);
        when(course.getRegUsers()).thenReturn(Collections.singleton(user));
        when(user.getRegCourses()).thenReturn(new HashSet<>());

        mockMvc.perform(post("/regCourse")
                .principal(principal)
                .param("accessCode", "accessCode"))

                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

    @Test
    public void courseChart_ShouldRenderCourseChartDisplay() throws Exception {
        mockMvc.perform(get("/courses/{courseId}/chart", 1L))
                .andExpect(model().attribute("courseId", 1L))

                .andExpect(status().isOk())
                .andExpect(view().name("courseChartDisplay"));
    }

    /**
     * This test tests the three methods getCourseChart, getCourseResults and getAvgScoreForCourseChart:
     * The method getCourseChart calls getCourseResults, which gets an Iterable<Quiz> containing two quizzes.
     * For each quiz, getAvgScoreForCourseChart gets called, in order to check the average score for each quiz if there are any answers.
     * The first quiz contains no answers.
     * Therefore getAvgScoreForCourseChart returns null after catching ArithmeticException when dividing by the number of answers, which is zero.
     * This triggers the else statement in getCourseResults and the result 0.0 is added for the first quiz.
     * The second quiz has two answers, one correct and one incorrect.
     * Therefore getAvgScoreForCourseChart returns 50.0, and the result 50.0 is added for the second quiz.
     * "results" should thus consist of an ArrayList<Double> containing 0.0 and 50.0
     */
    @Test
    public void getCourseChart_ShouldRenderCourseChart() throws Exception {
        when(quizService.findAllByCourse_id(1L)).thenReturn(new ArrayList<>(Arrays.asList(quizOne, quizTwo)));
        when(quizOne.getId()).thenReturn(1L);
        when(answerService.findAllByQuiz_Id(1L)).thenReturn(new ArrayList<>());
        when(quizTwo.getId()).thenReturn(2L);
        when(answerService.findAllByQuiz_Id(2L)).thenReturn(Arrays.asList(answerOne, answerTwo));
        when(answerOne.isCorrect()).thenReturn(true);
        when(answerTwo.isCorrect()).thenReturn(false);

        when(courseService.findOne(1L)).thenReturn(course);
        when(course.getCourseName()).thenReturn("courseName");

        mockMvc.perform(get("/courseChart/{courseId}", 1L))
                .andExpect(model().attribute("courseName", "courseName"))
                .andExpect(model().attribute("results", new ArrayList<>(Arrays.asList(0.0, 50.0))))

                .andExpect(status().isOk())
                .andExpect(view().name("courseChart:: courseChart"));
    }

}
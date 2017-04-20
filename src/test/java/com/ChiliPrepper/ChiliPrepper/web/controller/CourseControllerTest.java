package com.ChiliPrepper.ChiliPrepper.web.controller;

import java.util.*;
import org.mockito.*;
import org.junit.Test;
import org.junit.Before;
import java.util.Arrays;
import java.security.Principal;
import org.junit.runner.RunWith;
import com.ChiliPrepper.ChiliPrepper.model.*;
import com.ChiliPrepper.ChiliPrepper.service.*;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import com.ChiliPrepper.ChiliPrepper.web.FlashMessage;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.instanceOf;
import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.hasProperty;
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
 * The tests confirms that the appropriate values are returned.
 * If the method contains if/else statements, there will be created tests that will test each separately
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
    public void index_RendersIndexView() throws Exception {
        Iterable<Course> myCreatedCourses = new ArrayList<>(Collections.singletonList(new Course()));
        Set<Course> participatesInCourses = new HashSet<>(Collections.singletonList(new Course()));

        //Finds the logged in user
        when(principal.getName()).thenReturn("user");
        when(userService.findByUsername("user")).thenReturn(user);

        //Finds the courses which the user participates in
        when(user.getRegCourses()).thenReturn(participatesInCourses);

        //Finds the courses which the user have created
        when(courseService.findAllForCreator()).thenReturn(myCreatedCourses);

        mockMvc.perform(get("/")
                .principal(principal))

                .andExpect(model().attribute("myCourses", myCreatedCourses))
                .andExpect(model().attribute("regCourses", participatesInCourses))
                .andExpect(model().attribute("course", instanceOf(Course.class)))

                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }



    @Test
    public void course_RendersCourseView() throws Exception {
        Iterable<Quiz> quizzes= new ArrayList<>(Arrays.asList(quizOne, quizTwo));
        Iterable<Answer> answers= new ArrayList<>(Arrays.asList(answerOne, answerTwo));
        Iterable<Answer> totalAnswers = new ArrayList<>(Arrays.asList(answerOne, answerTwo, new Answer(), new Answer()));

        //Finds the logged in user and its ID
        when(principal.getName()).thenReturn("user");
        when(userService.findByUsername("user")).thenReturn(user);
        when(user.getId()).thenReturn(1L);

        //Finds the selected course and its ID and creator
        when(courseService.findOne(1L)).thenReturn(course);
        when(course.getCreator()).thenReturn(creator);
        when(creator.getId()).thenReturn(2L);

        //Finds the course's quizzes, the user's answer in the course and the answer for all users in the course
        when(quizService.findAllByCourse_id(1L)).thenReturn(quizzes);
        when(answerService.findAllByCourse_IdAndUser_Id(1L, 1L)).thenReturn(answers);
        when(answerService.findAllByCourse_Id(1L)).thenReturn(totalAnswers);

        /*There are two quizzes within the course; quizOne and quizTwo.
        * The user have answered one question in the quizOne, which is correct.
        * The user have answered one question in the quizTwo, which is incorrect*/
        when(quizOne.getId()).thenReturn(1L);
        when(quizTwo.getId()).thenReturn(2L);
        when(questionService.findAllByQuiz_Id(1L)).thenReturn(new ArrayList<>(Collections.singletonList(questionOne)));
        when(questionService.findAllByQuiz_Id(2L)).thenReturn(new ArrayList<>(Collections.singletonList(questionTwo)));
        when(answerOne.isCorrect()).thenReturn(true);
        when(answerTwo.isCorrect()).thenReturn(false);

        mockMvc.perform(get("/courses/{courseId}", 1L)
                .principal(principal))

                .andExpect(model().attribute("userId", 1L))
                .andExpect(model().attribute("creatorId", 2L))
                .andExpect(model().attribute("quiz", instanceOf(Quiz.class)))
                .andExpect(model().attribute("courseId", 1L))
                .andExpect(model().attribute("myQuizzes", quizzes))
                .andExpect(model().attribute("course", course))
                .andExpect(model().attribute("score", 10))
                .andExpect(model().attribute("maxScore", 20))

                .andExpect(status().isOk())
                .andExpect(view().name("course"));
    }



    @Test
    public void addCourse_SuccessfullyCreatesCourse() throws Exception {
        //Finds the logged in user
        when(principal.getName()).thenReturn("user");
        when(userService.findByUsername("user")).thenReturn(user);

        //Finds the course's name and its access code
        when(course.getCourseName()).thenReturn("courseName");
        when(course.getAccessCode()).thenReturn("accessCode");


        mockMvc.perform(post("/addCourse")
                .principal(principal)
                .flashAttr("course", course))

                .andExpect(flash().attributeExists("flash"))
                .andExpect(flash().attribute("flash", hasProperty("message", is("You've successfully created courseName !"))))
                .andExpect(flash().attribute("flash", hasProperty("status", is(FlashMessage.Status.SUCCESS))))

                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));

        verify(course).setCreator(user);
        verify(courseService).save(course);
    }

    @Test
    public void addCourse_FailsToCreateCourse_NoCourseNameEntered() throws Exception {
        //Finds the logged in user
        when(principal.getName()).thenReturn("user");
        when(userService.findByUsername("user")).thenReturn(user);

        //The course name ain't specified, which will cause a failure when trying to create a course
        when(course.getCourseName()).thenReturn("");
        when(course.getAccessCode()).thenReturn("accessCode");


        mockMvc.perform(post("/addCourse")
                .principal(principal)
                .flashAttr("course", course))

                .andExpect(flash().attributeExists("flash"))
                .andExpect(flash().attribute("flash", hasProperty("message", is("Could not create the course. Name and accessCode cannot be empty! "))))
                .andExpect(flash().attribute("flash", hasProperty("status", is(FlashMessage.Status.FAILURE))))

                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

    @Test
    public void addCourse_ShouldFailToCreateCourse_NoAccessCodeEntered() throws Exception {
        //Finds the logged in user
        when(principal.getName()).thenReturn("user");
        when(userService.findByUsername("user")).thenReturn(user);

        //The access code ain't specified, which will cause a failure when trying to create a course
        when(course.getCourseName()).thenReturn("courseName");
        when(course.getAccessCode()).thenReturn("");


        mockMvc.perform(post("/addCourse")
                .principal(principal)
                .flashAttr("course", course))

                .andExpect(flash().attributeExists("flash"))
                .andExpect(flash().attribute("flash", hasProperty("message", is("Could not create the course. Name and accessCode cannot be empty! "))))
                .andExpect(flash().attribute("flash", hasProperty("status", is(FlashMessage.Status.FAILURE))))

                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }



    @Test
    public void regCourse_ShouldSuccessfullyRegisterForCourse() throws Exception {
        //Finds the logged in user
        when(principal.getName()).thenReturn("user");
        when(userService.findByUsername("user")).thenReturn(user);

        //Finds the course name, based of the access code
        when(courseService.findByAccessCode("accessCode")).thenReturn(course);
        when(course.getCourseName()).thenReturn("courseName");

        //Finds all users registered for the course, and the courses the user is registered for
        when(course.getRegUsers()).thenReturn(new HashSet<>(Arrays.asList(user, new User())));
        when(user.getRegCourses()).thenReturn(new HashSet<>(Arrays.asList(course, new Course())));

        mockMvc.perform(post("/regCourse")
                .principal(principal)
                .param("accessCode", "accessCode"))

                .andExpect(flash().attributeExists("flash"))
                .andExpect(flash().attribute("flash", hasProperty("message", is("You've registered in courseName"))))
                .andExpect(flash().attribute("flash", hasProperty("status", is(FlashMessage.Status.SUCCESS))))

                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

    @Test
    public void regCourse_ShouldFailToRegisterForCourse() throws Exception {
        //Finds the logged in user
        when(principal.getName()).thenReturn("user");
        when(userService.findByUsername("user")).thenReturn(user);

        //The access code ain't related to a course, which causes the registration to fail
        when(courseService.findByAccessCode("accessCode")).thenReturn(null);


        mockMvc.perform(post("/regCourse")
                .principal(principal)
                .param("accessCode", "accessCode"))

                .andExpect(flash().attributeExists("flash"))
                .andExpect(flash().attribute("flash", hasProperty("message", is("Registration failed! No course with that accessCode found."))))
                .andExpect(flash().attribute("flash", hasProperty("status", is(FlashMessage.Status.FAILURE))))

                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }



    @Test
    public void courseChart_ShouldRenderCourseChartDisplayView() throws Exception {
        mockMvc.perform(get("/courses/{courseId}/chart", 1L))

                .andExpect(model().attribute("courseId", 1L))

                .andExpect(status().isOk())
                .andExpect(view().name("courseChartDisplay"));
    }


    /**
     * This test tests the three methods getCourseChart, getCourseResults and getAvgScoreForCourseChart:
     *
     * The method getCourseChart calls getCourseResults, with an input containing two quizzes.
     * For each quiz, getAvgScoreForCourseChart gets called, in order to check the average score for each quiz if there are any answers.
     *
     * The first quiz contains no answers.
     * Therefore getAvgScoreForCourseChart returns null after catching ArithmeticException when dividing by the number of answers, which is zero.
     * This triggers the else statement in getCourseResults and the result 0.0 is added for the first quiz.
     *
     * The second quiz has two answers, one correct and one incorrect.
     * Therefore getAvgScoreForCourseChart returns 50.0, and the result 50.0 is added for the second quiz.
     *
     * "results" should thus consist of an ArrayList<Double> containing 0.0 and 50.0
     */
    @Test
    public void getCourseChart_ShouldRenderCourseChartView() throws Exception {
        String courseName = "courseName";

        when(quizService.findAllByCourse_id(1L)).thenReturn(new ArrayList<>(Arrays.asList(quizOne, quizTwo)));

        when(quizOne.getId()).thenReturn(1L);
        when(answerService.findAllByQuiz_Id(1L)).thenReturn(new ArrayList<>());

        when(quizTwo.getId()).thenReturn(2L);
        when(answerService.findAllByQuiz_Id(2L)).thenReturn(Arrays.asList(answerOne, answerTwo));
        when(answerOne.isCorrect()).thenReturn(true);
        when(answerTwo.isCorrect()).thenReturn(false);

        when(courseService.findOne(1L)).thenReturn(course);
        when(course.getCourseName()).thenReturn(courseName);

        mockMvc.perform(get("/courseChart/{courseId}", 1L))

                .andExpect(model().attribute("courseName", courseName))
                .andExpect(model().attribute("results", new ArrayList<>(Arrays.asList(0.0, 50.0))))

                .andExpect(status().isOk())
                .andExpect(view().name("courseChart:: courseChart"));
    }

}
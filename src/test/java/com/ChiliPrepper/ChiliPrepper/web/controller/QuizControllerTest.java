package com.ChiliPrepper.ChiliPrepper.web.controller;

import org.mockito.*;
import org.junit.Test;
import org.junit.Before;
import java.util.Arrays;
import java.util.ArrayList;
import java.security.Principal;
import org.junit.runner.RunWith;
import com.ChiliPrepper.ChiliPrepper.model.*;
import org.mockito.runners.MockitoJUnitRunner;
import com.ChiliPrepper.ChiliPrepper.service.*;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

/**
 * Created by dagki on 04/03/2017.
 */

/**The tests in the controller class will confirm that:
 * 1. All model- and flash attributes are invoked
 * 2. All save and delete calls on service objects are invoked
 * 3. The request status correspond to the expected outcome
 * 4. The view name or redirected url correspond to the expected outcome*/

@RunWith(MockitoJUnitRunner.class)
public class QuizControllerTest {
    private MockMvc mockMvc;
    private InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();

    @Mock
    private User user;

    @Mock
    private Quiz quiz;

    @Mock
    private Course course;

    @Mock
    private Answer answerOne;

    @Mock
    private Answer answerTwo;

    @Mock
    private QuizMail quizMail;

    @Mock
    private Principal principal;

    @Mock
    private Question questionOne;

    @Mock
    private Question questionTwo;

    @Mock
    private Alternative alternativeOne;

    @Mock
    private Alternative alternativeTwo;

    @Mock
    private UserService userService;

    @Mock
    private QuizService quizService;

    @Mock
    private AnswerService answerService;

    @Mock
    private CourseService courseService;

    @Mock
    private QuestionService questionService;

    @Mock
    private QuizMailService quizMailService;

    @Mock
    private AlternativeService alternativeService;

    @InjectMocks
    private QuizController controller;

    @Before
    public void setUp() throws Exception {
        viewResolver.setSuffix(".jsp");
        mockMvc = MockMvcBuilders.standaloneSetup(controller).setViewResolvers(viewResolver).build();
    }

    private void setUp_getQuizResults() throws Exception {
        when(questionOne.getId()).thenReturn(1L);
        when(questionTwo.getId()).thenReturn(1L);
        when(answerOne.isCorrect()).thenReturn(true);
        when(answerTwo.isCorrect()).thenReturn(false);
        when(answerService.findAllByQuestion_Id(1L)).thenReturn(new ArrayList<>(Arrays.asList(answerOne, answerTwo)));

    }

    @Test
    public void getQuizResults() throws Exception {
        setUp_getQuizResults();
        ArrayList<Double> results = controller.getQuizResults(new ArrayList<>(Arrays.asList(questionOne, questionTwo)));
        assertThat(results, is(new ArrayList<>(Arrays.asList(50.0, 50.0))));
    }

    @Test
    public void addQuiz_shouldRedirectToCourseSite() throws Exception {
        when(course.getId()).thenReturn(1L);
        when(courseService.findOne(1L)).thenReturn(course);

        mockMvc.perform(post("/addQuiz")
                .param("courseId", "1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/courses/" + course.getId()));

        verify(quizService).save(any(Quiz.class));
    }

    @Test
    public void quiz() throws Exception {
        Iterable<Question> questions = new ArrayList<>(Arrays.asList(questionOne, questionTwo));

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

    private void setUp_quizzer() {
        when(principal.getName()).thenReturn("username");
        when(userService.findByUsername("username")).thenReturn(user);

        when(courseService.findOne(1L)).thenReturn(course);
        when(quizService.findOne(1L)).thenReturn(quiz);
        when(quizService.findOne(2L)).thenReturn(quiz);

        when(questionService.findAllByQuiz_Id(1L)).thenReturn(new ArrayList<>(Arrays.asList(questionOne)));
        when(questionService.findAllByQuiz_Id(2L)).thenReturn(new ArrayList<>(Arrays.asList(questionTwo)));

        when(user.getId()).thenReturn(1L);
        when(questionOne.getId()).thenReturn(1L);
        when(questionTwo.getId()).thenReturn(2L);


        when(answerService.findOneByQuestion_IdAndUser_Id(1L, 1L)).thenReturn(null);
        when(answerService.findOneByQuestion_IdAndUser_Id(2L, 1L)).thenReturn(answerOne);

        when(alternativeService.findAllByQuestion_Id(1L)).thenReturn(new ArrayList<>(Arrays.asList(alternativeOne, alternativeTwo)));
        when(alternativeOne.getAlternative()).thenReturn("AlternativeOne");
        when(alternativeTwo.getAlternative()).thenReturn("AlternativeTwo");
        when(questionOne.getCorrectAnswer()).thenReturn("AlternativeOne");
    }

    @Test
    public void quizzer_1() throws Exception {
        setUp_quizzer();

        mockMvc.perform(get("/courses/{courseId}/{quizId}/quiz", 1L, 1L)
                .principal(principal))
                .andExpect(view().name("quizEvent"));
    }

    @Test
    public void quizzer_2() throws Exception {
        setUp_quizzer();
        setUp_getUserScore();
        setUp_getAvgScore();

        mockMvc.perform(get("/courses/{courseId}/{quizId}/quiz", 1L, 2L)
                .principal(principal))
                .andExpect(view().name("quizEvent"));
    }

    private void setUp_getAvgScore() {
        when(answerOne.isCorrect()).thenReturn(true);
        when(answerTwo.isCorrect()).thenReturn(false);
        when(answerService.findAllByQuiz_Id(1L)).thenReturn(new ArrayList<>(Arrays.asList()));
        when(answerService.findAllByQuiz_Id(2L)).thenReturn(new ArrayList<>(Arrays.asList(answerOne, answerTwo)));
    }

    @Test
    public void getAvgScore_shouldReturnNull() throws Exception {
        setUp_getAvgScore();
        assertNull(this.controller.getAvgScore(1L));
        verify(answerService).findAllByQuiz_Id(1L);
    }

    @Test
    public void getAvgScore_shouldReturnDouble() throws Exception {
        setUp_getAvgScore();
        double result = this.controller.getAvgScore(2L);
        assertThat(result, is(50.0));
        verify(answerService).findAllByQuiz_Id(2L);
    }

    private void setUp_getUserScore() {
        when(user.getId()).thenReturn(1L);
        when(answerOne.isCorrect()).thenReturn(true);
        when(answerTwo.isCorrect()).thenReturn(false);
        when(answerService.findAllByQuiz_IdAndUser_Id(1L, 1L)).thenReturn(new ArrayList<>());
        when(answerService.findAllByQuiz_IdAndUser_Id(2L, 1L)).thenReturn(new ArrayList<>(Arrays.asList(answerOne, answerTwo)));
    }

    @Test
    public void getUserScore_ShouldReturnNull() throws Exception {
        setUp_getUserScore();
        assertNull(this.controller.getUserScore(1L, user));
        verify(answerService).findAllByQuiz_IdAndUser_Id(1L, 1L);
    }

    @Test
    public void getUserScore_ShouldReturnDouble() throws Exception {
        setUp_getUserScore();
        double result = this.controller.getUserScore(2L, user);
        assertThat(result, is(50.0));
        verify(answerService).findAllByQuiz_IdAndUser_Id(2L, 1L);
    }

    @Test
    public void sendQuizResultsByMail() throws Exception {
        when(user.getId()).thenReturn(1L);
        when(user.getEmail()).thenReturn("username@domain.com");

        when(quizService.findOne(1L)).thenReturn(quiz);
        when(quizService.findOne(2L)).thenReturn(quiz);
        when(quizMailService.findOneByQuiz_IdAndParticipant_Id(1L,1L)).thenReturn(null);
        when(quizMailService.findOneByQuiz_IdAndParticipant_Id(2L,1L)).thenReturn(quizMail);



}

    private void setUp_sendQuizResultsByMail() {
        when(quiz.getQuizName()).thenReturn("Unit test");
        when(quizService.findOne(1L)).thenReturn(quiz);

        when(user.getId()).thenReturn(1L);
        when(quizMailService.findOneByQuiz_IdAndParticipant_Id(1L, 1L)).thenReturn(null);
        when(user.getEmail()).thenReturn("username@domain.com");


    }


    @Test
    public void generateMailSubject() throws Exception {
        when(quiz.getQuizName()).thenReturn("Unit test");
        when(quizService.findOne(1L)).thenReturn(quiz);

        String result = this.controller.generateMailSubject(1L);
        assertThat(result, is("Unit test - results"));

        verify(quizService).findOne(1L);
    }

    @Test
    public void generateMailBody_50() throws Exception {
        setUp_getUserScore();
        when(userService.findOne(1L)).thenReturn(user);
        when(quizService.findOne(2L)).thenReturn(quiz);

        when(user.getUsername()).thenReturn("username");
        //when(mockController.getUserScore(1L, user)).thenReturn(95.0);

        when(quiz.getQuizName()).thenReturn("quizName");

        String result = this.controller.generateMailBody(2L, 1L);
        assertThat(result, is("Hi username!\n\nYou got 50.0% correct on the quizName quiz.\n\nNot bad, but don't get cocky!  Keep it up :) \n\nChiliPrepper"));

    }


    private void setUpForSubmitAnswer() {
        when(course.getId()).thenReturn(1L);
        when(quiz.getId()).thenReturn(1L);

        when(principal.getName()).thenReturn("username");
        when(userService.findByUsername("username")).thenReturn(user);

        when(courseService.findOne(1L)).thenReturn(course);
        when(quizService.findOne(1L)).thenReturn(quiz);
        when(questionService.findOne(1L)).thenReturn(questionOne);

        when(questionOne.getCorrectAnswer()).thenReturn("correctAnswer");
    }

    @Test
    public void submitAnswer_() throws Exception {

        setUpForSubmitAnswer();
        mockMvc.perform(post("/submitAnswer")
                .param("questionId", "1")
                .param("courseId", "1")
                .param("quizId", "1")
                .param("answer", "correctAnswer")
                .principal(principal))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/courses/1/1/quiz"));

    }
    @Test
    public void submitAnswer_2() throws Exception {
        setUpForSubmitAnswer();
        mockMvc.perform(post("/submitAnswer")
                .param("questionId", "1")
                .param("courseId", "1")
                .param("quizId", "1")
                .param("answer", "incorrectAnswer")
                .principal(principal))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/courses/1/1/quiz"));
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

    /**Uses */
    @Test
    public void quizChart() throws Exception {
        Iterable<Question> questions = new ArrayList<>(Arrays.asList(questionOne, questionTwo));

        setUp_getQuizResults();
        when(questionService.findAllByQuiz_Id(1L)).thenReturn(questions);
        when(quizService.findOne(1L)).thenReturn(quiz);
        when(quiz.getQuizName()).thenReturn("quizName");

        mockMvc.perform(get("/quizChart/{quizId}", 1L))
                .andExpect(model().attribute("results", controller.getQuizResults(questions)))
                .andExpect(model().attribute("quizName", "quizName"))

                .andExpect(status().isOk())
                .andExpect(view().name("graph:: quizChart"));
    }

    @Test
    public void editQuiz() throws Exception {
        Iterable<Question> questions = new ArrayList<>(Arrays.asList(questionOne, questionTwo));

        when(quizService.findOne(1L)).thenReturn(quiz);
        when(questionService.findAllByQuiz_Id(1L)).thenReturn(questions);
        when(quiz.getCourse()).thenReturn(course);

        mockMvc.perform(get("/courses/{courseId}/{quizId}/editQuiz", 1L, 1L)
                .param("quizId", "1"))

                .andExpect(model().attribute("quiz", quiz))
                .andExpect(model().attribute("questions", questions))
                .andExpect(model().attribute("course", course))

                .andExpect(status().isOk())
                .andExpect(view().name("editQuiz"));
    }

    @Test
    public void saveEditQuiz() throws Exception {
        when(quiz.getCourse()).thenReturn(course);
        when(course.getId()).thenReturn(1L);
        when(courseService.findOne(1L)).thenReturn(course);
        when(quiz.getId()).thenReturn(1L);

        mockMvc.perform(post("/saveEditQuiz")
                .flashAttr("quiz", quiz))

                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/courses/1/1"));

        verify(quizService).save(any(Quiz.class));
    }

    @Test
    public void deleteQuiz() throws Exception {
        when(quizService.findOne(1L)).thenReturn(quiz);
        when(quiz.getCourse()).thenReturn(course);
        when(course.getId()).thenReturn(1L);

        mockMvc.perform(get("/deleteQuiz")
                .param("quizId", "1"))

                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/courses/1"));

        verify(quizService).delete(quiz);
    }

}
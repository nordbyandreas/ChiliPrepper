package com.ChiliPrepper.ChiliPrepper.web.controller;

import com.ChiliPrepper.ChiliPrepper.model.*;
import com.ChiliPrepper.ChiliPrepper.service.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;

import org.mockito.runners.MockitoJUnitRunner;

import org.mockito.stubbing.VoidMethodStubbable;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import org.mockito.MockitoAnnotations;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Created by dagki on 04/03/2017.
 */

@RunWith(MockitoJUnitRunner.class)
public class QuizControllerTest {
    private MockMvc mockMvc;

    @InjectMocks
    private QuizController controller;

    @Mock
    private QuizController quizController;

    @Mock
    private QuizController mockController;

    @Mock
    private QuizService quizService;

    @Mock
    private QuestionService questionService;

    @Mock
    private CourseService courseService;

    @Mock
    private AnswerService answerService;

    @Mock
    private Course course;

    @Mock
    Alternative alternativeOne;

    @Mock
    Alternative alternativeTwo;

    @Mock
    User user;

    @Mock
    private Quiz quiz;

    @Mock
    Question questionOne;

    @Mock
    Question questionTwo;

    @Mock
    QuizMailService quizMailService;

    @Mock
    Answer answerOne;

    @Mock
    QuizMail quizMail;

    @Mock
    Answer answerTwo;

    @Mock
    AlternativeService alternativeService;

    @Mock
    Principal principal;

    @Mock
    UserService userService;

    @Before

    public void setUp() throws Exception {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/jsp/view/");
        viewResolver.setSuffix(".jsp");
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).setViewResolvers(viewResolver).build();
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
        assertNull(this.controller.getAvgScore(1L)); ;
        verify(answerService).findAllByQuiz_Id(1L);
    }

    @Test
    public void getAvgScore_shouldReturnDouble() throws Exception {
        setUp_getAvgScore();
        double result = this.controller.getAvgScore(2L);
        assertThat(result, is(50.0));
        verify(answerService).findAllByQuiz_Id(2L);
    }

    //Sets the behavior for the mock objects needed in getUserScore_ShouldReturnDouble and getUserScore_ShouldReturnNull
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
        when(quizMailService.findOneByQuiz_IdAndParticipant_Id(2L,1L)).thenReturn(new QuizMail());



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
    @Test
    public void generateMailBody_10() throws Exception {
        setUp_getUserScore();
        when(userService.findOne(1L)).thenReturn(user);
        when(quizService.findOne(3L)).thenReturn(quiz);

        when(user.getUsername()).thenReturn("username");
        //when(mockController.getUserScore(1L, user)).thenReturn(95.0);

        when(quiz.getQuizName()).thenReturn("quizName");

        String result = this.controller.generateMailBody(2L, 1L);
        assertThat(result, is("Hi username!\n\nYou got 50.0% correct on the quizName quiz.\n\nNot bad, but don't get cocky!  Keep it up :) \n\nChiliPrepper"));

    }

    @Test
    public void generateMailBody_100() throws Exception {
        setUp_getUserScore();
        when(userService.findOne(1L)).thenReturn(user);
        when(quizService.findOne(4L)).thenReturn(quiz);

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

    @Test
    public void quizChart() throws Exception {
        Iterable<Question> questions = new ArrayList<>();
        ArrayList<Double> results = new ArrayList<>();

        when(mockController.getQuizResults(questions)).thenReturn(results);
        when(questionService.findAllByQuiz_Id(1L)).thenReturn(questions);
        when(quizService.findOne(1L)).thenReturn(quiz);
        when(quiz.getQuizName()).thenReturn("Quiz name");

        mockMvc.perform(get("/quizChart/{quizId}", 1L))
                .andExpect(status().isOk()).andExpect(view()
                .name("graph:: quizChart"));
    }

    @Test
    public void getQuizResults() throws Exception {
        when(questionOne.getId()).thenReturn(1L);
        when(questionTwo.getId()).thenReturn(1L);
        when(answerOne.isCorrect()).thenReturn(true);
        when(answerTwo.isCorrect()).thenReturn(false);
        when(answerService.findAllByQuestion_Id(1L)).thenReturn(new ArrayList<Answer>(Arrays.asList(answerOne, answerTwo)));

        ArrayList<Double> results = controller.getQuizResults(new ArrayList<Question>(Arrays.asList(questionOne, questionTwo)));
        assertThat(results, is(new ArrayList<Double>(Arrays.asList(50.0, 50.0))));
    }

    @Test
    public void editQuiz() throws Exception {
        Iterable<Question> questions = new ArrayList<>(Arrays.asList(new Question(), new Question()));
        when(quizService.findOne(1L)).thenReturn(quiz);
        when(questionService.findAllByQuiz_Id(1L)).thenReturn(questions);
        when(quiz.getCourse()).thenReturn(course);

        mockMvc.perform(get("/courses/{courseId}/{quizId}/editQuiz", 1L, 1L)
                .param("quizId", "1"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("quiz"))
                .andExpect(model().attributeExists("questions"))
                .andExpect(model().attributeExists("course"))
                .andExpect(view().name("editQuiz"));
    }

    @Test
    public void saveEditQuiz() throws Exception {
        when(quiz.getCourse()).thenReturn(course);
        when(course.getId()).thenReturn(1L);
        when(quiz.getId()).thenReturn(1L);
        when(courseService.findOne(1L)).thenReturn(course);

        mockMvc.perform(post("/saveEditQuiz").param("quiz", "quiz")
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
                .requestAttr("quiz", quiz)
                .param("quizId", "1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/courses/1"));

        verify(quizService).delete(quiz);
    }

}
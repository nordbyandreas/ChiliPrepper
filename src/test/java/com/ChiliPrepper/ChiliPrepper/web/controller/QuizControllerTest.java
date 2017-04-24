package com.ChiliPrepper.ChiliPrepper.web.controller;

import java.util.*;
import org.mockito.*;
import org.junit.Test;
import org.junit.Before;
import java.security.Principal;
import org.junit.runner.RunWith;
import com.ChiliPrepper.ChiliPrepper.model.*;
import org.mockito.runners.MockitoJUnitRunner;

import com.ChiliPrepper.ChiliPrepper.service.*;

import org.springframework.test.web.servlet.MockMvc;
import com.ChiliPrepper.ChiliPrepper.web.FlashMessage;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.Matchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

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
    private QuizMailService quizMailService;

    @Mock
    private QuestionService questionService;

    @Mock
    private AlternativeService alternativeService;

    @InjectMocks
    private QuizController controller;

    @Before
    public void setUp() throws Exception {
        viewResolver.setSuffix(".jsp");
        mockMvc = MockMvcBuilders.standaloneSetup(controller).setViewResolvers(viewResolver).build();
    }

    @Test
    public void renderQuizView() throws Exception {
        Iterable<Question> questions = new ArrayList<>(Arrays.asList(new Question(), new Question()));

        //Finds the selected quiz within a course, in addition to the quiz's questions
        when(courseService.findOne(1L)).thenReturn(course);
        when(quizService.findOne(1L)).thenReturn(quiz);
        when(questionService.findAllByQuiz_Id(1L)).thenReturn(questions);

        mockMvc.perform(get("/courses/{courseId}/{quizId}", 1L, 1L))

                .andExpect(model().attribute("quiz", quiz))
                .andExpect(model().attribute("course", course))
                .andExpect(model().attribute("questions", questions))
                .andExpect(model().attribute("newQuestion", instanceOf(Question.class)))

                .andExpect(status().isOk())
                .andExpect(view().name("quiz"));

        verify(quizService).findOne(any(Long.class));
        verify(courseService).findOne(any(Long.class));
        verify(questionService).findAllByQuiz_Id(any(Long.class));
    }



    @Test
    public void addQuiz_SuccessfullyCreatesQuiz_RedirectsToTheCourse() throws Exception {
        //Finds the course, for which the quiz is being created in.
        when(courseService.findOne(1L)).thenReturn(course);

        //The quiz's name is entered, and will therefore successfully create the quiz.
        when(quiz.getQuizName()).thenReturn("quizName");

        mockMvc.perform(post("/addQuiz").flashAttr("quiz", quiz)
                .param("courseId", "1"))

                .andExpect(flash().attributeExists("flash"))
                .andExpect(flash().attribute("flash", hasProperty("message", is("Quiz created!"))))
                .andExpect(flash().attribute("flash", hasProperty("status", is(FlashMessage.Status.SUCCESS))))

                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/courses/1"));

        verify(quizService).save(quiz);
    }

    @Test
    public void addQuiz_FailsToCreateQuiz() throws Exception {
        //Finds the course, for which the quiz is being created in.
        when(courseService.findOne(1L)).thenReturn(course);

        //The quiz's name ain't entered, and will therefore fail to create the quiz.
        when(quiz.getQuizName()).thenReturn("");

        mockMvc.perform(post("/addQuiz").flashAttr("quiz", quiz)
                .param("courseId", "1"))

                .andExpect(flash().attributeExists("flash"))
                .andExpect(flash().attribute("flash", hasProperty("message", is("Could not create quiz. Quiz name cannot be empty!"))))
                .andExpect(flash().attribute("flash", hasProperty("status", is(FlashMessage.Status.FAILURE))))

                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/courses/1"));
    }



    @Test
    public void renderQuizChartView() throws Exception {
        //Finds the course and quiz, for which the quiz is being created in.
        when(courseService.findOne(1L)).thenReturn(course);
        when(quizService.findOne(1L)).thenReturn(quiz);

        mockMvc.perform(post("/courses/{courseId}/{quizId}/chart", 1L, 1L))

                .andExpect(model().attribute("quiz", quiz))
                .andExpect(model().attribute("course", course))

                .andExpect(status().isOk())
                .andExpect(view().name("quizChart"));
    }



    @Test
    public void quizEventHandler_AllQuestionsAnswered() throws Exception {
        when(principal.getName()).thenReturn("username");
        when(userService.findByUsername("username")).thenReturn(user);
        when(courseService.findOne(1L)).thenReturn(course);
        when(quizService.findOne(1L)).thenReturn(quiz);
        when(questionService.findAllByQuiz_Id(1L)).thenReturn(new ArrayList<>(Arrays.asList(questionOne)));

        when(user.getId()).thenReturn(1L);
        when(questionOne.getId()).thenReturn(1L);
        when(answerService.findOneByQuestion_IdAndUser_Id(1L, 1L)).thenReturn(answerOne, answerTwo);

        when(answerService.findAllByQuiz_IdAndUser_Id(1L, 1L)).thenReturn(Arrays.asList(answerOne));
        when(answerService.findAllByQuiz_Id(1L)).thenReturn(Arrays.asList(answerOne, answerTwo));
        when(answerOne.isCorrect()).thenReturn(true);
        when(answerTwo.isCorrect()).thenReturn(false);

        when(user.isParticipantQuizResults()).thenReturn(false);
        when(quizMailService.findOneByQuiz_IdAndParticipant_Id(1L, 1L)).thenReturn(null);

        mockMvc.perform(get("/courses/{courseId}/{quizId}/quiz", 1L, 1L)
                .principal(principal))

                .andExpect(model().attribute("message", "Congrats, you've completed this quiz!"))
                .andExpect(model().attribute("quizId", 1L))
                .andExpect(model().attribute("courseId", 1L))
                .andExpect(model().attribute("userScore", 100.0))
                .andExpect(model().attribute("avgScore", 50.0))

                .andExpect(status().isOk())
                .andExpect(view().name("quizEvent"));
    }

    @Test
    public void quizEventHandler_QuestionsNotAnswered() throws Exception {
        List<Alternative> alternativeList = new ArrayList<>(Arrays.asList(alternativeOne, alternativeTwo));

        when(principal.getName()).thenReturn("username");
        when(userService.findByUsername("username")).thenReturn(user);
        when(courseService.findOne(1L)).thenReturn(course);
        when(quizService.findOne(1L)).thenReturn(quiz);
        when(questionService.findAllByQuiz_Id(1L)).thenReturn(new ArrayList<>(Arrays.asList(questionOne)));

        when(user.getId()).thenReturn(1L);
        when(questionOne.getId()).thenReturn(1L);
        when(answerService.findOneByQuestion_IdAndUser_Id(1L, 1L)).thenReturn(null);

        when(alternativeService.findAllByQuestion_Id(1L)).thenReturn(alternativeList);
        when(alternativeOne.getAlternative()).thenReturn("alternativeOne");
        when(alternativeTwo.getAlternative()).thenReturn("alternativeTwo");
        when(questionOne.getCorrectAnswer()).thenReturn("correctAnswer");

        mockMvc.perform(get("/courses/{courseId}/{quizId}/quiz", 1L, 1L)
                .principal(principal))

                .andExpect(model().attribute("quiz", quiz))
                .andExpect(model().attribute("alternatives", is(org.hamcrest.Matchers.containsInAnyOrder("alternativeOne", "alternativeTwo", "correctAnswer"))))
                .andExpect(model().attribute("course", course))
                .andExpect(model().attribute("question", questionOne))
                .andExpect(model().attribute("questionId", 1L))
                .andExpect(model().attribute("quizId", 1L))
                .andExpect(model().attribute("courseId", 1L))

                .andExpect(status().isOk())
                .andExpect(view().name("quizEvent"));
    }



    @Test
    public void getUserScoreInQuiz_WithNoAnswers_ReturnsNullValue() throws Exception {
        when(user.getId()).thenReturn(1L);
        when(answerService.findAllByQuiz_IdAndUser_Id(1L, 1L)).thenReturn(Arrays.asList());

        Double getUserScoreInQuiz = controller.getUserScoreInQuiz(1L, user);
        assertThat(getUserScoreInQuiz, nullValue());
    }



    @Test
    public void getAvgScoreForQuiz_WithNoAnswers_ReturnsNullValue() throws Exception {
        when(answerService.findAllByQuiz_Id(1L)).thenReturn(Arrays.asList());

        Double result = controller.getAvgScoreForQuiz(1L);
        assertThat(result, nullValue());
    }



    @Test
    public void generateMailSubject() throws Exception {
        when(quizService.findOne(1L)).thenReturn(quiz);
        when(quiz.getQuizName()).thenReturn("quizName");

        String result = this.controller.generateMailSubject(1L);
        assertThat(result, is("quizName - results"));
    }



    @Test
    public void generateMailBody() throws Exception {
        User userOne = mock(User.class);
        User userTwo = mock(User.class);
        User userThree = mock(User.class);

        when(user.getUsername()).thenReturn("username");

        when(quiz.getQuizName()).thenReturn("quizName");

        when(answerOne.isCorrect()).thenReturn(true);
        when(answerTwo.isCorrect()).thenReturn(false);

        //One out of one answer correct
        when(quizService.findOne(1L)).thenReturn(quiz);
        when(userService.findOne(1L)).thenReturn(userOne);
        when(userOne.getId()).thenReturn(1L);
        when(userOne.getUsername()).thenReturn("username");
        when(answerService.findAllByQuiz_IdAndUser_Id(1L, 1L)).thenReturn(new ArrayList<>(Arrays.asList(answerOne)));

        String result1Of1 = this.controller.generateMailBody(1L, 1L);
        assertThat(result1Of1, is("Hi username!\n\nYou got 100.0% correct on the quizName quiz.\n\nExcellent work! You're doing great. Keep it up!\n\nChiliPrepper"));

        //One out of two answer correct
        when(quizService.findOne(2L)).thenReturn(quiz);
        when(userService.findOne(2L)).thenReturn(userTwo);
        when(userTwo.getId()).thenReturn(2L);
        when(userTwo.getUsername()).thenReturn("username");
        when(answerService.findAllByQuiz_IdAndUser_Id(2L, 2L)).thenReturn(new ArrayList<>(Arrays.asList(answerOne, answerTwo)));

        String result1Of2 = this.controller.generateMailBody(2L, 2L);
        assertThat(result1Of2, is("Hi username!\n\nYou got 50.0% correct on the quizName quiz.\n\nNot bad, but don't get cocky! Keep it up :)\n\nChiliPrepper"));

        //Zero out of one answer correct
        when(quizService.findOne(3L)).thenReturn(quiz);
        when(userService.findOne(3L)).thenReturn(userThree);
        when(userThree.getId()).thenReturn(3L);
        when(userThree.getUsername()).thenReturn("username");
        when(answerService.findAllByQuiz_IdAndUser_Id(3L, 3L)).thenReturn(new ArrayList<>(Arrays.asList(answerTwo)));

        String result0Of1 = this.controller.generateMailBody(3L, 3L);
        assertThat(result0Of1, is( "Hi username!\n\nYou got 0.0% correct on the quizName quiz.\n\nOh.. Not great =p I'm guessing you didn't prepare for this quiz =/\nYou should put in some more work.\n\nChiliPrepper"));
    }


    
    @Test
    public void submitAnswer_CorrectAnswer() throws Exception {
        when(course.getId()).thenReturn(1L);
        when(quiz.getId()).thenReturn(1L);

        when(principal.getName()).thenReturn("username");
        when(userService.findByUsername("username")).thenReturn(user);

        when(courseService.findOne(1L)).thenReturn(course);
        when(quizService.findOne(1L)).thenReturn(quiz);
        when(questionService.findOne(1L)).thenReturn(questionOne);

        when(questionOne.getCorrectAnswer()).thenReturn("correctAnswer");

        mockMvc.perform(post("/submitAnswer")
                .param("questionId", "1")
                .param("courseId", "1")
                .param("quizId", "1")
                .param("answer", "correctAnswer")
                .principal(principal))

                .andExpect(flash().attributeExists("flash"))
                .andExpect(flash().attribute("flash", hasProperty("message", is("Correct! Good job!"))))
                .andExpect(flash().attribute("flash", hasProperty("status", is(FlashMessage.Status.SUCCESS))))

                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/courses/1/1/quiz"));

        verify(answerService).save(any(Answer.class));
    }

    @Test
    public void submitAnswer_WrongAnswer() throws Exception {
        when(course.getId()).thenReturn(1L);
        when(quiz.getId()).thenReturn(1L);

        when(principal.getName()).thenReturn("username");
        when(userService.findByUsername("username")).thenReturn(user);

        when(courseService.findOne(1L)).thenReturn(course);
        when(quizService.findOne(1L)).thenReturn(quiz);
        when(questionService.findOne(1L)).thenReturn(questionOne);

        when(questionOne.getCorrectAnswer()).thenReturn("correctAnswer");

        mockMvc.perform(post("/submitAnswer")
                .param("questionId", "1")
                .param("courseId", "1")
                .param("quizId", "1")
                .param("answer", "incorrectAnswer")
                .principal(principal))

                .andExpect(flash().attributeExists("flash"))
                .andExpect(flash().attribute("flash", hasProperty("message", is("Wrong answer, too bad!"))))
                .andExpect(flash().attribute("flash", hasProperty("status", is(FlashMessage.Status.FAILURE))))

                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/courses/1/1/quiz"));

        verify(answerService).save(any(Answer.class));
    }



    @Test
    public void publishQuiz_QuizPublished() throws Exception {
        when(quiz.isPublished()).thenReturn(false);
        when(course.getId()).thenReturn(1L);
        when(quiz.getCourse()).thenReturn(course);
        when(quizService.findOne(1L)).thenReturn(quiz);

        mockMvc.perform(get("/publishQuiz")
                .param("quizId", "1"))

                .andExpect(flash().attributeExists("flash"))
                .andExpect(flash().attribute("flash", hasProperty("message", is("Quiz published!"))))
                .andExpect(flash().attribute("flash", hasProperty("status", is(FlashMessage.Status.SUCCESS))))

                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/courses/" + course.getId()));

        verify(quiz).setPublished(true);
        verify(quizService).save(any(Quiz.class));
    }

    @Test
    public void publishQuiz_QuizUnpublished() throws Exception {
        when(quiz.isPublished()).thenReturn(true);
        when(course.getId()).thenReturn(1L);
        when(quiz.getCourse()).thenReturn(course);
        when(quizService.findOne(1L)).thenReturn(quiz);

        mockMvc.perform(get("/publishQuiz")
                .param("quizId", "1"))

                .andExpect(flash().attributeExists("flash"))
                .andExpect(flash().attribute("flash", hasProperty("message", is("Quiz unpublished!"))))
                .andExpect(flash().attribute("flash", hasProperty("status", is(FlashMessage.Status.SUCCESS))))

                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/courses/" + course.getId()));

        verify(quizService).save(any(Quiz.class));
    }



    @Test
    public void feedQuizChartData_RendersQuizChartGraph() throws Exception {
        when(questionService.findAllByQuiz_Id(1L)).thenReturn(new ArrayList<>(Arrays.asList(questionOne, questionTwo)));

        when(questionOne.getId()).thenReturn(1L);
        when(answerService.findAllByQuestion_Id(1L)).thenReturn(new ArrayList<>(Arrays.asList(answerOne)));
        when(answerOne.isCorrect()).thenReturn(true);
        when(answerTwo.isCorrect()).thenReturn(false);

        when(questionTwo.getId()).thenReturn(2L);
        when(answerService.findAllByQuestion_Id(2L)).thenReturn(new ArrayList<>(Arrays.asList(answerTwo)));

        when(quizService.findOne(1L)).thenReturn(quiz);
        when(quiz.getQuizName()).thenReturn("quizName");

        mockMvc.perform(get("/quizChart/{quizId}", 1L))

                .andExpect(model().attribute("results", Arrays.asList(100.0, 0.0)))
                .andExpect(model().attribute("quizName", "quizName"))

                .andExpect(status().isOk())
                .andExpect(view().name("graph:: quizChart"));
    }



    @Test
    public void saveEditedQuiz_RedirectsToQuizView() throws Exception {
        when(quiz.getQuizName()).thenReturn("quizName");
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
    public void deleteQuiz_RedirectsToCourseView() throws Exception {
        when(quizService.findOne(1L)).thenReturn(quiz);
        when(quiz.getCourse()).thenReturn(course);
        when(course.getId()).thenReturn(1L);

        mockMvc.perform(get("/deleteQuiz")
                .param("quizId", "1"))

                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/courses/1"));

        verify(quizService).delete(quiz);
    }



    @Test
    public void renderEditQuiz() throws Exception {
        when(quizService.findOne(1L)).thenReturn(quiz);
        when(courseService.findOne(1L)).thenReturn(course);

        mockMvc.perform(get("/courses/{courseId}/{quizId}/editName", 1L, 1L))

                .andExpect(model().attribute("quiz", quiz))
                .andExpect(model().attribute("course", course))

                .andExpect(status().isOk())
                .andExpect(view().name("editQuiz"));
    }

    @Test
    public void saveNewQuizName_RendersQuizView() throws Exception {
        when(quizService.findOne(1L)).thenReturn(quiz);
        when(courseService.findOne(1L)).thenReturn(course);

        mockMvc.perform(post("/courses/{courseId}/{quizId}/editName", 1L, 1L)
                .param("quizName", "quizName")
                .param("courseId", "1")
                .param("quizId", "1"))

                .andExpect(flash().attributeExists("flash"))
                .andExpect(flash().attribute("flash", hasProperty("message", is("Quiz name was changed to quizName"))))
                .andExpect(flash().attribute("flash", hasProperty("status", is(FlashMessage.Status.SUCCESS))))

                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/courses/1/1"));

        verify(quizService).save(quiz);
    }

}
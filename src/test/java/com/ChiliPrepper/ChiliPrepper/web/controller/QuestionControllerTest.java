package com.ChiliPrepper.ChiliPrepper.web.controller;

import org.junit.Test;
import org.junit.Before;
import org.mockito.Mock;
import java.util.Arrays;
import java.util.ArrayList;
import org.mockito.InjectMocks;
import org.junit.runner.RunWith;
import com.ChiliPrepper.ChiliPrepper.model.*;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import com.ChiliPrepper.ChiliPrepper.web.FlashMessage;
import com.ChiliPrepper.ChiliPrepper.service.QuizService;
import com.ChiliPrepper.ChiliPrepper.service.QuestionService;
import com.ChiliPrepper.ChiliPrepper.service.AlternativeService;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.hasProperty;
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
 */

@RunWith(MockitoJUnitRunner.class)
public class QuestionControllerTest {
    private MockMvc mockMvc;
    private InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();

    @Mock
    private Quiz quiz;

    @Mock
    private Course course;

    @Mock
    private Alternative alt1;

    @Mock
    private Alternative alt2;

    @Mock
    private Alternative alt3;

    @Mock
    private Question question;

    @Mock
    private QuizService quizService;

    @Mock
    private QuestionService questionService;

    @Mock
    private AlternativeService alternativeService;

    @InjectMocks
    private QuestionController controller;

    @Before
    public void setUp() throws Exception {
        viewResolver.setSuffix(".jsp");
        mockMvc = MockMvcBuilders.standaloneSetup(controller).setViewResolvers(viewResolver).build();
    }

    @Test
    public void renderQuestionView() throws Exception {
        //Finds the selected question
        when(questionService.findOne(1L)).thenReturn(question);

        mockMvc.perform(get
                ("/courses/{courseId}/{quizId}/{questionId}", 1L, 1L, 1L))
                .andExpect(model().attributeExists("question"))
                .andExpect(status().isOk())
                .andExpect(view().name("question"));

        verify(questionService).findOne(1L);
    }



    @Test
    public void addQuestion_SuccessfullyAddsQuestion_RedirectsToTheQuiz() throws Exception {
        //Finds the quiz that is selected within the course
        when(course.getId()).thenReturn(1L);
        when(quiz.getCourse()).thenReturn(course);
        when(quizService.findOne(1L)).thenReturn(quiz);

        //The question and the correct answer to the question is entered
        when(question.getTheQuestion()).thenReturn("theQuestion");
        when(question.getCorrectAnswer()).thenReturn("correctAnswer");

        mockMvc.perform(post("/addQuestion")
                .flashAttr("question", question)
                .param("quizId", "1")
                .param("alt1", "alt1")
                .param("alt2", "alt2")
                .param("alt3", "alt3"))

                .andExpect(flash().attributeExists("flash"))
                .andExpect(flash().attribute("flash", hasProperty("message", is("Question added!"))))
                .andExpect(flash().attribute("flash", hasProperty("status", is(FlashMessage.Status.SUCCESS))))

                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/courses/1/1"));

        verify(quizService).findOne(1L);
        verify(questionService).save(any(Question.class));
        verify(alternativeService, times(3)).save(any(Alternative.class));
    }

    @Test
    public void addQuestion_QuestionNotEntered_FailsToAddQuestion_RedirectsToTheQuiz() throws Exception {
        //Finds the quiz that is selected within the course
        when(course.getId()).thenReturn(1L);
        when(quiz.getCourse()).thenReturn(course);
        when(quizService.findOne(1L)).thenReturn(quiz);

        //Question not entered while trying to add question
        when(question.getTheQuestion()).thenReturn("");

        mockMvc.perform(post("/addQuestion")
                .flashAttr("question", question)
                .param("quizId", "1")
                .param("alt1", "alt1")
                .param("alt2", "alt2")
                .param("alt3", "alt3"))

                .andExpect(flash().attributeExists("flash"))
                .andExpect(flash().attribute("flash", hasProperty("message", is("Failed to add question. Needs at least a question and correct answer!"))))
                .andExpect(flash().attribute("flash", hasProperty("status", is(FlashMessage.Status.FAILURE))))

                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/courses/1/1"));
    }

    @Test
    public void addQuestion_CorrectAnswerNotEntered_FailsToAddQuestion_RedirectsToTheQuiz() throws Exception {
        //Finds the quiz that is selected within the course
        when(course.getId()).thenReturn(1L);
        when(quiz.getCourse()).thenReturn(course);
        when(quizService.findOne(1L)).thenReturn(quiz);

        //The question's correct answer ain't entered
        when(question.getTheQuestion()).thenReturn("theQuestion");
        when(question.getCorrectAnswer()).thenReturn("");

        mockMvc.perform(post("/addQuestion")
                .flashAttr("question", question)
                .param("quizId", "1")
                .param("alt1", "alt1")
                .param("alt2", "alt2")
                .param("alt3", "alt3"))

                .andExpect(flash().attributeExists("flash"))
                .andExpect(flash().attribute("flash", hasProperty("message", is("Failed to add question. Needs at least a question and correct answer!"))))
                .andExpect(flash().attribute("flash", hasProperty("status", is(FlashMessage.Status.FAILURE))))

                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/courses/1/1"));
    }



    @Test
    public void renderEditQuestionView() throws Exception {
        //Finds the selected question
        when(questionService.findOne(1L)).thenReturn(question);

        //Finds the question's alternatives
        when(alternativeService.findAllByQuestion_Id(1L)).thenReturn(new ArrayList<>(Arrays.asList(alt1, alt2, alt3)));

        //Finds the quiz and course in order to add them to the model
        when(quiz.getId()).thenReturn(1L);
        when(course.getId()).thenReturn(1L);
        when(quiz.getCourse()).thenReturn(course);
        when(question.getQuiz()).thenReturn(quiz);

        mockMvc.perform(get("/courses/{courseId}/{quizId}/{questionId}/editQuestion", 1L, 1L, 1L)
                .param("questionId", "1"))

                .andExpect(model().attributeExists("alt1"))
                .andExpect(model().attributeExists("alt2"))
                .andExpect(model().attributeExists("alt3"))
                .andExpect(model().attributeExists("quizId"))
                .andExpect(model().attributeExists("courseId"))
                .andExpect(model().attributeExists("question"))

                .andExpect(status().isOk())
                .andExpect(view().name("editQuestion"));

        verify(questionService).findOne(1L);
        verify(alternativeService).findAllByQuestion_Id(1L);
    }



    @Test
    public void saveEditedQuestion_RedirectsToTheQuestion() throws Exception {
        //Finds the question's alternatives
        when(alternativeService.findOne(1L)).thenReturn(alt1);
        when(alternativeService.findOne(2L)).thenReturn(alt2);
        when(alternativeService.findOne(3L)).thenReturn(alt3);

        //Finds the selected question and the question's quiz
        when(questionService.findOne(1L)).thenReturn(question);
        when(quizService.findOne(1L)).thenReturn(quiz);

        //Finds the the course's, quiz's and question's ID fir the redirected URL
        when(quiz.getCourse()).thenReturn(course);
        when(course.getId()).thenReturn(1L);
        when(quiz.getId()).thenReturn(1L);
        when(question.getId()).thenReturn(1L);

        mockMvc.perform(get("/courses/{courseId}/{quizId}/{questionId}/editQuestion/saveEditQuestion", 1L, 1L, 1L)
                .param("alt1", "alt1")
                .param("alt2", "alt2")
                .param("alt3", "alt3")
                .param("alt1Id", "1")
                .param("alt2Id", "2")
                .param("alt3Id", "3")
                .param("questionId", "1")
                .param("correctAnswer", "correctAnswer")
                .param("theQuestion", "theQuestion")
                .param("quizId", "1")
                .param("topic", "topic"))

                .andExpect(flash().attributeExists("flash"))
                .andExpect(flash().attribute("flash", hasProperty("message", is("Question updated!"))))
                .andExpect(flash().attribute("flash", hasProperty("status", is(FlashMessage.Status.SUCCESS))))

                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/courses/1/1/1/editQuestion?questionId=1"));

        verify(alternativeService, times(3)).save(any(Alternative.class));
        verify(questionService).save(any(Question.class));
        }



        @Test
        public void deleteQuestion_RedirectsToTheQuiz() throws Exception{
        //Finds the selected question
        when(questionService.findOne(1L)).thenReturn(question);

        //Finds the quiz and course in order to add them to the model
        when(quiz.getId()).thenReturn(1L);
        when(course.getId()).thenReturn(1L);
        when(quiz.getCourse()).thenReturn(course);
        when(question.getQuiz()).thenReturn(quiz);


        mockMvc.perform(get("/deleteQuestion")
                .param("questionId", "1"))

                .andExpect(flash().attributeExists("flash"))
                .andExpect(flash().attribute("flash", hasProperty("message", is("Question deleted!"))))
                .andExpect(flash().attribute("flash", hasProperty("status", is(FlashMessage.Status.SUCCESS))))

                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl(("/courses/1/1/editQuiz?quizId=1")));

        verify(questionService).delete(question);
    }

}
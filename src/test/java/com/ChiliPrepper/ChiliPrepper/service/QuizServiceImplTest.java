package com.ChiliPrepper.ChiliPrepper.service;

import org.junit.Test;
import java.util.List;
import java.util.Arrays;
import org.mockito.Mock;
import java.util.ArrayList;
import org.mockito.InjectMocks;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import com.ChiliPrepper.ChiliPrepper.model.Quiz;
import com.ChiliPrepper.ChiliPrepper.dao.QuizDao;
import com.ChiliPrepper.ChiliPrepper.dao.AnswerDao;
import com.ChiliPrepper.ChiliPrepper.model.Question;
import com.ChiliPrepper.ChiliPrepper.dao.QuestionDao;
import com.ChiliPrepper.ChiliPrepper.dao.AlternativeDao;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;

/**
 * Created by Dag Kirstihagen on 15/03/2017.
 */

@RunWith(MockitoJUnitRunner.class)
public class QuizServiceImplTest {
    private Long courseId, quizId = courseId = 1L;
    private List<Quiz> quizList = new ArrayList<>(Arrays.asList(new Quiz()));
    private List<Question> questionList = new ArrayList<>(Arrays.asList(new Question()));

    @Mock
    private Quiz quiz;

    @Mock
    private QuizDao quizDao;

    @Mock
    private Question question;

    @Mock
    private AnswerDao answerDao;

    @Mock
    private QuestionDao questionDao;

    @Mock
    private AlternativeDao alternativeDao;

    @InjectMocks
    private QuizService service = new QuizServiceImpl();

    /**Confirms that quizService.findOne(quizId) returns quizDao.findOne(quizId)*/
    @Test
    public void findOne() throws Exception {
        when(quizDao.findOne(quizId)).thenReturn(quiz);
        assertThat(service.findOne(quizId), is(quiz));
        verify(quizDao).findOne(quizId);
    }

    /**Confirms that quizService.findAllByCourse_Id(courseId) returns quizDao.findAllByCourse_Id(courseId)*/
    @Test
    public void findAllByCourse_id() throws Exception {
        when(quizDao.findAllByCourse_id(courseId)).thenReturn(quizList);
        assertThat(service.findAllByCourse_id(courseId), is(quizList));
        verify(quizDao).findAllByCourse_id(courseId);
    }

    /**Confirms that quizService.save(quiz) calls quizDao.save(quiz)*/
    @Test
    public void save() throws Exception {
        service.save(quiz);
        verify(quizDao).save(quiz);
    }

    /**Confirms that quizService.delete(quiz) calls
     * answerDao.deleteAllByQuiz_Id(quizId),
     * questionDao.deleteAllByQuiz_Id(quizId)
     * and quizDao.delete(quiz)*/
    @Test
    public void delete() throws Exception {
        when(quiz.getId()).thenReturn(quizId);
        when(questionDao.findAllByQuiz_Id(quizId)).thenReturn(questionList);
        when(question.getId()).thenReturn(quizId);
        service.delete(quiz);
        verify(answerDao).deleteAllByQuiz_Id(quizId);
        verify(questionDao).deleteAllByQuiz_Id(quizId);
        verify(quizDao).delete(quiz);
    }

}
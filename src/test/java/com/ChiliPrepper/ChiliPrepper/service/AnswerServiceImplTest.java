package com.ChiliPrepper.ChiliPrepper.service;

import org.junit.Test;
import java.util.List;
import java.util.Arrays;
import org.mockito.Mock;
import java.util.ArrayList;
import org.mockito.InjectMocks;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import com.ChiliPrepper.ChiliPrepper.model.Answer;
import com.ChiliPrepper.ChiliPrepper.dao.AnswerDao;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;

/**
 * Created by dagki on 15/03/2017.
 */

@RunWith(MockitoJUnitRunner.class)
public class AnswerServiceImplTest {
    private Answer answer = new Answer();
    private List<Answer> answerList = new ArrayList<>(Arrays.asList(answer));
    private Long questionId, courseId, userId, quizId = userId = courseId = questionId = 1L;

    @Mock
    private AnswerDao answerDao;

    @InjectMocks
    private AnswerService answerService = new AnswerServiceImpl();

    /**Confirms that answerService.findOneByQuestion_Id(questionId) returns answerDao.findOneByQuestion_Id(questionId)*/
    @Test
    public void findOneByQuestion_Id() throws Exception {
        when(answerDao.findOneByQuestion_Id(questionId)).thenReturn(answer);
        assertThat(answerService.findOneByQuestion_Id(questionId), is(answer));
        verify(answerDao).findOneByQuestion_Id(questionId);
    }

    /**Confirms that answerService.findOneByQuestion_IdAndUser_Id(questionId, userId) returns answerDao.findOneByQuestion_IdAndUser_Id(questionId, userId)*/
    @Test
    public void findOneByQuestion_IdAndUser_Id() throws Exception {
        when(answerDao.findOneByQuestion_IdAndUser_Id(questionId, userId)).thenReturn(answer);
        assertThat(answerService.findOneByQuestion_IdAndUser_Id(questionId, userId), is(answer));
        verify(answerDao).findOneByQuestion_IdAndUser_Id(questionId, userId);
    }

    /**Confirms that answerService.findAllByUser_Id(userId) returns answerDao.findAllByUser_Id(userId)*/
    @Test
    public void findAllByUser_Id() throws Exception {
        when(answerDao.findAllByUser_Id(userId)).thenReturn(answerList);
        assertThat(answerService.findAllByUser_Id(userId), is(answerList));
        verify(answerDao).findAllByUser_Id(userId);
    }

    /**Confirms that answerService.findAllByQuestion_Id(questionId) returns answerDao.findAllByQuestion_Id(questionId)*/
    @Test
    public void findAllByQuestion_Id() throws Exception {
        when(answerDao.findAllByQuestion_Id(questionId)).thenReturn(answerList);
        assertThat(answerService.findAllByQuestion_Id(questionId), is(answerList));
        verify(answerDao).findAllByQuestion_Id(questionId);
    }

    /**Confirms that answerService.deleteAllByQuiz_Id(quizId) returns answerDao.deleteAllByQuiz_Id(quizId)*/
    @Test
    public void findAllByQuiz_Id() throws Exception {
        when(answerDao.findAllByQuiz_Id(quizId)).thenReturn(answerList);
        assertThat(answerService.findAllByQuiz_Id(quizId), is(answerList));
        verify(answerDao).findAllByQuiz_Id(quizId);
    }

    /**Confirms that answerService.findAllByCourse_Id(courseId) returns answerDao.findAllByCourse_Id(courseId)*/
    @Test
    public void findAllByCourse_Id() throws Exception {
        when(answerDao.findAllByCourse_Id(courseId)).thenReturn(answerList);
        assertThat(answerService.findAllByCourse_Id(courseId), is(answerList));
        verify(answerDao).findAllByCourse_Id(courseId);
    }

    /**Confirms that answerService.findAllByQuiz_IdAndUser_Id(quizId, userId) returns answerDao.findAllByQuiz_IdAndUser_Id(quizId, userId)*/
    @Test
    public void findAllByQuiz_IdAndUser_Id() throws Exception {
        when(answerDao.findAllByQuiz_IdAndUser_Id(quizId, userId)).thenReturn(answerList);
        assertThat(answerService.findAllByQuiz_IdAndUser_Id(quizId, userId), is(answerList));
        verify(answerDao).findAllByQuiz_IdAndUser_Id(quizId, userId);
    }

    /**Confirms that answerService.findAllByCourse_IdAndUser_Id(courseId, userId) returns answerDao.findAllByCourse_IdAndUser_Id(courseId, userId)*/
    @Test
    public void findAllByCourse_IdAndUser_Id() throws Exception {
        when(answerDao.findAllByCourse_IdAndUser_Id(courseId, userId)).thenReturn(answerList);
        assertThat(answerService.findAllByCourse_IdAndUser_Id(courseId, userId), is(answerList));
        verify(answerDao).findAllByCourse_IdAndUser_Id(courseId, userId);
    }

    /**Confirms that answerService.save(answer) calls answerDao.save(answer)*/
    @Test
    public void save() throws Exception {
        answerService.save(answer);
        verify(answerDao).save(answer);
    }

    /**Confirms that answerService.deleteAllByQuiz_Id(quizId) calls answerDao.deleteAllByQuiz_Id(quizId)*/
    @Test
    public void deleteAllByQuiz_Id() throws Exception {
        answerService.deleteAllByQuiz_Id(quizId);
        verify(answerDao).deleteAllByQuiz_Id(quizId);
    }

    /**Confirms that answerService.deleteAllByQuestion_Id(questionId) calls answerDao.deleteAllByQuestion_Id(questionId)*/
    @Test
    public void deleteAllByQuestion_Id() throws Exception {
        answerService.deleteAllByQuestion_Id(questionId);
        verify(answerDao).deleteAllByQuestion_Id(questionId);
    }

}
package com.ChiliPrepper.ChiliPrepper.service;

import com.ChiliPrepper.ChiliPrepper.dao.AnswerDao;
import com.ChiliPrepper.ChiliPrepper.dao.CourseDao;
import com.ChiliPrepper.ChiliPrepper.model.Answer;
import com.ChiliPrepper.ChiliPrepper.model.Course;
import com.ChiliPrepper.ChiliPrepper.model.Quiz;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.codehaus.groovy.runtime.DefaultGroovyMethods.any;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;

/**
 * Created by dagki on 15/03/2017.
 */

@RunWith(MockitoJUnitRunner.class)
public class AnswerServiceImplTest {


    @Mock
    Answer answerOne;

    @Mock
    Answer answerTwo;

    @Mock
    private AnswerDao dao;

    @InjectMocks
    private AnswerService service = new AnswerServiceImpl();

    List<Answer> answerList = new ArrayList<>(Arrays.asList(answerOne, answerTwo));

    @Test
    public void findOneByQuestion_IdAndUser_Id() throws Exception {
        Long questionId = 1L;
        Long userId = 1L;
        when(dao.findOneByQuestion_IdAndUser_Id(questionId, userId)).thenReturn(answerOne);
        assertThat(service.findOneByQuestion_IdAndUser_Id(questionId, userId), is(answerOne));
        verify(dao).findOneByQuestion_IdAndUser_Id(questionId, userId);
    }

    @Test
    public void deleteAllByQuiz_Id() throws Exception {
        Long quizId = 1L;
        service.deleteAllByQuiz_Id(quizId);
        verify(dao).deleteAllByQuiz_Id(quizId);
    }

    @Test
    public void deleteAllByQuestion_Id() throws Exception {
        Long questionId = 1L;
        service.deleteAllByQuestion_Id(questionId);
        verify(dao).deleteAllByQuestion_Id(questionId);

    }

    @Test
    public void findAllByCourse_IdAndUser_Id() throws Exception {
        Long courseId = 1L;
        Long userId = 1L;
        when(dao.findAllByCourse_IdAndUser_Id(courseId, userId)).thenReturn(answerList);
        assertThat(service.findAllByCourse_IdAndUser_Id(courseId, userId), is(answerList));
        verify(dao).findAllByCourse_IdAndUser_Id(courseId, userId);
    }

    @Test
    public void findAllByQuiz_IdAndUser_Id() throws Exception {
        Long quizId = 1L;
        Long userId = 1L;
        when(dao.findAllByQuiz_IdAndUser_Id(quizId, userId)).thenReturn(answerList);
        assertThat(service.findAllByQuiz_IdAndUser_Id(quizId, userId), is(answerList));
        verify(dao).findAllByQuiz_IdAndUser_Id(quizId, userId);
    }

    @Test
    public void findAllByUser_Id() throws Exception {
        Long userId = 1L;
        when(dao.findAllByUser_Id(userId)).thenReturn(answerList);
        assertThat(service.findAllByUser_Id(userId), is(answerList));
        verify(dao).findAllByUser_Id(userId);
    }



    @Test
    public void findAllByQuestion_Id() throws Exception {
        Long questionId = 1L;
        when(dao.findAllByQuestion_Id(questionId)).thenReturn(answerList);
        assertThat(service.findAllByQuestion_Id(questionId), is(answerList));
        verify(dao).findAllByQuestion_Id(questionId);
    }

    @Test
    public void findAllByQuiz_Id() throws Exception {
        Long quizId = 1L;
        when(dao.findAllByQuiz_Id(quizId)).thenReturn(answerList);
        assertThat(service.findAllByQuiz_Id(quizId), is(answerList));
        verify(dao).findAllByQuiz_Id(quizId);
    }

    @Test
    public void findAllByCourse_Id() throws Exception {
        Long courseId = 1L;
        when(dao.findAllByCourse_Id(courseId)).thenReturn(answerList);
        assertThat(service.findAllByCourse_Id(courseId), is(answerList));
        verify(dao).findAllByCourse_Id(courseId);
    }

    @Test
    public void findOneByQuestion_Id() throws Exception {
        when(dao.findOneByQuestion_Id(1L)).thenReturn(new Answer());
        assertThat(service.findOneByQuestion_Id(1L), instanceOf(Answer.class));
        verify(dao).findOneByQuestion_Id(1L);
    }

    @Test
    public void save() throws Exception {
        service.save(new Answer());
        verify(dao).save(any(Answer.class));
    }

}
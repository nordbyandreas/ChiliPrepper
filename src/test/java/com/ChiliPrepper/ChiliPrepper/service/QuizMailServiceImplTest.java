package com.ChiliPrepper.ChiliPrepper.service;

import com.ChiliPrepper.ChiliPrepper.dao.QuizMailDao;
import com.ChiliPrepper.ChiliPrepper.model.QuizMail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.codehaus.groovy.runtime.DefaultGroovyMethods.any;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;

/**
 * Created by dagki on 29/03/2017.
 */

@RunWith(MockitoJUnitRunner.class)
public class QuizMailServiceImplTest {

    @Mock
    QuizMail quizMail;

    @Mock
    QuizMailDao dao;

    @InjectMocks
    QuizMailService service = new QuizMailServiceImpl();

    List<QuizMail> quizMailList = new ArrayList<>(Arrays.asList(quizMail));

    @Test
    public void findAllByQuiz_Id() throws Exception {
        Long quizId = 1L;
        when(dao.findAllByQuiz_Id(quizId)).thenReturn(quizMailList);
        assertThat(service.findAllByQuiz_Id(quizId), is(quizMailList));
        verify(dao).findAllByQuiz_Id(quizId);
    }

    @Test
    public void findAllByParticipant_Id() throws Exception {
        Long participantId = 1L;
        when(dao.findAllByParticipant_Id(participantId)).thenReturn(quizMailList);
        assertThat(service.findAllByParticipant_Id(participantId), is(quizMailList));
        verify(dao).findAllByParticipant_Id(participantId);
    }

    @Test
    public void findAllByQuiz_IdAndCourse_Id() throws Exception {
        Long quizId = 1L;
        Long courseId = 1L;
        when(dao.findAllByQuiz_IdAndCourse_Id(quizId, courseId)).thenReturn(quizMailList);
        assertThat(service.findAllByQuiz_IdAndCourse_Id(quizId, courseId), is(quizMailList));
        verify(dao).findAllByQuiz_IdAndCourse_Id(quizId, courseId);
    }

    @Test
    public void findOneByQuiz_Id() throws Exception {
        Long quizId = 1L;
        when(dao.findOneByQuiz_Id(quizId)).thenReturn(quizMail);
        assertThat(service.findOneByQuiz_Id(quizId), is(quizMail));
        verify(dao).findOneByQuiz_Id(quizId);
    }

    @Test
    public void findOneByQuiz_IdAndParticipant_Id() throws Exception {
        Long quizId = 1L;
        Long participantId = 1L;
        when(dao.findOneByQuiz_IdAndParticipant_Id(quizId, participantId)).thenReturn(quizMail);
        assertThat(service.findOneByQuiz_IdAndParticipant_Id(quizId, participantId), is(quizMail));
        verify(dao).findOneByQuiz_IdAndParticipant_Id(quizId, participantId);
    }

    @Test
    public void deleteAllByQuiz_Id() throws Exception {
        Long quizId = 1L;
        service.deleteAllByQuiz_Id(quizId);
        verify(dao).deleteAllByQuiz_Id(quizId);
    }

    @Test
    public void deleteAllByParticipant_Id() throws Exception {
        Long participantId = 1L;
        service.deleteAllByParticipant_Id(participantId);
        verify(dao).deleteAllByParticipant_Id(participantId);
    }

    @Test
    public void save() throws Exception {
        service.save(new QuizMail());
        verify(dao).save(any(QuizMail.class));
    }

}
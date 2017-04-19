package com.ChiliPrepper.ChiliPrepper.service;

import java.util.List;
import org.junit.Test;
import java.util.Arrays;
import org.mockito.Mock;
import java.util.ArrayList;
import org.mockito.InjectMocks;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import com.ChiliPrepper.ChiliPrepper.model.QuizMail;
import com.ChiliPrepper.ChiliPrepper.dao.QuizMailDao;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;

/**
 * Created by dagki on 29/03/2017.
 */

@RunWith(MockitoJUnitRunner.class)
public class QuizMailServiceImplTest {
    private QuizMail quizMail = new QuizMail();
    private Long participantId, courseId, quizId = courseId = participantId = 1L;
    private List<QuizMail> quizMailList = new ArrayList<>(Arrays.asList(quizMail));

    @Mock
    QuizMailDao quizMailDao;

    @InjectMocks
    QuizMailService quizMailService = new QuizMailServiceImpl();

    /**Confirms that quizMailService.findOneByQuiz_Id(quizId) returns quizMailDao.findOneByQuiz_Id(quizId)*/
    @Test
    public void findOneByQuiz_Id() throws Exception {
        when(quizMailDao.findOneByQuiz_Id(quizId)).thenReturn(quizMail);
        assertThat(quizMailService.findOneByQuiz_Id(quizId), is(quizMail));
        verify(quizMailDao).findOneByQuiz_Id(quizId);
    }

    /**Confirms that quizMailService.findOneByQuiz_IdAndParticipant_Id(quizId, participantId) returns quizMailDao.findOneByQuiz_IdAndParticipant_Id(quizId, participantId)*/
    @Test
    public void findOneByQuiz_IdAndParticipant_Id() throws Exception {
        when(quizMailDao.findOneByQuiz_IdAndParticipant_Id(quizId, participantId)).thenReturn(quizMail);
        assertThat(quizMailService.findOneByQuiz_IdAndParticipant_Id(quizId, participantId), is(quizMail));
        verify(quizMailDao).findOneByQuiz_IdAndParticipant_Id(quizId, participantId);
    }

    /**Confirms that quizMailService.findAllByQuiz_Id(quizId) returns quizMailDao.findAllByQuiz_Id(quizId)*/
    @Test
    public void findAllByQuiz_Id() throws Exception {
        when(quizMailDao.findAllByQuiz_Id(quizId)).thenReturn(quizMailList);
        assertThat(quizMailService.findAllByQuiz_Id(quizId), is(quizMailList));
        verify(quizMailDao).findAllByQuiz_Id(quizId);
    }

    /**Confirms that quizMailService.findAllByParticipant_Id(participantId) returns quizMailDao.findAllByParticipant_Id(participantId)*/
    @Test
    public void findAllByParticipant_Id() throws Exception {
        when(quizMailDao.findAllByParticipant_Id(participantId)).thenReturn(quizMailList);
        assertThat(quizMailService.findAllByParticipant_Id(participantId), is(quizMailList));
        verify(quizMailDao).findAllByParticipant_Id(participantId);
    }

    /**Confirms that quizMailService.findAllByQuiz_IdAndCourse_Id(quizId, courseId) returns quizMailDao.findAllByQuiz_IdAndCourse_Id(quizId, courseId)*/
    @Test
    public void findAllByQuiz_IdAndCourse_Id() throws Exception {
        when(quizMailDao.findAllByQuiz_IdAndCourse_Id(quizId, courseId)).thenReturn(quizMailList);
        assertThat(quizMailService.findAllByQuiz_IdAndCourse_Id(quizId, courseId), is(quizMailList));
        verify(quizMailDao).findAllByQuiz_IdAndCourse_Id(quizId, courseId);
    }

    /**Confirms that quizMailService.save(quizMail) calls quizMailDao.save(quizMail)*/
    @Test
    public void save() throws Exception {
        quizMailService.save(quizMail);
        verify(quizMailDao).save(quizMail);
    }

    /**Confirms that quizMailService.deleteAllByQuiz_Id(quizId) calls quizMailDao.deleteAllByQuiz_Id(quizId)*/
    @Test
    public void deleteAllByQuiz_Id() throws Exception {
        quizMailService.deleteAllByQuiz_Id(quizId);
        verify(quizMailDao).deleteAllByQuiz_Id(quizId);
    }

    /**Confirms that questionService.deleteAllByParticipant_Id(participantId) calls quizMailDao.deleteAllByParticipant_Id(participantId)*/
    @Test
    public void deleteAllByParticipant_Id() throws Exception {
        quizMailService.deleteAllByParticipant_Id(participantId);
        verify(quizMailDao).deleteAllByParticipant_Id(participantId);
    }

}
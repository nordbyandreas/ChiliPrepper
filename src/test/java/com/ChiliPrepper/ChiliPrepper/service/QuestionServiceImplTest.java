package com.ChiliPrepper.ChiliPrepper.service;

import java.util.List;
import org.junit.Test;
import org.mockito.Mock;
import java.util.Arrays;
import java.util.ArrayList;
import org.mockito.InjectMocks;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
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
public class QuestionServiceImplTest {
    private String topic = "topic";
    private Long questionId, quizId = questionId = 1L;
    private List<Question> questionList = new ArrayList<>(Arrays.asList(new Question()));

    @Mock
    private Question question;

    @Mock
    private AnswerDao answerDao;

    @Mock
    private QuestionDao questionDao;

    @Mock
    private AlternativeDao alternativeDao;

    @InjectMocks
    private QuestionService questionService = new QuestionServiceImpl();

    /**Confirms that questionService.findOne(questionId) returns questionDao.findOne(questionId)*/
    @Test
    public void findOne() throws Exception {
        when(questionDao.findOne(questionId)).thenReturn(question);
        assertThat(questionService.findOne(questionId), is(question));
        verify(questionDao).findOne(questionId);
    }

    /**Confirms that questionService.findAllByTopic(topic) returns questionDao.findAllByTopic(topic)*/
    @Test
    public void findAllByTopic() {
        when(questionDao.findAllByTopic(topic)).thenReturn(questionList);
        assertThat(questionService.findAllByTopic(topic), is(questionList));
        verify(questionDao).findAllByTopic(topic);
    }

    /**Confirms that questionService.findAllByQuiz_Id(quizId) returns questionDao.findAllByQuiz_Id(quizId)*/
    @Test
    public void findAllByQuiz_Id() throws Exception {
        when(questionDao.findAllByQuiz_Id(questionId)).thenReturn(questionList);
        assertThat(questionService.findAllByQuiz_Id(questionId), is(questionList));
        verify(questionDao).findAllByQuiz_Id(questionId);
    }

    /**Confirms that questionService.save(question) calls questionDao.save(question)*/
    @Test
    public void save() throws Exception {
        questionService.save(question);
        verify(questionDao).save(question);
    }

    /**Confirms that questionService.delete(question) calls
     * answerDao.deleteAllByQuestion_Id(question.getId()),
     * alternativeDao.deleteAllByQuestion_Id(question.getId())
     * and questionDao.delete(question)*/
    @Test
    public void delete() throws Exception {
        when(question.getId()).thenReturn(questionId);
        questionService.delete(question);
        verify(answerDao).deleteAllByQuestion_Id(questionId);
        verify(alternativeDao).deleteAllByQuestion_Id(questionId);
        verify(questionDao).delete(question);
    }

    /**Confirms that questionService.deleteAllByQuiz_Id(quizId) calls questionDao.deleteAllByQuiz_Id(quizId)*/
    @Test
    public void deleteAllByQuiz_Id() throws Exception {
        questionService.deleteAllByQuiz_Id(quizId);
        verify(questionDao).deleteAllByQuiz_Id(quizId);
    }

}
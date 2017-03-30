package com.ChiliPrepper.ChiliPrepper.service;

import com.ChiliPrepper.ChiliPrepper.dao.AlternativeDao;
import com.ChiliPrepper.ChiliPrepper.dao.AnswerDao;
import com.ChiliPrepper.ChiliPrepper.dao.QuestionDao;
import com.ChiliPrepper.ChiliPrepper.model.Course;
import com.ChiliPrepper.ChiliPrepper.model.Question;
import com.ChiliPrepper.ChiliPrepper.model.Quiz;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.codehaus.groovy.runtime.DefaultGroovyMethods.any;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;

/**
 * Created by dagki on 15/03/2017.
 */

@RunWith(MockitoJUnitRunner.class)
public class QuestionServiceImplTest {

    @Mock
    AnswerDao answerDao;

    @Mock
    QuestionDao questionDao;

    @Mock
    AlternativeDao alternativeDao;

    @Mock
    Question question;



    @InjectMocks
    QuestionService service = new QuestionServiceImpl();

    @Test
    public void findAllByQuiz_Id() throws Exception {
        Quiz quizOne = new Quiz();
        quizOne.setId(1L);
        Quiz quizTwo = new Quiz();
        quizTwo.setId(2L);
        Question Q1 = new Question();
        Q1.setQuiz(quizOne);
        Question Q2 = new Question();
        Q2.setQuiz(quizOne);
        Question Q3 = new Question();
        Q3.setQuiz(quizTwo);
        List<Question> questions = Arrays.asList(
                Q1,
                Q2,
                Q3
        );
        Iterable<Question> courseIterable = questions;
        when(questionDao.findAllByQuiz_Id(1L)).thenReturn(questions);
        assertThat(service.findAllByQuiz_Id(1L), is(courseIterable));
        verify(questionDao).findAllByQuiz_Id(1L);
    }

    @Test
    public void findOne() throws Exception {
        when(questionDao.findOne(1L)).thenReturn(new Question());
        assertThat(service.findOne(1L), instanceOf(Question.class));
        verify(questionDao).findOne(1L);
    }

    @Test
    public void save() throws Exception {
        service.save(new Question());
        verify(questionDao).save(any(Question.class));
    }

    @Test
    public void deleteAllByQuiz_Id() throws Exception {
        Long quizId = 1L;
        service.deleteAllByQuiz_Id(quizId);
        verify(questionDao).deleteAllByQuiz_Id(quizId);
    }

    @Test
    public void delete() throws Exception {
        when(question.getId()).thenReturn(1L);
        service.delete(question);
        verify(answerDao).deleteAllByQuestion_Id(1L);
        verify(alternativeDao).deleteAllByQuestion_Id(1L);
        verify(questionDao).delete(question);
    }

    @Test
    public void findAllByTopic() {
        String topic = "topic";
        List<Question> questionList = new ArrayList<>(Arrays.asList(question));
        when(questionDao.findAllByTopic(topic)).thenReturn(questionList);
        service.findAllByTopic(topic);
        verify(questionDao).findAllByTopic(topic);
    }

}
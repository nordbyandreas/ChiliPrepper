package com.ChiliPrepper.ChiliPrepper.service;

import com.ChiliPrepper.ChiliPrepper.dao.QuestionDao;
import com.ChiliPrepper.ChiliPrepper.model.Course;
import com.ChiliPrepper.ChiliPrepper.model.Question;
import com.ChiliPrepper.ChiliPrepper.model.Quiz;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by dagki on 15/03/2017.
 */

@RunWith(MockitoJUnitRunner.class)
public class QuestionServiceTest {
    @Mock
    QuestionDao dao;

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
        when(dao.findAllByQuiz_Id(1L)).thenReturn(questions);
        assertThat(service.findAllByQuiz_Id(1L), is(courseIterable));
        verify(dao).findAllByQuiz_Id(1L);
    }

    @Test
    public void findOne() throws Exception {
        when(dao.findOne(1L)).thenReturn(new Question());
        assertThat(service.findOne(1L), instanceOf(Question.class));
        verify(dao).findOne(1L);
    }

    @Test
    public void save() throws Exception {
        final Question question = new Question();
        service.save(question);
        verify(dao).save(question);
    }

}
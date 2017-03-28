package com.ChiliPrepper.ChiliPrepper.service;

import com.ChiliPrepper.ChiliPrepper.dao.QuestionDao;
import com.ChiliPrepper.ChiliPrepper.dao.QuizDao;
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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by dagki on 15/03/2017.
 */

@RunWith(MockitoJUnitRunner.class)
public class QuizServiceTest {
    @Mock
    QuizDao dao;

    @InjectMocks
    QuizService service = new QuizServiceImpl();

    @Test
    public void findAllByCourse_id() throws Exception {
        /*Course C1 = new Course();
        C1.setId(1L);
        Course C2 = new Course();
        C2.setId(2L);
        Course C3 = new Course();
        C3.setId(quizOne);
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
        verify(dao).findAllByQuiz_Id(1L);*/
    }

    @Test
    public void findOne() throws Exception {
        when(dao.findOne(1L)).thenReturn(new Quiz());
        assertThat(service.findOne(1L), instanceOf(Quiz.class));
        verify(dao).findOne(1L);
    }

    @Test
    public void save() throws Exception {
        final Quiz quiz = new Quiz();
        service.save(quiz);
        verify(dao).save(quiz);
    }

}
package com.ChiliPrepper.ChiliPrepper.service;

import com.ChiliPrepper.ChiliPrepper.dao.AlternativeDao;
import com.ChiliPrepper.ChiliPrepper.dao.AnswerDao;
import com.ChiliPrepper.ChiliPrepper.dao.QuestionDao;
import com.ChiliPrepper.ChiliPrepper.dao.QuizDao;
import com.ChiliPrepper.ChiliPrepper.model.Question;
import com.ChiliPrepper.ChiliPrepper.model.Quiz;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
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
public class QuizServiceImplTest {

    @Mock
    Quiz quiz;

    @Mock
    Question question;
    @Mock
    QuizDao quizDao;

    @Mock
    AlternativeDao alternativeDao;
    @Mock
    AnswerDao answerDao;

    @Mock
    QuestionDao questionDao;

    @InjectMocks
    QuizService service = new QuizServiceImpl();

    @Test
    public void findAllByCourse_id() throws Exception {
        Long courseId = 1L;
        List<Quiz> quizList = new ArrayList<>(Arrays.asList(new Quiz(), new Quiz()));

        when(quizDao.findAllByCourse_id(courseId)).thenReturn(quizList);
        assertThat(service.findAllByCourse_id(courseId), is(quizList));
        verify(quizDao).findAllByCourse_id(courseId);
    }

    @Test
    public void findOne() throws Exception {
        when(quizDao.findOne(1L)).thenReturn(new Quiz());
        assertThat(service.findOne(1L), instanceOf(Quiz.class));
        verify(quizDao).findOne(1L);
    }

    @Test
    public void save() throws Exception {
        final Quiz quiz = new Quiz();
        service.save(quiz);
        verify(quizDao).save(quiz);
    }

    @Test
    public void delete() throws Exception {
        List<Question> questionList = new ArrayList<>(Arrays.asList(question));
        when(quiz.getId()).thenReturn(1L);
        when(questionDao.findAllByQuiz_Id(1L)).thenReturn(questionList);
        when(question.getId()).thenReturn(1L);
        service.delete(quiz);


    }

}
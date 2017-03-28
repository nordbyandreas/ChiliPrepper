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

import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by dagki on 15/03/2017.
 */

@RunWith(MockitoJUnitRunner.class)
public class AnswerServiceTest {
    @Mock
    private AnswerDao dao;

    @InjectMocks
    private AnswerService service = new AnswerServiceImpl();

    @Test
    public void findAllByQuestion_Id() throws Exception {

    }

    @Test
    public void findAllByQuiz_Id() throws Exception {

    }

    @Test
    public void findAllByCourse_Id() throws Exception {

    }

    @Test
    public void findOneByQuestion_Id() throws Exception {
        when(dao.findOneByQuestion_Id(1L)).thenReturn(new Answer());
        assertThat(service.findOneByQuestion_Id(1L), instanceOf(Answer.class));
        verify(dao).findOneByQuestion_Id(1L);
    }

    @Test
    public void save() throws Exception {
        final Answer answer = new Answer();
        service.save(answer);
        verify(dao).save(answer);
    }

}
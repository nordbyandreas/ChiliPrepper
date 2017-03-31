package com.ChiliPrepper.ChiliPrepper.service;

import com.ChiliPrepper.ChiliPrepper.dao.CreatorQuizMailDao;
import com.ChiliPrepper.ChiliPrepper.model.CreatorQuizMail;
import com.ChiliPrepper.ChiliPrepper.model.Quiz;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by dagki on 28/03/2017.
 */

@RunWith(MockitoJUnitRunner.class)
public class CreatorQuizMailServiceImplTest {

    //Variable List
    Long quizId;
    CreatorQuizMail creatorQuizMail;

    @Mock
    CreatorQuizMailDao creatorQuizMailDao;

    @InjectMocks
    CreatorQuizMailService creatorQuizMailService = new CreatorQuizMailServiceImpl();

    @Before
    public void setUp() {
        quizId = 1L;
        creatorQuizMail = new CreatorQuizMail();
    }

    @Test
    public void findOneByQuiz_Id() throws Exception {
        Long quizId = 1L;

        when(creatorQuizMailDao.findOneByQuiz_Id(quizId)).thenReturn(creatorQuizMail);
        assertThat("findAllByQuestion_Id should return an Iterable object containing the two Alternative objects within alternativeList", creatorQuizMailService.findOneByQuiz_Id(quizId), is(creatorQuizMail));
        verify(creatorQuizMailDao).findOneByQuiz_Id(quizId);

    }

    @Test
    public void save() throws Exception {
        creatorQuizMailService.save(new CreatorQuizMail());
        verify(creatorQuizMailDao).save(any(CreatorQuizMail.class));
    }

}
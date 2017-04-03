package com.ChiliPrepper.ChiliPrepper.service;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import com.ChiliPrepper.ChiliPrepper.model.CreatorQuizMail;
import com.ChiliPrepper.ChiliPrepper.dao.CreatorQuizMailDao;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;

/**
 * Created by dagki on 28/03/2017.
 */

@RunWith(MockitoJUnitRunner.class)
public class CreatorQuizMailServiceImplTest {
    private Long quizId = 1L;
    private CreatorQuizMail creatorQuizMail = new CreatorQuizMail();

    @Mock
    private CreatorQuizMailDao creatorQuizMailDao;

    @InjectMocks
    private CreatorQuizMailService creatorQuizMailService = new CreatorQuizMailServiceImpl();

    /**Confirms that creatorQuizMailService.findOneByQuiz_Id(creatorQuizMail) returns creatorQuizMailDao.findOneByQuiz_Id(creatorQuizMail)*/
    @Test
    public void findOneByQuiz_Id() throws Exception {
        when(creatorQuizMailDao.findOneByQuiz_Id(quizId)).thenReturn(creatorQuizMail);
        assertThat(creatorQuizMailService.findOneByQuiz_Id(quizId), is(creatorQuizMail));
        verify(creatorQuizMailDao).findOneByQuiz_Id(quizId);
    }

    /**Confirms that creatorQuizMailService.save(creatorQuizMail) calls creatorQuizMailDao.save(creatorQuizMail)*/
    @Test
    public void save() throws Exception {
        creatorQuizMailService.save(creatorQuizMail);
        verify(creatorQuizMailDao).save(creatorQuizMail);
    }

}
package com.ChiliPrepper.ChiliPrepper.service;

import org.junit.Test;
import java.util.List;
import org.mockito.Mock;
import java.util.Arrays;
import org.mockito.InjectMocks;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import com.ChiliPrepper.ChiliPrepper.model.Alternative;
import com.ChiliPrepper.ChiliPrepper.dao.AlternativeDao;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

/**
 * Created by Dag Kirstihagen on 29/03/2017.
 */

@RunWith(MockitoJUnitRunner.class)
public class AlternativeServiceImplTest {
    private Alternative alternative = new Alternative();
    private Long alternativeId, questionId = alternativeId = 1L;
    private List<Alternative> alternativeList = Arrays.asList(alternative);

    @Mock
    private AlternativeDao alternativeDao;

    @InjectMocks
    private AlternativeService alternativeService = new AlternativeServiceImpl();

    /**Confirms that alternativeService.findOne(alternativeId) returns alternativeDao.findOne(alternativeId)*/
    @Test
    public void findOne() throws Exception {
        when(alternativeDao.findOne(alternativeId)).thenReturn(alternative);
        assertThat(alternativeService.findOne(alternativeId), is(alternative));
        verify(alternativeDao).findOne(alternativeId);
    }

    /**Confirms that alternativeService.findAllByQuestion_Id(questionId) returns alternativeDao.findAllByQuestion_Id(questionId)*/
    @Test
    public void findAllByQuestion_Id() throws Exception {
        when(alternativeDao.findAllByQuestion_Id(questionId)).thenReturn(alternativeList);
        assertThat(alternativeService.findAllByQuestion_Id(questionId), is(alternativeList));
        verify(alternativeDao).findAllByQuestion_Id(questionId);
    }

    /**Confirms that alternativeService.save(alternative) calls alternativeDao.save(alternative)*/
    @Test
    public void save() throws Exception {
        alternativeService.save(alternative);
        verify(alternativeDao).save(alternative);
    }

    /**Confirms that alternativeService.deleteAllByQuestion_Id(questionId) calls alternativeDao.deleteAllByQuestion_Id(questionId)*/
    @Test
    public void deleteAllByQuestion_Id() throws Exception {
        alternativeService.deleteAllByQuestion_Id(questionId);
        verify(alternativeDao).deleteAllByQuestion_Id(questionId);
    }

}
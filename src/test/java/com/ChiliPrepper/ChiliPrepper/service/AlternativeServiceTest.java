package com.ChiliPrepper.ChiliPrepper.service;

import com.ChiliPrepper.ChiliPrepper.dao.AlternativeDao;
import com.ChiliPrepper.ChiliPrepper.model.Alternative;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by dagki on 15/03/2017.
 */

@RunWith(MockitoJUnitRunner.class)
public class AlternativeServiceTest {

    //Variables
    Long questionId = 1L;
    Long alternativeId = 1L;
    List<Alternative> alternativeList = Arrays.asList(new Alternative(), new Alternative());

    @Mock
    private AlternativeDao dao;

    @InjectMocks
    private AlternativeService service = new AlternativeServiceImpl();

    @Test
    public void findAllByQuestion_Id() throws Exception {
        when(dao.findAllByQuestion_Id(questionId)).thenReturn(alternativeList);
        assertTrue("findAllByQuestion_Id should return an Iterable object containing the two Alternative objects within alternativeList", service.findAllByQuestion_Id(questionId).equals(alternativeList));
        verify(dao).findAllByQuestion_Id(questionId);
    }

    @Test
    public void findOne() throws Exception {
        when(dao.findOne(alternativeId)).thenReturn(new Alternative());
        assertThat("findOne with an alternativeId associated to a course should return the Course object", service.findOne(alternativeId), instanceOf(Alternative.class));
        verify(dao).findOne(alternativeId);
    }

    @Test
    public void save_ShouldSaveOneAlternative() throws Exception {
        service.save(new Alternative());
        verify(dao).save(any(Alternative.class));
    }

    @Test
    public void deleteAllByQuestion_Id() throws Exception {
       service.deleteAllByQuestion_Id(1L);
       verify(dao).deleteAllByQuestion_Id(1L);
    }

}
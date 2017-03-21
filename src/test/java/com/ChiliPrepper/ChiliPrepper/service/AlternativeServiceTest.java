package com.ChiliPrepper.ChiliPrepper.service;

import com.ChiliPrepper.ChiliPrepper.dao.AlternativeDao;
import com.ChiliPrepper.ChiliPrepper.dao.CourseDao;
import com.ChiliPrepper.ChiliPrepper.model.Alternative;
import com.ChiliPrepper.ChiliPrepper.model.Course;
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
public class AlternativeServiceTest {
    @Mock
    private AlternativeDao dao;

    @InjectMocks
    private AlternativeService service = new AlternativeServiceImpl();

    @Test
    public void findAllByQuestion_Id() throws Exception {

    }

    @Test
    public void findOne() throws Exception {
        when(dao.findOne(1L)).thenReturn(new Alternative());
        assertThat(service.findOne(1L), instanceOf(Alternative.class));
        verify(dao).findOne(1L);
    }

    @Test
    public void save() throws Exception {
        final Alternative alternative = new Alternative();
        service.save(alternative);
        verify(dao).save(alternative);
    }

}
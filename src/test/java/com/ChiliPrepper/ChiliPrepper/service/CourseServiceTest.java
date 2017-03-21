package com.ChiliPrepper.ChiliPrepper.service;

import com.ChiliPrepper.ChiliPrepper.dao.CourseDao;
import com.ChiliPrepper.ChiliPrepper.model.Course;
import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;



import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import org.mockito.Mockito.*;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.codehaus.groovy.runtime.DefaultGroovyMethods.any;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.Matchers.isA;
import static org.mockito.Matchers.matches;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by dagki on 09/03/2017.
 */

@RunWith(MockitoJUnitRunner.class)
public class CourseServiceTest {
    @Mock
    private CourseDao dao;

    @InjectMocks
    private CourseService service = new CourseServiceImpl();

    @Test
    public void findAllShouldReturnTwo() throws Exception {
        List<Course> courses = Arrays.asList(
                new Course(),
                new Course()
        );
        Iterable<Course> courseIterable = courses;
        when(dao.findAll()).thenReturn(courses);
        assertThat(service.findAll(), is(courseIterable));
        verify(dao).findAll();
    }

    @Test
    public void findOne() throws Exception {
        when(dao.findOne(1L)).thenReturn(new Course());
        assertThat(service.findOne(1L), instanceOf(Course.class));
        verify(dao).findOne(1L);
    }

    @Test
    public void save() throws Exception {
        final Course course = new Course();
        service.save(course);
        verify(dao).save(course);
    }

    @Test
    public void findByAccessCode() throws Exception {
        when(dao.findByAccessCode("TDT4140")).thenReturn(new Course());
        assertThat(service.findByAccessCode("TDT4140"), instanceOf(Course.class));
        verify(dao).findByAccessCode("TDT4140");
    }

}
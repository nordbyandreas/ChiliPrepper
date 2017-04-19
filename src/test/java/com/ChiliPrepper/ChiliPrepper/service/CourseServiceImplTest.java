package com.ChiliPrepper.ChiliPrepper.service;

import org.junit.Test;
import java.util.List;
import java.util.Arrays;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import com.ChiliPrepper.ChiliPrepper.model.Course;
import com.ChiliPrepper.ChiliPrepper.dao.CourseDao;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;

/**
 * Created by dagki on 09/03/2017.
 */

@RunWith(MockitoJUnitRunner.class)
public class CourseServiceImplTest {
    private Long courseId = 1L;
    private Course course = new Course();
    private String accessCode = "accessCode";
    private List<Course> courseList = Arrays.asList(course);

    @Mock
    private CourseDao courseDao;

    @InjectMocks
    private CourseService courseService = new CourseServiceImpl();

    /**Confirms that courseService.findOne(courseId) returns courseDao.findOne(courseId)*/
    @Test
    public void findOne() throws Exception {
        when(courseDao.findOne(courseId)).thenReturn(course);
        assertThat(courseService.findOne(courseId), is(course));
        verify(courseDao).findOne(courseId);
    }

    /**Confirms that courseService.findByAccessCode(accessCode) returns courseDao.findByAccessCode(accessCode)*/
    @Test
    public void findByAccessCode() throws Exception {
        when(courseDao.findByAccessCode(accessCode)).thenReturn(course);
        assertThat(courseService.findByAccessCode(accessCode), is(course));
        verify(courseDao).findByAccessCode(accessCode);
    }

    /**Confirms that courseService.findAll() returns courseDao.findAll()*/
    @Test
    public void findAll() throws Exception {
        when(courseDao.findAll()).thenReturn(courseList);
        assertThat(courseService.findAll(), is(courseList));
        verify(courseDao).findAll();
    }

    /**Confirms that courseService.findAllForCreator() returns courseDao.findAllForCreator()*/
    @Test
    public void findAllForCreator() throws Exception {
        when(courseDao.findAllForCreator()).thenReturn(courseList);
        assertThat(courseService.findAllForCreator(), is(courseList));
        verify(courseDao).findAllForCreator();
    }

    /**Confirms that courseService.save(course) calls courseDao.save(course)*/
    @Test
    public void save() throws Exception {
        courseService.save(course);
        verify(courseDao).save(course);
    }

}
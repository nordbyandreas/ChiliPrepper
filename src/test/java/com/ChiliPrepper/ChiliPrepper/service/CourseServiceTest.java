package com.ChiliPrepper.ChiliPrepper.service;

import org.junit.Test;
import java.util.List;
import java.util.Arrays;
import org.mockito.Mock;
import org.mockito.Matchers;
import org.mockito.InjectMocks;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import com.ChiliPrepper.ChiliPrepper.model.Course;
import com.ChiliPrepper.ChiliPrepper.dao.CourseDao;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.hamcrest.Matchers.instanceOf;

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
    public void findAll_ShouldReturnCourseDaoFindAll() throws Exception {
        List<Course> courseList = Arrays.asList(new Course(), new Course());

        when(dao.findAll()).thenReturn(courseList);
        assertTrue("findAll should return an Iterable<Course> object containing the two Course objects within roleList", service.findAll().equals(courseList));
        verify(dao).findAll();
    }

    @Test
    public void findOne_ShouldReturnOne() throws Exception {
        Long courseId = 1L;

        when(dao.findOne(courseId)).thenReturn(new Course());
        assertThat("findOne with an courseId associated to a course should return the Course object", service.findOne(courseId), instanceOf(Course.class));
        verify(dao).findOne(courseId);
    }

    @Test
    public void save_ShouldSaveOneCourse() throws Exception {
        service.save(new Course());
        verify(dao).save(Matchers.any(Course.class));
    }

    @Test
    public void findByAccessCode_ShouldReturnOneCourse() throws Exception {
        String accessCode = "accessCode";

        when(dao.findByAccessCode(accessCode)).thenReturn(new Course());
        assertThat("findByAccessCode with an accessCode associated to a course should return the Course object", service.findByAccessCode(accessCode), instanceOf(Course.class));
        verify(dao).findByAccessCode(accessCode);
    }

}
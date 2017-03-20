package com.ChiliPrepper.ChiliPrepper.service;

import com.ChiliPrepper.ChiliPrepper.dao.CourseDao;
import com.ChiliPrepper.ChiliPrepper.model.Course;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.lang.Object.*;


import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import org.mockito.Mockito.*;
import static org.junit.Assert.*;
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
        Iterable<Course> courses = Arrays.asList(
                new Course(),
                new Course()
        );
    }

    @Test
    public void findOne() throws Exception {

    }

    @Test
    public void save() throws Exception {

    }

    @Test
    public void findByAccessCode() throws Exception {

    }

}
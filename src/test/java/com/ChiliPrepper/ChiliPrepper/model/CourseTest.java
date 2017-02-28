package com.ChiliPrepper.ChiliPrepper.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;


/**
 * Created by dagki on 24/02/2017.
 */
public class CourseTest {

    User user = new User();
    Course course = new Course();

    @Test
    public void getRegUsers() throws Exception {

    }

    @Test
    public void setRegUsers() throws Exception {

    }

    @Test
    public void getSetId() throws Exception {
        assertNull("Id should be null before setting the value", user.getId());
        Long userId = 1337L;
        user.setId(userId);
        assertEquals("Id should be set to the userId Long value", userId, user.getId());
    }

    @Test
    public void setId() throws Exception {

    }

    @Test
    public void getCourseName() throws Exception {
        assertNull("Course name should be null before name is defined", course.getCourseName());
    }

    @Test
    public void setCourseName() throws Exception {

    }

    @Test
    public void getAccessCode() throws Exception {

    }

    @Test
    public void setAccessCode() throws Exception {

    }

    @Test
    public void getCreator() throws Exception {

    }

    @Test
    public void setCreator() throws Exception {

    }


}
package com.ChiliPrepper.ChiliPrepper.model;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Created by dagki on 02/03/2017.
 */
public class CourseTest {
    private User userOne;
    private User userTwo;
    private Course course;

    @Before
    public void setUp() throws Exception {
        userOne = new User();
        userTwo = new User();
        course = new Course();
    }

    @Test
    public void courseId() throws Exception {
        Long courseId = 10L;
        assertNull("Course ID should be null before a value is assigned", course.getId());
        course.setId(courseId);
        assertEquals("Course ID should be set to the assigned value", courseId, course.getId());
    }

    @Test
    public void courseName() throws Exception {
        String courseName = "TDT4140";
        assertNull("Course name should be null before a value is assigned", course.getCourseName());
        course.setCourseName(courseName);
        assertEquals("Course name should be set to the assigned value", courseName, course.getCourseName());
    }

    @Test
    public void courseAccessCode() throws Exception {
        String accessCode = "AccessCode";
        assertNull("Access code should be null before a value is assigned", course.getAccessCode());
        course.setAccessCode(accessCode);
        assertEquals("Access code should be set to the assigned value", accessCode, course.getAccessCode());
    }

    @Test
    public void courseCreator() throws Exception {
        assertNull("Course creator should be null before a value is assigned", course.getCreator());
        course.setCreator(userOne);
        assertEquals("Course creator should be set to the assigned value", userOne, course.getCreator());
    }

    @Test
    public void courseRegisteredUsers() throws Exception {
        Set<User> regUsers = new HashSet<>(Arrays.asList(userOne, userTwo));
        assertNull("Registered course users should be null before a value is assigned", course.getRegUsers());
        course.setRegUsers(regUsers);
        assertEquals("Registered course users should be set to the assigned value", regUsers, course.getRegUsers());
    }

}
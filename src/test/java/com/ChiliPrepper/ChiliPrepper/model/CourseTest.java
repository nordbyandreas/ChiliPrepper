package com.ChiliPrepper.ChiliPrepper.model;

import java.util.Set;
import org.junit.Test;
import org.junit.Before;
import java.util.Arrays;
import java.util.HashSet;
import static org.junit.Assert.*;

/**
 * Created by dagki on 02/03/2017.
 */

public class CourseTest {
    private User user;
    private Course course;

    @Before
    public void setUp() throws Exception {
        user = new User();
        course = new Course();
    }

    /**First confirms that the course's participants ain't assigned,
     * then assigns the participants
     * and concludes by confirming that the participants is assigned to the course.*/
    @Test
    public void getAndSetRegUsers() throws Exception {
        Set<User> regUsers = new HashSet<>(Arrays.asList(user));
        assertNull("The course's registered users ain't assigned, and should return: null", course.getRegUsers());
        course.setRegUsers(regUsers);
        assertEquals("The course's registered users is assigned, and should return: [user] (Set<User>)", regUsers, course.getRegUsers());
    }

    /**First confirms that the course's ID ain't assigned,
     * then assigns the ID
     * and concludes by confirming that the ID is assigned to the course.*/
    @Test
    public void getAndSetId() throws Exception {
        Long id = 1L;
        assertNull("The course's ID ain't assigned, and should return: null", course.getId());
        course.setId(id);
        assertEquals("The course's ID is assigned, and should return: 1L (Long)", id, course.getId());
    }

    /**First confirms that the course's name ain't assigned,
     * then assigns the name
     * and concludes by confirming that the name is assigned to the course.*/
    @Test
    public void getAndSetCourseName() throws Exception {
        String courseName = "courseName";
        assertNull("The course's name ain't assigned, and should return: null", course.getCourseName());
        course.setCourseName(courseName);
        assertEquals("The course's name is assigned, and should return: 'courseName' (String)", courseName, course.getCourseName());
    }

    /**First confirms that the course's access code ain't assigned,
     * then assigns the access code
     * and concludes by confirming that the access code is assigned to the course.*/
    @Test
    public void getAndSetAccessCode() throws Exception {
        String accessCode = "accessCode";
        assertNull("The course's access code ain't assigned, and should return: null", course.getAccessCode());
        course.setAccessCode(accessCode);
        assertEquals("The course's access code is assigned, and should return: 'accessCode' (String)", accessCode, course.getAccessCode());
    }

    /**First confirms that the course's creator ain't assigned,
     * then assigns the creator
     * and concludes by confirming that the creator is assigned to the course.*/
    @Test
    public void getAndSetCreator() throws Exception {
        assertNull("The course ain't assigned to a creator, and should return: null", course.getCreator());
        course.setCreator(user);
        assertEquals("The course is assigned to a creator, and should return: user (User)", user, course.getCreator());
    }




}
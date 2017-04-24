package com.ChiliPrepper.ChiliPrepper.model;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

/**
 * Created by Dag Kirstihagen on 24/04/2017.
 */

public class CourseUserTest {
    private CourseUser courseUser;

    @Before
    public void setUp() throws Exception {
        courseUser = new CourseUser();
    }

    /**First confirms that the course user's ID ain't assigned,
     * then assigns the course user's ID
     * and concludes by confirming that the ID is assigned to the course user.*/
    @Test
    public void getAndSetId() throws Exception {
        Long id = 1L;
        assertNull("The course user's id ain't assigned, and should return: null", courseUser.getId());
        courseUser.setId(id);
        assertEquals("The course user's id is assigned, and should return: 1L (Long)", id, courseUser.getId());
    }

    /**First confirms that the course user's ID ain't assigned,
     * then assigns the course user's user
     * and concludes by confirming that the ID is assigned to the course user.*/
    @Test
    public void getAndSetUser() throws Exception {
        User user = new User();
        assertNull("The course user's user ain't assigned, and should return: null", courseUser.getUser());
        courseUser.setUser(user);
        assertEquals("The course user's user is assigned, and should return: 1L (Long)", user, courseUser.getUser());
    }

    /**First confirms that the course user's course ain't assigned,
     * then assigns the course user's course
     * and concludes by confirming that the course is assigned to the course user.*/
    @Test
    public void getAndSetCourse() throws Exception {
        Course course = new Course();
        assertNull("The user's id ain't assigned, and should return: null", courseUser.getCourse());
        courseUser.setCourse(course);
        assertEquals("The course user's id is assigned, and should return: 1L (Long)", course, courseUser.getCourse());
    }

}
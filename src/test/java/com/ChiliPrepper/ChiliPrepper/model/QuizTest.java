package com.ChiliPrepper.ChiliPrepper.model;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

/**
 * Created by Dag Kirstihagen on 28/02/2017.
 */

public class QuizTest {
    private Quiz quiz;
    private Course course;

    @Before
    public void setUp() throws Exception {
        quiz = new Quiz();
        course = new Course();
    }

    /**First confirms that the quiz's ID ain't assigned,
     * then assigns the ID
     * and concludes by confirming that the ID is assigned to the quiz.*/
    @Test
    public void getAndSetId() throws Exception {
        Long id = 1L;
        assertNull("The quiz's ID ain't assigned, and should return: null", quiz.getId());
        quiz.setId(id);
        assertEquals("The quiz's ID is assigned, and should return: 1L (Long)", id, quiz.getId());
    }

    /**First confirms that the quiz ain't published,
     * then sets it to published
     * and concludes by confirming that quiz is published.*/
    @Test
    public void isAndSetPublished() throws Exception {
        assertFalse("The quiz ain't published, and should return: false (Boolean)", quiz.isPublished());
        quiz.setPublished(true);
        assertTrue("The quiz is published, and should return: true (Boolean)", quiz.isPublished());
    }

    /**First confirms that the quiz's name ain't assigned,
     * then assigns the name
     * and concludes by confirming that the name is assigned to the quiz.*/
    @Test
    public void getAndSetQuizName() throws Exception {
        String quizName = "quizName";
        assertNull("The quiz's name ain't assigned, and should return: null", quiz.getQuizName());
        quiz.setQuizName(quizName);
        assertEquals("The quiz's name is assigned, and should return: quizName (String)", quizName, quiz.getQuizName());
    }

    /**First confirms that the quiz ain't assigned to a course,
     * then assigns it to a course
     * and concludes by confirming that the quiz is assigned to the course.*/
    @Test
    public void getAndSetCourse() throws Exception {
        assertNull("The quiz ain't assigned to a course, and should return: null", quiz.getCourse());
        quiz.setCourse(course);
        assertEquals("The quiz is assigned to a course, and should return: course (Course)", course, quiz.getCourse());
    }

}
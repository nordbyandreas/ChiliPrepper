package com.ChiliPrepper.ChiliPrepper.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by dagki on 28/02/2017.
 */
public class QuizTest {
    private Quiz quiz;
    private Course course;

    @Before
    public void setUp() throws Exception {
        quiz = new Quiz();
        course = new Course();
    }

    @Test
    public void quizName() throws Exception {
        String quizName = "Unit testing";
        assertNull("The quiz name should be null before a value is assigned", quiz.getQuizName());
        quiz.setQuizName(quizName);
        assertEquals("The quiz name should be set to the assigned value", quizName, quiz.getQuizName());
    }

    @Test
    public void courseId() throws Exception {
        Long quizId = 10L;
        assertNull("The quiz's ID should be null before a value is assigned", quiz.getId());
        quiz.setId(quizId);
        assertEquals("The quiz's ID should be set to the assigned value", quizId, quiz.getId());
    }

    @Test
    public void quizAssignedToCourse() throws Exception {
        assertNull("The quiz should not be assigned to a course", quiz.getCourse());
        quiz.setCourse(course);
        assertEquals("The quiz should be assigned to the course", course, quiz.getCourse());
    }

    @Test
    public void coursePublishedStatus() throws Exception {
        assertFalse("The quiz should not be published as default", quiz.isPublished());
        quiz.setPublished(true);
        assertEquals("The quiz should be published", true, quiz.isPublished());
    }

}
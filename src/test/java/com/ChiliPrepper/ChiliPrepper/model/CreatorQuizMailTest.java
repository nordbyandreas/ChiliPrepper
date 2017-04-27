package com.ChiliPrepper.ChiliPrepper.model;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

/**
 * Created by Dag Kirstihagen on 28/03/2017.
 */

public class CreatorQuizMailTest {
    private Quiz quiz;
    private User creator;
    private Course course;
    private CreatorQuizMail creatorQuizMail;

    @Before
    public void setUp() throws Exception {
        quiz = new Quiz();
        creator = new User();
        course = new Course();
        creatorQuizMail = new CreatorQuizMail();
    }

    /**First confirms that the creator's quiz mail ID ain't assigned,
     * then assigns the ID
     * and concludes by confirming that the ID is assigned to the creator's quiz mail.*/
    @Test
    public void getAndSetId() throws Exception {
        Long id = 1L;
        assertNull("The creator's quiz mail ID ain't assigned, and should return: null", creatorQuizMail.getId());
        creatorQuizMail.setId(id);
        assertEquals("The creator's quiz mail is assigned, and should return: 1L (Long)", id, creatorQuizMail.getId());
    }

    /**First confirms that the creator's quiz mail ain't assigned to a user,
     * then assigns it to a user
     * and concludes by confirming that the creator's quiz mail is assigned to the user.*/
    @Test
    public void getAndSetCreator() throws Exception {
        assertNull("The creator's quiz mail ain't assigned to a creator, and should return: null", creatorQuizMail.getCreator());
        creatorQuizMail.setCreator(creator);
        assertEquals("The creator's quiz mail is assigned to a creator, and should return: creator (User)", creator, creatorQuizMail.getCreator());
    }

    /**First confirms that the creator's quiz mail ain't assigned to a course,
     * then assigns it to a course
     * and concludes by confirming that the creator's quiz mail is assigned to the course.*/
    @Test
    public void getAndSetCourse() throws Exception {
        assertNull("The creator's quiz mail ain't assigned to a course, and should return: null", creatorQuizMail.getCourse());
        creatorQuizMail.setCourse(course);
        assertEquals("The creator's quiz mail is assigned to a course, and should return: course (Course)", course, creatorQuizMail.getCourse());
    }

    /**First confirms that the creator's quiz mail ain't assigned to a quiz,
     * then assigns it to a quiz
     * and concludes by confirming that the creator's quiz mail is assigned to the quiz.*/
    @Test
    public void getAndSetQuiz() throws Exception {
        assertNull("The creator's quiz mail ain't assigned to a quiz, and should return: null", creatorQuizMail.getQuiz());
        creatorQuizMail.setQuiz(quiz);
        assertEquals("The creator's quiz mail is assigned to a quiz, and should return: quiz (Quiz)", quiz, creatorQuizMail.getQuiz());
    }

}
package com.ChiliPrepper.ChiliPrepper.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by dagki on 28/03/2017.
 */
public class CreatorQuizMailTest {

    private CreatorQuizMail creatorQuizMail = new CreatorQuizMail();

    @Test
    public void getId() throws Exception {
        Long creatorQuizMailId = 1L;
        assertNull("Question ID should be null before a value is assigned", creatorQuizMail.getId());
        creatorQuizMail.setId(creatorQuizMailId);
        assertEquals("Question ID should be set to the assigned value", creatorQuizMailId, creatorQuizMail.getId());
    }

    @Test
    public void getCreator() throws Exception {
        User creator = new User();
        assertNull("Question ID should be null before a value is assigned", creatorQuizMail.getCreator());
        creatorQuizMail.setCreator(creator);
        assertEquals("Question ID should be set to the assigned value", creator, creatorQuizMail.getCreator());
    }

    @Test
    public void setCreator() throws Exception {
        Course course = new Course();
        assertNull("Question ID should be null before a value is assigned", creatorQuizMail.getCourse());
        creatorQuizMail.setCourse(course);
        assertEquals("Question ID should be set to the assigned value", course, creatorQuizMail.getCourse());
    }

    @Test
    public void getCourse() throws Exception {
        Quiz quiz = new Quiz();
        assertNull("Question ID should be null before a value is assigned", creatorQuizMail.getQuiz());
        creatorQuizMail.setQuiz(quiz);
        assertEquals("Question ID should be set to the assigned value", quiz, creatorQuizMail.getQuiz());
    }

}
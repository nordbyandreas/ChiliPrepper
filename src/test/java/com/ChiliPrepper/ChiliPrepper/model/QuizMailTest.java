package com.ChiliPrepper.ChiliPrepper.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by dagki on 22/03/2017.
 */
public class QuizMailTest {
    private QuizMail quizMail;

    @Before
    public void setUp() throws Exception {
        quizMail = new QuizMail();
    }

    @Test
    public void quizMail_id() throws Exception {
        Long quizMailid = 1L;
        assertNull("quizMail_id should be null before a value is assigned", quizMail.getId());
        quizMail.setId(quizMailid);
        assertEquals("quizMail_id should be set to the assigned value", quizMailid, quizMail.getId());
    }

    @Test
    public void getParticipant() throws Exception {
        User participant = new User();
        assertNull("quizMail_id ID should be null before a value is assigned", quizMail.getParticipant());
        quizMail.setParticipant(participant);
        assertEquals("quizMail_id should be set to the assigned value", participant, quizMail.getParticipant());
    }

    @Test
    public void getCourse() throws Exception {
        Course course = new Course();
        assertNull("quizMail_id ID should be null before a value is assigned", quizMail.getCourse());
        quizMail.setCourse(course);
        assertEquals("quizMail_id should be set to the assigned value", course, quizMail.getCourse());
    }

    @Test
    public void getQuiz() throws Exception {
        Quiz quiz = new Quiz();
        assertNull("quizMail_id ID should be null before a value is assigned", quizMail.getQuiz());
        quizMail.setQuiz(quiz);
        assertEquals("quizMail_id should be set to the assigned value", quiz, quizMail.getQuiz());
    }

    @Test
    public void isParticipantMailSent() throws Exception {
        assertFalse("quizMail_id ID should be null before a value is assigned", quizMail.isParticipantMailSent());
        quizMail.setParticipantMailSent(true);
        assertTrue("quizMail_id should be set to the assigned value", quizMail.isParticipantMailSent());
    }

    @Test
    public void isCreatorMailSent() throws Exception {
        assertFalse("quizMail_id ID should be null before a value is assigned", quizMail.isCreatorMailSent());
        quizMail.setCreatorMailSent(true);
        assertTrue("quizMail_id should be set to the assigned value", quizMail.isCreatorMailSent());
    }

}
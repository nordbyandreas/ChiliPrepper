package com.ChiliPrepper.ChiliPrepper.model;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

/**
 * Created by dagki on 22/03/2017.
 */

public class QuizMailTest {
    private Quiz quiz;
    private Course course;
    private User participant;
    private QuizMail quizMail;

    @Before
    public void setUp() throws Exception {
        quiz = new Quiz();
        course = new Course();
        participant = new User();
        quizMail = new QuizMail();
    }

    /**First confirms that the quiz mail's ID ain't assigned,
     * then assigns the ID
     * and concludes by confirming that the ID is assigned to the quiz mail.*/
    @Test
    public void getAndSetId() throws Exception {
        Long id = 1L;
        assertNull("The quiz mail's ID ain't assigned, and should return: null", quizMail.getId());
        quizMail.setId(id);
        assertEquals("The quiz mail's ID is assigned, and should return: 1L (Long)", id, quizMail.getId());
    }

    /**First confirms that the quiz mail's participant ain't assigned,
     * then assigns the participant
     * and concludes by confirming that the participant is assigned to the quiz mail.*/
    @Test
    public void getAndSetParticipant() throws Exception {
        assertNull("The quiz mail's ain't assigned to a participant, and should return: null", quizMail.getParticipant());
        quizMail.setParticipant(participant);
        assertEquals("The quiz mail is assigned to a participant, and should return: participant (User)", participant, quizMail.getParticipant());
    }

    /**First confirms that the quiz mail ain't assigned to a course,
     * then assigns it to a course
     * and concludes by confirming that the quiz mail is assigned to the course.*/
    @Test
    public void getAndSetCourse() throws Exception {
        assertNull("The quiz mail ain't assigned to a course, and should return: null", quizMail.getCourse());
        quizMail.setCourse(course);
        assertEquals("The quiz mail is assigned to a course, and should return: course (Course)", course, quizMail.getCourse());
    }

    /**First confirms that the quizMail ain't assigned to a quiz,
     * then assigns it to a quiz
     * and concludes by confirming that the quizMail is assigned to the quiz.*/
    @Test
    public void getAndSetQuiz() throws Exception {
        assertNull("The quiz mail ain't assigned to a quiz, and should return: null", quizMail.getQuiz());
        quizMail.setQuiz(quiz);
        assertEquals("The quiz mail is assigned to a quiz, and should return: quiz (Quiz)", quiz, quizMail.getQuiz());
    }

    /**First confirms that the quiz mail ain't sent to the participant,
     * then sets it to sent
     * and concludes by confirming that the quiz mail is sent to the participant.*/
    @Test
    public void isParticipantMailSent() throws Exception {
        assertFalse("The mail ain't sent to the participant, and should return: false (Boolean)", quizMail.isParticipantMailSent());
        quizMail.setParticipantMailSent(true);
        assertTrue("The mail is sent to the participant, and should return: true (Boolean)", quizMail.isParticipantMailSent());
    }

    /**First confirms that the quiz mail ain't sent to the creator,
     * then sets it to sent
     * and concludes by confirming that the quiz mail is sent to the creator.*/
    @Test
    public void isCreatorMailSent() throws Exception {
        assertFalse("The mail ain't sent to the creator, and should return: false (Boolean)", quizMail.isCreatorMailSent());
        quizMail.setCreatorMailSent(true);
        assertTrue("The mail is sent to the creator, and should return: true (Boolean)", quizMail.isCreatorMailSent());
    }

}
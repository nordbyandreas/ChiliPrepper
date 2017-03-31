package com.ChiliPrepper.ChiliPrepper.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by dagki on 09/03/2017.
 */
public class AnswerTest {
    private User user;
    private Quiz quiz;
    private Answer answer;
    private Course course;
    private Question question;

    @Before
    public void setUp() throws Exception {
        user = new User();
        quiz = new Quiz();
        answer = new Answer();
        course = new Course();
        question = new Question();
    }

    @Test
    public void assignAnswerId() throws Exception {
        Long answerId = 10L;
        assertNull("The answer ID shouldn't be assigned", answer.getId());
        answer.setId(answerId);
        assertEquals("The answer ID should be set to the assigned value", answerId, answer.getId());
    }

    @Test
    public void checkIfAnswerIsCorrect() throws Exception {
        assertFalse("The answer should be wrong as default", answer.isCorrect());
        answer.setCorrect(true);
        assertTrue("The answer should be set to correct", answer.isCorrect());
    }

    @Test
    public void assignAnswerText() throws Exception {
        String answerText = "Unit testing is a waste of time";
        assertNull("The answer text shouldn't be assigned before set", answer.getAnswer());
        answer.setAnswer(answerText);
        assertEquals("The answer text should be set to the assigned value", answerText, answer.getAnswer());
    }

    @Test
    public void assignAnswerToQuestion() throws Exception {
        assertNull("The answer shouldn't be assigned to a question before set", answer.getQuestion());
        answer.setQuestion(question);
        assertEquals("The answer should be assigned to the selected question", question, answer.getQuestion());
    }

    @Test
    public void assignAnswerToQuiz() throws Exception {
        assertNull("The answer shouldn't be assigned to a quiz", answer.getQuiz());
        answer.setQuiz(quiz);
        assertEquals("The answer should be assigned to the selected quiz", quiz, answer.getQuiz());
    }

    @Test
    public void assignAnswerToCourse() throws Exception {
        assertNull("The answer shouldn't be assigned to a course before set", answer.getCourse());
        answer.setCourse(course);
        assertEquals("The answer should be assigned to the selected course", course, answer.getCourse());
    }

    @Test
    public void assignAnswerToUser() throws Exception {
        assertNull("The answer shouldn't be assigned to a course before set", answer.getUser());
        answer.setUser(user);
        assertEquals("The answer should be assigned to the selected course", user, answer.getUser());
    }

}
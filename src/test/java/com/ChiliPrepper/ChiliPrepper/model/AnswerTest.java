package com.ChiliPrepper.ChiliPrepper.model;

import org.junit.Test;
import org.junit.Before;
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

    /**First confirms that the answer's ID ain't assigned,
     * then assigns the ID
     * and concludes by confirming that the ID is assigned to the answer.*/
    @Test
    public void getAndSetId() throws Exception {
        Long id = 1L;
        assertNull("The answer's ID ain't assigned, and should return: null", answer.getId());
        answer.setId(id);
        assertEquals("The answer's ID is assigned, and should return: 1L (Long)", id, answer.getId());
    }

    /**First confirms that the answer is false,
     * then assigns it correct
     * and concludes by confirming that the answer is correct.*/
    @Test
    public void isAndSetCorrect() throws Exception {
        assertFalse("The answer ain't set to correct, and should return: false (Boolean)", answer.isCorrect());
        answer.setCorrect(true);
        assertTrue("The answer is set to correct, and should return: true (Boolean)", answer.isCorrect());
    }

    /**First confirms that the answer's text ain't assigned,
     * then assigns the text
     * and concludes by confirming that the text is assigned to the answer.*/
    @Test
    public void getAndSetTheAnswer() throws Exception {
        String answerText = "answerText";
        assertNull("The answer's text ain't assigned, and should return: null", answer.getAnswer());
        answer.setAnswer(answerText);
        assertEquals("The answer's text is assigned, and should return: answerText (String)", answerText, answer.getAnswer());
    }

    /**First confirms that the answer ain't assigned to a question,
     * then assigns it to a question
     * and concludes by confirming that the answer is assigned to the question.*/
    @Test
    public void getAndSetQuestion() throws Exception {
        assertNull("The answer ain't assigned to a question, and should return: null", answer.getQuestion());
        answer.setQuestion(question);
        assertEquals("The answer is assigned to a question, and should return: question (Question)", question, answer.getQuestion());
    }

    /**First confirms that the answer ain't assigned to a quiz,
     * then assigns it to a quiz
     * and concludes by confirming that the answer is assigned to the quiz.*/
    @Test
    public void getAndSetQuiz() throws Exception {
        assertNull("The answer ain't assigned to a quiz, and should return: null", answer.getQuiz());
        answer.setQuiz(quiz);
        assertEquals("The answer is assigned to a quiz, and should return: quiz (Quiz)", quiz, answer.getQuiz());
    }

    /**First confirms that the answer ain't assigned to a course,
     * then assigns it to a course
     * and concludes by confirming that the answer is assigned to the course.*/
    @Test
    public void getAndSetCourse() throws Exception {
        assertNull("The answer ain't assigned to a course, and should return: null", answer.getCourse());
        answer.setCourse(course);
        assertEquals("The answer is assigned to a course, and should return: course (Course)", course, answer.getCourse());
    }

    /**First confirms that the answer ain't assigned to a user,
     * then assigns it to a user
     * and concludes by confirming that the answer is assigned to the user.*/
    @Test
    public void getAndSetUser() throws Exception {
        assertNull("The answer ain't assigned to a user, and should return: null", answer.getUser());
        answer.setUser(user);
        assertEquals("The answer is assigned to a user, and should return: user (User)", user, answer.getUser());
    }

}
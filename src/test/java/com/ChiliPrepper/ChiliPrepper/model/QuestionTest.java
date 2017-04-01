package com.ChiliPrepper.ChiliPrepper.model;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

/**
 * Created by dagki on 23/02/2017.
 */

public class QuestionTest {
    private Quiz quiz;
    private Question question;

    @Before
    public void setUp() throws Exception {
        quiz = new Quiz();
        question = new Question();
    }

    /**First confirms that the quiz's ID ain't assigned,
     * then assigns the ID
     * and concludes by confirming that the ID is assigned to the quiz.*/
    @Test
    public void getAndSetId() throws Exception {
        Long id = 1L;
        assertNull("The question's ID ain't assigned, and should return: null", question.getId());
        question.setId(id);
        assertEquals("The question's ID is assigned, and should return: 1L (Long)", id, question.getId());
    }

    /**First confirms that the question's topic ain't assigned,
     * then assigns the topic
     * and concludes by confirming that the topic is assigned to the question.*/
    @Test
    public void getAndSetQuestionTopic() throws Exception {
        String topic = "topic";
        assertNull("The question's topic ain't assigned, and should return: null", question.getTopic());
        question.setTopic(topic);
        assertEquals("The question's topic is assigned, and should return: topic (String)", topic, question.getTopic());
    }

    /**First confirms that the question's text ain't assigned,
     * then assigns the text
     * and concludes by confirming that the text is assigned to the question.*/
    @Test
    public void getAndSetTheQuestion() throws Exception {
        String questionText = "questionText";
        assertNull("The question's text ain't assigned, and should return: null", question.getTheQuestion());
        question.setTheQuestion(questionText);
        assertEquals("The question's text is assigned, and should return: questionText (String)", questionText, question.getTheQuestion());
    }

    /**First confirms that the question's correct answer ain't assigned,
     * then assigns the correct answer
     * and concludes by confirming that the correct answer is assigned to the quiz.*/
    @Test
    public void getAndSetCorrectAnswer() throws Exception {
        String correctAnswer = "correctAnswer";
        assertNull("The question's correct answer ain't assigned, and should return: null", question.getCorrectAnswer());
        question.setCorrectAnswer(correctAnswer);
        assertEquals("The question's correct answer is assigned, and should return: correctAnswer (String)", correctAnswer, question.getCorrectAnswer());
    }

    /**First confirms that the question's ID ain't assigned to a quiz,
     * then sets it to a quiz
     * and concludes by confirming that the question is assigned to the quiz.*/
    @Test
    public void getAndSetQuiz() throws Exception {
        assertNull("The question ain't assigned to a quiz, and should return: null", question.getQuiz());
        question.setQuiz(quiz);
        assertEquals("The question is assigned to a quiz, and should return: quiz (Quiz)", quiz, question.getQuiz());
    }

}
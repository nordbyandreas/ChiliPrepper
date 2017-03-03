package com.ChiliPrepper.ChiliPrepper.model;

import org.junit.Before;
import org.junit.Test;
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

    @Test
    public void questionId() throws Exception {
        Long questionId = 10L;
        assertNull("Question ID should be null before a value is assigned", question.getId());
        question.setId(questionId);
        assertEquals("Question ID should be set to the assigned value", questionId, question.getId());
    }

    @Test
    public void questionTopic() throws Exception {
        String questionTopic = "Unit testing";
        assertNull("Question topic should be null before a value is assigned", question.getTopic());
        question.setTopic(questionTopic);
        assertEquals("Question topic should be set to the assigned value", questionTopic, question.getTopic());
    }

    @Test
    public void questionText() throws Exception {
        String questionText = "Why is unit testing important?";
        assertNull("The question's text should be null before a value is assigned", question.getTheQuestion());
        question.setTheQuestion(questionText);
        assertEquals("The question's text should be set to the assigned value", questionText, question.getTheQuestion());
    }

    @Test
    public void questionCorrectAnswer() throws Exception {
        String correctAnswer = "Assuring code quality";
        assertNull("The question's correct answer should be null before a value is assigned", question.getCorrectAnswer());
        question.setCorrectAnswer(correctAnswer);
        assertEquals("The question's correct answer should be set to the assigned value", correctAnswer, question.getCorrectAnswer());
    }

    @Test
    public void assignQuestionToQuiz() throws Exception {
        assertNull("The question shouldn't be assigned a quiz", question.getQuiz());
        question.setQuiz(quiz);
        assertEquals("The question's text should be set to the assigned value", quiz, question.getQuiz());
    }

}
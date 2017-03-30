package com.ChiliPrepper.ChiliPrepper.model;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by dagki on 23/02/2017.
 */

public class QuestionTest {

    private Question question = new Question();

    //Asserts that the question ID is null, then sets the question ID and asserts that the value is set.
    @Test
    public void questionId() throws Exception {
        Long questionId = 1L;
        assertNull("Question ID should be null", question.getId());
        question.setId(questionId);
        assertEquals("Question ID should be set to the assigned value", questionId, question.getId());
}

    //Asserts that the question topic is null, then sets the question topic value and asserts that the value is set.
    @Test
    public void getAndSetQuestionTopic() throws Exception {
        String questionTopic = "questionTopic";
        assertNull("The question's topic should be null", question.getTopic());
        question.setTopic(questionTopic);
        assertEquals("The question's topic should be set to the assigned value", questionTopic, question.getTopic());
    }

    //Asserts that the question topic is null, then sets the question topic value and asserts that the value is set.
    @Test
    public void questionText() throws Exception {
        String questionText = "questionText";
        assertNull("The question's text should be null", question.getTheQuestion());
        question.setTheQuestion(questionText);
        assertEquals("The question's text should be set to the assigned value", questionText, question.getTheQuestion());
    }

    //Asserts that the question topic is null, then sets the question topic value and asserts that the value is set.
    @Test
    public void questionCorrectAnswer() throws Exception {
        String correctAnswer = "correctAnswer";
        assertNull("The question's correct answer should be null before a value is assigned", question.getCorrectAnswer());
        question.setCorrectAnswer(correctAnswer);
        assertEquals("The question's correct answer should be set to the assigned value", correctAnswer, question.getCorrectAnswer());
    }

    //Asserts that the question topic is null, then sets the question topic value and asserts that the value is set.
    @Test
    public void assingQuestionToQuiz() throws Exception {
        Quiz quiz = new Quiz();
        assertNull("The question shouldn't be assigned a quiz", question.getQuiz());
        question.setQuiz(quiz);
        assertEquals("The question's text should be set to the assigned value", quiz, question.getQuiz());
    }

}
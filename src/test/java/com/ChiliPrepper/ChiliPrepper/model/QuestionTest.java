package com.ChiliPrepper.ChiliPrepper.model;

import org.junit.Before;
import org.junit.Test;

import javax.validation.constraints.Null;

import static org.junit.Assert.*;

/**
 * Created by dagki on 23/02/2017.
 */
public class QuestionTest {
    private Question question;

    @Before
    public void setUp() throws Exception {
        question = new Question();
    }

    @Test
    public void questionId() throws Exception {
        Long questionId = 10L;
        assertNull("Question ID should be null before a value is assigned", question.getId());
        question.setId(questionId);
        assertEquals("Question ID users should be set to the assigned value", questionId, question.getId());
    }
/*
    @Test
    public void setId() throws Exception {
        //It should not be possible to have identivcal IDs.
        //https://www.intertech.com/Blog/clarifying-spring-framework-ids-and-names/

        //Long q1InitID = question1.getId();
        //Long q2InitID = question2.getId();
        //question1.setId(q2InitID);
    }

    @Test
    public void getTopic() throws Exception {
        //Check initial q1 topic.
        String q1InitTopic = question1.getTopic();
        assertEquals(q1InitTopic, question1.getTopic());

        //Set new topic and check if q1 topic attribute has changed.
        String newQ1Topic = "Philology";
        question1.setTopic(newQ1Topic);
        assertNotEquals(q1InitTopic, question1.getTopic());
        assertEquals(newQ1Topic, question1.getTopic());
    }

    @Test
    public void setTopic() throws Exception {
        //It should not be possible to topic to empty string.
        question1.setTopic("");

    }

    @Test
    public void getQuestion() throws Exception {
        //Check init q1 question.
        String q1InitQuestion = question1.getTheQuestion();
        assertEquals(q1InitQuestion, question1.getTheQuestion());

        //Set new question and check if it has changed.
        String newQ1Question = "How are you?";
        question1.setTheQuestion(newQ1Question);
        assertNotEquals(q1InitQuestion, question1.getTheQuestion());
        assertEquals(newQ1Question, question1.getTheQuestion());
    }

    @Test
    public void setQuestion() throws Exception {
        //It should not be possible to set Question to an empty string.
    }

    @Test
    public void getCorrectAnswer() throws Exception {
        //Check init q1 correct answer.
        String q1InitCorrAns = question1.getCorrectAnswer();
        assertEquals(q1InitCorrAns, question1.getCorrectAnswer());

        //Set new correct answer and check if it has changed.
        String newQ1CorrAns = "Aristotle";
        question1.setCorrectAnswer(newQ1CorrAns);
        assertNotEquals(q1InitCorrAns, question1.getCorrectAnswer());
        assertEquals(newQ1CorrAns, question1.getCorrectAnswer());
    }

    @Test
    public void setCorrectAnswer() throws Exception {
        //It should not be possible to set Question to an empty string.
    }

    @Test
    public void getQuiz() throws Exception {
        //Check initial q1 quiz.
        Quiz q1InitQuiz = question1.getQuiz();
        assertEquals(q1InitQuiz, question1.getQuiz());

        //Set new quiz and check if q1 quiz attribute has changed.
        Quiz newQ1Quiz = new Quiz();
        question1.setQuiz(newQ1Quiz);
        assertNotEquals(q1InitQuiz, question1.getQuiz());
        assertEquals(newQ1Quiz, question1.getQuiz());
    }

    @Test
    public void setQuiz() throws Exception {

    }
*/
}
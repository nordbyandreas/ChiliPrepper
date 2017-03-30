package com.ChiliPrepper.ChiliPrepper.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Christer on 02.03.2017.
 */
public class AlternativeTest {
    private Alternative alternative;
    private Question question;

    @Before
    public void setUp() throws Exception {
        alternative = new Alternative();
        question = new Question();
    }

    @Test
    public void assignIdToAlternativeAnswer() throws Exception {
        Long alternativeId = 10L;
        assertNull("The alternative answer ID should be null before a value is assigned", alternative.getId());
        alternative.setId(alternativeId);
        assertEquals("The alternative answer ID should be set to the assigned value", alternativeId, alternative.getId());
    }

    @Test
    public void assignAlternativeAnswerText() throws Exception {
        String alternativeAnswerText = "Unit testing is a waste of time";
        assertNull("The alternative answer should be null before a value is assigned", alternative.getAlternative());
        alternative.setAlternative(alternativeAnswerText);
        assertEquals("The alternative answer should be set to the assigned value", alternativeAnswerText, alternative.getAlternative());
    }

    @Test
    public void assignAlternativeAnswerToQuestion() throws Exception {
        assertNull("The alternative answer shouldn't be assigned to a question", alternative.getQuestion());
        alternative.setQuestion(question);
        assertEquals("The alternative answer should be assigned to the question", question, alternative.getQuestion());
    }

}
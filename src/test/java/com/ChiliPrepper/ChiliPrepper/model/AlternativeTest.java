package com.ChiliPrepper.ChiliPrepper.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Christer on 02.03.2017.
 */
public class AlternativeTest {
    private Alternative alternative;

    @Before
    public void setUp() throws Exception {
        alternative = new Alternative();
    }

    @Test
    public void getSetId() throws Exception {
        //Check init alternative ID.
        Long alternativeInitId = alternative.getId();
        assertEquals(alternativeInitId, alternative.getId());

        //Set new ID and check if it has changed.
        Long newAlternativeId = 10000L;
        alternative.setId(newAlternativeId);
        assertNotEquals(alternativeInitId, alternative.getId());
        assertEquals(newAlternativeId, alternative.getId());
    }

    @Test
    public void setId() throws Exception {

    }

    @Test
    public void getAlternative() throws Exception {
        //Check init alternative alternative.
        String alternativeInitAlt = alternative.getAlternative();
        assertEquals(alternativeInitAlt, alternative.getAlternative());

        //Set new ID and check if it has changed.
        String newAlternativeAlt = "Anton Tsjekov";
        alternative.setAlternative(newAlternativeAlt);
        assertNotEquals(alternativeInitAlt, alternative.getAlternative());
        assertEquals(newAlternativeAlt, alternative.getAlternative());
    }

    @Test
    public void setAlternative() throws Exception {

    }

    @Test
    public void getQuestion() throws Exception {
        //Check init alternative question.
        Question alternativeInitQuestion = alternative.getQuestion();
        assertEquals(alternativeInitQuestion, alternative.getQuestion());

        //Set new question and check if it has changed.
        Question newAlternativeQuestion = new Question();
        alternative.setQuestion(newAlternativeQuestion);
        assertNotEquals(alternativeInitQuestion, alternative.getQuestion());
        assertEquals(newAlternativeQuestion, alternative.getQuestion());
    }

    @Test
    public void setQuestion() throws Exception {

    }

}
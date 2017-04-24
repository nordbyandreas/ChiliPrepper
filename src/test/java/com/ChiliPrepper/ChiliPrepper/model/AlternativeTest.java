package com.ChiliPrepper.ChiliPrepper.model;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

/**
 * Created by Dag Kirstihagen on 02.03.2017.
 */

public class AlternativeTest {
    private Question question;
    private Alternative alternative;

    @Before
    public void setUp() throws Exception {
        question = new Question();
        alternative = new Alternative();
    }

    /**First confirms that the alternative's ID ain't assigned,
     * then assigns the ID
     * and concludes by confirming that the ID is assigned to the alternative.*/
    @Test
    public void getAndSetId() throws Exception {
        Long id = 1L;
        assertNull("The alternative's ID ain't assigned, and should return: null", alternative.getId());
        alternative.setId(id);
        assertEquals("The alternative's ID is assigned, and should return: 1L (Long)", id, alternative.getId());
    }

    /** First confirms that the alternative's text ain't assigned,
     * then assigns the text
     * and concludes by confirming that the text is assigned to the alternative.*/
    @Test
    public void getAndSetTheAlternative() throws Exception {
        String altText = "altText";
        assertNull("The alternative's text ain't assigned, and should return: null", alternative.getAlternative());
        alternative.setAlternative(altText);
        assertEquals("The alternative' text is assigned, and should return: text (String)", altText, alternative.getAlternative());
    }

    /**First confirms that the alternative ain't assigned to a question,
     * then assigns it to a question
     * and concludes by confirming that the alternative is assigned to the question.*/
    @Test
    public void getAndSetQuestion() throws Exception {
        assertNull("The alternative ain't assigned to a question, and should return: null", alternative.getQuestion());
        alternative.setQuestion(question);
        assertEquals("The alternative is assigned to a question, and should return: question (Question)", question, alternative.getQuestion());
    }

}
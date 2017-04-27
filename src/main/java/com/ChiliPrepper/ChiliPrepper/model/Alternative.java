package com.ChiliPrepper.ChiliPrepper.model;

import javax.persistence.*;

/**
 * Created by Christer on 20.02.2017.
 *
 * Model for the Alternative object, containing fields, getters and setters
 *
 * Alternative-Objects relates to questions
 *
 * Hibernate takes care of the object relational mapping, so we can save and search for "objects" in the DB.
 *
 * the @Entity annotation informs hibernate that a schema should be created in the database
 *
 * Uses @Annotations to specify fields
 *
 */

@Entity
public class Alternative {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //automatically set unique ID
    @Column(name= "alternative_id")
    private Long id;

    @Column(name = "alternative")
    private String alternative;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;


    public Alternative() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAlternative() {
        return alternative;
    }

    public void setAlternative(String alternative) {
        this.alternative = alternative;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

}
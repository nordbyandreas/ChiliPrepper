package com.ChiliPrepper.ChiliPrepper.model;

import javax.persistence.*;




/**
 * Created by Andreas on 20.02.2017.
 *
 * Model for the Question object, containing fields, getters and setters
 *
 * Question-Objects relates to quizes
 *
 *
 * Hibernate takes care of the object relational mapping, so we can save and search for "objects" in the DB.
 *
 * the @Entity annotation informs hibernate that a schema should be created in the database
 *
 * Uses @Annotations to specify fields
 *
 *
 *
 *
 */
@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //automaticly set unique ID
    @Column(name= "question_id")
    private Long id;

    @Column(name = "topic")
    private String topic;

    @Column(name = "theQuestion")
    private String theQuestion;

    @Column(name = "correct_answer")
    private String correctAnswer;

    @ManyToOne
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;




    public Question() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getTheQuestion() {
        return theQuestion;
    }

    public void setTheQuestion(String theQuestion) {
        this.theQuestion = theQuestion;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }
}
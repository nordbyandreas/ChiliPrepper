package com.ChiliPrepper.ChiliPrepper.model;

import javax.persistence.*;

/**
 * Created by Andreas on 20.02.2017.
 *
 * Model for the Quiz object, containing fields, getters and setters
 *
 * Quiz-Objects relates to courses
 *
 * Hibernate takes care of the object relational mapping, so we can save and search for "objects" in the DB.
 *
 * the @Entity annotation informs hibernate that a schema should be created in the database
 *
 * Uses @Annotations to specify fields
 *
 */

@Entity
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //automatically set unique ID
    @Column(name = "quiz_id")
    private Long id;

    @Column(name="quiz_name")
    private String quizName;

    @Column
    private boolean published;

    @ManyToOne
    @JoinColumn(name="course_id")
    private Course course;


    public Quiz() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

    public String getQuizName() {
        return quizName;
    }

    public void setQuizName(String quizName) {
        this.quizName = quizName;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

}
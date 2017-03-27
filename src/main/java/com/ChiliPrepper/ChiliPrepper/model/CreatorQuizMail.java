package com.ChiliPrepper.ChiliPrepper.model;

import javax.persistence.*;

/**
 * Created by Andreas on 23.03.2017.
 */


@Entity
public class CreatorQuizMail {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //automaticly set unique ID
    @Column(name = "CreatorQuizMail_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User creator;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }
}

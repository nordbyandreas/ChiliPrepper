package com.ChiliPrepper.ChiliPrepper.model;

import javax.persistence.*;

/**
 * Created by Christer on 17.03.2017.
 */

@Entity
public class QuizMail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //automaticly set unique ID
    @Column(name = "quizMail_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User participant;


    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;

    @Column(name = "participant_mail_sent")
    private boolean participantMailSent;

    @Column(name = "creator_mail_sent")
    private boolean creatorMailSent;


    public QuizMail() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getParticipant() {
        return participant;
    }

    public void setParticipant(User participant) {
        this.participant = participant;
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

    public boolean isParticipantMailSent() {
        return participantMailSent;
    }

    public void setParticipantMailSent(boolean participantMailSent) {
        this.participantMailSent = participantMailSent;
    }

    public boolean isCreatorMailSent() {
        return creatorMailSent;
    }

    public void setCreatorMailSent(boolean creatorMailSent) {
        this.creatorMailSent = creatorMailSent;
    }

}

package com.ChiliPrepper.ChiliPrepper.model;

import javax.persistence.*;

/**
 * Created by Andreas on 16.02.2017.
 */

@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String courseName;

    @Column
    private String accessCode;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User creator;

    public Course() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getAccessCode() {
        return accessCode;
    }

    public void setAccessCode(String accessCode) {
        this.accessCode = accessCode;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }
}

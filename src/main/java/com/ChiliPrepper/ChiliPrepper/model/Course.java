package com.ChiliPrepper.ChiliPrepper.model;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Andreas on 16.02.2017.
 *
 *
 * Model for the Course object, containing fields, getters and setters
 *
 * Course-Objects relates to a creator(user).
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
@Entity                    //Mark the class as an entity, so a schema will be created in the database
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //automaticly set unique ID
    @Column(name= "course_id")
    private Long id;

    @Column
    private String courseName;

    @Column(unique = true)
    private String accessCode;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User creator;


    //Default constructor
    public Course() {}



    //getters and setters for all fields:


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

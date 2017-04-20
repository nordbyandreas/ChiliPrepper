package com.ChiliPrepper.ChiliPrepper.model;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Andreas on 16.02.2017.
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

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "regCourses")
    private Set<User> regUsers;




    //Default constructor
    public Course() {}



    //getters and setters for all fields:

    public Set<User> getRegUsers() {
        return regUsers;
    }

    public void setRegUsers(Set<User> regUsers) {
        this.regUsers = regUsers;
    }


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

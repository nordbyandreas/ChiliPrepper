package com.ChiliPrepper.ChiliPrepper.service;

import com.ChiliPrepper.ChiliPrepper.model.Course;

import java.util.List;

/**
 * Created by Andreas on 16.02.2017.
 *
 *
 * Interface for the Service Layer for Courses
 *
 * Look at the Implementation for method descriptions
 *
 * All methods to be used from the DAO layer must be included here
 */
public interface CourseService {


    Iterable<Course> findAllForCreator();
    Iterable<Course> findAll();
    Course findOne(Long id);
    void save(Course course);
    Course findByAccessCode(String accessCode);

}

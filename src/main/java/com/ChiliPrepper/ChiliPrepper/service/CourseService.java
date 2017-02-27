package com.ChiliPrepper.ChiliPrepper.service;

import com.ChiliPrepper.ChiliPrepper.model.Course;

import java.util.List;

/**
 * Created by Andreas on 16.02.2017.
 */
public interface CourseService {
    Iterable<Course> findAll();
    Course findOne(Long id);
    void save(Course course);
    Course findByAccessCode(String accessCode);

}

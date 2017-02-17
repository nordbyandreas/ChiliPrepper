package com.ChiliPrepper.ChiliPrepper.service;

import com.ChiliPrepper.ChiliPrepper.dao.CourseDao;
import com.ChiliPrepper.ChiliPrepper.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Andreas on 16.02.2017.
 */

@Service
public class CourseServiceImpl implements CourseService{

    @Autowired
    private CourseDao courseDao;

    @Override
    public Iterable<Course> findAll() {
        return courseDao.findAll();
    }

    @Override
    public Course findOne(Long id) {
        return courseDao.findOne(id);
    }

    @Override
    public void save(Course course) {
        courseDao.save(course);
    }
}

package com.ChiliPrepper.ChiliPrepper.dao;

import com.ChiliPrepper.ChiliPrepper.model.Course;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Andreas on 16.02.2017.
 */
@Repository
public interface CourseDao extends CrudRepository<Course, Long> {
    @Query("select course from Course course where course.creator.id=:#{principal.id}")
    List<Course> findAll();
}

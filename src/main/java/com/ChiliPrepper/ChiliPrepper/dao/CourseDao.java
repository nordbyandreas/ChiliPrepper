package com.ChiliPrepper.ChiliPrepper.dao;

import com.ChiliPrepper.ChiliPrepper.model.Course;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Andreas on 16.02.2017.
 */


//data access object for COURSES


//Because of -Enabled JPARepository(DataConfig)  and  - extended Crudrepository (from the spring data library)
//Spring data will generate the implemented class automaticly upon Application Boot


//uses "SMART METHOD NAMING"
// example:
// Spring Data would know by a method named "findById"
//that the implementation it generates needs to return a Course-object
//that matches the id passed




@Repository
public interface CourseDao extends CrudRepository<Course, Long> {

    @Query("select course from Course course where course.creator.id=:#{principal.id}")
    List<Course> findAll();

    Course findByAccessCode(String accessCode);

}

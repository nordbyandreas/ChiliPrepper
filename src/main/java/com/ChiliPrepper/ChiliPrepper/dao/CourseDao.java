package com.ChiliPrepper.ChiliPrepper.dao;

import com.ChiliPrepper.ChiliPrepper.model.Course;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Created by Andreas on 16.02.2017.
 *
 * Interface for generic CRUD operations on the repository for the Course type.
 *
 * Because of -Enabled JPARepository(DataConfig)  and  - extended Crudrepository (from the spring data library)
 * Spring data will generate the implemented class automatically upon Application Boot
 *
 * uses "SMART METHOD NAMING"
 *
 * example for the UserDao:
 * Spring Data would know by a method named "User findByUsername(String Username),"
 * that the implementation it generates needs to return a User-object that matches the username passed
 *
 */


@Repository
public interface CourseDao extends CrudRepository<Course, Long> {


    /**
     * Searches the database for all Courses with the logged in user's id as creator id
     *
     * @return A List of all course objects matching the given Creator Id
     */
    @Query("select course from Course course where course.creator.id=:#{principal.id}")
    List<Course> findAllForCreator();


    /**
     * Searches the database for all Courses
     *
     * @return A List of all Course objects
     */
    List<Course> findAll();


    /**
     * Searches the Database for a Course with the given accessCode
     *
     * @param accessCode
     * @return a Course matching the given accesscode
     */
    Course findByAccessCode(String accessCode);

}

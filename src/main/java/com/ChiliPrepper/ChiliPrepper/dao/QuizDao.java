package com.ChiliPrepper.ChiliPrepper.dao;

import com.ChiliPrepper.ChiliPrepper.model.Quiz;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Created by Christer on 20.02.2017.
 *
 * Interface for generic CRUD operations on the repository for the Quiz type.
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
public interface QuizDao extends CrudRepository<Quiz, Long>{

    /**
     * Searches the database for all Quiz objects with the given course Id
     *
     * @param id
     * @return A List of Quiz objects matching the course id
     */
    List<Quiz> findAllByCourse_id(Long id);

}
package com.ChiliPrepper.ChiliPrepper.dao;

import com.ChiliPrepper.ChiliPrepper.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Created by Andreas on 15.02.2017.
 *
 * Interface for generic CRUD operations on a the repository for the USER type.
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
public interface UserDao extends CrudRepository<User,Long> {

    /**
     * Retrieves a User by username
     *
     * @param username
     * @return User object
     */
    User findByUsername(String username);



    /**
     * Returns a list of all users
     *
     * @return List of User's
     */
    List<User> findAll();




}

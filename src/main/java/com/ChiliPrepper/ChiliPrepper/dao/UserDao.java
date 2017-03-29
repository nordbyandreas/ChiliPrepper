package com.ChiliPrepper.ChiliPrepper.dao;

/**
 * Created by Andreas on 15.02.2017.
 */

import com.ChiliPrepper.ChiliPrepper.model.Course;
import com.ChiliPrepper.ChiliPrepper.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


//data access object for USERS

//Because of -Enabled JPARepository(DataConfig)  and  - extended Crudrepository (from the spring data library)
//Spring data will generate the implemented class automaticly upon Application Boot


//uses "SMART METHOD NAMING"
// example:
// Spring Data would know by a method named "findByUsername"
//that the implementation it generates needs to return a User-object
//that matches the username passed


@Repository
public interface UserDao extends CrudRepository<User,Long> {
    User findByUsername(String username);
    List<User> findAll();




}

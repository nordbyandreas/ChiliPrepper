package com.ChiliPrepper.ChiliPrepper.dao;

import com.ChiliPrepper.ChiliPrepper.model.CreatorQuizMail;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


/**
 * Created by Andreas on 23.03.2017.
 *
 * Interface for generic CRUD operations on a the repository for the CreatorQuizMail type.
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
public interface CreatorQuizMailDao extends CrudRepository<CreatorQuizMail, Long> {


    CreatorQuizMail findOneByQuiz_Id(Long id);


}

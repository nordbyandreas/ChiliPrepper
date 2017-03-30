package com.ChiliPrepper.ChiliPrepper.dao;

import com.ChiliPrepper.ChiliPrepper.model.Question;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Created by Christer on 20.02.2017.
 *
 * Interface for generic CRUD operations on a the repository for the Question type.
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
public interface QuestionDao extends CrudRepository<Question, Long> {

    List<Question> findAllByQuiz_Id(Long id);

    void deleteAllByQuiz_Id(Long id);
    List<Question> findAllByTopic(String topic);

}

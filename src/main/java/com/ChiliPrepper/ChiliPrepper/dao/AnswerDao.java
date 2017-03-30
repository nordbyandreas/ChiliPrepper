package com.ChiliPrepper.ChiliPrepper.dao;

import com.ChiliPrepper.ChiliPrepper.model.Answer;
import com.ChiliPrepper.ChiliPrepper.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Created by Andreas on 02.03.2017.
 *
 * Interface for generic CRUD operations on a the repository for the Answer type.
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
public interface AnswerDao extends CrudRepository<Answer, Long> {

    List<Answer> findAllByQuestion_Id(Long id);
    List<Answer> findAllByQuiz_Id(Long id);
    List<Answer> findAllByCourse_Id(Long id);
    Answer findOneByQuestion_Id(Long id);
    Answer findOneByQuestion_IdAndUser_Id(Long questionId, Long userId);
    void deleteAllByQuiz_Id(Long id);
    void deleteAllByQuestion_Id(Long id);
    List<Answer> findAllByCourse_IdAndUser_Id(Long courseId, Long userId);
    List<Answer> findAllByQuiz_IdAndUser_Id(Long quizId, Long userId);
    List<Answer> findAllByUser_Id(Long id);
}

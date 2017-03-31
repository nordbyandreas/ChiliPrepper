package com.ChiliPrepper.ChiliPrepper.dao;

import com.ChiliPrepper.ChiliPrepper.model.QuizMail;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Created by Christer on 17.03.2017.
 *
 * Interface for generic CRUD operations on a the repository for the QuizMail type.
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
public interface QuizMailDao extends CrudRepository<QuizMail, Long> {


    List<QuizMail> findAllByQuiz_Id(Long id);




    List<QuizMail> findAllByParticipant_Id(Long id);




    List<QuizMail> findAllByQuiz_IdAndCourse_Id(Long quizId, Long courseId);




    QuizMail findOneByQuiz_Id(Long id);




    QuizMail findOneByQuiz_IdAndParticipant_Id(Long quizId, Long participantId);




    void deleteAllByQuiz_Id(Long id);




    void deleteAllByParticipant_Id(Long id);





}

package com.ChiliPrepper.ChiliPrepper.dao;

import com.ChiliPrepper.ChiliPrepper.model.QuizMail;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Created by Christer on 17.03.2017.
 *
 * Interface for generic CRUD operations on the repository for the QuizMail type.
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

//TODO: Clean up methods - there are some redundant methods here, such as "findOneByQuizId"



@Repository
public interface QuizMailDao extends CrudRepository<QuizMail, Long> {

    /**
     * Searches the database for  all QuizMail objects matching given Quiz Id
     *
     * @param id
     * @return List of QuizMail objects with matching id
     */
    List<QuizMail> findAllByQuiz_Id(Long id);


    /**
     * Searches the database for  all QuizMail objects matching the given participant id
     *
     * @param id
     * @return List of QuizMail objects with matching id
     */
    List<QuizMail> findAllByParticipant_Id(Long id);


    /**
     *
     * Searches the database for  all QuizMail objects matching both the given QuizId and CourseId
     *
     * @param quizId
     * @param courseId
     * @return List of Quizmail objects matching the parameters
     */
    List<QuizMail> findAllByQuiz_IdAndCourse_Id(Long quizId, Long courseId);


    /**
     *Searches the database for a QuizMail Object matching the given Quiz Id
     *
     * @param id
     * @return A QuizMail object matching the given Id
     */
    QuizMail findOneByQuiz_Id(Long id);


    /**
     * Searches the database for a Quizmail object matching the given QuizId and participantID
     *
     * @param quizId
     * @param participantId
     * @return A QuizMail object matching the parameters
     */
    QuizMail findOneByQuiz_IdAndParticipant_Id(Long quizId, Long participantId);


    /**
     * Deletes all QuizMail objects in the database matching the given Quiz Id
     *
     * @param id
     */
    void deleteAllByQuiz_Id(Long id);


    /**
     * Deletes all QuizMail objects in the database matching the given participant id
     *
     * @param id
     */
    void deleteAllByParticipant_Id(Long id);

}
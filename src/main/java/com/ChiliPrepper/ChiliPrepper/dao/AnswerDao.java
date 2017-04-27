package com.ChiliPrepper.ChiliPrepper.dao;

import com.ChiliPrepper.ChiliPrepper.model.Answer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Created by Andreas on 02.03.2017.
 *
 * Interface for generic CRUD operations on the repository for the Answer type.
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

    /**
     * Searches the database for all Answers with matching Question Id
     *
     * @param id
     * @return a List of Answer objects matching the given Question id
     */
    List<Answer> findAllByQuestion_Id(Long id);


    /**
     * Searches the database for all Answers with matching quiz id
     *
     * @param id
     * @return a List of Answers matching the Quiz id
     */
    List<Answer> findAllByQuiz_Id(Long id);


    /**
     * Searches the database for all Answers with matching Course id
     *
     * @param id
     * @return a List of Answers matching the Course id
     */
    List<Answer> findAllByCourse_Id(Long id);


    /**
     *
     *
     * @param id
     * @return
     */
    Answer findOneByQuestion_Id(Long id);


    /**
     * Searches the database for an Answer with matching QuestionId and UserId
     *
     * @param questionId
     * @param userId
     * @return a Answer objects matching the questionId and userId
     */
    Answer findOneByQuestion_IdAndUser_Id(Long questionId, Long userId);


    /**
     * Deletes all Answers in the database with matching QuizId
     *
     * @param id
     */
    void deleteAllByQuiz_Id(Long id);


    /**
     * Deletes all Answers in the database with matching QuestionId
     *
     * @param id
     */
    void deleteAllByQuestion_Id(Long id);


    /**
     * Searches the database for all Answers with matching CourseId and UserId
     *
     * @param courseId
     * @param userId
     * @return a List of Answers matching the given courseId and userId
     */
    List<Answer> findAllByCourse_IdAndUser_Id(Long courseId, Long userId);


    /**
     * Searches the database for all Answers with matching quizId and userId
     *
     * @param quizId
     * @param userId
     * @return a List of Answer objects matching the given quizId and userId
     */
    List<Answer> findAllByQuiz_IdAndUser_Id(Long quizId, Long userId);


    /**
     * Searches the database for all Answers with matching userId
     *
     * @param id
     * @return a List of all Answers objects matching the given userId
     */
    List<Answer> findAllByUser_Id(Long id);

}

package com.ChiliPrepper.ChiliPrepper.dao;

import com.ChiliPrepper.ChiliPrepper.model.Answer;
import com.ChiliPrepper.ChiliPrepper.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Andreas on 02.03.2017.
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

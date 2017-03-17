package com.ChiliPrepper.ChiliPrepper.dao;

import com.ChiliPrepper.ChiliPrepper.model.QuizMail;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Christer on 17.03.2017.
 */
public interface QuizMailDao extends CrudRepository<QuizMail, Long> {

    List<QuizMail> findAllByParticipantmail_Id(Long id);
    List<QuizMail> findAllByQuiz_Id(Long id);
    List<QuizMail> findAllByUser_Id(Long id);
    QuizMail findOneByQuiz_Id(Long id);
    QuizMail findOneByQuiz_IdAndUser_Id(Long quizId, Long userId);
    void deleteAllByQuiz_Id(Long id);
    void deleteAllByUser_Id(Long id);
    List<QuizMail> findAllByQuiz_IdAndUser_Id(Long quizId, Long userId);

}

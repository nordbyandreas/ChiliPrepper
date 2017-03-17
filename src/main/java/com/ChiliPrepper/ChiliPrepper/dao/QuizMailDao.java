package com.ChiliPrepper.ChiliPrepper.dao;

import com.ChiliPrepper.ChiliPrepper.model.QuizMail;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Christer on 17.03.2017.
 */

@Repository
public interface QuizMailDao extends CrudRepository<QuizMail, Long> {

    List<QuizMail> findAllByQuizmail_Id(Long id);
    List<QuizMail> findAllByQuiz_Id(Long id);
    List<QuizMail> findAllByParticipant_Id(Long id);
    QuizMail findOneByQuiz_Id(Long id);
    QuizMail findOneByQuiz_IdAndParticipant_Id(Long quizId, Long participantId);
    void deleteAllByQuiz_Id(Long id);
    void deleteAllByPartcipant_Id(Long id);
    List<QuizMail> findAllByQuiz_IdAndCourse_Id(Long quizId, Long courseId);

}

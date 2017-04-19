package com.ChiliPrepper.ChiliPrepper.service;

import com.ChiliPrepper.ChiliPrepper.model.QuizMail;

import java.util.List;

/**
 * Created by Christer on 17.03.2017.
 *
 *
 *  * Interface for the Service Layer for QuizMail
 *
 * Look at the Implementation for method descriptions
 *
 * All methods to be used from the DAO layer must be included here
 */
public interface QuizMailService {

    //Iterable<QuizMail> findOneByQuizMail_Id(Long id);
    Iterable<QuizMail> findAllByQuiz_Id(Long id);
    Iterable<QuizMail> findAllByParticipant_Id(Long id);
    Iterable<QuizMail> findAllByQuiz_IdAndCourse_Id(Long quizId, Long courseId);

    QuizMail findOneByQuiz_Id(Long id);
    QuizMail findOneByQuiz_IdAndParticipant_Id(Long quizId, Long participantId);

    void deleteAllByQuiz_Id(Long id);
    void deleteAllByParticipant_Id(Long id);

    void save(QuizMail quizMail);
}

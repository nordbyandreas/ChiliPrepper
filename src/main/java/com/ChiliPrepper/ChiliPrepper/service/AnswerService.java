package com.ChiliPrepper.ChiliPrepper.service;

import com.ChiliPrepper.ChiliPrepper.model.Answer;

import java.util.List;

/**
 * Created by Andreas on 02.03.2017.
 *
 *
 *  Interface for the Service Layer for Answers
 *
 * Look at the Implementation for method descriptions
 *
 * All methods to be used from the DAO layer must be included here
 */



public interface AnswerService {

    Iterable<Answer> findAllByQuestion_Id(Long id);
    Iterable<Answer> findAllByQuiz_Id(Long id);
    Iterable<Answer> findAllByCourse_Id(Long id);
    Answer findOneByQuestion_Id(Long id);

    Answer findOneByQuestion_IdAndUser_Id(Long questionId, Long userId);

    void deleteAllByQuiz_Id(Long id);

    void deleteAllByQuestion_Id(Long id);


    Iterable<Answer> findAllByCourse_IdAndUser_Id(Long courseId, Long userId);
    Iterable<Answer> findAllByQuiz_IdAndUser_Id(Long quizId, Long userId);

    Iterable<Answer> findAllByUser_Id(Long id);


    void save(Answer answer);

}

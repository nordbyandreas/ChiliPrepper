package com.ChiliPrepper.ChiliPrepper.service;

import com.ChiliPrepper.ChiliPrepper.model.Quiz;

/**
 * Created by Christer on 20.02.2017.
 *
 * Interface for the Service Layer for Quiz
 *
 * Look at the Implementation for method descriptions
 *
 * All methods to be used from the DAO layer must be included here
 *
 */

public interface QuizService {

    Iterable<Quiz> findAllByCourse_id(Long id);
    Quiz findOne(Long id);

    void save(Quiz quiz);
    void delete(Quiz quiz);

}
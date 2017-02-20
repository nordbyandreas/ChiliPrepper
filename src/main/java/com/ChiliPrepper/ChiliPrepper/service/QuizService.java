package com.ChiliPrepper.ChiliPrepper.service;

import com.ChiliPrepper.ChiliPrepper.model.Quiz;

/**
 * Created by Christer on 20.02.2017.
 */
public interface QuizService {
    Iterable<Quiz> findAll();
    Quiz findOne(Long id);
    void save(Quiz quiz);
}

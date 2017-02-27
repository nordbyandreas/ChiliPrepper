package com.ChiliPrepper.ChiliPrepper.service;

import com.ChiliPrepper.ChiliPrepper.model.Quiz;

import java.util.List;

/**
 * Created by Christer on 20.02.2017.
 */
public interface QuizService {
    Iterable<Quiz> findAllByCourse_id(Long id);

    Quiz findOne(Long id);
    void save(Quiz quiz);
}

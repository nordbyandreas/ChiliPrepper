package com.ChiliPrepper.ChiliPrepper.service;

import com.ChiliPrepper.ChiliPrepper.model.Question;

/**
 * Created by Andreas on 24.02.2017.
 */
public interface QuestionService {

    Iterable<Question> findAll();
    Question findOne(Long id);
    void save(Question question);


}

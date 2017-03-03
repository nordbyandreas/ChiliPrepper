package com.ChiliPrepper.ChiliPrepper.service;

import com.ChiliPrepper.ChiliPrepper.model.Question;

import java.util.List;

/**
 * Created by Andreas on 24.02.2017.
 */
public interface QuestionService {

    Iterable<Question> findAllByQuiz_Id(Long id);
    Question findOne(Long id);
    void save(Question question);


}

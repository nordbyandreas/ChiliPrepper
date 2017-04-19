package com.ChiliPrepper.ChiliPrepper.service;

import com.ChiliPrepper.ChiliPrepper.model.Question;

import java.util.List;

/**
 * Created by Andreas on 24.02.2017.
 *
 *
 *  * Interface for the Service Layer for Questions
 *
 * Look at the Implementation for method descriptions
 *
 * All methods to be used from the DAO layer must be included here
 */
public interface QuestionService {

    Iterable<Question> findAllByQuiz_Id(Long id);
    Question findOne(Long id);
    void save(Question question);

    void deleteAllByQuiz_Id(Long id);

    void delete(Question question);

    Iterable<Question> findAllByTopic(String topic);


}

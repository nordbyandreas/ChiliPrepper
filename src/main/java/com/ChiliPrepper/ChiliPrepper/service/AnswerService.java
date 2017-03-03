package com.ChiliPrepper.ChiliPrepper.service;

import com.ChiliPrepper.ChiliPrepper.model.Answer;

import java.util.List;

/**
 * Created by Andreas on 02.03.2017.
 */



public interface AnswerService {

    Answer findOneByQuestion_Id(Long id);
    Iterable<Answer> findAllByQuiz_Id(Long id);
    Iterable<Answer> findAllByCourse_Id(Long id);




    void save(Answer answer);

}

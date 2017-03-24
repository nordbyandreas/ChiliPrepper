package com.ChiliPrepper.ChiliPrepper.service;

import com.ChiliPrepper.ChiliPrepper.model.CreatorQuizMail;

/**
 * Created by Andreas on 23.03.2017.
 */
public interface CreatorQuizMailService {


    CreatorQuizMail findOneByQuiz_Id(Long id);

    void save(CreatorQuizMail creatorQuizMail);

}

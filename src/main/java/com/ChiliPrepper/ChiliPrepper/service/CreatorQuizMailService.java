package com.ChiliPrepper.ChiliPrepper.service;

import com.ChiliPrepper.ChiliPrepper.model.CreatorQuizMail;

/**
 * Created by Andreas on 23.03.2017.
 *
 * Interface for the Service Layer for CreatorQuizMail
 *
 * Look at the Implementation for method descriptions
 *
 * All methods to be used from the DAO layer must be included here
 */

public interface CreatorQuizMailService {

    CreatorQuizMail findOneByQuiz_Id(Long id);
    void save(CreatorQuizMail creatorQuizMail);

}
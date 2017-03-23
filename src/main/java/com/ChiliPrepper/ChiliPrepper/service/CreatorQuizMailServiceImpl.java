package com.ChiliPrepper.ChiliPrepper.service;

import com.ChiliPrepper.ChiliPrepper.dao.CreatorQuizMailDao;
import com.ChiliPrepper.ChiliPrepper.model.CreatorQuizMail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Andreas on 23.03.2017.
 */
@Service
public class CreatorQuizMailServiceImpl implements CreatorQuizMailService {

    @Autowired
    private CreatorQuizMailDao creatorQuizMailDao;


    @Override
    public CreatorQuizMail findOneByQuiz_Id(Long id) {
        return creatorQuizMailDao.findOneByQuiz_Id(id);
    }

    @Override
    public void save(CreatorQuizMail creatorQuizMail) {
        creatorQuizMailDao.save(creatorQuizMail);
    }
}

package com.ChiliPrepper.ChiliPrepper.service;

import com.ChiliPrepper.ChiliPrepper.dao.QuestionDao;
import com.ChiliPrepper.ChiliPrepper.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Andreas on 24.02.2017.
 */

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionDao questionDao;

    @Override
    public Iterable<Question> findAll() {
        return questionDao.findAll();
    }

    @Override
    public Question findOne(Long id) {
        return questionDao.findOne(id);
    }

    @Override
    public void save(Question question) {
        questionDao.save(question);

    }
}

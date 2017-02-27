package com.ChiliPrepper.ChiliPrepper.service;

import com.ChiliPrepper.ChiliPrepper.dao.QuizDao;
import com.ChiliPrepper.ChiliPrepper.model.Quiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Christer on 20.02.2017.
 */
@Service
public class QuizServiceImpl implements QuizService {

    @Autowired
    private QuizDao quizDao;


    @Override
    public Iterable<Quiz> findAllByCourse_id(Long id) {
        return quizDao.findAllByCourse_id(id);
    }

    @Override
    public Quiz findOne(Long id) {
        return quizDao.findOne(id);
    }

    @Override
    public void save(Quiz quiz) {
        quizDao.save(quiz);
    }
}

package com.ChiliPrepper.ChiliPrepper.service;

import com.ChiliPrepper.ChiliPrepper.dao.AnswerDao;
import com.ChiliPrepper.ChiliPrepper.model.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Andreas on 02.03.2017.
 */


@Service
public class AnswerServiceImpl implements AnswerService {


    @Autowired
    private AnswerDao answerDao;


    @Override
    public Iterable<Answer> findAllByQuestion_Id(Long id) {
        return answerDao.findAllByQuestion_Id(id);
    }

    @Override
    public Iterable<Answer> findAllByQuiz_Id(Long id) {
        return answerDao.findAllByQuiz_Id(id);
    }

    @Override
    public Iterable<Answer> findAllByCourse_Id(Long id) {
        return answerDao.findAllByCourse_Id(id);
    }

    @Override
    public Answer findOneByQuestion_Id(Long id) {
        return answerDao.findOneByQuestion_Id(id);
    }

    @Override
    public void deleteAllByQuiz_Id(Long id) {
        answerDao.deleteAllByQuiz_Id(id);
    }

    @Override
    public void deleteAllByQuestion_Id(Long id) {
        answerDao.deleteAllByQuestion_Id(id);
    }

    @Override
    public void save(Answer answer) {
        answerDao.save(answer);

    }
}

package com.ChiliPrepper.ChiliPrepper.service;

import com.ChiliPrepper.ChiliPrepper.dao.AlternativeDao;
import com.ChiliPrepper.ChiliPrepper.dao.AnswerDao;
import com.ChiliPrepper.ChiliPrepper.dao.QuestionDao;
import com.ChiliPrepper.ChiliPrepper.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Andreas on 24.02.2017.
 */

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionDao questionDao;
    @Autowired
    private AnswerDao answerDao;
    @Autowired
    private AlternativeDao alternativeDao;

    @Override
    public Iterable<Question> findAllByQuiz_Id(Long id) {
        return questionDao.findAllByQuiz_Id(id);
    }

    @Override
    public Question findOne(Long id) {
        return questionDao.findOne(id);
    }

    @Override
    public void save(Question question) {
        questionDao.save(question);

    }

    @Override
    public void deleteAllByQuiz_Id(Long id) {
        questionDao.deleteAllByQuiz_Id(id);
    }

    @Override
    @Transactional
    public void delete(Question question) {
        answerDao.deleteAllByQuestion_Id(question.getId());
        alternativeDao.deleteAllByQuestion_Id(question.getId());
        questionDao.delete(question);
    }

    @Override
    public Iterable<Question> findAllByTopic(String topic) {
        return questionDao.findAllByTopic(topic);
    }
}

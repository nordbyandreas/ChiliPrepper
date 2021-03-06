package com.ChiliPrepper.ChiliPrepper.service;

import com.ChiliPrepper.ChiliPrepper.dao.AnswerDao;
import com.ChiliPrepper.ChiliPrepper.model.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Andreas on 02.03.2017.
 *
 * Implementation of the Service layer
 *
 * All methods call the respective DAO-layer's methods.
 *
 * The DAO layer is "@Autowired" into this class, which means that Spring will inject
 * a constructed DAO-class when needed.
 *
 * There is not much need for commenting every single method in this layer, because almost all methods simply
 * call upon the corresponding method in the DAO-layer.
 *
 * The only difference is that the Lists returned from the DAO's are returned as Iterables here.
 *
 * Also, in the QuestionServiceImpl you may see an example of a method that does more.
 *
 * Although, we have not made the most use of this separation of the DAO and Service layers, this architectural decision
 * gives us the opportunity to implement different and more complex functionality for every layer.
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
    public Answer findOneByQuestion_IdAndUser_Id(Long questionId, Long userId) {
        return answerDao.findOneByQuestion_IdAndUser_Id(questionId, userId);
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
    public Iterable<Answer> findAllByCourse_IdAndUser_Id(Long courseId, Long userId) {
        return answerDao.findAllByCourse_IdAndUser_Id(courseId, userId);
    }

    @Override
    public Iterable<Answer> findAllByQuiz_IdAndUser_Id(Long quizId, Long userId) {
        return answerDao.findAllByQuiz_IdAndUser_Id(quizId, userId);
    }

    @Override
    public Iterable<Answer> findAllByUser_Id(Long id) {
        return answerDao.findAllByUser_Id(id);
    }

    @Override
    public void save(Answer answer) {
        answerDao.save(answer);
    }

}
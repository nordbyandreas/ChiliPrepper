package com.ChiliPrepper.ChiliPrepper.service;

import com.ChiliPrepper.ChiliPrepper.dao.QuizMailDao;
import com.ChiliPrepper.ChiliPrepper.model.QuizMail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Christer on 17.03.2017.
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
 *
 */

@Service
public class QuizMailServiceImpl implements QuizMailService {

    @Autowired
    private QuizMailDao quizMailDao;

    @Override
    public Iterable<QuizMail> findAllByQuiz_Id(Long id) {
        return quizMailDao.findAllByQuiz_Id(id);
    }

    @Override
    public Iterable<QuizMail> findAllByParticipant_Id(Long id) {
        return quizMailDao.findAllByParticipant_Id(id);
    }

    @Override
    public Iterable<QuizMail> findAllByQuiz_IdAndCourse_Id(Long quizId, Long courseId) {
        return quizMailDao.findAllByQuiz_IdAndCourse_Id(quizId, courseId);
    }

    @Override
    public QuizMail findOneByQuiz_Id(Long id) {
        return quizMailDao.findOneByQuiz_Id(id);
    }

    @Override
    public QuizMail findOneByQuiz_IdAndParticipant_Id(Long quizId, Long participantId) {
        return quizMailDao.findOneByQuiz_IdAndParticipant_Id(quizId, participantId);
    }

    @Override
    public void deleteAllByQuiz_Id(Long id) {
        quizMailDao.deleteAllByQuiz_Id(id);
    }

    @Override
    public void deleteAllByParticipant_Id(Long id) {
        quizMailDao.deleteAllByParticipant_Id(id);
    }

    @Override
    public void save(QuizMail quizMail) {
        quizMailDao.save(quizMail);
    }

}
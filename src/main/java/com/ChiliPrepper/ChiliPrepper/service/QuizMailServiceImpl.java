package com.ChiliPrepper.ChiliPrepper.service;

import com.ChiliPrepper.ChiliPrepper.dao.QuizMailDao;
import com.ChiliPrepper.ChiliPrepper.model.QuizMail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Christer on 17.03.2017.
 */

@Service
public class QuizMailServiceImpl implements QuizMailService {

    @Autowired
    private QuizMailDao quizMailDao;



    @Override
    public Iterable<QuizMail> findAllByQuizmail_Id(Long id) {
        return quizMailDao.findAllByQuizmail_Id(id);
    }

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
    public void deleteAllByPartcipant_Id(Long id) {
        quizMailDao.deleteAllByPartcipant_Id(id);
    }

    @Override
    public void save(QuizMail quizMail) {
        quizMailDao.save(quizMail);
    }
}

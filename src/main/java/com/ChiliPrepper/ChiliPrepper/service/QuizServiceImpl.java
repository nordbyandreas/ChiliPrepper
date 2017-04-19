package com.ChiliPrepper.ChiliPrepper.service;

import com.ChiliPrepper.ChiliPrepper.dao.AlternativeDao;
import com.ChiliPrepper.ChiliPrepper.dao.AnswerDao;
import com.ChiliPrepper.ChiliPrepper.dao.QuestionDao;
import com.ChiliPrepper.ChiliPrepper.dao.QuizDao;
import com.ChiliPrepper.ChiliPrepper.model.Question;
import com.ChiliPrepper.ChiliPrepper.model.Quiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Christer on 20.02.2017.
 *
 *
 *  * Implementation of the Service layer
 *
 * All methods call the respective DAO-layer's methods.
 *
 * The DAO layer is "@Autowired" into this class, which means that Spring will inject
 * a constructed DAO-class when needed.
 *
 *
 * There is not much need for commenting every single method in this layer, because almost all methods simply
 * call upon the corresponding method in the DAO-layer.
 *
 * The only difference is that the Lists returned from the DAO's are returned as Iterables here.
 *
 * Also, in the QuestionServiceImpl you may see an example of a method that does more.
 *
 *
 *
 * Although, we have not made the most use of this separation of the DAO and Service layers, this architectural decision
 * gives us the opportunity to implement different and more complex functionality for every layer.
 *
 *
 */
@Service
public class QuizServiceImpl implements QuizService {

    @Autowired
    private QuizDao quizDao;

    @Autowired
    private AnswerDao answerDao;

    @Autowired
    private AlternativeDao alternativeDao;

    @Autowired
    private QuestionDao questionDao;


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


    @Override
    @Transactional
    public void delete(Quiz quiz) {
        answerDao.deleteAllByQuiz_Id(quiz.getId());

        Iterable<Question> questions = questionDao.findAllByQuiz_Id(quiz.getId());
        for(Question question : questions){
            alternativeDao.deleteAllByQuestion_Id(question.getId());
        }

        questionDao.deleteAllByQuiz_Id(quiz.getId());



        quizDao.delete(quiz);
    }
}

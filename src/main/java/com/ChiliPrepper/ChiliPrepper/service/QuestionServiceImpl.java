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



    /**
     * Method for deleting a Question.
     *
     * Also, this method deletes all Alternatives and Answers belonging to this Question
     *
     * @param question
     */
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

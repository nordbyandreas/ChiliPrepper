package com.ChiliPrepper.ChiliPrepper.service;

import com.ChiliPrepper.ChiliPrepper.dao.CreatorQuizMailDao;
import com.ChiliPrepper.ChiliPrepper.model.CreatorQuizMail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Andreas on 23.03.2017.
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

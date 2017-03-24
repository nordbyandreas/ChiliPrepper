package com.ChiliPrepper.ChiliPrepper.dao;

import com.ChiliPrepper.ChiliPrepper.model.Question;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Christer on 20.02.2017.
 */

@Repository
public interface QuestionDao extends CrudRepository<Question, Long> {

    List<Question> findAllByQuiz_Id(Long id);

    void deleteAllByQuiz_Id(Long id);
    List<Question> findAllByTopic(String topic);

}

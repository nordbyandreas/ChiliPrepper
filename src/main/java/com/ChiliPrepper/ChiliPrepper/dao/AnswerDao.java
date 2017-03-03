package com.ChiliPrepper.ChiliPrepper.dao;

import com.ChiliPrepper.ChiliPrepper.model.Answer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Andreas on 02.03.2017.
 */

@Repository
public interface AnswerDao extends CrudRepository<Answer, Long> {

    Answer findOneByQuestion_Id(Long id);
    List<Answer> findAllByQuiz_Id(Long id);
    List<Answer> findAllByCourse_Id(Long id);
}

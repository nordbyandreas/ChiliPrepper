package com.ChiliPrepper.ChiliPrepper.dao;

import com.ChiliPrepper.ChiliPrepper.model.Question;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Christer on 20.02.2017.
 */
@Repository
public interface QuestionDao extends CrudRepository<Question, Long> {



}

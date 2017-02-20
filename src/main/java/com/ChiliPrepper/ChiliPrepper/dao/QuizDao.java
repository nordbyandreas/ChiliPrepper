package com.ChiliPrepper.ChiliPrepper.dao;

import com.ChiliPrepper.ChiliPrepper.model.Quiz;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Christer on 20.02.2017.
 */
@Repository
public interface QuizDao extends CrudRepository<Quiz, Long>{
    List<Quiz> findAll();
}

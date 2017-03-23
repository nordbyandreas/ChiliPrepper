package com.ChiliPrepper.ChiliPrepper.dao;

import com.ChiliPrepper.ChiliPrepper.model.CreatorQuizMail;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Andreas on 23.03.2017.
 */

@Repository
public interface CreatorQuizMailDao extends CrudRepository<CreatorQuizMail, Long> {


    CreatorQuizMail findOneByQuiz_Id(Long id);


}

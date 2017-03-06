package com.ChiliPrepper.ChiliPrepper.dao;

import com.ChiliPrepper.ChiliPrepper.model.Alternative;
import com.ChiliPrepper.ChiliPrepper.model.Question;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Christer on 20.02.2017.
 */
@Repository
public interface AlternativeDao extends CrudRepository<Alternative, Long> {

    List<Alternative> findAllByQuestion_Id(Long id);

}

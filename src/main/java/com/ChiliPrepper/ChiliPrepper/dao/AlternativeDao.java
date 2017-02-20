package com.ChiliPrepper.ChiliPrepper.dao;

import com.ChiliPrepper.ChiliPrepper.model.Alternative;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Christer on 20.02.2017.
 */
@Repository
public interface AlternativeDao extends CrudRepository<Alternative, Long> {

}

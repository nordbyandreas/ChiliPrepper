package com.ChiliPrepper.ChiliPrepper.service;

import com.ChiliPrepper.ChiliPrepper.model.Alternative;
import com.ChiliPrepper.ChiliPrepper.model.Question;

/**
 * Created by Andreas on 02.03.2017.
 *
 * Interface for the Service Layer for Alternatives
 *
 * Look at the Implementation for method descriptions
 *
 * All methods to be used from the DAO layer must be included here
 *
 */
public interface AlternativeService {

    Iterable<Alternative> findAllByQuestion_Id(Long id);
    Alternative findOne(Long id);
    void save(Alternative alternative);
    void deleteAllByQuestion_Id(Long id);
}




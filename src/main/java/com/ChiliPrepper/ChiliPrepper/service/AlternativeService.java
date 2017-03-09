package com.ChiliPrepper.ChiliPrepper.service;

import com.ChiliPrepper.ChiliPrepper.model.Alternative;
import com.ChiliPrepper.ChiliPrepper.model.Question;

/**
 * Created by Andreas on 02.03.2017.
 */
public interface AlternativeService {

    Iterable<Alternative> findAllByQuestion_Id(Long id);
    Alternative findOne(Long id);
    void save(Alternative alternative);
}




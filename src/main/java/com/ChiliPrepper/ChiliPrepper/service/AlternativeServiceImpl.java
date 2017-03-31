package com.ChiliPrepper.ChiliPrepper.service;

import org.springframework.stereotype.Service;
import com.ChiliPrepper.ChiliPrepper.model.Alternative;
import com.ChiliPrepper.ChiliPrepper.dao.AlternativeDao;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Andreas on 02.03.2017.
 */

@Service
public class AlternativeServiceImpl implements AlternativeService {
    @Autowired
    private AlternativeDao alternativeDao;

    @Override
    public Iterable<Alternative> findAllByQuestion_Id(Long id) {
        return alternativeDao.findAllByQuestion_Id(id);
    }

    @Override
    public Alternative findOne(Long id) {
        return alternativeDao.findOne(id);
    }

    @Override
    public void save(Alternative alternative) {
        alternativeDao.save(alternative);
    }

    @Override
    public void deleteAllByQuestion_Id(Long id) {
        alternativeDao.deleteAllByQuestion_Id(id);
    }
}
package com.ChiliPrepper.ChiliPrepper.service;

import com.ChiliPrepper.ChiliPrepper.dao.CourseUserDao;
import com.ChiliPrepper.ChiliPrepper.model.CourseUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Andreas on 22.04.2017.
 */

@Service
public class CourseUserServiceImpl implements  CourseUserService {

    @Autowired
    private CourseUserDao courseUserDao;

    @Override
    public void save(CourseUser courseUser) {
        courseUserDao.save(courseUser);
    }

    @Override
    public Iterable<CourseUser> findAllByUser_id(Long id) {
        return courseUserDao.findAllByUser_id(id);
    }

}
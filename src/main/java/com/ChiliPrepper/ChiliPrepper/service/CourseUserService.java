package com.ChiliPrepper.ChiliPrepper.service;

import com.ChiliPrepper.ChiliPrepper.model.CourseUser;

/**
 * Created by Andreas on 22.04.2017.
 */

public interface CourseUserService  {

    void save(CourseUser courseUser);
    Iterable<CourseUser> findAllByUser_id(Long id);

}
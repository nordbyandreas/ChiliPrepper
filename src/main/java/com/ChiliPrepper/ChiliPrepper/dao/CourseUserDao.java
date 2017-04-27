package com.ChiliPrepper.ChiliPrepper.dao;

import com.ChiliPrepper.ChiliPrepper.model.CourseUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Created by Andreas on 22.04.2017.
 */

@Repository
public interface CourseUserDao extends CrudRepository<CourseUser, Long> {

    List<CourseUser> findAllByUser_id(Long id);

}

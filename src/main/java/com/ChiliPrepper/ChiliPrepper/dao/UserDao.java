package com.ChiliPrepper.ChiliPrepper.dao;

/**
 * Created by Andreas on 15.02.2017.
 */

import com.ChiliPrepper.ChiliPrepper.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends CrudRepository<User,Long> {
    User findByUsername(String username);
}

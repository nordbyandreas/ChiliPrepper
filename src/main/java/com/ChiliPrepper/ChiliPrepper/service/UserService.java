package com.ChiliPrepper.ChiliPrepper.service;

import com.ChiliPrepper.ChiliPrepper.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Created by Andreas on 15.02.2017.
 *
 *
 *
 *  * Interface for the Service Layer for Users
 *
 * Look at the Implementation for method descriptions
 *
 *
 * All methods to be used from the DAO layer must be included here
 */



import java.util.List;

/**
 * Created by Andreas on 17.02.2017.
 *
 *
 *  * Interface for the Service Layer for User
 *
 * Look at the Implementation for method descriptions
 *
 * All methods to be used from the DAO layer must be included here
 */

public interface UserService extends UserDetailsService {

    User findByUsername(String username);
    User findOne(Long id);
    Iterable<User> findAll();
    void save(User user);
}
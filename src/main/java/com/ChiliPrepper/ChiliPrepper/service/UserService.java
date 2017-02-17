package com.ChiliPrepper.ChiliPrepper.service;

/**
 * Created by Andreas on 15.02.2017.
 */
import com.ChiliPrepper.ChiliPrepper.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User findByUsername(String username);
    void save(User user);
}

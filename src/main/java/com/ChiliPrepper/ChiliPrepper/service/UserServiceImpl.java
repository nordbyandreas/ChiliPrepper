package com.ChiliPrepper.ChiliPrepper.service;

/**
 * Created by Andreas on 15.02.2017.
 */

import com.ChiliPrepper.ChiliPrepper.dao.UserDao;
import com.ChiliPrepper.ChiliPrepper.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleService roleService;



    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }



    @Override
    public void save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(true);
        user.setRole(roleService.findOne(1L));
        userDao.save(user);
    }

    @Override
    public User findOne(Long id) {
        return userDao.findOne(id);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Load user from the database (throw exception if not found)
        User user = userDao.findByUsername(username);
        if(user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        // Return user object
        return user;
    }
}
package com.ChiliPrepper.ChiliPrepper.service;

import com.ChiliPrepper.ChiliPrepper.dao.UserDao;
import com.ChiliPrepper.ChiliPrepper.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


/**
 * Created by Andreas on 15.02.2017.
 *
 *
 *  * Implementation of the Service layer
 *
 * All methods call the respective DAO-layer's methods.
 *
 * The DAO layer is "@Autowired" into this class, which means that Spring will inject
 * a constructed DAO-class when needed.
 *
 *
 * There is not much need for commenting every single method in this layer, because almost all methods simply
 * call upon the corresponding method in the DAO-layer.
 *
 * The only difference is that the Lists returned from the DAO's are returned as Iterables here.
 *
 * Also, in the QuestionServiceImpl you may see an example of a method that does more.
 *
 *
 *
 * Although, we have not made the most use of this separation of the DAO and Service layers, this architectural decision
 * gives us the opportunity to implement different and more complex functionality for every layer.
 *
 */


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
    public Iterable<User> findAll() {
        return userDao.findAll();
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
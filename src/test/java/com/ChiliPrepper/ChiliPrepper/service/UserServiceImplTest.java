package com.ChiliPrepper.ChiliPrepper.service;

import java.util.List;
import org.junit.Test;
import java.util.Arrays;
import org.mockito.Mock;
import java.util.ArrayList;
import org.mockito.InjectMocks;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import com.ChiliPrepper.ChiliPrepper.model.User;
import com.ChiliPrepper.ChiliPrepper.dao.UserDao;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.is;

/**
 * Created by Dag Kirstihagen on 15/03/2017.
 */

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {
    private Long userId = 1L;
    private User user = new User();
    private String username = "username";
    private List<User> userList = new ArrayList<>(Arrays.asList(user));

    @Mock
    private UserDao dao;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    RoleService roleService = new RoleServiceImpl();

    @InjectMocks
    private UserService service = new UserServiceImpl();

    /**Confirms that userService.findOne(userId) returns userDao.findOne(userId)*/
    @Test
    public void findOne()throws Exception  {
        when(dao.findOne(userId)).thenReturn(user);
        assertThat(service.findOne(userId), is(user));
        verify(dao).findOne(userId);
    }

    /**Confirms that userService.findOneByUsername(username) returns userDao.findOneByUsername(username)*/
    @Test
    public void findByUsername() throws Exception {
        when(dao.findByUsername(username)).thenReturn(user);
        assertThat(service.findByUsername(username), is(user));
        verify(dao).findByUsername(username);
    }

    /**Confirms that userService.findAll() returns userDao.findAll()*/
    @Test
    public void findAll()throws Exception  {
        when(dao.findAll()).thenReturn(userList);
        assertThat(service.findAll(), is(userList));
        verify(dao).findAll();
    }

    /**Confirms that userService.loadUserByUsername(username) returns userDao.loadUserByUsername(username)*/
    @Test
    public void loadUserByUsername() throws Exception {
        when(dao.findByUsername(username)).thenReturn(user);
        assertThat(service.loadUserByUsername(username), is(user));
        verify(dao).findByUsername(username);
    }

    /**Confirms that userService.loadUserByUsername(username) with an invalid username returns userDao.loadUserByUsername(username) and throws UsernameNotFoundException*/
    @Test(expected = UsernameNotFoundException.class)
    public void loadUserByUsername_shouldThrowUsernameNotFoundException() throws Exception {
        when(dao.findByUsername(username)).thenReturn(null);
        service.loadUserByUsername(username);
        verify(dao).findByUsername(username);
    }

    /**Confirms that userService.save(user) calls userDao.save(user)*/
    @Test
    public void save() throws Exception {
        service.save(user);
        verify(dao).save(user);
    }

}
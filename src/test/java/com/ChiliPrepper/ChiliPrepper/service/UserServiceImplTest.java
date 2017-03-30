package com.ChiliPrepper.ChiliPrepper.service;

import com.ChiliPrepper.ChiliPrepper.dao.CourseDao;
import com.ChiliPrepper.ChiliPrepper.dao.UserDao;
import com.ChiliPrepper.ChiliPrepper.model.Course;
import com.ChiliPrepper.ChiliPrepper.model.Role;
import com.ChiliPrepper.ChiliPrepper.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.theInstance;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by dagki on 15/03/2017.
 */

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {



    @Mock
    PasswordEncoder passwordEncoder;

    @Mock
    RoleService roleService = new RoleServiceImpl();

    @Mock
    User user;

    @Mock
    private UserDao dao;

    @InjectMocks
    private UserService service = new UserServiceImpl();

    @Test
    public void findByUsername() throws Exception {
        when(dao.findByUsername("Unit test")).thenReturn(new User());
        assertThat(service.findByUsername("Unit test"), instanceOf(User.class));
        verify(dao).findByUsername("Unit test");
    }

    @Test
    public void save() throws Exception {
        service.save(user);
        verify(dao).save(user);
    }

    @Test
    public void findOne()throws Exception  {
        Long userId = 1L;
        when(dao.findOne(userId)).thenReturn(user);
        assertThat(service.findOne(userId), is(user));
        verify(dao).findOne(userId);

    }

    @Test
    public void findAll()throws Exception  {
        List<User> userList = new ArrayList<>(Arrays.asList(user));
        when(dao.findAll()).thenReturn(userList);
        assertThat(service.findAll(), is(userList));
        verify(dao).findAll();
        //return userDao.findAll();
    }

    @Test(expected = UsernameNotFoundException.class)
    public void loadUserByUsername() throws Exception {
        String username = "username";
        when(dao.findByUsername(username)).thenReturn(null);
        service.loadUserByUsername(username);
        verify(dao).findByUsername(username);
    }

    @Test
    public void loadUserByUsewername() throws Exception {
        String username = "username";
        when(dao.findByUsername(username)).thenReturn(user);
        assertThat(service.loadUserByUsername(username), is(user));
        verify(dao).findByUsername(username);
    }




}
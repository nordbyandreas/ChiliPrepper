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
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.theInstance;
import static org.junit.Assert.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by dagki on 15/03/2017.
 */

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
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

    @Test //(expected = UsernameNotFoundException.class)
    public void loadUserByUsername() throws Exception {
        /*when(dao.findByUsername("Unit test")).thenReturn(null);
        service.findByUsername("Unit test");
        verify(dao).findByUsername("Unit test");*/
    }


}
package com.ChiliPrepper.ChiliPrepper.service;

import com.ChiliPrepper.ChiliPrepper.dao.CourseDao;
import com.ChiliPrepper.ChiliPrepper.dao.RoleDao;
import com.ChiliPrepper.ChiliPrepper.model.Course;
import com.ChiliPrepper.ChiliPrepper.model.Role;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static java.lang.Math.toIntExact;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by dagki on 15/03/2017.
 */

@RunWith(MockitoJUnitRunner.class)
public class RoleServiceTest {
    @Mock
    private RoleDao dao;

    @InjectMocks
    private RoleService service = new RoleServiceImpl();

    @Test
    public void findAll() throws Exception {
        List<Role> roles = Arrays.asList(
                new Role(),
                new Role()
        );
        Iterable<Role> roleIterable = roles;
        when(dao.findAll()).thenReturn(roles);
        assertThat(service.findAll(), is(roleIterable));
        verify(dao).findAll();
    }

    @Test
    public void findOne() throws Exception {
        when(dao.findOne(1L)).thenReturn(new Role());
        assertThat(service.findOne(1L), instanceOf(Role.class));
        verify(dao).findOne(1L);
    }

    @Test
    public void save() throws Exception {
        final Role role = new Role();
        service.save(role);
        verify(dao).save(role);
    }

}
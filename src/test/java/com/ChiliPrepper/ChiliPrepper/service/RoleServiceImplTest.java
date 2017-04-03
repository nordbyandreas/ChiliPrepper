package com.ChiliPrepper.ChiliPrepper.service;

import org.junit.Test;
import java.util.List;
import java.util.Arrays;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import com.ChiliPrepper.ChiliPrepper.model.Role;
import com.ChiliPrepper.ChiliPrepper.dao.RoleDao;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;

/**
 * Created by dagki on 15/03/2017.
 */

@RunWith(MockitoJUnitRunner.class)
public class RoleServiceImplTest {
    private Long roleId = 1L;
    private Role role = new Role();
    private List<Role> roleList = Arrays.asList(role);

    @Mock
    private RoleDao roleDao;

    @InjectMocks
    private RoleService roleService = new RoleServiceImpl();

    /**Confirms that roleService.findOne(roleId) returns roleDao.findOne(roleId)*/
    @Test
    public void findOne() throws Exception {
        when(roleDao.findOne(roleId)).thenReturn(role);
        assertThat(roleService.findOne(roleId), is(role));
        verify(roleDao).findOne(roleId);
    }

    /**Confirms that roleService.findAll() returns roleDao.findAll()*/
    @Test
    public void findAll() throws Exception {
        when(roleDao.findAll()).thenReturn(roleList);
        assertThat(roleService.findAll(), is(roleList));
        verify(roleDao).findAll();
    }

    /**Confirms that roleService.save(role) calls roleDao.save(role)*/
    @Test
    public void save() throws Exception {
        roleService.save(role);
        verify(roleDao).save(role);
    }

}
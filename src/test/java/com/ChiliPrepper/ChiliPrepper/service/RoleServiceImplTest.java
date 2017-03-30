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
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.hamcrest.Matchers.instanceOf;

/**
 * Created by dagki on 15/03/2017.
 */

@RunWith(MockitoJUnitRunner.class)
public class RoleServiceImplTest {

    @Mock
    private RoleDao roleDao;

    @InjectMocks
    private RoleService service = new RoleServiceImpl();

    //The findAll method returns roleDao.findAll() as an Iterable<Role> object, and should return two Course objects
    @Test
    public void findAll_ShouldReturnTwo() throws Exception {
        List<Role> roleList = Arrays.asList(new Role(), new Role());

        when(roleDao.findAll()).thenReturn(roleList);
        assertTrue("findAll should return an Iterable<Role> object containing two Role objects.", service.findAll().equals(roleList));
        verify(roleDao).findAll();
    }

    //The findOne method returns roleDao.findOne(Long roleId), and should return a Role object
    @Test
    public void findOne_ShouldReturnOne() throws Exception {
        Long roleId = 1L;

        when(roleDao.findOne(roleId)).thenReturn(new Role());
        assertThat("findOne with an roleId associated to a Course object should return it.", service.findOne(roleId), instanceOf(Role.class));
        verify(roleDao).findOne(roleId);
    }

    //The save method should call roleDao.save(Role role), which saves the Role object
    @Test
    public void save_ShouldSaveOne() throws Exception {
        service.save(new Role());
        verify(roleDao).save(any(Role.class));
    }
}
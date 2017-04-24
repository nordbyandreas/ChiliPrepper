package com.ChiliPrepper.ChiliPrepper.service;

import java.util.List;
import org.junit.Test;
import java.util.Arrays;
import org.mockito.Mock;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import org.mockito.runners.MockitoJUnitRunner;
import com.ChiliPrepper.ChiliPrepper.model.CourseUser;
import com.ChiliPrepper.ChiliPrepper.dao.CourseUserDao;
import org.mockito.InjectMocks;import org.junit.runner.RunWith;

/**
 * Created by Dag Kirstihagen on 24/04/2017.
 */

@RunWith(MockitoJUnitRunner.class)
public class CourseUserServiceImplTest {
    Long userId = 1L;
    CourseUser courseUser = new CourseUser();
    private List<CourseUser> courseUserList = Arrays.asList(courseUser);


    @Mock
    CourseUserDao courseUserDao;

    @InjectMocks
    CourseUserServiceImpl courseUserService = new CourseUserServiceImpl();

    /**Confirms that courseUserService.save(courseUser) calls courseUserDao.save(courseUser)*/
    @Test
    public void save() throws Exception {
        courseUserService.save(courseUser);
        verify(courseUserDao).save(courseUser);
    }

    /**Confirms that courseUserService.findAllByUser_id(userId) calls courseUserDao.findAllByUser_id(userId)*/
    @Test
    public void findAllByUser_id() throws Exception {
        when(courseUserDao.findAllByUser_id(userId)).thenReturn(courseUserList);
        assertThat(courseUserService.findAllByUser_id(userId), is(courseUserList));
        verify(courseUserDao).findAllByUser_id(userId);
    }

}
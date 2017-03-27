package com.ChiliPrepper.ChiliPrepper.service;

import com.ChiliPrepper.ChiliPrepper.dao.RoleDao;
import com.ChiliPrepper.ChiliPrepper.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Andreas on 17.02.2017.
 */

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public Iterable<Role> findAll() { return roleDao.findAll(); }

    @Override
    public Role findOne(Long id) { return roleDao.findOne(id); }

    @Override
    public void save(Role role) { roleDao.save(role); }
}
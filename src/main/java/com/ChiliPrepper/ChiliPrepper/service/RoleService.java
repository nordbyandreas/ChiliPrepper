package com.ChiliPrepper.ChiliPrepper.service;

import com.ChiliPrepper.ChiliPrepper.model.Role;

/**
 * Created by Andreas on 17.02.2017.
 */
public interface RoleService {

    Iterable<Role> findAll();
    Role findOne(Long id);
    void save(Role role);
}

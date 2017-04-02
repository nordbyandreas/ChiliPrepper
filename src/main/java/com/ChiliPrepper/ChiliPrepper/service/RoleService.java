package com.ChiliPrepper.ChiliPrepper.service;

import com.ChiliPrepper.ChiliPrepper.model.Role;

/**
 * Created by Andreas on 17.02.2017.
 *
 *
 *  * Interface for the Service Layer for Roles
 *
 * Look at the Implementation for method descriptions
 *
 * All methods to be used from the DAO layer must be included here
 */
public interface RoleService {

    Iterable<Role> findAll();
    Role findOne(Long id);
    void save(Role role);
}

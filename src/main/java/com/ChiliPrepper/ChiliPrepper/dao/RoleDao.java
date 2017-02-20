package com.ChiliPrepper.ChiliPrepper.dao;

import com.ChiliPrepper.ChiliPrepper.model.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Andreas on 17.02.2017.
 */

//data access object for ROLES

@Repository
public interface RoleDao extends CrudRepository<Role, Long>{


}

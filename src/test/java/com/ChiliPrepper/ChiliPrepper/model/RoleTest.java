package com.ChiliPrepper.ChiliPrepper.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by dagki on 28/02/2017.
 */
public class RoleTest {
    private Role role;

    @Before
    public void setUp() throws Exception {
        role = new Role();
    }

    @Test
    public void roleId() throws Exception {
        Long roleId = 10L;
        assertNull("Role ID should be null before a value is assigned", role.getId());
        role.setId(roleId);
        assertEquals("Role ID users should be set to the assigned value", roleId, role.getId());
    }

    @Test
    public void roleName() throws Exception {
        String roleName = "participant";
        assertNull("Role name users should be null before a value is assigned", role.getName());
        role.setName(roleName);
        assertEquals("Role name users should be set to the assigned value", roleName, role.getName());
    }

}
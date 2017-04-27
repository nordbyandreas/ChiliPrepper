package com.ChiliPrepper.ChiliPrepper.model;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

/**
 * Created by Dag Kirstihagen on 28/02/2017.
 */

public class RoleTest {
    private Role role;

    @Before
    public void setUp() throws Exception {
        role = new Role();
    }

    /**First confirms that the role's ID ain't assigned,
     * then assigns the ID
     * and concludes by confirming that the ID is assigned to the role.*/
    @Test
    public void getAndSetRoleId() throws Exception {
        Long id = 1L;
        assertNull("The role's ID ain't assigned, and should return: null", role.getId());
        role.setId(id);
        assertEquals("The role's ID is assigned, and should return: 1L (Long)", id, role.getId());
    }

    /**First confirms that the role's name ain't assigned,
     * then assigns the name
     * and concludes by confirming that the name is assigned to the role.*/
    @Test
    public void roleName() throws Exception {
        String roleName = "roleName";
        assertNull("The role's name ain't assigned, and should return: null", role.getName());
        role.setName(roleName);
        assertEquals("The role's name is assigned, and should return: 'roleName' (String)", roleName, role.getName());
    }

}
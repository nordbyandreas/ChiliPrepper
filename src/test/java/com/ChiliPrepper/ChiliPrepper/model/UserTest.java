package com.ChiliPrepper.ChiliPrepper.model;

import org.junit.Before;
import org.junit.Test;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.*;

import static org.junit.Assert.*;

/**
 * Created by dagki on 28/02/2017.
 */
public class UserTest {
    private Role role;
    private User user;
    private Course courseOne;
    private Course courseTwo;

    @Before
    public void setUp() throws Exception {
        role = new Role();
        user = new User();
        courseOne = new Course();
        courseTwo = new Course();
    }

    @Test
    public void userUsername() throws Exception {
        String username = "William Hansen";
        assertNull("The user's username should be null before a value is assigned", user.getUsername());
        user.setUsername(username);
        assertEquals("The user's username should be set to the assigned value", username, user.getUsername());
    }

    @Test
    public void userPassword() throws Exception {
        String password = "Password";
        assertNull("The user's password should be null before a value is assigned", user.getPassword());
        user.setPassword(password);
        assertEquals("The user's password should be set to the assigned value", password, user.getPassword());
    }

    @Test
    public void userId() throws Exception {
        Long userId = 10L;
        assertNull("The user's ID should be null before a value is assigned", user.getId());
        user.setId(userId);
        assertEquals("The user's ID should be set to the assigned value", userId, user.getId());
    }

    @Test
    public void userEmail() throws Exception {
        String email = "me@domain.com";
        assertNull("The user's email should be null before a value is assigned", user.getEmail());
        user.setEmail(email);
        assertEquals("The user's email should be set to the assigned value", email, user.getEmail());
    }

    @Test
    public void userRegisteredInCourses() throws Exception {
        Set<Course> regCourses = new HashSet<>(Arrays.asList(courseOne, courseTwo));
        assertNull("The user's registered courses should be null before a value is assigned", user.getRegCourses());
        user.setRegCourses(regCourses);
        assertEquals("The user's registered courses should be set to the assigned value", regCourses, user.getRegCourses());
    }

    @Test
    public void userRole() throws Exception {
        assertNull("The user's role should be null before a value is assigned", user.getRole());
        user.setRole(role);
        assertEquals("The user's role should be set to the assigned value", role, user.getRole());
    }

    @Test
    public void getAuthorities() throws Exception {

    }

    @Test
    public void userEnabled() throws Exception {
        assertFalse("The user's account shouldn't be enabled as default", user.isEnabled());
        user.setEnabled(true);
        assertTrue("The user's account should be enabled", user.isEnabled());
    }

    @Test
    public void userAccountNonExpired() throws Exception {
        assertTrue("The user's account shouldn't be expired as default", user.isAccountNonExpired());
    }

    @Test
    public void userAccountNonLocked() throws Exception {
        assertTrue("The user's account shouldn't be locked as default", user.isAccountNonLocked());
    }

    @Test
    public void userCredentialsNonExpired() throws Exception {
        assertTrue("The user's credentials shouldn't be expired as default", user.isCredentialsNonExpired());
    }

}
package com.ChiliPrepper.ChiliPrepper.model;

import org.junit.Before;
import org.junit.Test;
import org.springframework.security.core.Authentication;
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
    public void assignUserUsername() throws Exception {
        String username = "username";
        assertNull("The user's username should be null before a value is assigned", user.getUsername());
        user.setUsername(username);
        assertEquals("The user's username should be set to the assigned value", username, user.getUsername());
    }

    @Test
    public void assignUserPassword() throws Exception {
        String password = "Password";
        assertNull("The user's password should be null before a value is assigned", user.getPassword());
        user.setPassword(password);
        assertEquals("The user's password should be set to the assigned value", password, user.getPassword());
    }

    @Test
    public void assignUserId() throws Exception {
        Long userId = 10L;
        assertNull("The user's ID should be null before a value is assigned", user.getId());
        user.setId(userId);
    }

    @Test
    public void assignUserToCourses() throws Exception {
        Set<Course> regCourses = new HashSet<>(Arrays.asList(courseOne, courseTwo));
        assertNull("The user's registered courses should be null before a value is assigned", user.getRegCourses());
        user.setRegCourses(regCourses);
        assertEquals("The user's registered courses should be set to the assigned value", regCourses, user.getRegCourses());
    }

    @Test
    public void assignUserRole() throws Exception {
        List<GrantedAuthority> authorities = new ArrayList<>();
        role.setName("creator");
        assertNull("The user's role should be null before a value is assigned", user.getRole());
        user.setRole(role);
        assertEquals("The user's role should be set to the assigned value", role, user.getRole());
        authorities.add(new SimpleGrantedAuthority(user.getRole().getName()));
        assertEquals(authorities, user.getAuthorities());

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
    @Test
    public void userCredentialsNonExre3434ired() throws Exception {
        assertFalse(user.isCreatorQuizResults());
        user.setCreatorQuizResults(true);
        assertTrue(user.isCreatorQuizResults());
    }
    @Test
    public void userCredentialsNonE343wxpired() throws Exception {
        assertFalse(user.isCreatorCourseUpdate());
        user.setCreatorCourseUpdate(true);
        assertTrue(user.isCreatorCourseUpdate());
    }
    @Test
    public void wr() throws Exception {
        assertFalse(user.isParticipantQuizResults());
        user.setParticipantQuizResults(true);
        assertTrue(user.isParticipantQuizResults());

    }
    @Test
    public void rwr34() throws Exception {
        assertFalse(user.isParticipantTopicUpdate());
        user.setParticipantTopicUpdate(true);
        assertTrue(user.isParticipantTopicUpdate());
    }


}
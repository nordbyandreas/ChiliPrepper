package com.ChiliPrepper.ChiliPrepper.model;

import org.junit.Test;
import org.junit.Before;
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
    private Course course;

    @Before
    public void setUp() throws Exception {
        role = new Role();
        user = new User();
        course = new Course();
    }

    /**First confirms that the user's email ain't assigned,
     * then assigns the email
     * and concludes by confirming that the email is assigned to the user.*/
    @Test
    public void getAndSetEmail() throws Exception {
        String email = "username@domain.com";
        assertNull("The user's email ain't assigned, and should return: null", user.getEmail());
        user.setEmail(email);
        assertEquals("The user's email is assigned, and should return: email (String)", email, user.getEmail());
    }

    /**First confirms that the user's username ain't assigned,
     * then assigns the username
     * and concludes by confirming that the username is assigned to the user.*/
    @Test
    public void getAndSetUsername() throws Exception {
        String username = "username";
        assertNull("The user's username ain't assigned, and should return: null", user.getUsername());
        user.setUsername(username);
        assertEquals("The user's username is assigned, and should return: username (String)", username, user.getUsername());
    }

    /**First confirms that the user's password ain't assigned,
     * then assigns the password
     * and concludes by confirming that the password is assigned to the user.*/
    @Test
    public void getAndSetPassword() throws Exception {
        String password = "password";
        assertNull("The user's password ain't assigned, and should return: null", user.getPassword());
        user.setPassword(password);
        assertEquals("The user's password is assigned, and should return: password (String)", password, user.getPassword());
    }

    /**First confirms that the user's ID ain't assigned,
     * then assigns the user's ID
     * and concludes by confirming that the ID is assigned to the user.*/
    @Test
    public void getAndSetId() throws Exception {
        Long id = 1L;
        assertNull("The user's id ain't assigned, and should return: null", user.getId());
        user.setId(id);
        assertEquals("The user's id is assigned, and should return: 1L (Long)", id, user.getId());
    }

    /**First confirms that the user's registered courses ain't assigned,
     * then assigns the registered courses
     * and concludes by confirming that the registered courses is assigned to the user.*/
    @Test
    public void getAndSetRegCourses() throws Exception {
        Set<Course> regCourses = new HashSet<>(Arrays.asList(course));
        assertNull("The user's password ain't assigned, and should return: null", user.getRegCourses());
        user.setRegCourses(regCourses);
        assertEquals("The user's registered courses is assigned, and should return: [course] (Set<Course>)", regCourses, user.getRegCourses());
    }

    /**First confirms that the user ain't assigned to a role,
     * then assigns it to a role
     * and concludes by confirming that the user is assigned to the role.*/
    @Test
    public void getAndSetRoleAndGetAuthorities() throws Exception {
        role.setName("role");
        assertNull("The user's role should be null before a value is assigned", user.getRole());
        user.setRole(role);
        assertEquals("The user's role should be set to the assigned value", role, user.getRole());
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole().getName()));
        assertEquals(authorities, user.getAuthorities());
    }

    /**First confirms that the user ain't enabled,
     * then sets the user to enabled
     * and concludes by confirming that the user is enabled.*/
    @Test
    public void isAndSetEnabled() throws Exception {
        assertFalse("The user's account ain't enabled, and should return: false (Boolean)", user.isEnabled());
        user.setEnabled(true);
        assertTrue("The user's account is assigned, and should return: true (Boolean)", user.isEnabled());
    }

    /**Confirms that the user's account ain't expired as default
     * */
    @Test
    public void isAccountNonExpired() throws Exception {
        assertTrue("The user's account ain't expired, and should return: true (Boolean)", user.isAccountNonExpired());
    }

    /**Confirms that the user's account ain't locked as default
     * */
    @Test
    public void isAccountNonLocked() throws Exception {
        assertTrue("The user's account ain't locked, and should return: true (Boolean)", user.isAccountNonLocked());
    }

    /**Confirms that the user's credentials ain't expired as default
     * */
    @Test
    public void isCredentialsNonExpired() throws Exception {
        assertTrue("The user's credentials ain't expired, and should return: null", user.isCredentialsNonExpired());
    }

    /**First confirms that the creator's mail preference regarding quiz results is set off,
     * then sets it to true
     * and concludes by confirming that the creator's mail preference regarding topic results is on.*/
    @Test
    public void isAndSetCreatorQuizResults() throws Exception {
        assertFalse("The creator's preferences regarding mail update for quiz results ain't set, and should return: false (Boolean)", user.isCreatorQuizResults());
        user.setCreatorQuizResults(true);
        assertTrue("The creator's preferences regarding mail update for quiz results is set to true, and should return: true (Boolean)", user.isCreatorQuizResults());
    }

    /**First confirms that the creator's mail preference regarding course results is set off,
     * then sets it to true
     * and concludes by confirming that the creator's mail preference regarding course results is on.*/
    @Test
    public void isAndSetCreatorCourseUpdate() throws Exception {
        assertFalse("The participant's preferences regarding mail update for courses ain't set, and should return: false (Boolean)", user.isCreatorCourseUpdate());
        user.setCreatorCourseUpdate(true);
        assertTrue("The participant's preferences regarding mail update for courses is set to true, and should return: true (Boolean)", user.isCreatorCourseUpdate());
    }

    /**First confirms that the participant's mail preference regarding quiz results is set off,
     * then sets it to true
     * and concludes by confirming that the participant's mail preference regarding quiz results is on.*/
    @Test
    public void isAndSetParticipantQuizResults() throws Exception {
        assertFalse("The participant's preferences regarding mail update for quiz results ain't set, and should return: false (Boolean)", user.isParticipantQuizResults());
        user.setParticipantQuizResults(true);
        assertTrue("The participant's preferences regarding mail update for quiz results is set to true, and should return: true (Boolean)", user.isParticipantQuizResults());

    }

    /**First confirms that the participant's mail preference regarding topic results is set off,
     * then sets it to true
     * and concludes by confirming that the participant's mail preference regarding topic results is on.*/
    @Test
    public void isAndSetParticipantTopicUpdate() throws Exception {
        assertFalse("The participant's preferences regarding mail update for topics ain't set, and should return: false (Boolean)", user.isParticipantTopicUpdate());
        user.setParticipantTopicUpdate(true);
        assertTrue("The participant's preferences regarding mail update for topics is set to true, and should return: true (Boolean)", user.isParticipantTopicUpdate());
    }

}
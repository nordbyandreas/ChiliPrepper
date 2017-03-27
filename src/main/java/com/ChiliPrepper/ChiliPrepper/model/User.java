package com.ChiliPrepper.ChiliPrepper.model;

/**
 * Created by Andreas on 15.02.2017.
 */
import org.hibernate.validator.constraints.Email;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity                                //Mark the class as an entity, so a schema will be created in the database
public class User implements UserDetails {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)    //automaticly set unique ID
    @Column(name= "user_id")
    private Long id;

    @Column(unique = true)
    @Size(min = 1, max = 20)
    private String username;

    @Column(length = 100)
    private String password;


    // @Column(unique = true, nullable = false)  removed uniqe for testing of mail sending
    @Column(nullable = false)
    @Email
    private String email;

    @Column(nullable = false)
    private boolean enabled;

    @OneToOne
    @JoinColumn(name = "role_id")
    private Role role;


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name= "course_user", joinColumns = @JoinColumn(name="user_id"),
            inverseJoinColumns = @JoinColumn(name="course_id"))
    private Set<Course> regCourses;


    //TODO: når vi lager disse må vi
    // TODO              -oppdatere "import.sql"
    //TODO:              -sette disse feltene under registration !
    //TODO:              -

    /*
    @Column(name= "creator_quiz_results")
    private boolean creatorQuizResults;

    @Column(name= "creator_course_update")
    private boolean creatorCourseUpdate;

    @Column(name= "participant_quiz_results")
    private boolean participantQuizResults;

    @Column(name= "participant_topic_update")
    private boolean participantTopicUpdate;

*/



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role.getName()));
        return authorities;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


//Getters and Setters

    public Set<Course> getRegCourses() {
        return regCourses;
    }

    public void setRegCourses(Set<Course> regCourses) {
        this.regCourses = regCourses;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

/*
    public boolean isCreatorQuizResults() {
        return creatorQuizResults;
    }

    public void setCreatorQuizResults(boolean creatorQuizResults) {
        this.creatorQuizResults = creatorQuizResults;
    }

    public boolean isCreatorCourseUpdate() {
        return creatorCourseUpdate;
    }

    public void setCreatorCourseUpdate(boolean creatorCourseUpdate) {
        this.creatorCourseUpdate = creatorCourseUpdate;
    }

    public boolean isParticipantQuizResults() {
        return participantQuizResults;
    }

    public void setParticipantQuizResults(boolean participantQuizResults) {
        this.participantQuizResults = participantQuizResults;
    }

    public boolean isParticipantTopicUpdate() {
        return participantTopicUpdate;
    }

    public void setParticipantTopicUpdate(boolean participantTopicUpdate) {
        this.participantTopicUpdate = participantTopicUpdate;
    }

    */

}

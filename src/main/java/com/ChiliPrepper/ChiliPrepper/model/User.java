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


    public static class UserBuilder{
        private Long id;
        private String username;
        private String password;
        private String email;
        private boolean enabled;
        private Role role;
        private Set<Course> regCourses;

        public UserBuilder(Long id){
            this.id = id;
        }

        public UserBuilder(){

        }

        public UserBuilder withId(Long id){
            this.id = id;
            return this;
        }

        public UserBuilder withUsername(String username){
            this.username = username;
            return this;
        }

        public UserBuilder withPassword(String password){
            this.password = password;
            return this;
        }

        public UserBuilder withEmail(String email){
            this.email = email;
            return this;
        }

        public UserBuilder withEnabled(boolean enabled){
            this.enabled = enabled;
            return this;
        }

        public UserBuilder withRole(Role role){
            this.role = role;
            return this;
        }

        public UserBuilder withRegCourses(Set<Course> regCourses){
            this.regCourses = regCourses;
            return this;
        }

        public User build(){
            User user = new User();
            user.setId(id);
            user.setUsername(username);
            user.setPassword(password);
            user.setEmail(email);
            user.setEnabled(enabled);
            user.setRole(role);
            user.setRegCourses(regCourses);
            return user;
        }

    }



}

package com.ChiliPrepper.ChiliPrepper.config;




import com.ChiliPrepper.ChiliPrepper.service.UserService;
import com.ChiliPrepper.ChiliPrepper.web.FlashMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.repository.query.spi.EvaluationContextExtension;
import org.springframework.data.repository.query.spi.EvaluationContextExtensionSupport;
import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;



/**
 * Created by Andreas on 15.02.2017.
 *
 *
 * Configuration class for security
 *
 *
 *
 */
@Configuration
@EnableWebSecurity
@ComponentScan(basePackages = "com.ChiliPrepper.ChiliPrepper")
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private UserService userService;


    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }




    /**
     *
     * Creates a PasswordEncoder Bean
     *
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }   //set password enctyption



    /**
     *
     * Configuration class for websecurity
     *
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/static/**");    //allow user access to static resources (CSS) before log in

    }

    /**
     *
     * Configuration class for websecurity, authentication and authorization
     * Login and logout
     *
     *
     * @param http
     * @throws Exception
     */
    /**
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/register", "/img/LogoLiten.png", "/css/**").permitAll()  //allows all users to access the register-form
                .anyRequest().authenticated()   //hasRole("USER")  somewhere here?
                .and()
                .formLogin()
                .loginPage("/login")  //allows all users access to the login form
                .permitAll()
                .successHandler(loginSuccessHandler())
                .failureHandler(loginFailureHandler())
                .and()
                .logout().invalidateHttpSession(true).deleteCookies("JSESSIONID")
                .permitAll()
                .logoutSuccessUrl("/login")
                .and()
                .csrf();
    }
**/

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/register", "/img/LogoLiten.png", "/css/**").permitAll()  //allows all users to access the register-form
                .anyRequest().authenticated()   //hasRole("USER")  somewhere here?
                .and()
                    .formLogin()
                    .loginPage("/login")  //allows all users access to the login form
                    .permitAll()
                    .successHandler(loginSuccessHandler())
                    .failureHandler(loginFailureHandler())
                .and()
                .logout()
                    //.logoutSuccessHandler(logoutSuccessHandler)
                    .invalidateHttpSession(true)
                    //.addLogoutHandler(logoutHandler)
                    .deleteCookies("JSESSIONID")
                    .clearAuthentication(true)

                .permitAll()
                .logoutSuccessUrl("/login")
                .and()
                .csrf();
    }



    /**
     *
     * Redirects to index page after successful login
     *
     * @return request, response, authentication
     */
    public AuthenticationSuccessHandler loginSuccessHandler() {  //redirect to index when successfull login
        return (request, response, authentication) -> response.sendRedirect("/");
    }




    /**
     *
     * Redirects to log in page if not successful log in
     *
     * @return request, response, exception
     */
    public AuthenticationFailureHandler loginFailureHandler() {  //redirect to login when log in failed, and send flash message
        return (request, response, exception) -> {
            request.getSession().setAttribute("flash", new FlashMessage("Incorrect username and/or password. Please try again.", FlashMessage.Status.FAILURE));
            response.sendRedirect("/login");
        };
    }


    /**
     *
     * Creates EvaluationContextExtension Bean
     *
     * @return EvaluationContextExtension
     */
    @Bean
    public EvaluationContextExtension securityExtension() {
        return new EvaluationContextExtensionSupport() {
            @Override
            public String getExtensionId() {
                return "security";
            }

            @Override
            public Object getRootObject() {
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                return new SecurityExpressionRoot(authentication) {};
            }
        };
    }
}
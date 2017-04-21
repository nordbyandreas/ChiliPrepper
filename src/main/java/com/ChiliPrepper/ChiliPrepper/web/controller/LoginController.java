package com.ChiliPrepper.ChiliPrepper.web.controller;

import org.springframework.ui.Model;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.ChiliPrepper.ChiliPrepper.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

/**
 * Created by Andreas on 15.02.2017.
 *
 * The @Controller annotation lets Spring know that this is a controller.
 *
 * Controller classes handles URI requests from the browser with methods marked with the
 * @RequestMapping annotation.
 *
 * These methods return a String with the name of which HTML file to render from the
 * templates directory. Various objects or variables may be added to, or read from, the model.
 * (adding something to the model is like adding something to that particular HTML file rendering).
 *
 * This is the class that handles the login and logout of ChiliPrepper
 */

@Controller
public class LoginController {

    /**
     * Renders the login page
     *
     * When not logged in, all requests will be redirected here.
     *
     * If the user is successfully logs in, the user will be redirected to the index page
     *
     * @param model   model, to which attributes may be added
     * @param request
     * @return returns the String which points to the correct HTML file
     */
    @RequestMapping(path = "/login", method = RequestMethod.GET)
    public String renderLoginView(Model model, HttpServletRequest request) {

        model.addAttribute("user", new User());

        try {
            Object flash = request.getSession().getAttribute("flash");
            model.addAttribute("flash", flash);
            request.getSession().removeAttribute("flash");
        }
        catch (Exception ex) {
            // "flash" session attribute must not exist...do nothing and proceed normally
        }

        return "login";
    }

    /**
     * Returns the access_denied page
     * (currently not in use)
     *
     * @return a String pointing to correct HTML file
     */
    @RequestMapping("/access_denied")
    public String renderAccessDeniedView() {

        return "access_denied";
    }

    /**
     * Logs a user out
     *
     * @param request
     * @param response
     * @return returns a            redirect to the login page HTML
     */
    @RequestMapping(path = "/logout", method = RequestMethod.GET)
    public String logOut(HttpServletRequest request, HttpServletResponse response){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if(auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }

        return "redirect:/login";
    }
}
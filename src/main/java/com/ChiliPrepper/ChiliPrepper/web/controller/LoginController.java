package com.ChiliPrepper.ChiliPrepper.web.controller;

/**
 * Created by Andreas on 15.02.2017.
 */
import com.ChiliPrepper.ChiliPrepper.model.User;
import com.ChiliPrepper.ChiliPrepper.service.CourseService;
import com.ChiliPrepper.ChiliPrepper.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.servlet.http.HttpServletRequest;



@Controller                   //marks class as a controller
public class LoginController {



    @RequestMapping(path = "/login", method = RequestMethod.GET)
    public String loginForm(Model model, HttpServletRequest request) {
        model.addAttribute("user", new User());
        try {
            Object flash = request.getSession().getAttribute("flash");
            model.addAttribute("flash", flash);
            request.getSession().removeAttribute("flash");
        } catch (Exception ex) {
            // "flash" session attribute must not exist...do nothing and proceed normally
        }
        return "login";
    }


    //not in use
    @RequestMapping("/access_denied")
    public String accessDenied() {
        return "access_denied";
    }




}
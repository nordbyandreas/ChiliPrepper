package com.ChiliPrepper.ChiliPrepper.web.controller;

/**
 * Created by Andreas on 15.02.2017.
 */
import com.ChiliPrepper.ChiliPrepper.model.Course;
import com.ChiliPrepper.ChiliPrepper.model.User;
import com.ChiliPrepper.ChiliPrepper.service.CourseService;
import com.ChiliPrepper.ChiliPrepper.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private CourseService courseService;


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

    @RequestMapping("/access_denied")
    public String accessDenied() {
        return "access_denied";
    }


    @RequestMapping("/")
    public String index(Model model) {

        Iterable<Course> courses = courseService.findAll();
        model.addAttribute("courses", courses);
        model.addAttribute("course", new Course());


        return "index";
    }

    @RequestMapping(path = "/addCourse", method = RequestMethod.POST)
    public String addCourse(@ModelAttribute Course course, Principal principal) {
        User user = (User)((UsernamePasswordAuthenticationToken)principal).getPrincipal();
        course.setCreator(user);
        courseService.save(course);
        return "redirect:/";
    }

}
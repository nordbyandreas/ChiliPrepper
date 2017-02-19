package com.ChiliPrepper.ChiliPrepper.web.controller;

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
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.Set;

/**
 * Created by Andreas on 19.02.2017.
 */

@Controller
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private UserService userService;


    @RequestMapping("/")
    public String index(Model model, Principal principal) {

        Iterable<Course> myCourses = courseService.findAll();
        model.addAttribute("myCourses", myCourses);

        User user = (User)((UsernamePasswordAuthenticationToken)principal).getPrincipal();
        user = userService.findByUsername(user.getUsername());

        Set<Course> regCourses = user.getRegCourses();

        //add courses user are registered in to the model
        model.addAttribute("regCourses", regCourses);

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

    @RequestMapping(path = "/regCourse", method = RequestMethod.POST)
    public String regCourse (Principal principal, @RequestParam String accessCode){
        User user = (User)((UsernamePasswordAuthenticationToken)principal).getPrincipal();

        user = userService.findByUsername(user.getUsername());

        Course course = courseService.findByAccessCode(accessCode);

        Set<User> regUsers = course.getRegUsers();
        regUsers.add(user);

        Set<Course> regCourses = user.getRegCourses();
        regCourses.add(course);

        course.setRegUsers(regUsers);
        user.setRegCourses(regCourses);

        userService.save(user);
        courseService.save(course);


        return "redirect:/";
    }


}

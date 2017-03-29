package com.ChiliPrepper.ChiliPrepper.web.controller;

import com.ChiliPrepper.ChiliPrepper.model.User;
import com.ChiliPrepper.ChiliPrepper.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

/**
 * Created by Andreas on 20.02.2017.
 */

@Controller
public class ProfileController {


    @Autowired
    private UserService userService;


    @RequestMapping("/profile")
    public String profile(Model model, Principal principal){
        String username = principal.getName();
        User user = userService.findByUsername(username);
        model.addAttribute("user", user);
        return "profile";
    }

    @RequestMapping("/about")
    public String about(Model model){
        return "about";
    }

    @RequestMapping(value = "/saveBotDetails", method = RequestMethod.POST)
    public String saveBotDetails(Principal principal, @ModelAttribute User user){

        System.out.println(user.getUsername());
        System.out.println(user.getEmail());

        userService.save(user);

        return "redirect:/profile";
    }


}

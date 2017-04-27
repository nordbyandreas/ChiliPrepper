package com.ChiliPrepper.ChiliPrepper.web.controller;

import java.security.Principal;
import org.springframework.ui.Model;
import com.ChiliPrepper.ChiliPrepper.model.User;
import org.springframework.stereotype.Controller;
import com.ChiliPrepper.ChiliPrepper.web.FlashMessage;
import com.ChiliPrepper.ChiliPrepper.service.UserService;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Created by Andreas on 20.02.2017.
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
 * This class renders the profile and about pages, and also lets you save you're "BOT"-preferences
 */
@Controller
public class ProfileController {

    @Autowired
    private UserService userService;

    /**
     * Renders the profile page
     *
     * @param model  model to add attributes
     * @param principal uses the principal from the session to get the logged in user
     * @return String pointing to the profile.html
     */
    @RequestMapping("/profile")
    public String renderProfileView(Model model, Principal principal){

        String username = principal.getName();
        User user = userService.findByUsername(username);

        model.addAttribute("user", user);

        return "profile";
    }

    /**
     * Renders the about page
     *
     * @return  String pointing to the correct HTML
     */
    @RequestMapping("/about")
    public String renderAboutView(){

        return "about";
    }

    @RequestMapping(value = "/saveBotDetails", method = RequestMethod.POST)
    public String saveBotPreferences(@ModelAttribute User user, RedirectAttributes redirectAttributes){

        userService.save(user);

        String message = "Bot preferences saved!";
        redirectAttributes.addFlashAttribute("flash", new FlashMessage(message, FlashMessage.Status.SUCCESS));

        return "redirect:/profile";
    }
}
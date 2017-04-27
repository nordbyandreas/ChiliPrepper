package com.ChiliPrepper.ChiliPrepper.web.controller;

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
 * Created by Andreas on 17.02.2017.
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
 * This is the class that handles the registration of new Users of ChiliPrepper
 */

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;






    /**
     * Renders the registration page.
     *
     * Adds a new empty user to the model.
     *
     * @param model  the model to be added to the page
     * @return  Returns the String "registration" which links to the respective HTML file.
     */
    @RequestMapping(path = "/register", method = RequestMethod.GET)
    public String renderRegistrationFormView(Model model) {

        model.addAttribute("user", new User());

        return "registration";
    }







    /**
     * Saves the new user to the DB and redirects to the login page
     *
     * @param user Gets the user object from the model
     * @param redirectAttributes
     * @return Returns a String which points to the correct HTML file to be rendered
     */
    @RequestMapping(path = "/register", method = RequestMethod.POST)
    public String registerUser(@ModelAttribute User user, RedirectAttributes redirectAttributes) {

        boolean emailNotEntered = user.getEmail().isEmpty();
        boolean usernameAlreadyTaken = userService.findByUsername(user.getUsername()) != null;

        if(emailNotEntered){
            String message = "Registration failed! You must include a correct email!";
            redirectAttributes.addFlashAttribute("flash", new FlashMessage(message, FlashMessage.Status.FAILURE));
            return "redirect:/register";
        }
        else if(usernameAlreadyTaken){
            String message = "Registration failed! The username is already taken!";
            redirectAttributes.addFlashAttribute("flash", new FlashMessage(message, FlashMessage.Status.FAILURE));
            return "redirect:/register";
        }
        else if(user.getPassword().length() < 6){
            String message = "Registration failed! Password must be at least 6 characters.";
            redirectAttributes.addFlashAttribute("flash", new FlashMessage(message, FlashMessage.Status.FAILURE));
            return "redirect:/register";
        }


        //Setting for whether the bot should contact the user
        user.setCreatorQuizResults(false);
        user.setCreatorCourseUpdate(false);

        user.setParticipantTopicUpdate(false);
        user.setParticipantQuizResults(false);
        userService.save(user);

        String message = "Success!  Welcome to ChiliPrepper, log in to start !";
        redirectAttributes.addFlashAttribute("flash", new FlashMessage(message, FlashMessage.Status.SUCCESS));

        return "redirect:/login";
    }
}
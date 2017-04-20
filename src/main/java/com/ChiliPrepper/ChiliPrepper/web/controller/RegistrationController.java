package com.ChiliPrepper.ChiliPrepper.web.controller;

import com.ChiliPrepper.ChiliPrepper.model.User;
import com.ChiliPrepper.ChiliPrepper.service.UserService;
import com.ChiliPrepper.ChiliPrepper.web.FlashMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
 *
 * This is the class that handles the registration of new Users of ChiliPrepper
 *
 *
 *
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
     *
     * @param model  the model to be added to the page
     * @return  Returns the String "registration" which links to the respective HTML file.
     */
    @RequestMapping(path = "/register", method = RequestMethod.GET)
    public String RegForm(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }


    /**
     * Saves the new user to the DB and redirects to the login page
     *
     * @param user   Gets the user object from the model
     * @param redirectAttributes
     * @return  Returns a String which points to the correct HTML file to be rendered
     */
    @RequestMapping(path = "/register", method = RequestMethod.POST)
    public String RegUser(@ModelAttribute User user, RedirectAttributes redirectAttributes) {
        if(user.getEmail().isEmpty() || user.getUsername().isEmpty()  || user.getPassword().isEmpty()){
            redirectAttributes.addFlashAttribute("flash",new FlashMessage("Registration failed! All fields must be filled out!", FlashMessage.Status.FAILURE));
            return "redirect:/register";
        }
        else if(userService.findByUsername(user.getUsername()) != null){
            redirectAttributes.addFlashAttribute("flash",new FlashMessage("Registration failed! That username is taken!", FlashMessage.Status.FAILURE));
            return "redirect:/register";
        }
        user.setCreatorCourseUpdate(false);
        user.setCreatorQuizResults(false);
        user.setParticipantTopicUpdate(false);
        user.setParticipantQuizResults(false);
        userService.save(user);
        redirectAttributes.addFlashAttribute("flash",new FlashMessage("Registration success! Return to login page to get started !", FlashMessage.Status.SUCCESS));
        return "redirect:/register";
    }

}

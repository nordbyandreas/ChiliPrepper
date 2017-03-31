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
 */

//marks class as a controller
@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @RequestMapping(path = "/register", method = RequestMethod.GET)
    public String RegForm(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }

    @RequestMapping(path = "/register", method = RequestMethod.POST)
    public String RegUser(@ModelAttribute User user, RedirectAttributes redirectAttributes) {
        user.setCreatorCourseUpdate(true);
        user.setCreatorQuizResults(true);
        user.setParticipantTopicUpdate(true);
        user.setParticipantQuizResults(true);
        userService.save(user);
        //TODO: add flashmessage for success or failure of registration
        return "redirect:/login";
    }

}

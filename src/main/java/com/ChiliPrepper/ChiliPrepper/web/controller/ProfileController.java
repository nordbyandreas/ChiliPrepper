package com.ChiliPrepper.ChiliPrepper.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Andreas on 20.02.2017.
 */

@Controller
public class ProfileController {

    @RequestMapping("/profile")
    public String profile(Model model){
        return "profile";
    }

    @RequestMapping("/about")
    public String about(Model model){
        return "about";
    }

}

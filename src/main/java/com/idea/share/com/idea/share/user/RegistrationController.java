package com.idea.share.com.idea.share.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RegistrationController {

    @GetMapping("/register")
    String getRegistrationPage(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }

}

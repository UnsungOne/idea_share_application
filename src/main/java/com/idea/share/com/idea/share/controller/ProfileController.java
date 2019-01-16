package com.idea.share.com.idea.share.controller;

import com.idea.share.com.idea.share.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfileController {

    @GetMapping("/profile")
    public String getProfilePage(Model model) {
        model.addAttribute("user", new User((long) 1, "Testowy", "Testowy", "Testowy"));
        return "profile";

    }

}
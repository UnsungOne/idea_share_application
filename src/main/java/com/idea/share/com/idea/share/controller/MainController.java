package com.idea.share.com.idea.share.controller;


import com.idea.share.com.idea.share.model.Idea;
import com.idea.share.com.idea.share.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {

    @GetMapping("/")
    public String getMainPage(Model model) {
        model.addAttribute("idea", new Idea((long) 1, "Idea", "Idea", null, 23,true));
        return "index";

    }
}
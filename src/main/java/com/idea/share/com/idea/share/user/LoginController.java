package com.idea.share.com.idea.share.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    String getLoginPage(Model model){
       return "login";
    }

}
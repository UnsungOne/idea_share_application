package com.idea.share.com.idea.share.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    private final UserService userService;
    private final Validator userLoginDTOValidator;

    @Autowired
    public LoginController(UserService userService, @Qualifier("userLoginDTOValidator") Validator userLoginDTOValidator) {
        this.userService = userService;
        this.userLoginDTOValidator = userLoginDTOValidator;
    }

    @InitBinder
    public void initBinding(WebDataBinder binder) {
        binder.setValidator(userLoginDTOValidator);
    }

    @GetMapping("/login")
    public String getLoginPage(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(@ModelAttribute("user") @Validated User user, BindingResult bindingResult, HttpSession session) {

        if (bindingResult.hasErrors()) {
            return "login";
        }
        User userByEmailAndPassword = userService.findUserByEmailAndPassword(user.getEmail(), user.getPassword());
        session.setAttribute("username", userByEmailAndPassword);
        session.setAttribute("userid", userByEmailAndPassword.getId());
        return "redirect:/";
    }
}
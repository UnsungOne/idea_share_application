package com.idea.share.com.idea.share.user;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
public class RegistrationController {

    private final UserService userService;
    private final Validator userDTOValidator;

    public RegistrationController(UserService userService, @Qualifier("userDTOValidator") Validator userDTOValidator) {
        this.userService = userService;
        this.userDTOValidator = userDTOValidator;
    }

    @InitBinder
    public void initBinding(WebDataBinder binder){
        binder.setValidator(userDTOValidator);
    }

    @GetMapping("/register")
    String getRegistrationPage(Model model) {
        model.addAttribute("user", new UserDTO());
        return "registration";
    }


    @PostMapping("/registerUser")
    public String registerUser(@ModelAttribute("user") @Validated UserDTO userDTO, BindingResult bindingResult, HttpSession session) {


        if (bindingResult.hasErrors()) {
            return "registration";
        }

        userService.registerUser(userDTO);
        User registeredUser = userService.loginUser(userDTO.getEmail(), userDTO.getPassword());
        session.setAttribute("user", registeredUser);
        session.setAttribute("name", registeredUser.getName());
        return "redirect:/";

    }

}
package com.idea.share.com.idea.share.user;

import com.idea.share.com.idea.share.idea.IdeaDTO;
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
    public String registerUser(@ModelAttribute("user") @Validated UserDTO userDTO, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        userService.registerUser(userDTO);
        return "redirect:/";

    }

}
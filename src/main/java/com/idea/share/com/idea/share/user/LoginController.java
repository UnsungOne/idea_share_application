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

import javax.servlet.http.HttpServletRequest;

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
    public String getLoginPage(Model model, HttpServletRequest request) {

        if (request.getSession().getAttribute("user") != null) {
            return "redirect:/";
        } else {
            model.addAttribute("user", new UserLoginDTO());
            return "login";
        }
    }

    @PostMapping("/login")
    public String loginUser(@ModelAttribute("user") @Validated UserLoginDTO userLoginDTO, BindingResult bindingResult, HttpServletRequest request) {

        if (bindingResult.hasErrors()) {
            return "login";
        }
        User loggedInUser = userService.loginUser(userLoginDTO.getEmail(), userLoginDTO.getPassword());
        if (loggedInUser == null) {
            return "login";
        } else {
            request.getSession().setAttribute("user", loggedInUser);
            request.getSession().setAttribute("name", loggedInUser.getName());
            return "redirect:/";
        }

    }

}
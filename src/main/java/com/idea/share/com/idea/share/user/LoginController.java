package com.idea.share.com.idea.share.user;

import com.idea.share.com.idea.share.dto.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
    public String getLoginPage(Model model, HttpServletRequest request) {

        if (request.getSession().getAttribute("user") != null) {
            return "redirect:/";
        } else {
            model.addAttribute("user", new User());
            return "login";
        }
    }

    @PostMapping("/login")
    public String loginUser(@ModelAttribute("user") @Validated User user, BindingResult bindingResult, Model model, HttpServletRequest request) throws Exception {

        if (bindingResult.hasErrors()) {
            return "login";
        }
        User loggedInUser = userService.findUserByEmailAndPassword(user.getEmail(), user.getPassword());
        request.getSession().setAttribute("user", loggedInUser);
        request.getSession().setAttribute("name", loggedInUser.getName());
        request.getSession().setAttribute("voting", loggedInUser.isVoted());
        request.getSession().setAttribute("canVote", userService.isEligibleToVote(loggedInUser.getId()));
        return "redirect:/";

    }

}
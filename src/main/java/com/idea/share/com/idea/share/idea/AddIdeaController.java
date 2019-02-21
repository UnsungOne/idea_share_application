package com.idea.share.com.idea.share.idea;

import com.idea.share.com.idea.share.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class AddIdeaController {

    private final IdeaService ideaService;
    private final Validator ideaDTOValidator;

    @Autowired
    public AddIdeaController(IdeaService ideaService, @Qualifier("ideaDTOValidator") Validator ideaDTOValidator) {
        this.ideaService = ideaService;
        this.ideaDTOValidator = ideaDTOValidator;
    }

    @InitBinder
    public void initBinding(WebDataBinder binder){
        binder.setValidator(ideaDTOValidator);
    }

    @GetMapping("/add")
    public String getIdeaAddingPage(Model model, HttpSession session) {
            model.addAttribute("idea", new Idea());
            return "add_idea";
        }

    @PostMapping("/addIdea")
    public String addIdea(@ModelAttribute("idea") @Validated Idea idea, BindingResult bindingResult, @SessionAttribute User user, HttpServletRequest request) throws Exception {

        if (bindingResult.hasErrors()) {
            return "add_idea";
        }
        ideaService.addIdea(idea, user.getId());
        request.getSession().setAttribute("user", user);
        return "redirect:/";
    }
}
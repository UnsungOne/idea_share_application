package com.idea.share.com.idea.share.idea;

import com.idea.share.com.idea.share.exception.IdeaNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@Controller
public class EditMyIdeasController {

    private final IdeaService ideaService;
    private final Validator ideaDTOValidator;

    @Autowired
    public EditMyIdeasController(IdeaService ideaService, Validator ideaDTOValidator) {
        this.ideaService = ideaService;
        this.ideaDTOValidator = ideaDTOValidator;
    }

    @InitBinder
    public void initBinding(WebDataBinder dataBinder) {
        dataBinder.setValidator(ideaDTOValidator);
    }
    @PostMapping("/edit")
    public String edit(@ModelAttribute("newIdea") @Validated Idea idea, BindingResult bindingResult) throws Exception {

        if (bindingResult.hasErrors()) {
            return "add_idea";
        }

        ideaService.updateExistingIdea(idea.getTitle(), idea.getDescription(), idea.getId());
        return "redirect:/ideas";
    }

    @GetMapping("/find/{ideaId}")
    @ResponseBody
    public IdeaEditDTO findOne(@PathVariable Integer ideaId) throws IdeaNotFoundException {
        return ideaService.findIdeaById(ideaId);
    }

}
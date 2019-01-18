package com.idea.share.com.idea.share.idea;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@Controller
public class IdeaController {

    private final IdeaService ideaService;
    private final Validator ideaDTOValidator;

    @InitBinder
    public void initBinding(WebDataBinder binder){
        binder.setValidator(ideaDTOValidator);
    }

    @Autowired
    public IdeaController(IdeaService ideaService, @Qualifier("ideaDTOValidator") Validator ideaDTOValidator) {
        this.ideaService = ideaService;
        this.ideaDTOValidator = ideaDTOValidator;
    }

    @GetMapping("/")
    public String getMainPage(Model model, @RequestParam(value = "page", defaultValue = "0") int page,
    @RequestParam(value = "limit", defaultValue = "10") int limit) {
        model.addAttribute("idea", ideaService.fetchAllIdeas(page, limit));
        return "index";

    }

    @GetMapping("/add")
    public String getIdeaAddingPage(Model model) {
        model.addAttribute("idea", new IdeaDTO());
        return "add_idea";
    }

    @PostMapping("/addIdea")
    public String addIdea(@ModelAttribute("idea") @Validated IdeaDTO ideaDTO, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "add_idea";
        }

        ideaService.addIdea(ideaDTO);
        return "redirect:/";
    }

//    @GetMapping(value = "/idea/{id}/rate")
//    public String rateMovie(Model model, IdeaRate rate, @PathVariable Integer id){
//        return "redirect:/";
//    }

}
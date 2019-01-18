package com.idea.share.com.idea.share.idea;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
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

    private static final String DEFAULT_PAGE_VALUE = "0";
    private static final String DEFAULT_LIMIT_VALUE = "10";

    @Autowired
    public IdeaController(IdeaService ideaService) {
        this.ideaService = ideaService;
    }

    @GetMapping("/")
    public String getMainPage(Model model, @RequestParam(value = "page", defaultValue = DEFAULT_PAGE_VALUE) int page,
                              @RequestParam(value = "limit", defaultValue = DEFAULT_LIMIT_VALUE) int limit) {
        Page<IdeaDTO> ideas = ideaService.fetchAllIdeas(page, limit);
        model.addAttribute("idea", ideas);
        model.addAttribute("allPages", ideas.getTotalPages());
        return "index";
    }

//    @GetMapping(value = "/idea/{id}/rate")
//    public String rateMovie(Model model, IdeaRate rate, @PathVariable Integer id){
//        return "redirect:/";
//    }

}
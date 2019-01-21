package com.idea.share.com.idea.share.idea;


import com.idea.share.com.idea.share.sorting.SortEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.EnumSet;

@Controller
public class IdeaController {

    private final IdeaService ideaService;

    private static final String DEFAULT_PAGE_VALUE = "0";
    private static final String DEFAULT_LIMIT_VALUE = "10";
    private static final String DEFAULT_SORT_VALUE = "ADDED";

    @Autowired
    public IdeaController(IdeaService ideaService) {
        this.ideaService = ideaService;
    }

    @GetMapping("/")
    public String getMainPage(Model model, @RequestParam(value = "page", defaultValue = DEFAULT_PAGE_VALUE) int page,
                              @RequestParam(value = "limit", defaultValue = DEFAULT_LIMIT_VALUE) int limit,
                              @RequestParam(value = "sort", defaultValue = DEFAULT_SORT_VALUE) SortEnum sort, HttpServletRequest request) {
        Page<IdeaDTO> ideas = ideaService.fetchAllIdeas(page, limit, sort);
        model.addAttribute("idea", ideas);
        request.getSession().setAttribute("sort", ideaService.getOrders(sort));
        model.addAttribute("allPages", ideas.getTotalPages());
        model.addAttribute("sortingTypes", EnumSet.allOf(SortEnum.class));
        return "index";


//    @GetMapping(value = "/idea/{id}/rate")
//    public String rateMovie(Model model, IdeaRate rate, @PathVariable Integer id){
//        return "redirect:/";
//    }

    }

//    @PutMapping("/idea/{id}/rateUp")
//    public String rateIdeaUp(@ModelAttribute IdeaDTO ideaDTO, @PathVariable Integer id) throws Exception {
//        IdeaDTO currentIdea = ideaService.getIdeaById(id);
//        currentIdea.setScore(ideaService.rateIdeaUp(ideaDTO, id));
//        return "index";
//    }

    @PutMapping("/idea/{id}/rateDown")
    public String rateIdea(@ModelAttribute Idea ideaDTO, @PathVariable Integer id) throws Exception {
        Idea currentIdea = ideaService.getIdeaById(id);
        currentIdea.setScore(ideaService.rateIdeaDown(ideaDTO, id));
        return "index";
    }
}
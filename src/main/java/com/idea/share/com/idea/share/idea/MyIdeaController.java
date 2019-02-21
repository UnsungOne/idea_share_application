package com.idea.share.com.idea.share.idea;

import com.idea.share.com.idea.share.configuration.EnumConverter;
import com.idea.share.com.idea.share.sorting.SortEnum;
import com.idea.share.com.idea.share.user.User;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.EnumSet;

@Controller
public class MyIdeaController {

    private static final String DEFAULT_PAGE_VALUE = "0";
    private static final String DEFAULT_LIMIT_VALUE = "4";
    private static final String DEFAULT_SORT_VALUE = "ADDED";
    private final IdeaService ideaService;
    private final Validator ideaEditDTOValidator;

    @Autowired
    public MyIdeaController(IdeaService ideaService, @Qualifier("ideaDTOValidator") Validator ideaEditDTOValidator) {
        this.ideaService = ideaService;
        this.ideaEditDTOValidator = ideaEditDTOValidator;
    }

//    @InitBinder
//    public void initIdeaDTOValidatorForModal(WebDataBinder dataBinder) {
//        dataBinder.setValidator(ideaEditDTOValidator);
//    }
//

    @InitBinder
    public void initSorting(WebDataBinder dataBinder) {
        dataBinder.registerCustomEditor(SortEnum.class, new EnumConverter());

    }


    @GetMapping("/find")
    @ResponseBody
    public IdeaEditDTO findOne(Integer id) throws Exception {
        return ideaService.findIdeaById(id);
    }

    @GetMapping("/ideas")
    public String getIdeasCreatedByUserPage(Model model, @RequestParam(value = "page", defaultValue = DEFAULT_PAGE_VALUE) int page,
                                            @RequestParam(value = "limit", defaultValue = DEFAULT_LIMIT_VALUE) int limit,
                                            @RequestParam(value = "sort", defaultValue = DEFAULT_SORT_VALUE) SortEnum sort, HttpSession session, HttpServletRequest request,
                                            @SessionAttribute User user) {

        model.addAttribute("newIdea", new Idea());

        if (request.getSession().getAttribute("sort") != null) {
            session.setAttribute("sort", SortEnum.SCORE);
        }

        Page<IdeaDTO> ideas = ideaService.fetchMyIdeas(user.getId(), page, limit, sort, session);
        model.addAttribute("idea", ideas);
        model.addAttribute("allPages", ideas.getTotalPages());
        model.addAttribute("sortingTypes", EnumSet.allOf(SortEnum.class));
        return "myideas";
    }

    @PostMapping("/edit")
    public String edit(@ModelAttribute("newIdea") @Validated Idea idea, BindingResult bindingResult) throws Exception {

        if (bindingResult.hasErrors()) {
            return "add_idea";
        }

        ideaService.updateExistingIdea(idea.getTitle(), idea.getDescription(), idea.getId());
        return "redirect:/ideas";
    }
}
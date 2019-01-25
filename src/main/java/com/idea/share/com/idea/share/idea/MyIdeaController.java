package com.idea.share.com.idea.share.idea;

import com.idea.share.com.idea.share.configuration.EnumConverter;
import com.idea.share.com.idea.share.sorting.SortEnum;
import com.idea.share.com.idea.share.user.User;
import com.idea.share.com.idea.share.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    private final UserService userService;


    @Autowired
    public MyIdeaController(IdeaService ideaService, UserService userService) {
        this.ideaService = ideaService;
        this.userService = userService;
    }

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        dataBinder.registerCustomEditor(SortEnum.class, new EnumConverter());
    }

    @GetMapping("/ideas")
    public String getIdeasCreatedByUserPage(Model model, @RequestParam(value = "page", defaultValue = DEFAULT_PAGE_VALUE) int page,
                                            @RequestParam(value = "limit", defaultValue = DEFAULT_LIMIT_VALUE) int limit,
                                            @RequestParam(value = "sort", defaultValue = DEFAULT_SORT_VALUE) SortEnum sort, HttpSession session, HttpServletRequest request, @SessionAttribute User user) {

        if (session.getAttribute("user") == null) {
            return "redirect:/";
        } else {

            if (request.getSession().getAttribute("sort") != null) {
                session.setAttribute("sort", SortEnum.SCORE);
            }

            Page<IdeaDTO> ideas = ideaService.fetchMyIdeas(user.getId(), page, limit, sort, session);
            model.addAttribute("idea", ideas);
            model.addAttribute("allPages", ideas.getTotalPages());
            model.addAttribute("sortingTypes", EnumSet.allOf(SortEnum.class));
            return "myideas";
        }

    }

    @PostMapping("/edit/")
    public String edit(@SessionAttribute User user, @ModelAttribute Idea idea) throws Exception {
        ideaService.updateExistingIdea(idea.getTitle(), idea.getDescription(), idea.getId());
        return "redirect:/ideas";
    }

}





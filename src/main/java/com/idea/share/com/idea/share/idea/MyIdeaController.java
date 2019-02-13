package com.idea.share.com.idea.share.idea;

import com.idea.share.com.idea.share.configuration.EnumConverter;
import com.idea.share.com.idea.share.sorting.SortEnum;
import com.idea.share.com.idea.share.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.EnumSet;

@Controller
public class MyIdeaController {

    private static final String DEFAULT_PAGE_VALUE = "0";
    private static final String DEFAULT_LIMIT_VALUE = "4";
    private static final String DEFAULT_SORT_VALUE = "ADDED";
    private final IdeaService ideaService;


    @Autowired
    public MyIdeaController(IdeaService ideaService) {
        this.ideaService = ideaService;

    }

    @InitBinder
    public void initBinding(WebDataBinder dataBinder) {
        dataBinder.registerCustomEditor(SortEnum.class, new EnumConverter());

    }

    @GetMapping("/ideas")
    public String getIdeasCreatedByUserPage(Model model, @RequestParam(value = "page", defaultValue = DEFAULT_PAGE_VALUE) int page,
                                            @RequestParam(value = "limit", defaultValue = DEFAULT_LIMIT_VALUE) int limit,
                                            @RequestParam(value = "sort", defaultValue = DEFAULT_SORT_VALUE) SortEnum sort, HttpSession session, HttpServletRequest request,
                                            @SessionAttribute User user) {


        if (request.getSession().getAttribute("sort") != null) {
            session.setAttribute("sort", SortEnum.SCORE);
        }

        if (user == null) {
            return "redirect:/";
        } else {

            Page<IdeaDTO> ideas = ideaService.fetchMyIdeas(user.getId(), page, limit, sort, session);
            model.addAttribute("idea", ideas);
            model.addAttribute("newIdea", new Idea());
            model.addAttribute("allPages", ideas.getTotalPages());
            model.addAttribute("sortingTypes", EnumSet.allOf(SortEnum.class));


            return "myideas";
        }

    }

}
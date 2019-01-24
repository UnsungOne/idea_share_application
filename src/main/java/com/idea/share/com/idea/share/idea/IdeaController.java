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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.EnumSet;

@Controller
public class IdeaController {

    private final IdeaService ideaService;
    private final UserService userService;

    private static final String DEFAULT_PAGE_VALUE = "0";
    private static final String DEFAULT_LIMIT_VALUE = "10";
    private static final String DEFAULT_SORT_VALUE = "ADDED";


    @Autowired
    public IdeaController(IdeaService ideaService, UserService userService) {
        this.ideaService = ideaService;
        this.userService = userService;
    }


    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        dataBinder.registerCustomEditor(SortEnum.class, new EnumConverter());
    }

    @GetMapping("/")
    public String getMainPage(Model model, @RequestParam(value = "page", defaultValue = DEFAULT_PAGE_VALUE) int page,
                              @RequestParam(value = "limit", defaultValue = DEFAULT_LIMIT_VALUE) int limit,
                              @RequestParam(value = "sort", defaultValue = DEFAULT_SORT_VALUE) SortEnum sort, HttpSession session, HttpServletRequest request, User user) throws Exception {
        Page<IdeaDTO> ideas = ideaService.fetchAllIdeas(page, limit, sort);
        model.addAttribute("idea", ideas);
        request.getSession().setAttribute("sort", ideaService.getOrders(sort));
        model.addAttribute("allPages", ideas.getTotalPages());
        model.addAttribute("sortingTypes", EnumSet.allOf(SortEnum.class));
        return "index";

    }


}
package com.idea.share.com.idea.share.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpSession;

@Controller
public class LogoutController {
    @GetMapping("/logout")
    public String logOut(HttpSession session, SessionStatus status) {
            session.removeAttribute("user");
            session.invalidate();
            status.setComplete();
            return "redirect:/";
        }
    }
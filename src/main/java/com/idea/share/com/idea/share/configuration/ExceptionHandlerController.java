package com.idea.share.com.idea.share.configuration;

import com.idea.share.com.idea.share.exception.IdeaException;
import com.idea.share.com.idea.share.exception.UserException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(value = IdeaException.class)
    public ModelAndView handleIdeaException(IdeaException ex) {
        ModelAndView mnv = new ModelAndView("errorView");
        mnv.addObject("errorMessage", ex.getMessage());
        return mnv;
    }

    @ExceptionHandler(value = UserException.class)
    public ModelAndView handleUserException (UserException ex) {
        ModelAndView mnv = new ModelAndView("errorView");
        mnv.addObject("errorMessage", ex.getMessage());
        return mnv;
    }

    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public String handleHttpRequestMethodNotSupportedException() {
        return "redirect:/";
    }
}
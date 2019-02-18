package com.idea.share.com.idea.share.configuration;

import com.idea.share.com.idea.share.exception.IdeaException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(value = IdeaException.class)
    public ModelAndView IdeaException(Exception ex) {
        ModelAndView mnv = new ModelAndView("errorView");
        mnv.addObject("errorMessage", ex.getMessage());
        return mnv;
    }

    @ExceptionHandler(value = Exception.class)
    public ModelAndView basicException(Exception ex) {
        ModelAndView error = new ModelAndView("errorView");
        error.addObject("errorMessage", ex.getMessage());
        return error;
    }
}
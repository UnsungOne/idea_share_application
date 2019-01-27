package com.idea.share.com.idea.share.validator;

import com.idea.share.com.idea.share.idea.Idea;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


@Component("ideaDTOValidator")
public class IdeaDTOValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return Idea.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Idea idea = (Idea) o;
        ValidationUtils.rejectIfEmpty(errors, "title", "idea.validator.field.notEmpty");
        ValidationUtils.rejectIfEmpty(errors, "description", "idea.validator.field.notEmpty");

        if (idea.getTitle().length() > 255) {
            errors.rejectValue("title", "idea.validator.field.tooLong");
        }
        if (idea.getDescription().length() > 255) {
            errors.rejectValue("description", "idea.validator.field.tooLong");
        }

    }
}
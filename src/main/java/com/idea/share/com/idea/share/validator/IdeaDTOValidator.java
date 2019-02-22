package com.idea.share.com.idea.share.validator;

import com.idea.share.com.idea.share.idea.Idea;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Component("ideaDTOValidator")
public class IdeaDTOValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return Idea.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Idea idea = (Idea) o;
        Matcher ideaTitleMatcher = Pattern.compile(ValidationRules.IDEA_PATTERN).matcher(idea.getTitle());
        Matcher ideDescriptionMatcher = Pattern.compile(ValidationRules.IDEA_PATTERN).matcher(idea.getDescription());
        ValidationUtils.rejectIfEmpty(errors, "title", "idea.validator.field.notEmpty");
        ValidationUtils.rejectIfEmpty(errors, "description", "idea.validator.field.notEmpty");

        if (!ideaTitleMatcher.matches()) {
            errors.rejectValue("title", "idea.validator.field.IdeaTitlePattern");
        }

        if (!ideDescriptionMatcher.matches()) {
            errors.rejectValue("description", "idea.validator.field.IdeaDescriptionPattern");
        }

        if (idea.getTitle().length() > 255) {
            errors.rejectValue("title", "idea.validator.field.tooLong");
        }

        if (idea.getTitle().length() < 2) {
            errors.rejectValue("title", "idea.validator.field.tooShort");
        }

        if (idea.getDescription().length() > 255) {
            errors.rejectValue("description", "idea.validator.field.tooLong");
        }

        if (idea.getDescription().length() < 2) {
            errors.rejectValue("description", "idea.validator.field.tooShort");
        }

    }
}
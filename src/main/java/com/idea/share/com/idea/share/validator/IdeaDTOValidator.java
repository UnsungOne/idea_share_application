package com.idea.share.com.idea.share.validator;

import com.idea.share.com.idea.share.idea.IdeaDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


@Component("ideaDTOValidator")
public class IdeaDTOValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return IdeaDTO.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        IdeaDTO ideaDTO = (IdeaDTO) o;
        ValidationUtils.rejectIfEmpty(errors, "title", "idea.validator.field.notEmpty");
        ValidationUtils.rejectIfEmpty(errors, "description", "idea.validator.field.notEmpty");

        if (ideaDTO.getTitle().length() > 255) {
            errors.rejectValue("title", "idea.validator.field.tooLong");
        }
        if (ideaDTO.getDescription().length() > 255) {
            errors.rejectValue("description", "idea.validator.field.tooLong");
        }

    }
}
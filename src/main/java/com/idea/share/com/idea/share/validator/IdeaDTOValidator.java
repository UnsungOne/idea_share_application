package com.idea.share.com.idea.share.validator;

import com.idea.share.com.idea.share.idea.Idea;
import com.idea.share.com.idea.share.idea.IdeaDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


@Component("ideaDTOValidator")
public class IdeaDTOValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return IdeaDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        ValidationUtils.rejectIfEmpty(errors,"title", "idea.validator.field.notEmpty");
        ValidationUtils.rejectIfEmpty(errors,"description", "idea.validator.field.notEmpty");
        }
    }
package com.idea.share.com.idea.share.validator;

import com.idea.share.com.idea.share.user.UserDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
@Component("userDTOValidator")
public class UserDTOValidator implements Validator {

    private final Pattern PASSSWORD_LENGTH = Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$");

    @Override
    public boolean supports(Class<?> aClass) {
        return UserDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        UserDTO userDTO = (UserDTO) o;
        Matcher matcher = PASSSWORD_LENGTH.matcher(userDTO.getPassword());

        ValidationUtils.rejectIfEmpty(errors, "name", "user.validator.field.notEmpty");
        ValidationUtils.rejectIfEmpty(errors, "email", "user.validator.field.notEmpty");
        ValidationUtils.rejectIfEmpty(errors, "password", "user.validator.field.notEmpty");
        ValidationUtils.rejectIfEmpty(errors, "repeatPassword", "user.validator.field.notEmpty");

        if (!matcher.matches()) {
            errors.rejectValue("password", "user.validator.field.PasswordLength");
        }

        if (!userDTO.getPassword().equals(userDTO.getRepeatPassword())) {
            errors.rejectValue("password", "user.validator.field.PasswordMatch");
        }

    }
}
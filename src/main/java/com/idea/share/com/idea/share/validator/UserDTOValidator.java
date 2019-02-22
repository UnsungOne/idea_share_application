package com.idea.share.com.idea.share.validator;

import com.idea.share.com.idea.share.user.UserDTO;
import com.idea.share.com.idea.share.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component("userDTOValidator")
public class UserDTOValidator implements Validator {

    private final UserService userService;
    @Autowired
    public UserDTOValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return UserDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        UserDTO userDTO = (UserDTO) o;
        Matcher passwordMatcher = Pattern.compile(ValidationRules.PASSSWORD_LENGTH).matcher(userDTO.getPassword());
        Matcher emailMatcher = Pattern.compile(ValidationRules.EMAIL_PATTERN).matcher(userDTO.getEmail());

        ValidationUtils.rejectIfEmpty(errors, "name", "user.validator.field.notEmpty");
        ValidationUtils.rejectIfEmpty(errors, "email", "user.validator.field.notEmpty");
        ValidationUtils.rejectIfEmpty(errors, "password", "user.validator.field.notEmpty");
        ValidationUtils.rejectIfEmpty(errors, "repeatPassword", "user.validator.field.notEmpty");

        if (!emailMatcher.matches()) {
            errors.rejectValue("email", "user.validator.field.EmailPattern");
        }

        if (!passwordMatcher.matches()) {
            errors.rejectValue("password", "user.validator.field.PasswordLength");
        }

        if (userService.checkIfUserWithGivenEmailAlreadyExists(userDTO.getEmail())) {
            errors.rejectValue("email", "user.validator.field.Email");
        }
        if (!userDTO.getPassword().equals(userDTO.getRepeatPassword())) {
            errors.rejectValue("password", "user.validator.field.PasswordMatch");
        }

    }
}
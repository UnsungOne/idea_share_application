package com.idea.share.com.idea.share.validator;

import com.idea.share.com.idea.share.user.UserLoginDTO;
import com.idea.share.com.idea.share.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component("userLoginDTOValidator")
public class UserLoginDTOValidator implements Validator {

    private final UserService userService;

    @Autowired
    public UserLoginDTOValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return UserLoginDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        UserLoginDTO user  = (UserLoginDTO) o;
        ValidationUtils.rejectIfEmpty(errors, "email", "user.validator.field.notEmpty");
        ValidationUtils.rejectIfEmpty(errors, "password", "user.validator.field.notEmpty");

        if (userService.loginUser(user.getEmail(), user.getPassword()) == null) {
            errors.rejectValue("password", "user.validator.field.LoginError");
        }
    }
}
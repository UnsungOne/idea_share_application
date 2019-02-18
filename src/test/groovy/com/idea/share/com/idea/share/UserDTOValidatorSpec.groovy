package com.idea.share.com.idea.share

import com.idea.share.com.idea.share.idea.Idea
import com.idea.share.com.idea.share.user.UserDTO
import com.idea.share.com.idea.share.validator.UserDTOValidator
import org.springframework.validation.Errors
import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll

import java.util.regex.Matcher
import java.util.regex.Pattern

class UserDTOValidatorSpec extends Specification {

    @Subject

    UserDTOValidator userDTOValidator
    UserDTO userDTO
    Errors errors

    void setup() {
        userDTOValidator = new UserDTOValidator()
        userDTO = Mock()
        errors = Mock()

    }

    @Unroll
    def "VerifyIfTheProperUserClassIsTested"() {

        when:

        Matcher emailMatcher = EMAIL_PATTERN.matcher(userDTO.getEmail());
        def result = userDTOValidator.supports(clazz)
        then:
        expected == result
        where:
        clazz   | expected
        UserDTO | true
        Idea    | false
    }

    @Unroll
    def "checkValidationRulesForUserEnteringData"() {
        when:

        Pattern PASSSWORD_LENGTH = Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}\$");

        userDTOValidator.validate(userDTO, errors)
        then:
        passwordMatch * errors.rejectValue("password", "user.validator.field.PasswordMatch")

        PASSSWORD_LENGTH.matcher(userDTO.getPassword()) >> password
        PASSSWORD_LENGTH.matcher(userDTO.getRepeatPassword()) >> repeatPassword

        userDTO.getPassword() >> password

        where:
        password         | repeatPassword   | passwordMatch
        "test123123test" | "test123123test" | 0

    }
}
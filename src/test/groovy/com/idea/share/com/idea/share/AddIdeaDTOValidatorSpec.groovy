package com.idea.share.com.idea.share

import com.idea.share.com.idea.share.idea.Idea
import com.idea.share.com.idea.share.user.User
import com.idea.share.com.idea.share.validator.IdeaDTOValidator
import org.springframework.validation.Errors
import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll

class AddIdeaDTOValidatorSpec extends Specification {

    @Subject
    IdeaDTOValidator ideaDTOValidator
    Idea idea
    Errors errors

    void setup() {
        idea = Mock()
        errors = Mock()
        ideaDTOValidator = new IdeaDTOValidator()
    }

    @Unroll
    def "VerifyIfTheProperIdeaClassIsTested"() {

        when:
        def result = ideaDTOValidator.supports(clazz)
        then:
        expected == result
        where:
        clazz      | expected
        Idea.class | true
        User.class | false
    }

    def "checkIfValidationRulesAreOK"() {
        when:
        ideaDTOValidator.validate(idea, errors)
        then:
        TooShortTitle * errors.rejectValue("title", "idea.validator.field.tooShort")
        TooShortDescription * errors.rejectValue("description", "idea.validator.field.tooShort")
        InvalidCharactersInTitle * errors.rejectValue("title", "idea.validator.field.IdeaTitlePattern")
        InvalidCharactersInDescription * errors.rejectValue("description", "idea.validator.field.IdeaDescriptionPattern")
        idea.getTitle() >> title
        idea.getDescription() >> description
        where:
        title | description  | TooShortTitle | TooShortDescription | InvalidCharactersInTitle | InvalidCharactersInDescription
        "a"   | "longenough" | 1             | 0                   | 0                        | 0
        "?"   | "?"          | 1             | 1                   | 1                        | 1
    }
}
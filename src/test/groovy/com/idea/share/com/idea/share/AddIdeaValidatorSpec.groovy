package com.idea.share.com.idea.share

import com.idea.share.com.idea.share.idea.Idea
import com.idea.share.com.idea.share.user.User
import com.idea.share.com.idea.share.validator.IdeaDTOValidator
import org.springframework.validation.Errors
import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll

class AddIdeaValidatorSpec extends Specification {

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
    def "ToVerifyIfTheProperClassIsTested"() {
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
        TooShortTitle * errors.rejectValue("title", "idea.validator.field.tooShort");
        TooShortDescription * errors.rejectValue("description", "idea.validator.field.tooShort");
        idea.getTitle() >> title
        idea.getDescription() >> description
        where:
        title | description  | TooShortTitle | TooShortDescription
        "a"   | "longenough" | 1                | 0
    }
}

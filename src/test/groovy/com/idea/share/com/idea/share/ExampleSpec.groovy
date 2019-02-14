package com.idea.share.com.idea.share

import com.idea.share.com.idea.share.user.UserService
import spock.lang.Specification

class ExampleSpec extends Specification {

    def "the test determines if a user with a given email already exists in a database"() {

        given:
        UserService userService = Mock();

        when:
        String email = "kamil@example.pl"

        then:
        userService.checkIfUserWithGivenEmailAlreadyExists(email) >> false

    }
}

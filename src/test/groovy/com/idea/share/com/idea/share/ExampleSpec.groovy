package com.idea.share.com.idea.share

import com.idea.share.com.idea.share.user.User
import com.idea.share.com.idea.share.user.UserService
import spock.lang.Specification
import spock.lang.Subject

class ExampleSpec extends Specification {

    @Subject
    UserService userService

    def setup() {
        userService = new UserService()
    }

    def "shouldFindAGivenUser"() {

        when:
        def result = userService.findUserById(userId)

        then:
        expected == expectedUsers

        where:
        userId << [
                [1, 2, 3].collect( { n -> Mock(User) {
                    getId() >> n
                }

                })
        ]
    }

    //https://stackoverflow.com/questions/46644025/spock-set-list-as-parameter-by-using-table

}
package com.idea.share.com.idea.share

import com.idea.share.com.idea.share.user.User
import com.idea.share.com.idea.share.user.UserRepository
import com.idea.share.com.idea.share.user.UserService
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll

import javax.servlet.http.HttpServletRequest
@SpringBootTest
class RateIdeaInterceptor extends Specification {

    @Subject
    HttpServletRequest request
    UserService userService
    UserRepository userRepository

    void setup() {


        request = Mock()
        userService = new UserService(userRepository)
    }

    @Unroll
    def "checkIfUserIsEligibleForVoting"() {
        when:
            def users = [
                1: [new User(1, null, null, null, true, null)],
                2: [new User(2, null, null, null, false, null)],
            ] as User
            def result = userService.isEligibleToVote(userID, request)
            userService.findUserById(users.getId()).id >> userID
            userService.findUserById(users.getId()).voted >> voting
            request.getSession().getAttribute("user") >> requestValue

        then:
            result == expected

        where:
            userID | voting | requestValue | expected
            1      | true   | null         | true

    }
}
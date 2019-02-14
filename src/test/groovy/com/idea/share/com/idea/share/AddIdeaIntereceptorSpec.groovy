package com.idea.share.com.idea.share

import com.idea.share.com.idea.share.interceptor.AddIdeaInterceptor
import spock.lang.Ignore
import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import javax.servlet.http.HttpSession

class AddIdeaIntereceptorSpec extends Specification {

//    @Subject
//    AddIdeaInterceptor addIdeaInterceptor
//    HttpServletRequest request
//    HttpServletResponse response
//    HttpSession session
//
//    def setup() {
//        addIdeaInterceptor = new AddIdeaInterceptor()
//        request = Mock()
//        response = Mock()
//    }
//
//    @Unroll
//    @Ignore
//    def "checkIfTheInterceptorRedirectsUserIfSessionAttributeExists"(){
//        when:
//        def interceptor = addIdeaInterceptor.preHandle(request, response, null)
//        then:
//        1 * request.getSession().getAttribute("user") >> sessionattribute
//
//        where:
//
//    }
}

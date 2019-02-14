package com.idea.share.com.idea.share.configuration;

import com.idea.share.com.idea.share.interceptor.IdeasInterceptor;
import com.idea.share.com.idea.share.interceptor.LoginUserInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebAppConfiguration implements WebMvcConfigurer {

    @Autowired
    LoginUserInterceptor loginUserInterceptor;
    @Autowired
    IdeasInterceptor ideasInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginUserInterceptor).addPathPatterns("/login");
        registry.addInterceptor(ideasInterceptor).addPathPatterns("/ideas");
    }
}

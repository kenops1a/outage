package com.rat.web.interceptor.config;

import com.rat.web.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @type: outage
 * @author: yaominc
 * @description: write_bug
 * @date: 2021/12/21 13:35
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Autowired
    private LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // addPathPatterns 用于添加拦截规则
        // excludePathPatterns 用于排除拦截
        registry.addInterceptor(loginInterceptor).addPathPatterns("/**")
        .excludePathPatterns("/test/**")
        .excludePathPatterns("/index/**");
    }
}

package com.rat.web.interceptor.config;

import com.rat.web.interceptor.RoleInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ConcurrentTaskExecutor;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.concurrent.Executors;

/**
 * @type: outage
 * @author: yaominc
 * @description: write_bug
 * @date: 2021/12/21 13:35
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Autowired
    private RoleInterceptor roleInterceptor;


    /**
     * 解决跨域请求
     * @param registry iDontKnow
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedHeaders("*")
                .allowedMethods("*")
                .allowedOrigins("*")
                .allowCredentials(true);
    }

    /**
     * 异步请求配置
     * @param configurer iDontKnow
     */
    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
        configurer.setTaskExecutor(new ConcurrentTaskExecutor(Executors.newFixedThreadPool(3)))
                  .setDefaultTimeout(30000);
    }

    /**
     * addPathPatterns 用于添加拦截规则
     * excludePathPatterns 用于排除拦截
     * @param registry iDontKnow
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        /*
        排除test,index,login,register,static的拦截
         */
        registry.addInterceptor(roleInterceptor).addPathPatterns("/**")
        .excludePathPatterns("/test/**")
        .excludePathPatterns("/index/**")
        .excludePathPatterns("/login/byPassword")
        .excludePathPatterns("/register/**")
        .excludePathPatterns("/static/**")
        .excludePathPatterns("/mail/**")
        .excludePathPatterns("/login/byVerifyCode");
    }
}

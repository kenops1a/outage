package com.rat.web.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @type: outage
 * @author: yaominc
 * @description: write_bug
 * @date: 2021/12/10 10:16
 */

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // http相关配置，登入登出，异常处理，会话管理
        http.authorizeRequests()
                .antMatchers("/").hasAnyRole("train", "recruiter","manager")
                .antMatchers("/emp/**").hasAnyRole("train", "recruiter")
                .antMatchers("/emprp/**").hasAnyRole("train", "recruiter")
                .antMatchers("/performance/**").hasAnyRole("train", "recruiter")
                .antMatchers("/salary/**").hasAnyRole("train", "recruiter")
                .antMatchers("/train/**").hasAnyRole("train", "recruiter")
                .antMatchers("/wage/**").hasAnyRole("train", "recruiter").and().csrf().disable();
        http.formLogin()
                .loginPage("/toLogin").successForwardUrl("/index");

        http.headers().frameOptions().sameOrigin();
        http.headers().contentTypeOptions().disable();

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 配置认证方式
        auth.inMemoryAuthentication();
    }
}

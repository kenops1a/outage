package com.rat.web.interceptor;

import com.rat.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.security.auth.message.AuthException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @type: outage
 * @author: yaominc
 * @description: write_bug
 * @date: 2021/12/20 13:37
 */
@Slf4j
@Service
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private UserService userService;

    /**
     * 请求处理之前调用，controller方法调用之前
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getParameter("email") == null) {
            log.info("email is null");
            return false;
        }
        else {
            log.info("email is not null");
            return true;
        }
    }

    /**
     * 请求处理后调用，controller方法调用之后
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    /**
     * 整个请求结束后调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}

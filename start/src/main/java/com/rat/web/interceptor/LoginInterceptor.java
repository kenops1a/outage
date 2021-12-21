package com.rat.web.interceptor;

import com.rat.service.UserService;
import com.rat.web.token.TokenServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

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

    private static final String OPTIONS = "OPTIONS";

    /**
     * 引入userService依赖
     */
    @Autowired
    private UserService userService;

    /**
     * 引入token依赖
     */
    @Autowired
    private TokenServer tokenServer;

    /**
     * 请求处理之前调用，controller方法调用之前
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 跨域请求会先发送一个OPTIONS请求，直接返回true并通过拦截器
        if (OPTIONS.equals(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
            log.info("OPTIONS请求通过拦截器");
            return true;
        }

        response.setCharacterEncoding("utf-8");
        String token = request.getHeader("token");
        if (token != null) {
            return tokenServer.verifyToken(token);
        }
        return true;
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

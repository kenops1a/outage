package com.rat.web.interceptor;

import cn.hutool.json.JSONUtil;
import com.rat.info.ResultTool;
import com.rat.service.TokenService;
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
public class AccountInterceptor implements HandlerInterceptor {

    private static final String OPTIONS = "OPTIONS";

    /**
     * 引入token依赖
     */
    @Autowired
    private TokenService tokenService;

    /**
     * 请求处理之前调用，controller方法调用之前
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("*******************************");
        // 打印请求路径
        log.info("请求路径：" + request.getRequestURI());
        // 跨域请求会先发送一个OPTIONS请求，直接返回true并通过拦截器
        if (OPTIONS.equals(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
            log.info("OPTIONS请求通过");
            return true;
        }

        // 校验token是否有效（有效）
        // 设置响应编码
        response.setCharacterEncoding("utf-8");
        String token = request.getHeader("token");
        if (token != null) {
            if (tokenService.verifyToken(token)) {
                log.info("token验证通过");
                // 打印请求参数
                log.info(request.getQueryString());
                return true;
            }
        }

        // 设置响应头
        response.setContentType("application/json; charset=utf-8");
        try {
            // 将JsonResult对象序列化为json字符串并获取其字节数组
            response.getOutputStream().write(JSONUtil.toJsonStr(ResultTool.failed("token认证失败，请重新登录")).getBytes());
        } catch (Exception e) {
            log.info("token认证失败");
        }
        return false;
    }

    /**
     * 请求处理后调用，controller方法调用之后
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("请求处理结束");
        log.info("*******************************");
    }

    /**
     * 整个请求结束后调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}

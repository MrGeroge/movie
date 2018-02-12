package com.strival.movie.interceptor;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 设置全局常量
 *
 * Author:zhangyu
 * create on 15/11/8.
 */
public class ConstantsInterceptor extends HandlerInterceptorAdapter {

    private final static String HOST="http://localhost:8080";

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        modelAndView.addObject("HOST",HOST);
    }
}

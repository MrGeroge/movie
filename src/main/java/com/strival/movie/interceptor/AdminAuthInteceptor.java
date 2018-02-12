package com.strival.movie.interceptor;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by xinghai on 2015/12/21.
 */
public class AdminAuthInteceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        Long adminId = (Long) session.getAttribute("adminId");
        if (session != null) {
            if (adminId != null) {
                return true;
            }
            else{
                response.setStatus(401);
                return false;
            }
        } else {
            response.setStatus(400);
           return false;
        }
    }
}


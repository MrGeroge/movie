package com.strival.movie.interceptor;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by xinghai on 2015/12/21.
 */
public class AccountAuthInteceptor extends HandlerInterceptorAdapter {
    /*@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session=request.getSession();
        long accountId=(long)session.getAttribute("accountId");
        if(accountId>0){
            return true;
        }
        else{

            return false;
        }
    }*/

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HttpSession session=request.getSession();
        Long accountId=(Long)session.getAttribute("accountId");
if(session!=null){
    if(accountId!=null){
        if(modelAndView!=null){
            modelAndView.addObject("username",session.getAttribute("username"));
            modelAndView.addObject("status",true);
            modelAndView.addObject("message","登录验证成功");
        }
    }
    else{
        if(modelAndView!=null){
            modelAndView.addObject("status",false);
            modelAndView.addObject("message","登录验证失败，请用户重新登陆");
        }
    }
}
        else{

        }
        super.postHandle(request, response, handler, modelAndView);

    }
}

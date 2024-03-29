package com.zjut.qll.config;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginHandlerInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object emp = request.getSession().getAttribute("emp");

        if (emp == null) {
            request.getRequestDispatcher("/").forward(request,response);
            return false;
        }

        return true;
    }
}

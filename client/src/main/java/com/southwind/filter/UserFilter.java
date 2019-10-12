package com.southwind.filter;

import com.southwind.entity.User;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component

@WebFilter(urlPatterns = {"/index.html","/account/redirect/index","/order.html","/account/redirect/order"},filterName = "userFilter")
public class UserFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override  //过滤器请求都会被过滤
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession(); // 取出session
        User user = (User) session.getAttribute("user");
        if(user == null){
            response.sendRedirect("login.html"); // 没有登陆，响应回登陆页面
        }else{
            filterChain.doFilter(servletRequest,servletResponse); // 已登陆则继续往后走 ,放行
        }
    }

    @Override
    public void destroy() {

    }
}

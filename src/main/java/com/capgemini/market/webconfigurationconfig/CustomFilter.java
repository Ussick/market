package com.capgemini.market.webconfigurationconfig;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class CustomFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;

        String username = httpServletRequest.getParameter("username");

        System.out.println("username " + username);//всегда возвращает null как addFilterAfter так и addFilterBefore

        chain.doFilter(request, response);
    }
}
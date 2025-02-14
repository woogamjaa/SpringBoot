package com.bs.basicboot.common.config.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;

//서블릿 임포트

@Slf4j
@Component
@WebFilter("/*")
public class myFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.warn("filter 실행");
        filterChain.doFilter(servletRequest, servletResponse);
    }
}

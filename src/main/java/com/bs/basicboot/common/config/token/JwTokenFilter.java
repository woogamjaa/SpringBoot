package com.bs.basicboot.common.config.token;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.HttpRequestHandlerServlet;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
@Component
public class JwTokenFilter extends GenericFilter {

    private final JwtTokenUtils tokenUtil;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //사용자가 전달한 token값을 확인하는 로직을 구현
        //사용자가 요청시 Authorization해더에 token을 저장해서 보냄

       String token = ((HttpServletRequest)servletRequest).getHeader("Authorization");
       if(token!=null&&tokenUtil.validToToken(token)) {
           //인증처리
           Authentication authentication = tokenUtil.getAuthentication(token);
           SecurityContextHolder.getContext().setAuthentication(authentication);
       }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}

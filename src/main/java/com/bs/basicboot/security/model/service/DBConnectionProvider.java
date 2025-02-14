package com.bs.basicboot.security.model.service;

import com.bs.basicboot.jpa.model.dao.JpaMemberRepository;
import com.bs.basicboot.jpa.model.dto.JpaMember;
import com.bs.basicboot.jpa.model.entity.JapMemberEntity;
import lombok.RequiredArgsConstructor;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DBConnectionProvider implements AuthenticationProvider {

    private final JpaMemberRepository repository;
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password=(String) authentication.getCredentials();
        JpaMember member=repository.findByUserId(username)
                .orElseThrow(()->
                   {throw new BadCredentialsException("아이디를 찾을 수 없습니다.");})
                .toJpaMember();

        if(encoder.matches(password,member.getPassword())){
            throw new BadCredentialsException("아이디, 패스워드가 일치하지 않습니다.");
        }


        return new UsernamePasswordAuthenticationToken(member,member.getPassword(), member.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}

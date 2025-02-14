package com.bs.basicboot.common.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebSecurity

public class SecurityConfig {
    //시큐리티 설정은 시큐리티filter을 beab으로 등록

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(web->web.disable())
                //interceptor-url설정과 동일
                .authorizeHttpRequests( auth->
                    auth
                            .requestMatchers("/").permitAll()
                            .requestMatchers("/WEB-INF/views/**").permitAll()
                            .anyRequest().authenticated()
                )
//                .formLogin(formlogin->formlogin
//                        .loginProcessingUrl("/loginend.do")
//                        .successForwardUrl("/loginsuccess.do")
//                )
//                .logout(logout->logout
//                        .su
//                )
                //인즈처리하는 서비스를 등록 -> DB인증 절차 처리
                .authenticationProvider()
                .build();
    }

}

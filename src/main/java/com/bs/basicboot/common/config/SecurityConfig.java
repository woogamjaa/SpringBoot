package com.bs.basicboot.common.config;


import com.bs.basicboot.common.config.event.MyAccessDenied;
import com.bs.basicboot.common.config.token.JwTokenFilter;
import com.bs.basicboot.security.model.service.DBConnectionProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    //시큐리티 설정은 시큐리티filter을 beab으로 등록
    private final DBConnectionProvider dbProvider;
    private final JwTokenFilter tokenFilter;
//    @Bean
//    WebSecurityCustomizer configure() {
//        return (web)->{
//            web.ignoring()
//                    .requestMatchers(new AntPathRequestMatcher("/static/**"))
//                    .requestMatchers(new AntPathRequestMatcher("*.html"));
//        };
//    }
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(web->web.disable())
                //interceptor-url설정과 동일
                .authorizeHttpRequests( auth->
                    auth
                            .requestMatchers(req -> CorsUtils.isPreFlightRequest(req)).permitAll()
                            .requestMatchers("/**").permitAll()
                            .requestMatchers("index.html").permitAll()
                            .requestMatchers("/member/**").permitAll()
                            .requestMatchers("/api-docs").permitAll()
                            .requestMatchers("/swagger-ui/**").permitAll()
                            .requestMatchers(new AntPathRequestMatcher("/static/**")).permitAll()
                            .requestMatchers(new AntPathRequestMatcher("/templates/**")).permitAll()
                            //패치 내용 리퀘스트 허용
                            .requestMatchers("/auth/login.do").permitAll()
//                            .anyRequest().authenticated()
                )
//                .formLogin(formlogin->formlogin
//                        .loginProcessingUrl("/loginend.do")
//                        .successForwardUrl("/loginsuccess.do")
//                )
//                .logout(logout->logout
//                        .su
//                )
                //인즈처리하는 서비스를 등록 -> DB인증 절차 처리
//                .authenticationProvider(dbProvider)

                //권한이 부족한 사용자가 서비스 접근했을때
                .exceptionHandling(handle->handle.accessDeniedHandler(new MyAccessDenied()))
                .addFilterBefore(tokenFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

}

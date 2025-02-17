package com.bs.basicboot.common.config.properties;

import com.bs.basicboot.common.config.TestInterceptor;
import com.bs.basicboot.common.config.filter.myFilter;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableAspectJAutoProxy
//@EnableWebMvc
@ServletComponentScan
@MapperScan("com.bs.basicboot.model.dao")

public class WebMvcConfig implements WebMvcConfigurer {
    @Bean
    FilterRegistrationBean<myFilter> testFilter() {
        FilterRegistrationBean<myFilter> filter= new FilterRegistrationBean<>();
        filter.setFilter(new myFilter());
        filter.addUrlPatterns("/**");
        return filter;
    };


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(myInterceptor()).addPathPatterns("/**");
    }

    private TestInterceptor myInterceptor() {
        return new TestInterceptor();
    }

    @Bean
    Validator validator() {
       return Validation.buildDefaultValidatorFactory()
               .getValidator();
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("http://localhost:52330");
    }
}

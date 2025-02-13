package com.bs.basicboot.common.config.properties;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class WebMvcConfig {

    @Bean
    Validator validator() {
       return Validation.buildDefaultValidatorFactory()
               .getValidator();
    }
}

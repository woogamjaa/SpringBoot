package com.bs.basicboot.common.config.commandline;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

@Configuration
@Slf4j
public class CommandLineRunnerConfig {
    @Order(1)
    @Bean
    CommandLineRunner test() {
        return args -> {
            log.info("test메소드실행");
        };
    }
    @Order(2)
    @Bean
    CommandLineRunner test2() {
        return args -> {
            log.info("test2메소드실행");
        };
    }
    @Order(3)
    @Bean
    CommandLineRunner test3() {
        return args -> {
            log.info("test3메소드실행");
        };
    }

}

package com.bs.basicboot.common.config.commandline;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TestCommandLineRunnerImpl implements CommandLineRunner {
    @Order(4)
    @Override
    public void run(String... args) throws Exception {
        log.info("@Component로 선언한 run()메소드");
    }
}

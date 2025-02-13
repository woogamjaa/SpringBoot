package com.bs.basicboot.common.config.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class InnerEventListener {
    @EventListener(ApplicationReadyEvent.class)
    public void applicationReadyEvent(ApplicationReadyEvent event) {
        ConfigurableApplicationContext context=event.getApplicationContext();
        Environment env=context.getEnvironment();

        log.info("{}", env.getProperty("spring.datasource.username"));
        log.info("{}", env.getProperty("spring.datasource.password"));
        log.info("{}", env.getProperty("spring.datasource.url"));
    }

    @EventListener(WebServerInitializedEvent.class)
    public void webServerInitialized(WebServerInitializedEvent event) {
        log.info("웹서버 구동완료");
        log.info("연결포트 : {}",event.getWebServer().getPort());
    }
}

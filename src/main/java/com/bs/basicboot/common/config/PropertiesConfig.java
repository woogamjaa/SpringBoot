package com.bs.basicboot.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration //설정 파일
@PropertySource("classpath:test-prop.properties")
public class PropertiesConfig {

}

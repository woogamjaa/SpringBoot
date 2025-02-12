package com.bs.basicboot;

import com.bs.basicboot.common.config.properties.MyDataProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import java.util.Properties;

@SpringBootApplication
@EnableConfigurationProperties(MyDataProperties.class)
public class BasicApplication {

    public static void main(String[] args) {
        //부트 프로그램 시작하는 곳
        //시작할때 Properties를 설정해줄 수 있음.
        Properties applicationProperties = new Properties();
        applicationProperties.setProperty("server.port", "9099");

        //SpringApplication클래스에서 제공하는 setDefaultProperties()메소드를 이용
        SpringApplication springApplication = new SpringApplication(BasicApplication.class);
        springApplication.setDefaultProperties(applicationProperties);

        springApplication.run(args);


//        SpringApplication.run(BasicApplication.class, args);
    }

}

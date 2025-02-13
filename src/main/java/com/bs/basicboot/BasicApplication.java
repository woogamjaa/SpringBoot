package com.bs.basicboot;

import com.bs.basicboot.common.config.MyaBanner;
import com.bs.basicboot.common.config.properties.MyDataProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.Properties;


@SpringBootApplication
@EnableConfigurationProperties(MyDataProperties.class)
@Slf4j
public class BasicApplication implements CommandLineRunner {

    @Autowired
    private WebApplicationContext context;

    public static void main(String[] args) {
        //부트 프로그램 시작하는 곳
        //시작할때 Properties를 설정해줄 수 있음.
        Properties applicationProperties = new Properties();
        applicationProperties.setProperty("server.port", "9099");

        //SpringApplication클래스에서 제공하는 setDefaultProperties()메소드를 이용
        SpringApplication springApplication = new SpringApplication(BasicApplication.class);
        springApplication.setDefaultProperties(applicationProperties);
        //Banner 설정하기.
        springApplication.setBanner(new MyaBanner());
        springApplication.setBannerMode(Banner.Mode.LOG);



        springApplication.run(args);


//        SpringApplication.run(BasicApplication.class, args);
    }
    @Order(5)
    @Override
    public void run(String... args) throws Exception {
        log.info("CommandLineRunner 메소드 실행");

//        Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);

        Environment env = context.getBean(Environment.class);
        System.out.println(env);
    }
}

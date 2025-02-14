package com.bs.basicboot;

import com.bs.basicboot.common.config.MyaBanner;
import com.bs.basicboot.common.config.properties.MyDataProperties;
import com.bs.basicboot.model.dto.Member;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;

import java.util.Properties;
import java.util.Set;

@ServletComponentScan
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

    @Override
    public void run(String... args) throws Exception {
//        log.info("CommandLineRunner 메소드 실행");

//        Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);
//
//        Environment env = context.getBean(Environment.class);
//        System.out.println(env);

        Member m = Member.builder()
                .userId("bs")
                .password("1234")
                .birthday(LocalDate.of(1993, 8, 5)) // ?
                .age(31)
                .build();

        //유효성검사할 객체를 생성하기
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        //유효성 검사하기
        Set<ConstraintViolation<Member>> result = validator.validate(m);
        System.out.println(result);
        result.forEach(cons->{
            log.warn("대상값 : {}" ,cons.getInvalidValue());
            log.warn("대상값 : {}" ,cons.getMessage());
            log.warn("필드 : {}",cons.getPropertyPath().toString());
        });

        Member m2 = Member.builder()
                .userId("bslove")
                .password("1234abc")
                .birthday(LocalDate.of(1993, 8, 5)) // ?
                .age(31)
                .build();
        result=validator.validate(m2);
        result.forEach(cons->{
            log.warn("대상값 : {}" ,cons.getInvalidValue());
            log.warn("대상값 : {}" ,cons.getMessage());
            log.warn("필드 : {}",cons.getPropertyPath().toString());
        });
    }

}

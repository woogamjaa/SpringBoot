package com.bs.basicboot.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class WebMvcController {
    @Value("${mydata.test}")
    private String myData;

    @Value("${linux.home}")
    private String path;


    @Value("${linux.url}")
    private String id;


    @Value("${os.path}")
    private String envPath;

    @Value("${os.javahome}")
    private String javahome;

    @RequestMapping("/")
    public String index() {
        System.out.println("프로퍼티값 : "+myData);
        System.out.println("서버경로 : "+path);
        System.out.println("서버ip : "+id);
        System.out.println("자바홈  : "+javahome);
        System.out.println("환경경로 : "+envPath);

        return "index";
    }
}

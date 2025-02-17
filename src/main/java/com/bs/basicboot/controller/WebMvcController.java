package com.bs.basicboot.controller;

import com.bs.basicboot.common.config.properties.MyDataProperties;
import com.bs.basicboot.jpa.model.dto.JpaMember;
import com.bs.basicboot.model.dto.Demo;
import com.bs.basicboot.model.service.DemoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Controller
@Slf4j
public class WebMvcController {
    @Value("${mydata.test}")
    private String myData;

    @Value("${linux.home}")
    private String path;

    @Value("${linux.url}")
    private String ip;


    @Value("${os.path}")
    private String envPath;

    @Value("${os.javahome}")
    private String javahome;

    public final MyDataProperties myDataProperties;

    @RequestMapping("/")
    public String index() {
        System.out.println("프로퍼티값 : "+myData);
        System.out.println("서버경로 : "+path);
        System.out.println("서버ip : "+ip);
        System.out.println("자바홈  : "+javahome);
        System.out.println("환경경로 : "+envPath);
        System.out.println("myip : "+myDataProperties.getIp());
        System.out.println("myPort : "+myDataProperties.getPort());
        return "index.html";
    }

    @GetMapping("/")
    public String index(Model m) {
        m.addAttribute("name", "bslove");
        JpaMember member = JpaMember.builder()
                .userId("test")
                .password("test")
                .name("테스트")
                .age(44)
                .birthDay(LocalDate.now())
                .reservationDay(LocalDate.now())
                .build();
        m.addAttribute("member", member);
        List<String> team=List.of("우감자","이민영","김통통","이예진","최광훈","정다인");
        m.addAttribute("team", team);
        return "index";
    }


    private final DemoService service;

    @RequestMapping("/demo/demolist")
    public String demolist(Model model) {
        List<Demo> result=service.findAll();
        model.addAttribute("demos",result);
        log.info("demo조회결과 : {} ", result);
        return "demo/demoList";
    }

    @GetMapping("/member/{id}")
    public String member(@PathVariable String id) {
        return "member/member";
    }
}

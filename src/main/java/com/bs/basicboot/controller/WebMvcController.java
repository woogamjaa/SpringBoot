package com.bs.basicboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebMvcController {
    @RequestMapping("/")
    public String index() {
        return "index";
    }
}

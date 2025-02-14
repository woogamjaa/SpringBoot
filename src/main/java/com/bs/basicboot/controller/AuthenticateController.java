package com.bs.basicboot.controller;

import com.bs.basicboot.jpa.model.dao.JpaMemberRepository;
import com.bs.basicboot.jpa.model.dto.JpaMember;
import com.bs.basicboot.jpa.model.service.JpaMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthenticateController {

    private final JpaMemberService service;

    @PostMapping("/login.do")
    public ResponseEntity<Map> login(@RequestBody JpaMember m) {
        try {
            String token = service.loginService(m.getUserId(), m.getPassword());
            return ResponseEntity.ok().body(Map.of("token", token));
        } catch (BadCredentialsException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}

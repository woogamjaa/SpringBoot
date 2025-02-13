package com.bs.basicboot.controller;

import com.bs.basicboot.model.dto.Member;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController //@Controller , @ResponseBody
@RequestMapping("/member")
public class MemberRestController {

    private final Validator validator;

    @PostMapping
    public ResponseEntity saveMember(@RequestBody Member m) { //클라이언트가 보내는 파라미터 m
        //유효성 검사
       List<Map<String,String>> result= validator.validate(m).stream().map(cons->{
           return Map.of("msg", cons.getMessage(),
                   "property", cons.getPropertyPath().toString());
        }).toList();
       if(result.size()>0) {
           //유효성 검사 실패
           return ResponseEntity.badRequest().body(result);
       }else{
           //DB저장
           return ResponseEntity.ok(m);
       }
    }
}

package com.bs.basicboot.jpa.controller;


import com.bs.basicboot.jpa.model.dto.JpaMember;
import com.bs.basicboot.jpa.model.service.JpaMemberService;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@Controller
@RequestMapping("/api/member")
public class JpaMemberController {
    private final JpaMemberService service;
    private final Validator validator;

    @PostMapping
    public ResponseEntity saveMember(@RequestBody JpaMember member) {
        boolean result=service.addMember(member);
        if(result){
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public ResponseEntity member() {
        List<JpaMember> members=service.getMembers();
        if(members.isEmpty()){
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok().body(members);
        }
    }

    @GetMapping("/{no}")
    public ResponseEntity getMemberByNo(@PathVariable("no") Long no) {
        try{
            JpaMember findMember = service.getMemberByNo(no);
            return ResponseEntity.ok().body(service.getMemberByNo(no));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping
    public ResponseEntity updateMember(@RequestBody JpaMember m) {
        boolean result=service.updateMember(m);
        if(result){
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

//내코드
    @DeleteMapping("/{no}")
    public ResponseEntity deleteMember(@PathVariable("no") Long no) {
        boolean result=service.deleteMember(no);
        if(result){
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

//강사님코드
//    @DeleteMapping("/{no}")
//    public ResponseEntity deleteMember(@PathVariable Long no) {
//        try{
//            JpaMember findM=service.getMemberByNo(no);
//            boolean result=service.deleteMember(findM);
//            boolean result=service.deleteMember(no);
//
//            if(result){
//                return ResponseEntity.status(HttpStatus.OK).build();
//            }else{
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//            }
//
//        } catch (Exception e){
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//        }
//    }

}

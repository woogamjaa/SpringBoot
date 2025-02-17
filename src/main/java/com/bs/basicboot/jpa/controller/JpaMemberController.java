package com.bs.basicboot.jpa.controller;


import com.bs.basicboot.jpa.model.dto.JpaMember;
import com.bs.basicboot.jpa.model.service.JpaMemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.aspectj.weaver.tools.cache.SimpleCacheFactory.path;


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
    @Operation(summary = "회원 번호로 조회",
            description = "전달된 번호와 일치하는 회원정보를 반환")
    @ApiResponses(value={
            @ApiResponse(responseCode = "200", description = "번호와 일치하는 회원이 있음"),
            @ApiResponse(responseCode = "404", description = "번호와 일치하는 회원이 없음")
    })
    @GetMapping("/{no}")
    public ResponseEntity getMemberByNo(
            @Parameter(required = true, description = "조회할 번호." , example = "1")
            @PathVariable("no") Long no) {
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

    @GetMapping("/id")
    public ResponseEntity serachUserId(String userId) {
        return ResponseEntity.ok().body(service.getMemberById(userId));
    }

//    @GetMapping("/id")
//    public ResponseEntity serachUserAge(String userId){
//        return ResponseEntity.ok().body(service.getMemberById(userId));
//    }

    @GetMapping("/age")
    public ResponseEntity searchAge(Integer age) {
        return ResponseEntity.ok().body(service.getMemberByAge(age));
    }

    @PostMapping("/upload")
    public ResponseEntity fileUpload(
            //@RequestParam("")
            MultipartFile upfile) {
        System.out.println(upfile.getName());
        System.out.println(upfile.getOriginalFilename());
        System.out.println(upfile.getSize());

//        try{
//            upfile.transferTo(new File(path,upfile.getOriginalFilename()));
//        }catch(IOException e){
//            e.printStackTrace();
//        }

        return ResponseEntity.ok().build();
    }


}

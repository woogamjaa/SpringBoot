package com.bs.basicboot.jpa.model.service;

import com.bs.basicboot.common.config.token.JwtTokenUtils;
import com.bs.basicboot.jpa.model.dao.JpaMemberRepository;
import com.bs.basicboot.jpa.model.dto.JpaMember;
import com.bs.basicboot.jpa.model.entity.JapMemberEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class JpaMemberServiceImpl implements JpaMemberService {

    private final JwtTokenUtils jwtTokenUtils;
    private final JpaMemberRepository repository;
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    public boolean addMember(JpaMember m) {
        JapMemberEntity entity=JapMemberEntity.fromJpaMember(m);
        JapMemberEntity result=repository.save(entity);
        return result.getMemberNo()!=null;
    }

    @Override
    public boolean updateMember(JpaMember m) {
        try {
            repository.save(JapMemberEntity.fromJpaMember(m));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteMember(Long memberNo) {
        try{
            repository.delete(repository.findById(memberNo).get());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public List<JpaMember> getMembers() {
        return repository.findAll().stream()
                .map(entity->entity.toJpaMember()).toList();
    }

    @Override
    public JpaMember getMemberById(String userId) {
        return repository.findByUserId(userId).get().toJpaMember();
    }

    @Override
    public List<JpaMember> searchMember(Map param) {
        return List.of();
    }

    @Override
    public JpaMember getMemberByNo(Long memberNo) {
        return repository.findById(memberNo).orElse(new JapMemberEntity()).toJpaMember();
    }

    @Override
    public List<JpaMember> getMemberByAge(Integer age) {
        return repository.findByAgeGreaterThan(age).stream().map(entity->entity.toJpaMember()).toList();
    }

    @Override
    public String loginService(String userId, String password) {
        JpaMember loginMember=repository.findByUserId(userId).orElseThrow(()->{
            throw new BadCredentialsException("인증실패");
        }).toJpaMember();
        if(encoder.matches(password,loginMember.getPassword())){
            return jwtTokenUtils.generateToken(loginMember);
        }else{
            throw new BadCredentialsException("인증실패");
        }
    }
}

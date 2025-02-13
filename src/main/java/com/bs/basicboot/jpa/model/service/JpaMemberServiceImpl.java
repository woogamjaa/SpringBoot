package com.bs.basicboot.jpa.model.service;

import com.bs.basicboot.jpa.model.dao.JpaMemberRepository;
import com.bs.basicboot.jpa.model.dto.JpaMember;
import com.bs.basicboot.jpa.model.entity.JapMemberEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class JpaMemberServiceImpl implements JpaMemberService {

    private final JpaMemberRepository repository;

    @Override
    public boolean addMember(JpaMember m) {
        JapMemberEntity entity=JapMemberEntity.fromJpaMember(m);
        JapMemberEntity result=repository.save(entity);
        return result.getMemberNo()!=null;
    }

    @Override
    public boolean updateMember(JpaMember m) {
        return false;
    }

    @Override
    public boolean deleteMember(JpaMember m) {
        return false;
    }

    @Override
    public List<JpaMember> getMembers() {
        return List.of();
    }

    @Override
    public JpaMember getMemberById(String userId) {
        return null;
    }

    @Override
    public List<JpaMember> searchMember(Map param) {
        return List.of();
    }
}

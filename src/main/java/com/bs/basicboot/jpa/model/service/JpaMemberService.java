package com.bs.basicboot.jpa.model.service;

import com.bs.basicboot.jpa.model.dto.JpaMember;
import com.bs.basicboot.model.dto.Member;

import java.util.List;
import java.util.Map;

public interface JpaMemberService {
    boolean addMember(JpaMember m);
    boolean updateMember(JpaMember m);
    boolean deleteMember(JpaMember m);

    List<JpaMember> getMembers();
    JpaMember getMemberById(String userId);
    List<JpaMember> searchMember(Map param);

}

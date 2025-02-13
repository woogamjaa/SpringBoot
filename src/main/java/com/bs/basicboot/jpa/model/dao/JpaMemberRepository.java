package com.bs.basicboot.jpa.model.dao;

import com.bs.basicboot.jpa.model.entity.JapMemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaMemberRepository extends JpaRepository<JapMemberEntity,Long> {

}

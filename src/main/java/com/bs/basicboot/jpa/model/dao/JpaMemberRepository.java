package com.bs.basicboot.jpa.model.dao;

import com.bs.basicboot.jpa.model.entity.JapMemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JpaMemberRepository extends JpaRepository<JapMemberEntity,Long> {
    //쿼리메소드 선언하기
    //메소드명 : findBy필드명[연산자][OrderBy필드명ASC||DESC](...)
    Optional<JapMemberEntity> findByUserId(String userId);
    //select m from JapMemberEntity m where m.userId

    List<JapMemberEntity> findByAgeGreaterThan(Integer age);


}

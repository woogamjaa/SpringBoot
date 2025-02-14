package com.bs.basicboot.jpa.model.dao;

import com.bs.basicboot.jpa.model.entity.JapMemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Repository
public interface JpaMemberRepository extends JpaRepository<JapMemberEntity,Long> {
    //쿼리메소드 선언하기
    //메소드명 : findBy필드명[연산자][OrderBy필드명ASC||DESC](...)
    Optional<JapMemberEntity> findByUserId(String userId);
    //select m from JapMemberEntity m where m.userId

    List<JapMemberEntity> findByAgeGreaterThan(Integer age);

    //아이디와 나이를 조회하는 메소드
    //아이디는 포함, 나이는 이상인 조건
    List<JapMemberEntity> findByUserIdContainingAndAgeGreaterThan(String userId, Integer age);

    //생년월일이 이전인 회원을 조회
    List<JapMemberEntity> findByBrithDayLessThan(LocalDate birthDay);

    //반환값을 Stream설정할 수 있음
    Stream<JapMemberEntity> findByUserNameLike(String userName);

    List<JapMemberEntity> deleteByReservationDayBefore(LocalDate res);

    //직접 JPQL 구문 작성하기
    @Query("SELECT M FROM JapMemberEntity m")
    List<JapMemberEntity> selectAllMembers();

    @Query("SELECT M FROM JapMemberEntity m where m.age >=:age and m.userId like:userId")
    List<JapMemberEntity> selectMemberByAgeAndUserId(Integer age, String userId);


    @Query(value="select * FROM jap_MEMBER_ENTITY",nativeQuery = true)
    List<JapMemberEntity> selectNativeQuery();
}

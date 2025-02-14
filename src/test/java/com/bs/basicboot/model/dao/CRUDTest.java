package com.bs.basicboot.model.dao;

import com.bs.basicboot.jpa.model.dao.JpaMemberRepository;
import com.bs.basicboot.jpa.model.entity.JapMemberEntity;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Slf4j
//@DataJpaTest
////test클래스에서 데이터베이스를 자동으로 구성하도록 하는 어노테이션
////AUTO : 기본적으로 임베디드를 사용하고 없는경우 외부데이터베이스를 사용
////NONE : 데이터 베이스설정을 대체하지 않음
////ANY : 임베디드 데이터베이스 사용할때만 사용가능, 대체
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)

@SpringBootTest
public class CRUDTest {
        @Autowired
        private JpaMemberRepository repository;

        @Test
        void selectAllTest(){
            List<JapMemberEntity> result=repository.selectAllMembers();
            result.forEach(e-> log.debug("{}",e));

            //테스트의 결과를 확인하는 메소드
            assertNotNull(result);//null이 아니니 ?
            assertThat(result.size()).isEqualTo(10); //3번째꺼 임포트
        }

        @Test
        void selectSearch(){
            List<JapMemberEntity> result=
            repository.findByUserIdContainingAndAgeGreaterThan("u",22);
        }
}
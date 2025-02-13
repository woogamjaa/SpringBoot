package com.bs.basicboot.model.dao;

import com.bs.basicboot.model.dto.Demo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

import java.util.List;



@Mapper
public interface DemoMapper {
//    @Result(id="" , value={
//            @Result(property=, column= ),
//    })

    @Select("SELECT * FROM DEMO")
    List<Demo> findAll();

    @Insert("INSERT INTO DEMO VALUES (#{devName}, #{devGender},#{devAge},#{devLang})")
    public int insertDemo(Demo m);

}

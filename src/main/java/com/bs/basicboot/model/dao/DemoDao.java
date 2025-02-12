package com.bs.basicboot.model.dao;

import com.bs.basicboot.model.dto.Demo;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public interface DemoDao {
    List<Demo> findAll(SqlSession session);
}

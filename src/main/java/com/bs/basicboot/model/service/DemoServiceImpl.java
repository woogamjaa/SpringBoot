package com.bs.basicboot.model.service;

import com.bs.basicboot.model.dao.DemoDao;
import com.bs.basicboot.model.dto.Demo;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import java.util.List;
@RequiredArgsConstructor
@Service
public class DemoServiceImpl implements DemoService {
    private final SqlSession session;
    private final DemoDao dao;

    @Override
    public List<Demo> findAll() {
        return dao.findAll(session);
    }

    @Override
    public int savaDemo() {
        return 0;
    }
}

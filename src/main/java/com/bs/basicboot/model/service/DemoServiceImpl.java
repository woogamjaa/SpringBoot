package com.bs.basicboot.model.service;

import com.bs.basicboot.model.dto.Demo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DemoServiceImpl implements DemoService {
    @Override
    public List<Demo> findAll() {
        return List.of();
    }

    @Override
    public int savaDemo() {
        return 0;
    }
}

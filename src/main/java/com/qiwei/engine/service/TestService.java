package com.qiwei.engine.service;


import com.qiwei.engine.domain.Test;
import com.qiwei.engine.mapper.TestMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TestService {

    @Resource
    private TestMapper testMapper;

     public List<Test> list(){
         return testMapper.list();
     }
}

package com.qiwei.engine.service;


import com.qiwei.engine.domain.Demo;
import com.qiwei.engine.mapper.DemoMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DemoService {

    @Resource
    private DemoMapper demoMapper;

     public List<Demo> list(){
         return demoMapper.selectByExample(null);
     }
}

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


    /**
     * Save
     */
    public void save(Demo demo){
            //新增
            demoMapper.insert(demo);



    }

    public void update(Demo demo){
        demoMapper.updateByPrimaryKey(demo);

    }

}

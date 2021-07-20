package com.qiwei.engine;

import com.qiwei.engine.config.EngineApplication;
import com.qiwei.engine.domain.Demo;
import com.qiwei.engine.service.DemoService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest(classes = EngineApplication.class)
public class ServiceTests {

    @Resource
    DemoService demoService;

    @Test
    public void testDemo(){
        Demo demo = new Demo();
        long id = 8;
        demo.setId(id);
        demo.setName("t");
        demoService.save(demo);

    }

}

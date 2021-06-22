package com.qiwei.engine.controller;

import com.qiwei.engine.domain.Test;
import com.qiwei.engine.service.TestService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class TestController {

    @Resource
    private TestService testService;

    @RequestMapping("/hello")
    public String test(){
        return "hello word";
    }

    @GetMapping("/test/list")
    public List<Test> list(){
        return testService.list();
    }
}

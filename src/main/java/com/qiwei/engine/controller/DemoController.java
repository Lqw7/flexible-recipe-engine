package com.qiwei.engine.controller;


import com.qiwei.engine.domain.Demo;
import com.qiwei.engine.resp.CommonResp;
import com.qiwei.engine.service.DemoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/demo")
public class DemoController {

    @Resource
    private DemoService demoService;

    @GetMapping("/list")
    public List<Demo> list(){
        return demoService.list();
    }

    @ApiOperation(value = "save demo")
    @PostMapping("/save")
    public CommonResp save(@RequestBody Demo req){
        CommonResp resp = new CommonResp<>();
        demoService.save(req);
        return resp;
    }

    @ApiOperation(value = "demo update")
    @PostMapping("/update")
    public CommonResp update(@RequestBody Demo req){
        CommonResp resp = new CommonResp<>();
        demoService.update(req);
        return resp;
    }

}

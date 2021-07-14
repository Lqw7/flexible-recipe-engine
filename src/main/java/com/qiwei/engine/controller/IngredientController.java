package com.qiwei.engine.controller;

import com.qiwei.engine.req.IngredientReq;
import com.qiwei.engine.resp.CommonResp;
import com.qiwei.engine.resp.IngredientResp;
import com.qiwei.engine.service.IngredientService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@Api(value = "Ingredient Controller")
@RestController
@RequestMapping("/ingredient")
public class IngredientController {


    @Resource
    private IngredientService ingredientService;

    @ApiOperation(value = "search Substitution")
    @GetMapping("/searchSubstitution")
    public CommonResp searchSubstitution(IngredientReq req){
        CommonResp<List<IngredientResp>> resp = new CommonResp<>();
        List<IngredientResp> list = ingredientService.searchSubstitution(req);
        resp.setContent(list);
        return resp;
    }
}

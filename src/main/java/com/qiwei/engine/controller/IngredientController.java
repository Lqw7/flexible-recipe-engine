package com.qiwei.engine.controller;

import com.qiwei.engine.req.IngredientQueryReq;
import com.qiwei.engine.req.IngredientReq;
import com.qiwei.engine.req.IngredientSaveReq;
import com.qiwei.engine.resp.CommonResp;
import com.qiwei.engine.resp.IngredientResp;
import com.qiwei.engine.resp.PageResp;
import com.qiwei.engine.service.IngredientService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

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

    @ApiOperation(value = "get All Substitution List")
    @GetMapping("/getAllSubstitutionList")
    public CommonResp getAllSubstitutionList(IngredientQueryReq req){
        CommonResp<PageResp<IngredientResp>> resp = new CommonResp<>();
        PageResp<IngredientResp> list = ingredientService.getAllSubList(req);
        resp.setContent(list);
        return resp;
    }

    @ApiOperation(value = "save ingredient")
    @PostMapping("/save")
    public CommonResp save(@RequestBody IngredientSaveReq req){
        CommonResp resp = new CommonResp<>();
        ingredientService.save(req);
        return resp;
    }


    @ApiOperation(value = "delete ingredient")
    @DeleteMapping("/delete/{name}")
    public CommonResp delete(@PathVariable String name){
        CommonResp resp = new CommonResp<>();
        ingredientService.delete(name);
        return resp;
    }
}

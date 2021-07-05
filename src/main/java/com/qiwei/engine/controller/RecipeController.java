package com.qiwei.engine.controller;

import com.qiwei.engine.domain.Recipe;
import com.qiwei.engine.req.RecipeReq;
import com.qiwei.engine.resp.CommonResp;
import com.qiwei.engine.resp.RecipeResp;
import com.qiwei.engine.service.RecipeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Api(value = "Recipe Controller")
@RestController
@RequestMapping("/recipe")
public class RecipeController {

    @Resource
    private RecipeService recipeService;

    /**
     * get all recipe
     * @return
     */
    @ApiOperation(value = "get all list")
    @GetMapping("/list")
    public CommonResp list(){
        CommonResp<List<RecipeResp>> resp = new CommonResp<>();
        List<RecipeResp> list = recipeService.list();
        resp.setContent(list);
        return resp;
    }

    /**
     * search recipe by name
     * @param req
     * @return
     */
    @ApiOperation(value = "search By Name")
    @GetMapping("/searchByName")
    public CommonResp searchByName(RecipeReq req){
        CommonResp<List<RecipeResp>> resp = new CommonResp<>();
        List<RecipeResp> list = recipeService.searchByName(req);
        resp.setContent(list);
        return resp;
    }

    /**
     * search By Id
     * @param recipeReq
     * @return
     */
    @ApiOperation(value = "search By id")
    @GetMapping("/searchById")
    public CommonResp searchById(RecipeReq recipeReq){
        CommonResp<RecipeResp> resp = new CommonResp<>();
        RecipeResp recipeResp = recipeService.searchById(recipeReq);
        resp.setContent(recipeResp);
        return resp;
    }

    /**
     * search recipe by ingredients
     * @param ingredients
     * @return
     */
    @ApiOperation(value = "search By Ingredient")
    @GetMapping("/searchByIngredient")
    public CommonResp search(@RequestParam("ingredients") List<String> ingredients){
        CommonResp<List<RecipeResp>> resp = new CommonResp<>();
        List<RecipeResp> list = recipeService.searchByIngredient(ingredients);
        resp.setContent(list);
        return resp;
    }


    public List<Recipe> advancedSearch(List<String> compulsory, List<String> option) {
        return recipeService.advancedSearch(compulsory,option);
    }

}


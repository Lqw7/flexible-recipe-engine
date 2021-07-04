package com.qiwei.engine.controller;

import com.qiwei.engine.domain.Recipe;
import com.qiwei.engine.resp.CommonResp;
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
        CommonResp<List<Recipe>> resp = new CommonResp<>();
        List<Recipe> list = recipeService.list();
        resp.setContent(list);
        return resp;
    }

    /**
     * search recipe by name
     * @param name
     * @return
     */
    @ApiOperation(value = "search By Name")
    @GetMapping("/searchByName/{name}")
    public CommonResp searchByName(@PathVariable String name){
        CommonResp<List<Recipe>> resp = new CommonResp<>();
        List<Recipe> list = recipeService.searchByName(name);
        resp.setContent(list);
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
        CommonResp<List<Recipe>> resp = new CommonResp<>();
        List<Recipe> list = recipeService.searchByIngredient(ingredients);
        resp.setContent(list);
        return resp;
    }


    public List<Recipe> advancedSearch(List<String> compulsory, List<String> option) {
        return recipeService.advancedSearch(compulsory,option);
    }
    @ApiOperation(value = "search By id")
    @GetMapping("/searchByName")
    public Recipe searchByName(@RequestParam("id") Long id){
        return recipeService.searchById(id);
    }
}


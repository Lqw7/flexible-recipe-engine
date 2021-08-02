package com.qiwei.engine.controller;

import com.qiwei.engine.req.RecipeReq;
import com.qiwei.engine.resp.CommonResp;
import com.qiwei.engine.resp.RecipeResp;
import com.qiwei.engine.service.RecipeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
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
     * @param id
     * @return
     */
    @ApiOperation(value = "search By id")
    @GetMapping("/searchById/{id}")
    public CommonResp searchById(@PathVariable String id){
        CommonResp<List<RecipeResp>> resp = new CommonResp<>();
        List<String> list = Arrays.asList(id.split(","));
        List<RecipeResp> recipeResp = recipeService.searchById(list);
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
        if(!list.isEmpty()){
            resp.setContent(list);
        } else {
            resp.setContent(list);
            resp.setSuccess(false);
            resp.setMessage("No Data");
        }

        return resp;
    }

    /**
     * get Random Recipes
     * @return
     */
    @ApiOperation(value = "get Random Recipes")
    @GetMapping("/getRandomRecipes")
    public CommonResp getRandomRecipes(){
        CommonResp<List<RecipeResp>> resp = new CommonResp<>();
        List<RecipeResp> list = recipeService.getRandomRecipes();
        resp.setContent(list);
        return resp;

    }

    @ApiOperation(value = "advanced Search")
    @GetMapping("advancedSearch")
    public CommonResp advancedSearch(@RequestParam("necessary") List<String> necessary, @RequestParam("option") List<String> option){
        CommonResp<List<RecipeResp>> resp = new CommonResp<>();
        List<RecipeResp> list = recipeService.advancedSearch(necessary,option);
        if(!list.isEmpty()){
            resp.setContent(list);
        } else {
            resp.setContent(list);
            resp.setSuccess(false);
            resp.setMessage("No Data");
        }
        return resp;
    }

    @ApiOperation(value = "search")
    @GetMapping("search")
    public CommonResp search(@RequestParam("necessary") List<String> necessary, @RequestParam("option") List<String> option){
        CommonResp<List<RecipeResp>> resp = new CommonResp<>();
        List<RecipeResp> list = recipeService.search(necessary,option);
        resp.setContent(list);
        return resp;
    }

    @PostMapping ("/updateViewCount/{id}")
    public CommonResp findContent(@PathVariable Long id) {
        CommonResp resp = new CommonResp<>();
        recipeService.updateViewCount(id);
        return resp;
    }

}


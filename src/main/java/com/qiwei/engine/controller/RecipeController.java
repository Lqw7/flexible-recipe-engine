package com.qiwei.engine.controller;

import com.qiwei.engine.domain.Recipe;
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
    public List<Recipe> list(){
        return recipeService.list();
    }

    /**
     * search recipe by name
     * @param name
     * @return
     */
    @ApiOperation(value = "search By Name")
    @GetMapping("/searchByName/{name}")
    public List<Recipe> searchByName(@PathVariable String name){
        return recipeService.searchByName(name);
    }

    /**
     * search recipe by ingredients
     * @param ingredients
     * @return
     */
    @ApiOperation(value = "search By Ingredient")
    @GetMapping("/searchByIngredient")
    public List<Recipe> search(@RequestParam("ingredients") List<String> ingredients){
        return recipeService.searchByIngredient(ingredients);
    }


    public List<Recipe> advancedSearch(List<String> compulsory, List<String> option) {
        return recipeService.advancedSearch(compulsory,option);
    }
}


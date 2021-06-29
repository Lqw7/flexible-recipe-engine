package com.qiwei.engine.controller;

import com.qiwei.engine.domain.Recipe;
import com.qiwei.engine.service.RecipeService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/recipe")
public class RecipeController {

    @Resource
    private RecipeService recipeService;

    /**
     * get all recipe
     * @return
     */
    @GetMapping("/list")
    public List<Recipe> list(){
        return recipeService.list();
    }

    /**
     * search recipe by name
     * @param name
     * @return
     */
    @GetMapping("/searchByName/{name}")
    public List<Recipe> searchByName(@PathVariable String name){
        return recipeService.searchByName(name);
    }

    /**
     * search recipe by ingredients
     * @param ingredients
     * @return
     */
    @GetMapping("/searchByIngredient")
    public List<Recipe> search(@RequestParam("ingredients") List<String> ingredients){
        return recipeService.searchByIngredient(ingredients);
    }
}


package com.qiwei.engine.service;

import com.qiwei.engine.domain.Recipe;
import com.qiwei.engine.domain.RecipeExample;
import com.qiwei.engine.mapper.RecipeMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RecipeService {

    @Resource
    RecipeMapper recipeMapper;

    public List<Recipe> list(){
        return recipeMapper.selectByExample(null);
    }


    /**
     * search By Name
     * @param name
     * @return
     */
    public List<Recipe> searchByName(String name) {
        RecipeExample recipeExample = new RecipeExample();
        RecipeExample.Criteria criteria = recipeExample.createCriteria();
        criteria.andNameLike("%" + name + "%");
        return recipeMapper.selectByExample(recipeExample);
    }

    /**
     * search By Ingredient
     * @param ingredients
     * @return
     */
    public List<Recipe> searchByIngredient(List<String> ingredients){

        RecipeExample recipeExample = new RecipeExample();
        RecipeExample.Criteria criteria = recipeExample.createCriteria();

        for(String i: ingredients){
            criteria.andIngredientsLike("%" + i + "%");
        }
        return recipeMapper.selectByExample(recipeExample);
    }




}

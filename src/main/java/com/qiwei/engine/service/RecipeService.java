package com.qiwei.engine.service;

import com.qiwei.engine.domain.Recipe;
import com.qiwei.engine.domain.RecipeExample;
import com.qiwei.engine.mapper.RecipeMapper;
import com.qiwei.engine.req.RecipeReq;
import com.qiwei.engine.resp.RecipeResp;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class RecipeService {

    @Resource
    RecipeMapper recipeMapper;

    public List<RecipeResp> list(){
        List<Recipe> recipeList = recipeMapper.selectByExample(null);
        List<RecipeResp> respList = new ArrayList<>();
        for(Recipe recipe : recipeList){
            RecipeResp recipeResp = new RecipeResp();
            BeanUtils.copyProperties(recipe,recipeResp);
            respList.add(recipeResp);
        }
        return respList;
    }


    /**
     * search By Name
     * @param
     * @return
     */
    public List<Recipe> searchByName(RecipeReq req) {
        RecipeExample recipeExample = new RecipeExample();
        RecipeExample.Criteria criteria = recipeExample.createCriteria();
        criteria.andNameLike("%" + req.getName() + "%");
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


    public List<Recipe> advancedSearch(List<String> compulsory, List<String> option){
        RecipeExample recipeExample = new RecipeExample();
        RecipeExample.Criteria criteria = recipeExample.createCriteria();
        List<Recipe> result = new ArrayList<>();
        List<Recipe> resultCom = new ArrayList<>();
        List<Recipe> resultOpt = new ArrayList<>();
        for(String c: compulsory){
            criteria.andIngredientsLike("%" + c + "%");
        }
        resultCom = recipeMapper.selectByExample(recipeExample);
        if(!resultCom.isEmpty()){
             for(String o : option){
                 criteria.andIngredientsLike("%" + o + "%");
             }
        } else {
            result = resultCom;
        }
        return result;
    }


    public Recipe searchById(RecipeReq req){
          Recipe recipe =  recipeMapper.selectByPrimaryKey(req.getId());
          return recipe;
    }



}

package com.qiwei.engine.service;

import com.qiwei.engine.domain.Recipe;
import com.qiwei.engine.domain.RecipeExample;
import com.qiwei.engine.mapper.RecipeMapper;
import com.qiwei.engine.req.RecipeReq;
import com.qiwei.engine.resp.RecipeResp;
import com.qiwei.engine.util.CopyUtil;
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
        List<RecipeResp> list = CopyUtil.copyList(recipeList, RecipeResp.class);
        return list;
    }


    /**
     * search By Name
     * @param
     * @return
     */
    public List<RecipeResp> searchByName(RecipeReq req) {
        RecipeExample recipeExample = new RecipeExample();
        RecipeExample.Criteria criteria = recipeExample.createCriteria();
        criteria.andNameLike("%" + req.getName() + "%");
        List<Recipe> recipeList = recipeMapper.selectByExample(recipeExample);
        List<RecipeResp> list = CopyUtil.copyList(recipeList, RecipeResp.class);
        return list;
    }

    /**
     * search By Ingredient
     * @param ingredients
     * @return
     */
    public List<RecipeResp> searchByIngredient(List<String> ingredients){

        RecipeExample recipeExample = new RecipeExample();
        RecipeExample.Criteria criteria = recipeExample.createCriteria();

        for(String i: ingredients){
            criteria.andIngredientsLike("%" + i + "%");
        }
        List<Recipe> recipeList = recipeMapper.selectByExample(recipeExample);
        List<RecipeResp> list = CopyUtil.copyList(recipeList, RecipeResp.class);
        return list;
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


    public RecipeResp searchById(RecipeReq req){
        Recipe recipe = recipeMapper.selectByPrimaryKey(req.getId());
        RecipeResp recipeResp = CopyUtil.copy(recipe, RecipeResp.class);
        return recipeResp;
    }



}

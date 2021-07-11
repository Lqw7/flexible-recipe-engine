package com.qiwei.engine.service;

import com.qiwei.engine.domain.Recipe;
import com.qiwei.engine.domain.RecipeExample;
import com.qiwei.engine.mapper.RecipeMapper;
import com.qiwei.engine.req.RecipeReq;
import com.qiwei.engine.resp.RecipeResp;
import com.qiwei.engine.util.CopyUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class RecipeService {

    @Resource
    RecipeMapper recipeMapper;

    /**
     * get all recipes
     * @return
     */
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

    /**
     *
     * @param necessary necessary ingredients
     * @param option optional ingredients
     * @return
     */
    public List<RecipeResp> advancedSearch(List<String> necessary, List<String> option){
        RecipeExample recipeExample = new RecipeExample();
        RecipeExample.Criteria criteria = recipeExample.createCriteria();

        List<Recipe> searchResult = new ArrayList<>();
        //list is used to store final result
        List<RecipeResp> list = new ArrayList<>();

        //Add the necessary ingredients to the search criteria
        for(String n: necessary){
            criteria.andIngredientsLike("%" + n + "%");
        }

        //Search results for necessary use of ingredients
        List<Recipe> necessaryList = recipeMapper.selectByExample(recipeExample);

        Set<Recipe> searchSet = new HashSet();
         /*
        if(!necessaryList.isEmpty()){
            for(String o : option){

            }
        }*/

        if(!necessaryList.isEmpty()){
            //if Search results for necessary ingredients is not empty,
            //it ill continue to find out if the necessaryList contains optional ingredients
            for (Recipe r : necessaryList){
                for(String o : option){
                    if(r.getIngredients().contains(o)){
                        searchSet.add(r);
                    }
                }
            }
            if(!searchSet.isEmpty()){
                //if searchResult is not empty,it is returned as the final result
                for(Recipe r : searchSet){
                    searchResult.add(r);
                }
                list = CopyUtil.copyList(searchResult, RecipeResp.class);
            } else {
                //
                list = CopyUtil.copyList(necessaryList, RecipeResp.class);
            }
        } else {
            //if Search results for necessary ingredients is empty
            list = CopyUtil.copyList(necessaryList, RecipeResp.class);
        }

        return list;
    }

    /**
     * search By Id
     * @param req
     * @return
     */
    public RecipeResp searchById(RecipeReq req){
        Recipe recipe = recipeMapper.selectByPrimaryKey(req.getId());
        RecipeResp recipeResp = CopyUtil.copy(recipe, RecipeResp.class);
        return recipeResp;
    }

    /**
     * get Random Recipes
     * @return
     */
    public List<RecipeResp> getRandomRecipes(){
        Random random= new Random();
        Set<Integer> result= new LinkedHashSet<Integer>();
        while (result.size() < 10){
            Integer next = random.nextInt(879) + 1;
            result.add(next);
        }
        List<Recipe> data = new ArrayList<>();
        for(Integer i : result){
            Long l = i.longValue();
            data.add(recipeMapper.selectByPrimaryKey(l));
        }
        List<RecipeResp> list = CopyUtil.copyList(data, RecipeResp.class);
        return list;
    }


}

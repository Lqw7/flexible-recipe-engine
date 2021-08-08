package com.qiwei.engine.service;

import com.qiwei.engine.domain.Recipe;
import com.qiwei.engine.domain.RecipeExample;
import com.qiwei.engine.exception.BusinessException;
import com.qiwei.engine.exception.BusinessExceptionCode;
import com.qiwei.engine.mapper.RecipeMapper;
import com.qiwei.engine.mapper.RecipeMapperCust;
import com.qiwei.engine.req.RecipeReq;
import com.qiwei.engine.resp.RecipeResp;
import com.qiwei.engine.util.CopyUtil;
import com.qiwei.engine.util.RedisUtil;
import com.qiwei.engine.util.RequestContext;
import org.slf4j.MDC;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class RecipeService {

    @Resource
    RecipeMapper recipeMapper;

    @Resource
    RecipeMapperCust recipeMapperCust;

    @Resource
    RedisUtil redisUtil;

    @Resource
    public WsService wsService;

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
        /**
         * SELECT * FROM recipe
         * WHERE name LIKE 'ingredient';
         */
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
     * @param necessary ingredient
     * @param option ingredient
     * @return
     */
    public List<RecipeResp> search(List<String> necessary, List<String> option){
        List<RecipeResp> list = new ArrayList<>();
        if(necessary.isEmpty()){
            throw new BusinessException(BusinessExceptionCode.NECESSARY_EMPTY);
        } else {
            if(option.isEmpty()){
                list = searchByIngredient(necessary);
            } else {
                list = advancedSearch(necessary,option);
            }
        }
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

        //Search results for necessary ingredients
        List<Recipe> necessaryList = recipeMapper.selectByExample(recipeExample);
        //Search results for necessary and optional ingredients
        Set<Recipe> searchSet = new HashSet();
        if(!necessaryList.isEmpty()){
            //if Search results for necessary ingredients is not empty,
            //it will continue to find out if the necessaryList contains optional ingredients
            for (Recipe r : necessaryList){
                for(String o : option){
                    if(r.getIngredients().contains(o)){
                        searchSet.add(r);
                    }
                }
            }
            if(!searchSet.isEmpty()){
                //If the recipes contain optional ingredients, it is returned as the final result
                searchResult.addAll(searchSet);
                list = CopyUtil.copyList(searchResult, RecipeResp.class);
            } else {
                // If the recipe does not contain optional ingredients, return the necessaryList
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
     * @param ids
     * @return
     */
    public List<RecipeResp> searchById(List<String> ids){
        RecipeExample recipeExample = new RecipeExample();
        RecipeExample.Criteria criteria = recipeExample.createCriteria();
        criteria.andIdIn(ids);
        List<Recipe> recipeList = recipeMapper.selectByExample(recipeExample);
        List<RecipeResp> list = CopyUtil.copyList(recipeList, RecipeResp.class);
        return list;
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

    /**
     * updateViewCount
     */

    public void updateViewCount(Long id){
       recipeMapperCust.increaseViewCount(id);
    }

    /**
     * Like
     */
    public void vote(Long id) {
        // Remote IP and ID as Key, non-repeating for 24 hours
        String ip = RequestContext.getRemoteAddr();
        if (redisUtil.validateRepeat("RECIPE_VOTE_" + id + "_" + ip, 3600*24)) {
            recipeMapperCust.increaseVoteCount(id);
        } else {
            throw new BusinessException(BusinessExceptionCode.VOTE_REPEAT);
        }

        // Push messages
        Recipe recipeDb = recipeMapper.selectByPrimaryKey(id);
        String logId = MDC.get("LOG_ID");
        wsService.sendInfo("【" + recipeDb.getName() + "】be Liked", logId);
    }
}

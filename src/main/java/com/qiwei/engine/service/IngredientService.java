package com.qiwei.engine.service;

import com.qiwei.engine.domain.Ingredient;
import com.qiwei.engine.domain.IngredientExample;
import com.qiwei.engine.mapper.IngredientMapper;
import com.qiwei.engine.req.IngredientReq;
import com.qiwei.engine.resp.IngredientResp;
import com.qiwei.engine.util.CopyUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Iterator;
import java.util.List;

@Service
public class IngredientService {

    @Resource
    IngredientMapper ingredientMapper;
    @Resource
    RecipeService recipeService;


    public List<IngredientResp> searchSubstitution(IngredientReq req){
        List<Ingredient> substituteList = getSubstituteIngredient(req);
        List<IngredientResp> list = CopyUtil.copyList(substituteList, IngredientResp.class);
        return list;
    }


    public List<Ingredient> getSubstituteIngredient(IngredientReq req){
        Ingredient ingredient = ingredientMapper.selectByPrimaryKey(req.getName());
        IngredientExample ingredientExample = new IngredientExample();
        IngredientExample.Criteria criteria = ingredientExample.createCriteria();
        criteria.andCategoryEqualTo(ingredient.getCategory());
        List<Ingredient> substitutionsList = ingredientMapper.selectByExample(ingredientExample);
        Iterator<Ingredient> iterator = substitutionsList.iterator();
        while(iterator.hasNext()){
            Ingredient i = iterator.next();
            if(i.equals(ingredient)){
                iterator.remove();
            }
        }
        return substitutionsList;
    }

}

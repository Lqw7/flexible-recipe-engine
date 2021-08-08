package com.qiwei.engine.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qiwei.engine.domain.Ingredient;
import com.qiwei.engine.domain.IngredientExample;
import com.qiwei.engine.mapper.IngredientMapper;
import com.qiwei.engine.mapper.IngredientMapperCust;
import com.qiwei.engine.req.IngredientQueryReq;
import com.qiwei.engine.req.IngredientReq;
import com.qiwei.engine.req.IngredientSaveReq;
import com.qiwei.engine.resp.IngredientResp;
import com.qiwei.engine.resp.PageResp;
import com.qiwei.engine.util.CopyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.Iterator;
import java.util.List;

@Service
public class IngredientService {

    private static final Logger LOG = LoggerFactory.getLogger(IngredientService.class);

    @Resource
    IngredientMapper ingredientMapper;

    @Resource
    IngredientMapperCust ingredientMapperCust;


    public List<IngredientResp> searchSubstitution(IngredientReq req){
        List<Ingredient> substitutionsList = ingredientMapperCust.searchSubstitution(req.getName());
        //Remove the ingredient for the query
        Ingredient ingredient = ingredientMapper.selectByPrimaryKey(req.getName());
        Iterator<Ingredient> iterator = substitutionsList.iterator();
        while (iterator.hasNext()) {
            Ingredient i = iterator.next();
            if (i.equals(ingredient)) {
                iterator.remove();
            }
        }
        List<IngredientResp> list = CopyUtil.copyList(substitutionsList, IngredientResp.class);
        return list;
    }

    /**
     *
     * @return
     */
    public PageResp<IngredientResp> getAllSubList(IngredientQueryReq req){

        IngredientExample ingredientExample = new IngredientExample();
        IngredientExample.Criteria criteria = ingredientExample.createCriteria();
        if(!ObjectUtils.isEmpty(req.getCategory())){
            criteria.andCategoryLike("%" + req.getCategory() + "%");
        } else if(!ObjectUtils.isEmpty(req.getName())){
            criteria.andNameLike("%" + req.getName() + "%");
        }
        PageHelper.startPage(req.getPage(), req.getSize());
        ingredientExample.setOrderByClause("category asc");
        List<Ingredient> ingredientList = ingredientMapper.selectByExample(ingredientExample);

        PageInfo<Ingredient> pageInfo = new PageInfo<>(ingredientList);
        LOG.info("Total number of rows：{}", pageInfo.getTotal());
        LOG.info("Total number of pages：{}", pageInfo.getPages());

        List<IngredientResp> list = CopyUtil.copyList(ingredientList, IngredientResp.class);
        PageResp<IngredientResp> pageResp = new PageResp<>();
        pageResp.setTotal(pageInfo.getTotal());
        pageResp.setList(list);

        return pageResp;
    }

    public IngredientResp getIngredient(String name){
        Ingredient ingredient = ingredientMapper.selectByPrimaryKey(name);
        IngredientResp ingredientResp = CopyUtil.copy(ingredient, IngredientResp.class);
        return ingredientResp;
    }


    /**
     * save
     */
    public void save(IngredientSaveReq req){
        Ingredient ingredient = CopyUtil.copy(req,Ingredient.class);
        if(ObjectUtils.isEmpty(ingredientMapper.selectByPrimaryKey(req.getName()))){
            //add
            ingredientMapper.insert(ingredient);
        } else{
            //update
            ingredientMapper.updateByPrimaryKey(ingredient);
        }

    }


    /**
     * delete
     * @param name
     */
    public void delete(String name) {
        ingredientMapper.deleteByPrimaryKey(name);
    }

}

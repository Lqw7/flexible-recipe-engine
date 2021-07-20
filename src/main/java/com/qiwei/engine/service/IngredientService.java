package com.qiwei.engine.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qiwei.engine.domain.Ingredient;
import com.qiwei.engine.domain.IngredientExample;
import com.qiwei.engine.mapper.IngredientMapper;
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


    public List<IngredientResp> searchSubstitution(IngredientReq req){
        List<Ingredient> substituteList = getSubstituteIngredient(req);
        List<IngredientResp> list = CopyUtil.copyList(substituteList, IngredientResp.class);
        return list;
    }

    /**
     * get Substitute Ingredient
     * @param req
     * @return
     */
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

    /**
     *
     * @return
     */
    public PageResp<IngredientResp> getAllSubList(IngredientQueryReq req){

        IngredientExample ingredientExample = new IngredientExample();
        IngredientExample.Criteria criteria = ingredientExample.createCriteria();
        if(!ObjectUtils.isEmpty(req.getCategory())){
            criteria.andCategoryLike("%" + req.getCategory() + "%");
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

    /**
     * save
     */
    public void save(IngredientSaveReq req){
        Ingredient ingredient = CopyUtil.copy(req,Ingredient.class);
        if(ObjectUtils.isEmpty(ingredientMapper.selectByPrimaryKey(req.getName()))){
            //新增
            ingredientMapper.insert(ingredient);
        } else{
            //更新
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

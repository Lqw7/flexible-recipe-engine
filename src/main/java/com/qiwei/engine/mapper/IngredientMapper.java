package com.qiwei.engine.mapper;

import com.qiwei.engine.domain.Ingredient;
import com.qiwei.engine.domain.IngredientExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IngredientMapper {
    long countByExample(IngredientExample example);

    int deleteByExample(IngredientExample example);

    int deleteByPrimaryKey(String name);

    int insert(Ingredient record);

    int insertSelective(Ingredient record);

    List<Ingredient> selectByExample(IngredientExample example);

    Ingredient selectByPrimaryKey(String name);

    int updateByExampleSelective(@Param("record") Ingredient record, @Param("example") IngredientExample example);

    int updateByExample(@Param("record") Ingredient record, @Param("example") IngredientExample example);

    int updateByPrimaryKeySelective(Ingredient record);

    int updateByPrimaryKey(Ingredient record);
}
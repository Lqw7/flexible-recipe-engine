package com.qiwei.engine.mapper;

import com.qiwei.engine.domain.Recipe;
import com.qiwei.engine.domain.RecipeExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RecipeMapper {
    long countByExample(RecipeExample example);

    int deleteByExample(RecipeExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Recipe record);

    int insertSelective(Recipe record);

    List<Recipe> selectByExample(RecipeExample example);

    Recipe selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Recipe record, @Param("example") RecipeExample example);

    int updateByExample(@Param("record") Recipe record, @Param("example") RecipeExample example);

    int updateByPrimaryKeySelective(Recipe record);

    int updateByPrimaryKey(Recipe record);
}
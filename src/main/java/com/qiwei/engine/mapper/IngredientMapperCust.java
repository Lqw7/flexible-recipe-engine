package com.qiwei.engine.mapper;

import com.qiwei.engine.domain.Ingredient;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IngredientMapperCust {

    public List<Ingredient> searchSubstitution(@Param("name") String name);
}

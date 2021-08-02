package com.qiwei.engine.mapper;

import org.apache.ibatis.annotations.Param;

public interface RecipeMapperCust {


    public void increaseViewCount(@Param("id") Long id);

    public void increaseVoteCount(@Param("id") Long id);
}

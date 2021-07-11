package com.qiwei.engine;

import com.qiwei.engine.config.EngineApplication;
import com.qiwei.engine.resp.RecipeResp;
import com.qiwei.engine.service.RecipeService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest(classes = EngineApplication.class)
public class ServiceTests {

    @Resource
    RecipeService recipeService;

    @Test
    public void testAdvancedSearch(){
        List<String> necessary = new ArrayList<>();
        List<String> option = new ArrayList<>();
        necessary.add("Eggs");
        necessary.add("Mayonnaise");
        option.add("Vinegar");
        option.add("Steaks");
        List<RecipeResp> list = recipeService.advancedSearch(necessary,option);
        System.out.println(list);
    }

}

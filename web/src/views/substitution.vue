<template>
  <div class="about">
    <div id="title">
      <p>Find Substitute ingredients</p>
    </div>
    <div id="select">
      <a-select
          v-model:value ="value"
          mode="tags"
          style="width: 100%"
          placeholder="input ingredients"
          @change="handleChange"
      >
      </a-select>
      <div id="result">
        <a-tag v-for="item in ingredient" v-bind:key="item.name" @click="query(item.name)">{{item.name}}</a-tag>
      </div>
    </div>
    <a-list item-layout="vertical" size="large" :data-source="recipe">
      <template #renderItem="{ item }">
        <a-list-item key="item.title">
          <template #actions>
         <span>
                <component v-bind:is="'RiseOutlined'" style="margin-right: 8px"/>
                {{ item.viewCount }}
              </span>
            <span>
                <component v-bind:is="'LikeOutlined'" style="margin-right: 8px"/>
                {{ item.voteCount }}
              </span>
          </template>
          <template #extra>
            <img
                width="272"
                alt="logo"
                :src="item.image"
                @error="imgError(item)"
            />
          </template>
          <a-list-item-meta :description="item.description" >
            <template #title>
              <a :href="item.url" target="_blank" @click="updateViewCount(item.id)">{{ item.name }}</a>
            </template>
          </a-list-item-meta>
          {{ item.ingredients }}
        </a-list-item>
      </template>
    </a-list>
  </div>
</template>



<script lang="ts">
import {LikeOutlined, RiseOutlined} from '@ant-design/icons-vue';
import { defineComponent, onMounted, ref } from 'vue';
import axios from 'axios';

export default defineComponent({
  components: {
    LikeOutlined,
    RiseOutlined
  },
  setup() {
    const recipe = ref();
    const ingredient = ref();
    const s = ref();
    let globalSearch : string;

    const handleChange = (value: string[]) => {
      console.log(`selected ${value}`);
      if(value.length == 0){
        recipe.value = null;
        ingredient.value = null;
      } else{
        query(value[0]);
        axios.get("/ingredient/searchSubstitution?name=" + value).then((response: any) => {
          console.log(response);
          globalSearch = value[0];
          const data = response.data;
          ingredient.value = null;
          ingredient.value = data.content;
        });
      }
    }

    const query = (name: string) => {
      console.log(`selected ${name}`);
      axios.get("/recipe/searchByIngredient?ingredients=" + name).then((response: any) => {
        console.log(response)
        globalSearch = name;
        const data = response.data;
        recipe.value = null;
        recipe.value = data.content;
      });
    };

    /**
     * imgerror
     */
    const imgError = (item:any) => {
      item.image = "https://realfood.tesco.com/media/images/RFO-380x250-Sri-Lankan-style-sweet-potato-curry-01715a97-f294-44c7-9789-e5db773f55f5-0-380x250.jpg";
    };

    /**
     * updateViewCount
     * @param id
     */
    const updateViewCount = (id:any) => {
      console.log(id);
      axios.post("/recipe/updateViewCount/" + id).then((response: any) => {
        console.log(response);
        query(globalSearch);
      });
    };
    return {
      recipe,
      ingredient,
      query,
      handleChange,
      updateViewCount,
      imgError

    };
  },
});
</script>



<style scoped>
.about{
  width: 1000px;
  height: auto;
  margin: auto;
}

#title p{
  font-size: 34px;
  font-weight: bold;
  text-align: center;
  font-family: "Microsoft YaHei";
  color: #141414;
  margin-top: 80px;
}
#select{
  height: 120px;
  width:650px;
  margin: 0 auto;
}
#result {
  margin-top: 10px;
  margin-right: 10px;
}
#result a{
  float: left;
  padding: 15px;
}
</style>
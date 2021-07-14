<template>
  <div class="about">
    <div id="title">
      <p>Find Substitute ingredients</p>
    </div>
    <div id="select1">
      <a-select
          v-model:value ="value"
          mode="tags"
          style="width: 100%"
          placeholder="input ingredients"
          @change="handleChange"
      >
      </a-select>
      <div id="result">
        <a v-for="item in ingredient" v-bind:key="item.name" @click="query(item.name)">{{item.name}}</a>
      </div>
    </div>
    <a-list item-layout="vertical" size="large" :data-source="recipe">
      <template #renderItem="{ item }">
        <a-list-item key="item.title">
          <template #actions>
          <span v-for="{ type, text } in actions" :key="type">
            <component v-bind:is="type" style="margin-right: 8px" />
            {{ text }}
          </span>
          </template>
          <template #extra>
            <img
                width="272"
                alt="logo"
                :src="item.image"
            />
          </template>
          <a-list-item-meta :description="item.description">
            <template #title>
              <a :href="item.url" target="_blank">{{ item.name }}</a>
            </template>
          </a-list-item-meta>
          {{ item.ingredients }}
        </a-list-item>
      </template>
    </a-list>
  </div>
</template>



<script lang="ts">
import { StarOutlined, LikeOutlined, MessageOutlined } from '@ant-design/icons-vue';
import { defineComponent, onMounted, ref } from 'vue';
import axios from 'axios';

export default defineComponent({
  components: {
    StarOutlined,
    LikeOutlined,
    MessageOutlined,
  },
  setup() {
    const recipe = ref();
    const ingredient = ref();

    const handleChange = (value: string[]) => {
      console.log(`selected ${value}`);
      if(value.length == 0){
        recipe.value = null;
        ingredient.value = null;
      } else{
        axios.get("/recipe/searchByIngredient?ingredients=" + value).then((response: any) => {
          console.log(response)
          const data = response.data;
          recipe.value = null;
          recipe.value = data.content;
        });
        axios.get("/ingredient/searchSubstitution?name=" + value).then((response: any) => {
          console.log(response)
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
        const data = response.data;
        recipe.value = null;
        recipe.value = data.content;
      });
    };


    const actions: Record<string, string>[] = [
      { type: 'StarOutlined', text: '156' },
      { type: 'LikeOutlined', text: '156' },
      { type: 'MessageOutlined', text: '2' },
    ];
    return {
      recipe,
      ingredient,
      query,
      handleChange,
      actions

    };
  },
});
</script>



<style>
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
#select1{
  height: 120px;
  width:650px;
  margin: 0 auto;
}

#result a{
  float: left;
  padding: 15px;
}
</style>
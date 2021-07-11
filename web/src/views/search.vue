<template>
  <a-layout>
      <div id="title">
        <p>Find recipes by ingredients</p>
      </div>
      <div id="select1">
        <a-select
            v-model:value="value"
            mode="tags"
            style="width: 100%"
            placeholder="input ingredients"
            @change="handleChange"
        >
        </a-select>
      </div>
    <div id="result">
      <a-list item-layout="vertical" size="large" :pagination="pagination" :data-source="recipe">
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
  </a-layout>
</template>

<script lang="ts">
import axios from 'axios';
import {defineComponent, onMounted, ref} from 'vue';
import { StarOutlined, LikeOutlined, MessageOutlined } from '@ant-design/icons-vue';

export default defineComponent({
  components: {
    StarOutlined,
    LikeOutlined,
    MessageOutlined,
  },

  setup() {
    const recipe = ref();
    onMounted(() => {
      console.log("onMounted")
      axios.get("/recipe/getRandomRecipes").then((response: any) => {
        console.log(response)
        const data = response.data;
        recipe.value = data.content;
      });

    });

    const handleChange = (value: string[]) => {
      console.log(`selected ${value}`);
      axios.get("/recipe/searchByIngredient?ingredients=" + value).then((response: any) => {
        console.log(response)
        const data = response.data;
        recipe.value = null;
        recipe.value = data.content;
      });

    }

    const actions: Record<string, string>[] = [
      { type: 'StarOutlined', text: '156' },
      { type: 'LikeOutlined', text: '156' },
      { type: 'MessageOutlined', text: '2' },
    ];

    return {
      recipe,
      handleChange,
      actions
    };
  },
});
</script>



<style>
#title p{
  font-size: 34px;
  font-weight: bold;
  text-align: center;
  font-family: "Microsoft YaHei";
  color: #141414;
  margin-top: 80px;
}
#test1 p{
  font-size: 25px;
  font-weight: bold;
  text-align: center;
  font-family: "Microsoft YaHei";
  color: #141414;
  margin-top: 10px;
}
#select1{
  height: 120px;
  width:650px;
  margin: 0 auto;
}

#result{
  width:1250px;
  margin: 0 auto;
}

</style>
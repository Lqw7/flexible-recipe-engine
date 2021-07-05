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
            placeholder="Tags Mode"
            @change="handleChange"
        >
          <a-select-option v-for="i in 25" :key="(i + 9).toString(36) + i">
            {{ (i + 9).toString(36) + i }}
          </a-select-option>
        </a-select>
      </div>
    <div id="result">
      <a-list size="large" item-layout="horizontal" :data-source="recipe">
        <template #renderItem="{ item }">
          <a-list-item>
            <a-list-item-meta
                :description="item.ingredients"
            >
              <template #title>
                <a :href="item.url" target="_blank">{{ item.name }}</a>
              </template>
              <template #avatar>
                <a-avatar :src="item.image" />
              </template>
            </a-list-item-meta>
          </a-list-item>
        </template>
      </a-list>
    </div>
  </a-layout>
</template>

<script lang="ts">
import axios from 'axios';
import {defineComponent, onMounted, ref} from 'vue';

export default defineComponent({

  setup() {
    const recipe = ref();
    onMounted(() => {
      console.log("onMounted")
      axios.get("http://127.0.0.1:8888/recipe/searchByName?name=Sandwich").then((response: any) => {
        console.log(response)
        const data = response.data;
        recipe.value = data.content;
      });

    });

    const handleChange = (value: string[]) => {
      console.log(`selected ${value}`);
      axios.get("http://127.0.0.1:8888/recipe/searchByIngredient?ingredients=" + value).then((response: any) => {
        console.log(response)
        const data = response.data;
        recipe.value = data.content;
      });

    }


    return {
      recipe,
      handleChange
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
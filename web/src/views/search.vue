<template>
  <a-layout>
      <div id="title">
        <p>Find recipes by ingredients</p>
      </div>
      <div id="search" >
        <a-input-search
            v-model:value="value"
            placeholder="input ingredient"
            enter-button="Add"
            size="large"
            @search="onSearch"
        />
      </div>
      <div id="test1">
        <p>test</p>
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
      <div id="select2">
        <a-select
            mode="multiple"
            :size="size"
            placeholder="Please select"
            v-model:value="value2"
            style="width: 200px"
            @popupScroll="popupScroll"
        >
          <a-select-option v-for="i in 25" :key="(i + 9).toString(36) + i">
            {{ (i + 9).toString(36) + i }}
          </a-select-option>
        </a-select>
      </div>
    <div id="result">
      <pre>
        {{recipe}}
      </pre>
    </div>
  </a-layout>
</template>

<script lang="ts">
import axios from 'axios';
import {defineComponent, onMounted, ref} from 'vue';

export default defineComponent({

  setup() {
    const recipe = ref();
    const value = ref<string>('');
    const onSearch = (searchValue: string) => {
      console.log('use value', searchValue);
      console.log('or use this.value', value.value);
    };
    onMounted(() => {
      console.log("onMounted")
      axios.get("http://localhost:8888/recipe/searchByName?id=1").then((response: any) => {
        console.log(response)
        const data = response.data;
        recipe.value = data.content;
      });

    });


    return {
      recipe,
      value,
      onSearch,
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
#search{
  height: 100px;
  width:650px;
  margin: 0 auto;
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

#select2{
  height: 120px;
  width:550px;
  margin: 0 auto;
}

</style>
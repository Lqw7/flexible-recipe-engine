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
  </a-layout>
</template>

<script lang="ts">
import { defineComponent, ref } from 'vue';
export default defineComponent({
  setup() {
    const value = ref<string>('');
    const onSearch = (searchValue: string) => {
      console.log('use value', searchValue);
      console.log('or use this.value', value.value);
    };

    return {
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
</style>
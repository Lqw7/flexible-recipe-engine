<template>
  <div class="about">
    <a-list item-layout="horizontal" :data-source="recipe">
      <template #renderItem="{ item }">
        <a-list-item>
          <a-list-item-meta
              :description="item.description"
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
</template>

<script>
import axios from 'axios';
import {defineComponent, onMounted, ref} from 'vue';
export default defineComponent({

  setup() {
    const recipe = ref();
    onMounted(() => {
      console.log("onMounted")
      axios.get("http://127.0.0.1:8888/recipe/searchByName?name=Sandwich").then((response) => {
        console.log(response)
        const data = response.data;
        recipe.value = data.content;
      });

    });
    return {
      recipe,
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
</style>
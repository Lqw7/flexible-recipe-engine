<template>
  <div class="about">
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
    onMounted(() => {
      console.log("onMounted")
      axios.get("http://127.0.0.1:8888/recipe/searchByName?name=Sandwich").then((response) => {
        console.log(response)
        const data = response.data;
        recipe.value = data.content;
      });
    });

    const actions: Record<string, string>[] = [
      { type: 'StarOutlined', text: '156' },
      { type: 'LikeOutlined', text: '156' },
      { type: 'MessageOutlined', text: '2' },
    ];
    return {
      recipe,
      actions,
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
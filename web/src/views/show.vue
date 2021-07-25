<template>
  <div id="title">
    <pre>{{ name }} Recipe</pre></div>
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
                @error="imgError(item)"
                width="272"
                :src="item.image"
                alt="logo"
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
import axios from 'axios';
import {defineComponent, onMounted, ref} from 'vue';
import { StarOutlined, LikeOutlined, MessageOutlined } from '@ant-design/icons-vue';
import {useRoute} from "vue-router";

export default defineComponent({
  components: {
    StarOutlined,
    LikeOutlined,
    MessageOutlined,
  },

  setup() {
    const recipe = ref();
    const route = useRoute();
    const name = ref<String>();
    name.value = route.query.name

    onMounted(() => {
      console.log("onMounted")
      axios.get("/recipe/searchByName?name=" + route.query.name).then((response: any) => {
        console.log(response)
        const data = response.data;
        recipe.value = data.content;
      });

    });


    /**
     * imgerror
     */
    const imgError = (item:any) => {
      item.image = "https://realfood.tesco.com/media/images/RFO-380x250-Sri-Lankan-style-sweet-potato-curry-01715a97-f294-44c7-9789-e5db773f55f5-0-380x250.jpg";
    };

    const actions: Record<string, string>[] = [
      { type: 'StarOutlined', text: '156' },
      { type: 'LikeOutlined', text: '156' },
      { type: 'MessageOutlined', text: '2' },
    ];

    return {
      name,
      recipe,
      actions,
      imgError
    };
  },
});
</script>
<style>
#title {
  height: 100px;
  width: 200px;
  margin: 0 auto;
  background-color: aqua;
}
#title pre{
  font-size: 30px;
  font-weight: bold;
  text-align: center;
  font-family: "Microsoft YaHei";
  color: #141414;
  margin-top: 30px;
}
</style>
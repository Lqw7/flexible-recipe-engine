<template>
  <div id="recipe-title">
    <pre>{{ name }} Recipe</pre></div>
  <div id="result">
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
                @error="imgError(item)"
                width="272"
                :src="item.image"
                alt="logo"
            />
          </template>
          <a-list-item-meta :description="item.description">
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
import axios from 'axios';
import {defineComponent, onMounted, ref} from 'vue';
import {LikeOutlined,RiseOutlined } from '@ant-design/icons-vue';
import {useRoute} from "vue-router";

export default defineComponent({
  components: {
    LikeOutlined,
    RiseOutlined
  },

  setup() {
    const recipe = ref();
    const route = useRoute();
    const name = ref();
    name.value = route.query.name

    onMounted(() => {
      console.log("onMounted")
      query();

    });

    /**
     * query
     */
    const query = () => {
      axios.get("/recipe/searchByName?name=" + route.query.name).then((response: any) => {
        console.log(response)
        const data = response.data;
        recipe.value = data.content;
      });
    };


    /**
     * imgerror
     */
    const imgError = (item:any) => {
      item.image = "https://realfood.tesco.com/media/images/RFO-380x250-Sri-Lankan-style-sweet-potato-curry-01715a97-f294-44c7-9789-e5db773f55f5-0-380x250.jpg";
    };

    const actions: Record<string, string>[] = [
      { type: 'RiseOutlined', text: '2' },
      { type: 'LikeOutlined', text: '156' },

    ];

    /**
     * updateViewCount
     * @param id
     */
    const updateViewCount = (id:any) => {
      console.log(id);
      axios.post("/recipe/updateViewCount/" + id).then((response: any) => {
        console.log(response)
        query();
      });
    };

    return {
      name,
      recipe,
      actions,
      imgError,
      updateViewCount
    };
  },
});
</script>
<style scoped>
#recipe-title {
  height: 100px;
  width: 200px;
  margin: 0 auto;
}
#recipe-title pre{
  font-size: 30px;
  font-weight: bold;
  text-align: center;
  font-family: "Microsoft YaHei";
  color: #141414;
  margin-top: 30px;
}
#result{
  width:1250px;
  margin: 0 auto;
}
</style>
<template>
  <a-layout>
      <div id="title">
        <p>Find recipes by ingredients</p>
      </div>
      <div id="select1">
        <p>Necessary ingredient</p>
        <a-select
            v-model:value ="necessary"
            mode="tags"
            style="width: 100%"
            placeholder="input necessary ingredients"
            @change="necessaryChange"

        >
        </a-select>
      </div>
    <div id="select2">
      <p>Optional ingredient</p>
      <a-select
          v-model:value="option"
          mode="tags"
          style="width: 100%"
          placeholder="input optional ingredients"
          @change="optionChange"
      >
      </a-select>
    </div>
    <div id="result">
      <a-list item-layout="vertical" size="large" :pagination="pagination" :data-source="recipe">
        <template #renderItem="{ item }">
          <a-list-item key="item.title">
            <template #actions>
             <span>
                <component v-bind:is="'RiseOutlined'" style="margin-right: 8px"/>
                {{ item.viewCount }}
              </span>
              <span>
                <component v-bind:is="'LikeOutlined'" style="margin-right: 8px" @click="vote(item.id)"/>
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
  </a-layout>
</template>

<script lang="ts">
import axios from 'axios';
import {message} from 'ant-design-vue';
import {defineComponent, onMounted, ref} from 'vue';
import { LikeOutlined, RiseOutlined} from '@ant-design/icons-vue';
import {Tool} from "@/util/tool";

export default defineComponent({
  components: {
    LikeOutlined,
    RiseOutlined
  },

  setup() {
    const recipe = ref();
    let nece : string[];
    let opt : string[];
    onMounted(() => {
      console.log("onMounted")
    });

    const necessaryChange = (necessary: string[]) => {
        nece = necessary;
        search(necessary,opt)
    }

    const optionChange = (option: string[]) => {
      opt = option;
      search(nece,option)
    }


    const search = (necessary: string[],option: string[]) => {
      axios.get("/recipe/search?necessary=" + nece +"&option=" + option).then((response: any) => {
        console.log(response)
        const data = response.data;
        if(data.success){
          recipe.value = null;
          recipe.value = data.content;
        } else {
          message.error(data.message);
        }
      });
    }

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
        console.log(response)
        search(nece,opt)
      });
    };


    const vote = (id:any) => {
      console.log("vote:" + id);
      axios.get('/recipe/vote/' + id).then((response) => {
        const data = response.data;
        if (data.success) {
          search(nece,opt)
        } else {
          message.error(data.message);
        }
      });
    };
    return {
      recipe,
      necessaryChange,
      optionChange,
      imgError,
      updateViewCount,
      vote
    };
  },
});
</script>



<style scoped>
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
#select2{
  height: 120px;
  width:650px;
  margin: 0 auto;
}
#result{
  width:1250px;
  margin: 0 auto;
}
#select1 p{
  font-size: 16px;
  font-weight: bold;
  text-align: left;
  font-family: "Microsoft YaHei";
  color: #141414;
}

#select2 p{
  font-size: 16px;
  font-weight: bold;
  text-align: left;
  font-family: "Microsoft YaHei";
  color: #141414;
}
</style>
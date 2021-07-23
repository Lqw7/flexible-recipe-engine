<template>
  <a-layout-content >
    <div id="carousel-box" style="margin-top: 50px">
      <a-carousel arrows>
        <template #prevArrow>
          <div class="custom-slick-arrow" style="left: 10px; zindex: 1">
            <left-circle-outlined />
          </div>
        </template>
        <template #nextArrow>
          <div class="custom-slick-arrow" style="right: 10px">
            <right-circle-outlined />
          </div>
        </template>
        <div><img src="https://realfood.tesco.com/media/images/1400x919-Summer-veg-casserole-6284f2af-5584-49b1-b946-3694aeeb5f63-0-1400x919.jpg"></div>
        <div><img src="https://realfood.tesco.com/media/images/1400x919-Cheesecake-081e8326-40b9-4ab7-b843-752ac736c188-0-1400x919.jpg"></div>
        <div><img src="https://realfood.tesco.com/media/images/1400x919-Fish-finger-panini-d46b8bd4-f1f8-4773-b2f1-e9ec31f2bd01-0-1400x919.jpg"></div>
        <div><img src="https://realfood.tesco.com/media/images/1400x919-Cheesy-broccoli-calzones-a923256b-deeb-4d9c-951b-4d4312b8ec84-0-1400x919.jpg"></div>
      </a-carousel>
    </div>
    <div id="popular">
      <p>Popular Recipe</p>
    </div>
    <div id="recipe">
      <div class="card" v-for="item in columns" v-bind:key="item.title">
        <a-card hoverable style="width: 250px"  >
          <template #cover>
            <img alt="example" :src="item.src" />
          </template>
          <a-card-meta :title="item.title">
          </a-card-meta>
        </a-card>
      </div>
    </div>
  </a-layout-content>
</template>

<script lang="ts">
import { LeftCircleOutlined, RightCircleOutlined } from '@ant-design/icons-vue';
import { defineComponent,ref } from 'vue';
import router from "@/router";
export default defineComponent({
  components: {
    LeftCircleOutlined,
    RightCircleOutlined,
  },

  setup(){


    const columns = [
      {
        title: 'Curry recipes',
        src: 'https://realfood.tesco.com/media/images/RFO-380x250-Sri-Lankan-style-sweet-potato-curry-01715a97-f294-44c7-9789-e5db773f55f5-0-380x250.jpg'
      },
      {
        title: 'Pizza recipes',
        src: 'https://realfood.tesco.com/media/images/RFO-380x250-Sunshine-pizza-25662a25-7865-4df5-ae78-68c348b921c9-0-380x250.jpg'
      },
      {
        title: 'Bread recipes',
        src: 'https://realfood.tesco.com/media/images/RFO-380x250-Cornish-potato-and-rosemary-focaccia-520446b3-1e5d-4135-b837-9a4c0778e7fe-0-380x250.jpg'
      },
      {
        title: 'Salad recipes',
        src: 'https://realfood.tesco.com/media/images/RFO-380x250-CobbSalad-05924e5c-387f-4829-b7d6-972a208c640b-0-380x250.jpg'
      }
    ];

    return {
      columns
    }
  },

});

</script>
<style scoped>
/* For demo */
.ant-carousel :deep(.slick-slide) {
  text-align: center;
  height: 500px;
  line-height: 160px;
  background: #364d79;
  overflow: visible;
}

.ant-carousel :deep(.slick-arrow.custom-slick-arrow) {
  width: 40px;
  height: 40px;
  font-size: 40px;
  color: #001529;
  background-color: rgba(31, 45, 61, 0.11);
}
.ant-carousel :deep(.custom-slick-arrow:before) {
  display: none;
}
.ant-carousel :deep(.custom-slick-arrow:hover) {
  opacity: 0.5;
}

.ant-carousel :deep(.slick-slide h3) {
  color: #fff;
}

#carousel-box{
  width: 1100px;
  height: 500px;
  margin: 0 auto;
}
#carousel-box img{
  width: 1100px;
  height: 500px;
}
#popular {
  margin-top: 60px;
}
#popular p{
  font-weight: bold;
  font-family: "Microsoft YaHei";
  font-size: 28px;
  color: #141414;
  text-align: center;
}
#recipe {
  width: 1200px;
  height: 250px;
  margin: 0 auto;
}
.card {
  position: relative;
  float: left;
  margin-left: 40px;
}
</style>

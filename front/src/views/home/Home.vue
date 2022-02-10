<!--
* @Descripttion:
* @Project: outage
* @Author: yaoMinC
* @Date: 10/2/2022
-->
<template>
  <v-main style="width: 100%; height: 100%;">
    <div>
      <!-- 轮播图 -->
      <v-row justify="center" no-gutters>
        <v-col cols="12">
          <Carousels></Carousels>
        </v-col>
      </v-row>
    </div>
  </v-main>
</template>

<script>

import { getHostsByTime } from "@/api/host/host";

export default {
  name: "Home",
  data () {
    return {
      imgSrc: require('@/assets/jpg/home.jpg'),
      // 热门主持人列表
      hotHostList: [],
      // 近期加入的主持人列表
      timeHostList: [],
      pageSearch: {
        page: 1,
        pageSize: 6
      },
    }
  },
  components: {
    Carousels: () => import('@/components/home/Carousels'),
  },
}
</script>

<style scoped>
.background {
  z-index: 0;
  position: absolute;
}
.content {
  z-index: 1;
  position: absolute;
}
</style>

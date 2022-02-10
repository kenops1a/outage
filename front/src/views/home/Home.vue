<!--
* @Descripttion:
* @Project: outage
* @Author: yaoMinC
* @Date: 10/2/2022
-->
<template>
  <v-main style="width: 100%; height: 100%;">
    <v-container class="mt-8 pt-8">
      <v-row class="mb-0">
        <v-col>
          <span style="font-size: larger; color: #424242; font-weight: bold">CCTV主持人</span>
        </v-col>
      </v-row>

      <v-row class="mt-0">
        <!-- 轮播图 -->
        <v-col cols="6">
          <div class="carousel">
            <v-row justify="center" no-gutters>
              <v-col>
                <Carousels></Carousels>
              </v-col>
            </v-row>
          </div>
        </v-col>

        <!-- 推荐 -->
        <v-col cols="6">
          <div class="news">
            <v-row justify="center" no-gutters>
              <v-card color="primary" height="200" width="100%">
                精彩推荐
              </v-card>
            </v-row>
            <v-row>
              <v-col>
                <v-card color="primary" height="186" width="100%">
                  啊哈哈哈
                </v-card>
              </v-col>
              <v-col>
                <v-card color="primary" height="186" width="100%">
                  穿山甲
                </v-card>
              </v-col>
            </v-row>
          </div>
        </v-col>
      </v-row>

      <v-divider class="mt-12 mb-4"></v-divider>

      <!-- 近期活动 -->
      <div class="mb-8 pb-12">
        <v-row>
          <v-col>
            <span style="font-size: larger; color: #424242; font-weight: bold">近期活动</span>
          </v-col>
        </v-row>
        <v-row>
          <v-col>
            <v-card height="100" width="100%">

            </v-card>
          </v-col>
        </v-row>
      </div>

      <v-divider class="mt-4 mb-4"></v-divider>

      <!-- 热门主持人 -->
      <div class="mb-8 pb-12">
        <v-row>
          <v-col>
            <span style="font-size: larger; color: #424242; font-weight: bold">热门主持人</span>
          </v-col>
        </v-row>

        <v-row>
          <v-col cols="3">
            <v-card height="300" width="100%">

            </v-card>
          </v-col>
        </v-row>
      </div>

      <v-divider class="mt-4 mb-4"></v-divider>

      <!-- 新人主持人 -->
      <div class="mb-8 pb-12">
        <v-row>
          <v-col>
            <span style="font-size: larger; color: #424242; font-weight: bold">新人主持人</span>
          </v-col>
        </v-row>

        <v-row>
          <v-col cols="3">
            <v-card height="300" width="100%">

            </v-card>
          </v-col>
        </v-row>
      </div>

      <v-divider class="mt-4 mb-4"></v-divider>

      <!-- 热门动态 -->
      <div class="mb-8 pb-12">
        <v-row>
          <v-col>
            <span style="font-size: larger; color: #424242; font-weight: bold">热门动态</span>
          </v-col>
        </v-row>

        <v-row>
          <v-col cols="3">
            <v-card height="300" width="100%">

            </v-card>
          </v-col>
        </v-row>

        <v-row>
          <v-col>
            <div class="text-center pa-8 ma-8">
              <v-pagination v-model="pageSearch.page" :length="6" @input="getHostListDefalut"></v-pagination>
            </div>
          </v-col>
        </v-row>
      </div>

    </v-container>
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

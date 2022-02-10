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
                  平台新闻
                </v-card>
              </v-col>
              <v-col>
                <v-card color="primary" height="186" width="100%">
                  系统公告
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
          <v-col cols="3" v-for="(host, index) in newHostList" :key="index">
            <v-card height="300" width="100%">
              <v-img class="white&#45;&#45;text align-end" height="200px" src="https://cdn.vuetifyjs.com/images/cards/docks.jpg">
                <!--<v-card-title>Top 10 Australian beaches</v-card-title>-->
              </v-img>
              <div class="ma-3 mb-2">
                <h3 class="font-grey">{{ host.nick }}</h3>
              </div>
              <v-card-actions class="mb-0">
                <v-btn depressed tile @click="toMessage(host)">
                  <span>沟通</span>
                </v-btn>
                <v-btn @click="setHost(host)" depressed tile>
                  <span>详情</span>
                </v-btn>
              </v-card-actions>
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
          <v-col cols="3" v-for="(host, index) in newHostList" :key="index">
            <v-card height="300" width="100%">
              <v-img class="white&#45;&#45;text align-end" height="200px" src="https://cdn.vuetifyjs.com/images/cards/docks.jpg">
                <!--<v-card-title>Top 10 Australian beaches</v-card-title>-->
              </v-img>
              <div class="ma-3 mb-2">
                <h3 class="font-grey">{{ host.nick }}</h3>
              </div>
              <v-card-actions class="mb-0">
                <v-btn depressed tile @click="toMessage(host)">
                  <span>沟通</span>
                </v-btn>
                <v-btn @click="setHost(host)" depressed tile>
                  <span>详情</span>
                </v-btn>
              </v-card-actions>
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
<!--              <v-pagination v-model="pageSearch.page" :length="6" @input="getHostListDefalut"></v-pagination>-->
            </div>
          </v-col>
        </v-row>
      </div>

    </v-container>
  </v-main>
</template>

<script>

import { getHostsByTime, getHostList } from "@/api/host/host";
import router from "@/router/router";

export default {
  name: "Home",
  data () {
    return {
      imgSrc: require('@/assets/jpg/home.jpg'),
      // 热门主持人列表
      hotHostList: [],
      // 近期加入的主持人列表
      newHostList: [],
      pageSearch: {
        page: 1,
        pageSize: 4
      },
    }
  },
  components: {
    Carousels: () => import('@/components/home/Carousels'),
  },
  mounted() {
    this.getNewHosts()
  },
  methods: {
    getNewHosts () {
      getHostsByTime(this.pageSearch, 1).then(res => {
        if (res.success) {
          this.newHostList = res.record
        } else {
          alert('新人主持人列表加载失败')
        }
      })
      getHostList(this.pageSearch).then(res => {
        if (res.success) {
          this.hotHostList = res.record
        } else {
          alert('热门主持人列表加载失败')
        }
      })
    },
    setHost (val) {
      // 已过时，现使用vue-router进行登录拦截
      // 判断用户是否登录
      // if (this.$store.state.loginStatus === 0) {
      //   router.push('/login')
      // } else {
      this.$store.commit('$_setHost', val)
      localStorage.setItem('host', JSON.stringify(val))
      router.push('/hostDetail')
      // }
    },
    toMessage (val) {
      this.$store.commit('$_setHost', val)
      localStorage.setItem('host', JSON.stringify(val))
      // 判断主持人会话窗口是否已存在
      // 检查对象数组是否包含对象，some（）方法
      console.log(this.$store.state.hostList.some(item => item.id === val.id))
      if (this.$store.state.hostList.some(item => item.id === val.id)) {
        router.push('/message')
      } else {
        // 将该主持人对象保存在localstorage和vuex中
        this.$store.state.hostList.push(val)
        localStorage.setItem('hostList', JSON.stringify(this.$store.state.hostList))
        router.push('/message')
      }
    },
  }
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

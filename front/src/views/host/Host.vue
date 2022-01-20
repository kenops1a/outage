<!--
* @Descripttion:
* @Project: front
* @Author: yaoMinC
* @Date: 12/1/2022
-->
<template>
  <v-main style="width: 100%">
    <v-container class="mt-8 pt-8">
        <!--<v-card class="mx-auto" max-width="300">
          <v-img class="white&#45;&#45;text align-end" height="200px" src="https://cdn.vuetifyjs.com/images/cards/docks.jpg">
            <v-card-title>Top 10 Australian beaches</v-card-title>
          </v-img>

          <v-card-subtitle class="pb-0">
            Number 10
          </v-card-subtitle>

          <v-card-text class="text&#45;&#45;primary">
            <div>Whitehaven Beach</div>
            <div>Whitsunday Island, Whitsunday Islands</div>
          </v-card-text>

          <v-card-actions>
            <v-btn color="orange" text>
              沟通
            </v-btn>
            <v-btn color="orange" text>
              详情
            </v-btn>
          </v-card-actions>
        </v-card>-->

      <!-- 搜索框 -->
      <v-row justify="center">
        <v-col cols="5">
          <v-text-field v-model="nameSearch.nameLike" placeholder="关键字搜索" solo></v-text-field>
        </v-col>
        <v-col cols="1" class="" style="width: 48px; height: 48px">
          <v-icon size="48" @click="getHostsByLikeParam" type="button">mdi-magnify</v-icon>
        </v-col>
      </v-row>

      <!-- 分类 -->
      <v-card class="ma-10 pa-5" color="#f0f0f0">
        <v-row class="pa-3">
          <dl id="type-label" class="flex-label">
            <dt>分类</dt>
            <dd class="item-label">
              <a id="all-type" @click="getHostListDefalut">全部</a>
              <a class="item-label" v-for="(item, index) in typeList" :key="index" @click="catchType(item.label)">
                {{ item.label }}
              </a>
            </dd>
          </dl>
        </v-row>
        <v-row class="pa-3">
          <dl id="time-label" class="flex-label">
            <dt>时间</dt>
            <dd class="item-label">
              <a id="all-time" @click="getHostListDefalut">全部</a>
              <a class="item-label" @click="catchTime(item.value)" v-for="(item, index) in timeList" :key="index">
                {{ item.label }}
              </a>
            </dd>
          </dl>
        </v-row>
      </v-card>

      <!-- 主持人列表 -->
      <v-row align="center">
        <v-col cols="3" v-for="(host, index) in hostList" :key="index">
          <v-card class="ma-3 mb-2" min-height="200px" min-width="200px" max-width="300px">
            <v-img class="white&#45;&#45;text align-end" height="200px" src="https://cdn.vuetifyjs.com/images/cards/docks.jpg">
              <!--<v-card-title>Top 10 Australian beaches</v-card-title>-->
            </v-img>
            <div class="ma-3 mb-2">
              <h3>{{ host.nick }}</h3>
            </div>
            <v-card-actions class="mb-0">
              <v-btn depressed tile to="/takeMessage">
                沟通
              </v-btn>
              <v-btn @click="setHost(host)" depressed tile>
                详情
              </v-btn>
            </v-card-actions>
          </v-card>
        </v-col>
      </v-row>

      <div class="text-center pa-8 ma-8">
        <v-pagination v-model="pageSearch.page" :length="6" @input="getHostListDefalut"></v-pagination>
      </div>
    </v-container>
  </v-main>
</template>

<script>
import { getHostList,getHostListByLikeParam,getHostsByType,getHostsByTime } from "@/api/host/host";
import router from "@/router/router";

export default {
  data: () => ({
    attrs: {
      class: 'mb-6',
      boilerplate: true,
      elevation: 2,
    },
    pageSearch: {
      page: 1,
      pageSize: 6
    },
    // 模糊查询参数，主持人姓名关键字
    nameSearch: {
      nameLike: null,
    },
    // 类型查询
    paramSearch: {
      // 类型查询参数
      type: null,
    },
    // 时长查询
    timeSearch: {
      timeMark: undefined,
    },
    hostList: [],
    timeList: [
      { label: '近1月', value: 1},
      { label: '近3月', value: 3},
      { label: '近6月', value: 6},
      { label: '近1年', value: 12},
    ],
    typeList: [
      { label: '婚礼'},
      { label: '竞赛'},
      { label: '生日'},
      { label: '测试'}
    ]
  }),
  mounted() {
    // 页面渲染前查找主持人列表
    getHostList(this.pageSearch).then(res => {
      this.hostList = res.record
    })
  },
  methods: {
    // 注册时间标记方法
    catchTime (val) {
      this.timeSearch.timeMark = val
      this.getHostsByTime()
    },
    // 类型标记
    catchType (val) {
      this.paramSearch.type = val
      this.getHostListByType()
    },
    // 姓名模糊查询主持人列表
    getHostsByLikeParam () {
      getHostListByLikeParam(this.pageSearch, this.nameSearch.nameLike).then(res => {
        this.hostList = res.record
      })
    },
    // 分类查询
    getHostListByType () {
      getHostsByType(this.pageSearch, this.paramSearch.type).then(res => {
        this.hostList = res.record
      })
    },
    // 时长查询
    getHostsByTime () {
      getHostsByTime(this.pageSearch, this.timeSearch.timeMark).then(res => {
        this.hostList = res.record
      })
    },
    // 全部查询
    getHostListDefalut () {
      getHostList(this.pageSearch).then(res => {
        this.hostList = res.record
      })
    },
  /*  // 设置vuex中选中的主持人id
    setHostId (val) {
      console.log("hostId:" + val)
      this.$store.commit('$_setHostId', val)
      localStorage.setItem('hostId', val)
      router.push('/hostDetail')
    },*/
    // 将选中主持人信息存入vuex中
    setHost (val) {
      this.$store.commit('$_setHost', val)
      localStorage.setItem('host', val)
      router.push('/hostDetail')
    }
  }
}
</script>

<style scoped>
.item-label {
  margin-left: 15px;
}
.flex-label {
  display: flex;
}
.no-style {
  text-decoration: none;
  color: black;
}
</style>

<!--
* @Descripttion:
* @Project: front
* @Author: yaoMinC
* @Date: 18/1/2022
-->
<template>
  <v-main style="width: 100%; height: 100%">
    <!-- 分类 -->
    <v-card class="ma-10 pa-5">
      <v-row class="pa-3">
        <dl id="type-label" class="flex-label">
          <dt>分类</dt>
          <dd class="item-label">
            <a id="all-type" @click="catchType(null)" :class="this.paramSearch.type === null ? 'on' : ''">全部</a>
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
            <a id="all-time" @click="catchTime" :class="this.paramSearch.timeMark === 0 ? 'on': ''">全部</a>
            <a class="item-label" @click="catchTime(item.value)" v-for="(item, index) in timeList" :key="index">
              {{ item.label }}
            </a>
          </dd>
        </dl>
      </v-row>
    </v-card>
  </v-main>
</template>

<script>
export default {
  name: "ManyParamsTest",
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
    paramSearch: {
      timeMark: undefined,
      nameLike: null,
      // 类型查询参数
      type: null,
      // 日期查询参数
      time: undefined
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
  updated() {
    console.log('this.paramSearch.type：' + this.paramSearch.type)
    console.log('this.paramSearch.time：' + this.paramSearch.timeMark)
  },
  methods: {
    catchType (val) {
      this.paramSearch.type = val
      console.log(this.paramSearch.type)
    },
    catchTime (val) {
      this.paramSearch.timeMark = val
      console.log(this.paramSearch.timeMark)
    }
  }
}
</script>

<style scoped>

</style>

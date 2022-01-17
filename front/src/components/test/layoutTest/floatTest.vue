<!--
* @Descripttion:
* @Project: front
* @Author: yaoMinC
* @Date: 17/1/2022
-->
<template>
  <v-main style="height: 100%; width: 100%">
    <v-container>
      <v-card>
        <dl>
          <dt>分类</dt>
          <dd></dd>
        </dl>
        <dl class="float-test">
          <dt>时间</dt>
          <dd>
            <a>全部</a>
            <a v-for="(item, index) in timeList" :key="index">
              {{ item.label }}
            </a>
          </dd>
        </dl>
      </v-card>
    </v-container>
  </v-main>
</template>

<script>
export default {
  name: "floatTest",
  data () {
    return {
      timeList: [
        { label: '近1月', value: 1},
        { label: '近3月', value: 3},
        { label: '近6月', value: 6},
        { label: '近1年', value: 12},
      ]
    }
  }
}
</script>

<style scoped>
.float-test {
  display: flex;
}
</style>

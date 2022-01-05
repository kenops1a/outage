<!--
* @Descripttion:
* @Project: outage
* @Author: yaoMinC
* @Date: 2022/1/5
-->
<template>
  <div>
    <button v-on:click="getTest">点击测试</button>
  </div>
</template>

<script>
// 导入测试方法模块
import { getHostTest } from "@/api/test/test";

export default {
  name: "TestComponent",
  methods: {
    getTest () {
      getHostTest();
    }
  }
}
</script>

<style scoped>

</style>
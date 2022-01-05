<!--
* @Descripttion:
* @Project: outage
* @Author: yaoMinC
* @Date: 2022/1/5
-->
<template>
  <div>
    <button v-on:click="test">获取host列表</button>
    <button v-on:click="token">获取token</button>
    <button v-on:click="test">获取host列表</button>
    <button v-on:click="test">获取host列表</button>
  </div>
</template>

<script>
// 导入测试方法模块
import { getHostTest,getToken } from "@/api/test/test";

export default {
  name: "TestComponent",
  data () {
    return {
      params: {
        page: 1,
        pageSize: 1
      },
      loginParams: {
        email: 'xx',
        password: 'helloworld'
      }
    }
  },
  methods: {
    test () {
      getHostTest(this.params);
    },
    token () {
      getToken(this.loginParams).then(res => {
        if (res.data.success) {
          // 如果登录成功则将token存入localStorage
          localStorage.setItem('token', res.data.data)
          console.log(localStorage.getItem('token'))
        }
      });
    }
  }
}
</script>

<style scoped>

</style>
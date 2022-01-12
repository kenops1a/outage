<!--
* @Descripttion:
* @Project: front
* @Author: yaoMinC
* @Date: 11/1/2022
-->
<template>
  <div>
    <v-container>
      <v-row>
        <router-link to="/login">跳转至登录页</router-link>
      </v-row>
      <v-row>
        <router-link to="/textTest">跳转至输入测试页</router-link>
      </v-row>
    </v-container>
  </div>
</template>

<script>
export default {
  name: "RouterTest"
}
</script>

<style scoped>

</style>

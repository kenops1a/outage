<!--
* @Descripttion:
* @Project: front
* @Author: yaoMinC
* @Date: 11/1/2022
-->
<template>
  <v-main>
    <v-container class="mt-16 pt-16">
      <v-row>
        <router-link to="/login">跳转至登录页</router-link>
      </v-row>
      <v-row>
        <router-link to="/textTest">跳转至输入测试页</router-link>
      </v-row>
      <v-row>
        <router-link to="/validTest">跳转至校验测试页</router-link>
      </v-row>
    </v-container>
  </v-main>
</template>

<script>
export default {
  name: "RouterTest"
}
</script>

<style scoped>

</style>

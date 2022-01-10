<!--
* @Descripttion:
* @Project: front
* @Author: yaoMinC
* @Date: 10/1/2022
-->
<template>
    <v-container>
        <!-- 底部公共组件 -->
        <router-view></router-view>
        <span class="mb-0">
          已有账号？<router-link to="/register">link</router-link>
        </span>
    </v-container>
</template>
<script>
export default {
  name: "Login",
  // 渲染前修改背景色

}
</script>

<style scoped>

</style>

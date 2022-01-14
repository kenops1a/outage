<!--
* @Descripttion:
* @Project: front
* @Author: yaoMinC
* @Date: 12/1/2022
-->
<template>
    <!-- 登录注册页不显示导航栏 -->
    <v-app-bar app color="white" v-show="($route.name !== 'login' && $route.name !== 'register')">

      <!-- nav导航栏 -->
      <v-tabs centered class="ml-n2 mx-n16" color="grey darken-1">
        <v-tab v-for="(link, index) in links" :key="index" :to="link.path">
          {{ link.name }}
        </v-tab>
      </v-tabs>

      <!-- vuex中的登录标识loginStatus为0（未登录时显示登录按钮） -->
      <v-tab v-if="this.$store.state.loginStatus === 0" class="ml-0 mr-0" color="grey darken-1" to="/login"><span style="color: gray; line-height: 64px;">登录</span></v-tab>
      <!-- 已登录则显示用户昵称 -->
      <v-tab v-if="this.$store.state.loginStatus === 1" class="ml-0 mr-0" color="grey darken-1"><span style="color: gray; line-height: 64px;">{{$store.state.userNick}}</span></v-tab>
<!--      <v-avatar class="hidden-sm-and-down" color="grey darken-1 shrink" size="32"></v-avatar>-->
    </v-app-bar>
</template>

<script>
export default {
  data: () => ({
    links: [
      { name: '首页', path: '/home'},
      { name: '主持人', path: '/host'},
      { name: '动态', path: '/weblog'},
      { name: '消息', path: '/message'}
    ],
  }),
}
</script>

<!--
* @Descripttion:
* @Project: front
* @Author: yaoMinC
* @Date: 12/1/2022
-->
<template>
    <!-- 登录注册页不显示导航栏 -->
    <v-app-bar app>

      <!-- nav导航栏 -->
      <v-tabs centered class="ml-n2 mx-n16" color="grey darken-1">
        <v-tab v-for="(link, index) in links" :key="index" :to="link.path">
          {{ link.name }}
        </v-tab>
      </v-tabs>

      <!-- vuex中的登录标识loginStatus为0（未登录时显示登录按钮） -->
      <v-tab v-if="this.$store.state.loginStatus === 0" class="pa-0 ma-0" color="grey" to="/login"><span style="color: gray; line-height: 64px;">登录</span></v-tab>
      <!-- 已登录则显示用户昵称 -->
      <v-menu v-if="this.$store.state.loginStatus === 1" offset-y>
        <template v-slot:activator="{ on, attrs }">
          <v-avatar size="56" v-bind="attrs" v-on="on">
            <span style="color:gray;">{{$store.state.userNick}}</span>
          </v-avatar>
        </template>
        <v-list>
          <!-- 个人中心，没必要用循环，懒得改了 -->
          <v-list-item v-for="(item, index) in items" :key="index" :to="item.path">
            <v-list-item-title>
              {{ item.title }}
            </v-list-item-title>
          </v-list-item>
          <v-list-item  @click="exit">
            <v-list-item-title>
              <span>退出登录</span>
            </v-list-item-title>
          </v-list-item>
        </v-list>
      </v-menu>
<!--      <v-tab v-if="this.$store.state.loginStatus === 1" class="ml-0 mr-0" color="grey darken-1">-->
<!--          <span>已登录</span>-->
<!--      </v-tab>-->
<!--      <v-avatar class="hidden-sm-and-down" color="grey darken-1 shrink" size="32"></v-avatar>-->
<!--      <span style="color: gray; line-height: 64px;">{{$store.state.userNick}}</span>-->
    </v-app-bar>
</template>

<script>
import router from "@/router/router";

export default {
  data: () => ({
    // menuLabel: this.$store.state.userNick,
    links: [
      { name: '首页', path: '/home'},
      { name: '主持人', path: '/host'},
      { name: '动态', path: '/weblog'},
      { name: '消息', path: '/message'}
    ],
    items: [
      { title: '个人中心', path: '/userMenu'},
    ],
  }),
  methods: {
    exit () {
      // 清除vuex中的用户昵称
      this.$store.commit('$_setUserNick','')
      // 修改登录状态
      this.$store.commit('$_updateLoginStatus',0);
      // 清除本地存储
      localStorage.removeItem('userNow')
      localStorage.removeItem('hostId')
      localStorage.removeItem('host')
      localStorage.removeItem('token')
      // 将登录状态设置为未登录
      localStorage.removeItem('loginStatus')
    },
  }
}
</script>

<style scoped>

</style>

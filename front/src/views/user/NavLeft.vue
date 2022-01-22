<!--
* @Descripttion:
* @Project: front
* @Author: yaoMinC
* @Date: 14/1/2022
-->
<template>
  <v-card permanent>
    <v-navigation-drawer app color="white" class="pt-4" permanent>
      <v-list>
        <!-- 循环菜单 -->
        <v-list-item v-for="([icon, text, path], index) in items" :key="index" link :to="path">
          <v-list-item-icon>
            <v-icon>{{ icon }}</v-icon>
          </v-list-item-icon>

          <v-list-item-content>
            <v-list-item-title>{{ text }}</v-list-item-title>
          </v-list-item-content>
        </v-list-item>

        <!-- 退出按钮 -->
        <v-list-item @click="exitSystem">
          <v-list-item-icon>
            <v-icon>{{ exit.icon }}</v-icon>
          </v-list-item-icon>

          <v-list-item-content>
            <v-list-item-title>{{ exit.text }}</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
      </v-list>
    </v-navigation-drawer>
  </v-card>
</template>

<script>
import router from '@/router/router'

export default {
  data: () => ({
    items: [
      ['mdi-history', '返回首页', '/home'],
      ['mdi-account-supervisor-circle', '个人信息', '/userInfo'],
      ['mdi-check-circle', '订单列表', '/orderForm'],
      ['mdi mdi-account-circle', '账号信息', '/accountInfo']
    ],
    // exit: ['mdi-clock-start', '退出系统', '/exitSys'],
    exit: {
      icon: 'mdi-clock-start',
      text: '退出系统'
    }
  }),
  methods: {
    exitSystem () {
      // 清除vuex中的用户昵称
      this.$store.commit('$_setUserNick','')
      // 修改登录状态
      this.$store.commit('$_updateLoginStatus',0);
      // 清除token
      this.$store.commit('$_removeToken')
      // 清除本地存储
      localStorage.removeItem('userNow')
      localStorage.removeItem('hostId')
      localStorage.removeItem('host')
      localStorage.removeItem('token')
      router.push('/home')
    }
  }
}
</script>

<style scoped>

</style>

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
        <v-list-item v-for="([icon, text, path], index) in items" :key="index" link :to="path">
          <v-list-item-icon>
            <v-icon>{{ icon }}</v-icon>
          </v-list-item-icon>

          <v-list-item-content>
            <v-list-item-title>{{ text }}</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
      </v-list>
    </v-navigation-drawer>
  </v-card>
</template>

<script>
export default {
  data: () => ({
    items: [
      ['mdi-history', '返回首页', '/home'],
      ['mdi-account-supervisor-circle', '个人信息', '/userInfo'],
      ['mdi-check-circle', '订单列表', '/orderForm'],
      ['mdi-clock-start', '退出系统', '/exitSys'],
    ],
  }),
}
</script>

<style scoped>

</style>

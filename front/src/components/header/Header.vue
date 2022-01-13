<!--
* @Descripttion:
* @Project: front
* @Author: yaoMinC
* @Date: 12/1/2022
-->
<template>
    <v-app-bar app color="white">
      <v-avatar :color="$vuetify.breakpoint.smAndDown ? 'grey darken-1' : 'transparent'" size="32"></v-avatar>

      <!-- nav导航栏 -->
      <v-tabs centered class="ml-n2 mx-n16" color="grey darken-1">
        <v-tab v-for="link in links" :key="link">
          {{ link.name }}
        </v-tab>
      </v-tabs>

      <v-tab class="ml-0 mr-0" color="grey darken-1" to="/login"><span style="color: gray; line-height: 64px;">登录</span></v-tab>

<!--      <v-avatar class="hidden-sm-and-down" color="grey darken-1 shrink" size="32"></v-avatar>-->
    </v-app-bar>
</template>

<script>
export default {
  data: () => ({
    links: [
      { name: '首页', path: '/index'},
      { name: '主持人', path: '/host'},
      { name: '动态', path: '/weblock'},
      { name: '消息', path: '/message'}
    ],
  }),
}
</script>

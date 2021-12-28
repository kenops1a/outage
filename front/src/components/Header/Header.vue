<!--
* @Descripttion:
* @Project: outage
* @Author: yaominc
* @Date: 2021/12/24
* @LastEditiors: yaominc
-->
<template>
    <!-- flat 去除导航栏阴影 -->
    <v-app-bar app color="white" id="outage-header" absolute>
      <v-avatar :color="$vuetify.breakpoint.smAndDown ? 'grey darken-1' : 'transparent'" size="32"/>

      <!-- 选择块 -->
      <v-tabs centered class="ml-n9" color="grey darken-1">
        <v-tab v-for="link in links" :key="link.route" :to="link.route">
          {{link.text}}
        </v-tab>
      </v-tabs>

      <!-- 头像块 -->
      <v-avatar class="hidden-sm-and-down" color="grey darken-1 shrink" size="32">
        <v-menu offset-y>
          <template v-slot:activator="{ on, attrs }">
            <v-btn color="primary" dark v-bind="attrs" v-on="on">
              <v-icon dark>
                mdi-account-circle
              </v-icon>
            </v-btn>
          </template>
          <v-list>
            <v-list-item v-for="(item, index) in avatarLinks" :key="index" :to="item.route">
              <v-list-item-title>{{ item.text }}</v-list-item-title>
            </v-list-item>
          </v-list>
        </v-menu>
      </v-avatar>
    </v-app-bar>
</template>

<script>
export default {
  data: () => ({
    links: [
      { text: '首页', route: '/index'},
      { text: '主持人', route: '/host'},
      { text: '消息', route: '/message'},
      { text: '动态', route: '/webBlock'},
    ],
    avatarLinks: [
      { text: '登录', route: '/login'},
      { text: '注册', route: '/register'}
    ]
  }),
}
</script>

<style>

</style>
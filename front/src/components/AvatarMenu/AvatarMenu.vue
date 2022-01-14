<template>
  <div style="margin: 90px">
    <v-menu open-on-hover offset-y>
      <template v-slot:activator="{ on, attrs }">
        <v-avatar size="48" color="#FB7299" class="hidden-sm-and-down" v-bind="attrs" v-on="on">
          <img :src="icon" :alt="user.nickName">
        </v-avatar>
      </template>

      <v-card width="250">
        <v-img height="150px" :src="cardBackImage">
          <v-app-bar flat color="rgba(0, 0, 0, 0)">

            <v-toolbar-title class="text-h6 white--text pl-0">
              {{ user.nickName }}
            </v-toolbar-title>

          </v-app-bar>
        </v-img>

        <div style="text-align: center">
          <v-btn text color="#FB7299" width="200" style="margin-top: 10px" to="/profile" v-if="$route.path !== '/person'">个人中心</v-btn>
          <v-btn text color="#FB7299" width="200" style="margin-top: 10px" to="/friend">好友消息</v-btn>
          <v-btn text color="#FB7299" width="200" style="margin-top: 10px;margin-bottom: 10px">退出登录</v-btn>
        </div>
      </v-card>

    </v-menu>
  </div>
</template>

<script>
  export default {
    name: "AvatarMenu",
    computed: {
      user() {
        return this.$store.state.user
      },
      cardBackImage: function () {
        if (this.user.icon === null) {
          return 'https://w.wallhaven.cc/full/pk/wallhaven-pkylme.png'
          // return 'https://lorempixel.com/200/150/'
        }
        return this.user.icon
      },
      icon: function () {
        if (this.user.icon === null) {
          return 'https://img0.baidu.com/it/u=3684533571,3875739943&fm=26&fmt=auto'
        }
        return this.user.icon
      }
    }
  }
</script>

<style scoped>

</style>
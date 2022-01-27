<!--
* @Descripttion:
* @Project: front
* @Author: yaoMinC
* @Date: 12/1/2022
-->
<template>
  <v-main>
    <v-container class="mt-16 mb-16">
      <v-card min-width="900" min-height="600">
        <!-- 子元素会撑起父元素的高度 -->
        <v-row class="ma-0 pa-0">
          <v-col cols="4">
            <!--<router-link to="/home">此处是message页，返回index</router-link>-->
            <v-navigation-drawer permanent>
              <!-- 循环菜单 -->
              <v-list-item v-for="(msgSession, index) in sessionList" :key="index">
                <v-list-item-icon>
                  <v-icon>{{ msgSession.icon }}</v-icon>
                </v-list-item-icon>

                <v-list-item-content>
                  <v-list-item-title>{{ msgSession.label }}</v-list-item-title>
                </v-list-item-content>
              </v-list-item>
            </v-navigation-drawer>
          </v-col>
          <v-col>
            <div style="height: 600px"></div>
          </v-col>
        </v-row>
      </v-card>
    </v-container>
  </v-main>
</template>

<script>
export default {
  name: "Message",
  data () {
    return {
      sessionList: [
        { icon: 'mdi-history', label: '用户1' },
        { icon: 'mdi-history', label: '用户1' },
        { icon: 'mdi-history', label: '用户1' },
        { icon: 'mdi-history', label: '用户1' },
      ],
    }
  },
  created() {
    let websocket = null
    let userId = this.$store.state.userNow.id
    if ('WebSocket' in window) {
      websocket = new WebSocket("ws://localhost:8089/message/socket/" + userId)
      // 连接错误回调
      websocket.onerror = function () {
        console.log('连接失败')
      }
      // 连接成功回调
      websocket.onopen = function (event) {
        console.log('连接成功')
      }
      // 接收消息回调方法
      websocket.onmessage = function (event) {
        console.log(event.data)
      }
      // 连接关闭的回调方法
      websocket.onclose = function () {
        console.log('连接关闭')
      }
      // 页面关闭前，主动断开websocket连接
      window.onbeforeunload = function () {
        websocket.close();
      }
    } else {
      alert('Not support websocket')
    }
  },
  mounted() {
    // 加载接收者或发送者为当前用户的消息列表
  }
}
</script>

<style scoped>

</style>

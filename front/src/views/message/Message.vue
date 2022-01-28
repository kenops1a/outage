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
              <v-list-item v-for="(host, index) in hostList" :key="index">
                <v-list-item-icon>
                  <v-icon>{{ 'mdi-comment-account' }}</v-icon>
                </v-list-item-icon>

                <v-list-item-content>
                  <v-list-item-title>{{ host.nick }}</v-list-item-title>
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

// 日期格式化
Date.prototype.format = function(fmt) {
  var o = {
    "M+" : this.getMonth()+1,                 //月份
    "d+" : this.getDate(),                    //日
    "h+" : this.getHours(),                   //小时
    "m+" : this.getMinutes(),                 //分
    "s+" : this.getSeconds(),                 //秒
    "q+" : Math.floor((this.getMonth()+3)/3), //季度
    "S"  : this.getMilliseconds()             //毫秒
  };
  if(/(y+)/.test(fmt)) {
    fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));
  }
  for(var k in o) {
    if(new RegExp("("+ k +")").test(fmt)){
      fmt = fmt.replace(RegExp.$1, (RegExp.$1.length === 1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
    }
  }
  return fmt;
}

export default {
  name: "Message",
  data () {
    return {
      hostList: [],
      msgBody: {
        userId: undefined,
        toId: undefined,
        createTime: null,
        status: null,
        message: null
      }
    }
  },
  created() {
    // 加载联系人列表
    this.hostList = this.$store.state.hostList

    // 处理消息体初始状态
    // 设置发送者
    this.msgBody.userId = this.$store.state.userNow.id
    // 设置发送状态 0-未发送成功，1-发送成功
    this.msgBody.status = '0'

    // 初始化websocket
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
        let message = "测试"
        websocket.send(message)
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
  },
  methods: {
    sendMessage () {

    }
  }
}
</script>

<style scoped>

</style>

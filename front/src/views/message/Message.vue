<!--
* @Descripttion:
* @Project: front
* @Author: yaoMinC
* @Date: 12/1/2022
-->
<template>
  <v-main>
    <v-container class="mt-12 mb-16 pa-5">
      <v-card  min-width="900" min-height="600">
        <!-- 子元素会撑起父元素的高度 -->
        <v-row class="ma-0 pa-0">
          <v-col cols="3">
            <!--<router-link to="/home">此处是message页，返回index</router-link>-->
            <v-navigation-drawer permanent>
              <!-- 循环菜单 -->
              <v-list>
                <v-list-item v-for="(host, index) in hostList" :key="index" @click="setHostNow(host)">
                  <v-list-item-icon>
                    <v-icon>{{ 'mdi-comment-account' }}</v-icon>
                  </v-list-item-icon>

                  <v-list-item-content>
                    <v-list-item-title>{{ host.nick }}</v-list-item-title>
                  </v-list-item-content>
                </v-list-item>
              </v-list>
            </v-navigation-drawer>
          </v-col>

          <v-col class="ml-0 pl-0">
            <div style="height: 50px">
              <v-row justify="center" class="mt-3 mr-3">
                <h3>{{ host.nick }}</h3>
              </v-row>
              <v-row justify="center" class="mt-3 mr-3 mb-3">
                <span style="color: limegreen">在线</span>
              </v-row>
              <v-divider></v-divider>
            </div>

            <div id="scroll" style="height: 430px" class="pt-4 mt-4">
              <!-- 接收消息展示 -->
             <!-- <v-row justify="start" class="mb-2" align="center" no-gutters>
                <v-avatar size="56" class="mr-3 pl-2" color="orange"></v-avatar>
                <div>
                  <span>{{ host.nick }}~: </span>
                  <span style="color: #777777; font-size: large">测试消息</span>
                </div>
              </v-row>-->

              <!-- 消息展示 -->
              <div v-for="(msg, index) in msgList" :key="index">

                <!-- 发送时间 -->
                <v-row justify="center">
                  <span style="color: #777777; font-size: small">{{ msg.createTime }}</span>
                </v-row>

                <!-- 左侧我收到的消息 -->
                <div v-if="msg.toId === $store.state.userNow.id">
                  <!-- 发送消息块 -->
                  <v-row justify="start" class="mb-2" align="center" no-gutters>
                    <v-avatar size="56" class="mr-3 pl-2" color="orange"></v-avatar>
                    <div>
                      <span>{{ host.nick }}~: </span>
                      <span style="color: #777777; font-size: large">{{ msg.message }}</span>
                    </div>
                  </v-row>
                </div>

                <!-- 右侧我发送的消息 -->
                <div v-if="msg.toId === $store.state.host.id">
                  <!-- 发送消息块 -->
                  <v-row justify="end" class="mb-4" align="center" no-gutters>
                    <span style="color: #777777; font-size: large">{{ msg.message }}</span>
                    <span class="mr-2">:~</span>
                    <v-avatar size="56" class="mr-3 pl-2" color="orange"></v-avatar>
                  </v-row>
                </div>

              </div>
            </div>

            <v-divider class="pl-n3"></v-divider>

            <div style="height: 150px">
              <v-row class="pa-4 pt-0 pl-0 pr-6 mt-0" style="height: 100px">
                <label>
                  <textarea style="height: 100%; width: 55vw; outline: none; resize: none" class="pa-4 pt-2" v-model="msgBody.message" placeholder="弱小和无知不是生存的障碍，傲慢才是。"></textarea>
                </label>
              </v-row>
              <v-row justify="end" class="pr-12 pb-3">
                <v-btn-toggle group tile >
                  <v-btn class="pa-5" @click="close($store.state.host.id)">
                    关闭
                  </v-btn>
                  <v-btn class="pa-5" @click="sendMessage">
                    发送
                  </v-btn>
                </v-btn-toggle>
              </v-row>
            </div>
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
      host: null,
      hostList: [],
      msgBody: {
        userId: undefined,
        toId: undefined,
        createTime: null,
        status: null,
        message: null
      },
      msgList: [],
      webSocket: {},
    }
  },
  created() {
    // 加载接收者或发送者为当前用户的消息列表
    this.hostList = this.$store.state.hostList
    // 如果vuex中host为空，则显示hostlist的第一个主持人
    this.host = this.$store.state.host === null ? this.$store.state.hostList[0] : this.$store.state.host
    // 处理消息体初始状态
    // 设置发送者
    this.msgBody.userId = this.$store.state.userNow.id
    // 设置发送状态 0-未发送成功，1-发送成功
    this.msgBody.status = '0'

    // 初始化websocket
    let userId = this.$store.state.userNow.id
    if ('WebSocket' in window) {
      let websocket = new WebSocket("ws://localhost:8089/message/socket/" + userId)
      // 连接错误回调
      websocket.onerror = function () {
        console.log('连接失败')
      }
      // 连接成功回调
      websocket.onopen = function (event) {
        console.log('芜湖~，起飞！')
      }
      // 接收消息回调方法
      websocket.onmessage = function (event) {
        console.log(JSON.parse(event.data))
        console.log(this.test)
      }
      // 连接关闭的回调方法
      websocket.onclose = function () {
        console.log('莫名奇妙就被关闭了')
        console.log('连接关闭')
      }
      // 页面关闭前，主动断开websocket连接
      window.onbeforeunload = function () {
        websocket.close();
      }
      this.webSocket = websocket
    } else {
      alert('Not support websocket')
    }
  },
  methods: {
    getMsgList () {
      this.$store.state.msgList.forEach(item => {
        let msg = JSON.parse(item)
        // 将发送者id为当前用户和接收者id为该主持人的记录加入数组列表
        if (msg.toId === this.host.id || msg.toId === this.$store.state.userNow.id) {
          this.msgList.push(msg)
        }
      })
    },
    sendMessage () {
      // host: null,
      //     hostList: [],
      //     msgBody: {
      //     userId: undefined,
      //       toId: undefined,
      //       createTime: null,
      //       status: null,
      //       message: null
      // }
      //
      this.msgBody.userId = this.$store.state.userNow.id
      this.msgBody.toId = this.host.id
      this.msgBody.createTime = new Date().format('yyyy-MM-dd hh:mm:ss')
      // 消息状态设置为未发送成功
      this.msgBody.status = 0

      // 发送消息
      this.webSocket.send(JSON.stringify(this.msgBody))

      // 将已发送的消息对象转换为json字符串后放入vuex中
      this.$store.commit('$_addMessage', JSON.stringify(this.msgBody))
      // 清空输入框
      this.msgBody.message = ''
      // 清空原有信息列表
      this.msgList = []
      // 加载与该主持人的消息
      this.getMsgList()
    },
    setHostNow (val) {
      // 点击列表项，选择主持人对话框
      this.$store.commit('$_setHost', val)
      this.host = val
      // 清空原来的msgList
      this.msgList = []
      this.getMsgList()
    },
    // 传入主持人id
    close (val) {
      this.$store.state.hostList.some((item, i) => {
        if (item.id === val) {
          this.$store.state.hostList.splice(i, 1)
          return true
        }
      })
      // 覆写localStorage中存储的联系人列表
      localStorage.setItem('hostList', JSON.stringify(this.$store.state.hostList))
      // 将列表首位主持人设置为当前主持人
      this.$store.state.host = this.$store.state.hostList[0]
      this.host = this.$store.state.host
    }
  }
}
</script>

<style scoped>
#scroll {
  position: relative;
  overflow-y: auto;
  overflow-x: hidden;
}
</style>

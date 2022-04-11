<!--
* @Descripttion:
* @Project: front
* @Author: yaoMinC
* @Date: 18/1/2022
-->
<template>
  <v-main id="hostDetail-main">
    <v-container>
      <v-card class="ma-8 pa-3" min-width="450px" min-height="100%" color="#f0f0f0">
        <v-row>
          <!-- 照片 -->
          <v-col cols="6">
            <v-row>
              <v-col>
                <v-card color="black" class="ma-8 mb-3 mr-3">
                  <v-img :src="imgSrc"></v-img>
                </v-card>
              </v-col>
            </v-row>
            <v-row>
              <v-col>
                <v-card class="ma-8 mt-0 mr-3 pa-8" color="#26c6da" min-height="213px" dark>
                  <h2 class="mb-4 font-weight-bold">个人简介</h2>
                  <p>{{ host.text }}</p>
                </v-card>
              </v-col>
            </v-row>
          </v-col>
          <v-col cols="6">
            <v-row>
              <v-col>
                <v-card class="ma-8 pa-3 ml-0">
                  <v-simple-table>
                    <template v-slot:default>
                      <thead>
                        <tr>
                          <th class="text-left">
                            <h2>基础信息</h2>
                          </th>
                          <th class="text-left">
                          </th>
                        </tr>
                      </thead>
                      <tbody>
                        <tr v-for="(row, index) in showList" :key="index">
                          <td>{{ row.value }}</td>
                          <td>{{ host[row.label] }}</td>
                        </tr>
                      </tbody>
                    </template>
                  </v-simple-table>
                </v-card>
              </v-col>
            </v-row>
          </v-col>
        </v-row>
        <v-card min-height="100px" class="ma-8 mt-3 pa-3 pt-8 pl-8">
          <v-row>
            <v-col cols="3">
              <h2>预计佣金：<span style="color: orange">{{ host.money }}</span> ￥</h2>
            </v-col>
            <v-col cols="1" class="mr-10">
              <v-btn color="orange" large dark @click="toMessage">沟通</v-btn>
            </v-col>
            <v-col cols="2">
              <v-row>
                <v-dialog v-model="pop" persistent max-width="600px">
                  <template v-slot:activator="{ on, attrs }">
                    <v-btn color="orange" class="mt-3" large dark v-bind="attrs" v-on="on">
                      预定
                    </v-btn>
                  </template>
                  <v-card>
                    <v-card-title>
                      <span class="text-h5">订单</span>
                    </v-card-title>
                    <v-card-text>
                      <v-container>
                        <!--<v-row id="text-box" v-for="(row, index) in orderForm" :key="index" class="mb-0" no-gutters align="center">
                          <v-spacer></v-spacer>
                          <v-col cols="9">
                            <v-text-field :label="row.label" color="blue-grey" v-model="row.value" :placeholder="row.placeHolder"></v-text-field>
                          </v-col>
                          <v-spacer></v-spacer>
                        </v-row>-->
                        <v-row align="center" class="mt-n3 mb-n3">
                          <span>用户:</span>
                          <v-col cols="4">
                            <v-text-field readonly :value="this.$store.state.userNow.nick"></v-text-field>
                          </v-col>
                          <span>主持人:</span>
                          <v-col cols="4">
                            <v-text-field readonly :value="this.$store.state.host.nick"></v-text-field>
                          </v-col>
                        </v-row>
                        <v-row align="center" class="mt-n3 mb-n3">
                          <span>日期:</span>
                          <v-col cols="2" v-for="(col, index) in timeSelect" :key="index">
                            <v-text-field :label="col.label" v-model="col.value"></v-text-field>
                          </v-col>
                        </v-row>
                        <v-row align="center" class="mt-n3 mb-n3">
                          <span>类型:</span>
                          <v-col cols="4">
                            <v-select :items="typeSelect" v-model="orderForm.type" label="类型"></v-select>
                          </v-col>
                        </v-row>
                        <v-row align="center" class="mt-n3 mb-n3">
                          <span>金额:</span>
                          <v-col cols="4">
                            <v-text-field readonly :value="host.money"></v-text-field>
                          </v-col>
                        </v-row>
                        <v-row align="center" class="mt-n3 mb-n3">
                          <span>地址:</span>
                          <v-col cols="3" v-for="(col, index) in adrList" :key="index">
                            <v-select :items="col.select" :label="col.label" v-model="col.value"></v-select>
                          </v-col>
                        </v-row>
                        <v-row align="center" class="mt-n3 mb-n3">
                          <span>详址:</span>
                          <v-col cols="9">
                            <v-text-field placeholder="输入详细地址" label="详细地址" v-model="adrDetail"></v-text-field>
                          </v-col>
                        </v-row>
                         <!-- <v-col cols="12" sm="6">
                            <v-select :items="['0-17', '18-29', '30-54', '54+']" label="Age*" required></v-select>
                          </v-col>
                          <v-col cols="12" sm="6">
                            <v-autocomplete :items="['Skiing', 'Ice hockey', 'Soccer', 'Basketball', 'Hockey', 'Reading', 'Writing', 'Coding', 'Basejump']" label="Interests" multiple></v-autocomplete>
                          </v-col>-->
                      </v-container>
                    </v-card-text>
                    <v-card-actions>
                      <v-spacer></v-spacer>
                      <v-btn color="blue darken-1" text @click="pop = false">
                        取消
                      </v-btn>
                      <v-btn color="blue darken-1" text @click="forMartOrderForm" >
                        确认
                      </v-btn>
                    </v-card-actions>
                  </v-card>
                </v-dialog>
              </v-row>
            </v-col>
          </v-row>
        </v-card>
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

// 导入hostForm
import { createForm } from "@/api/host/hostForm";
import router from "@/router/router"

export default {
  data () {
    return {
      pop: false,
      host: {},
      imgSrc: require('@/assets/jpg/person.jpg'),
      showList: [
        { id:1, label: 'nick', value: '昵称'},
        { id:2, label: 'name', value: '姓名'},
        { id:3, label: 'age', value: '年龄'},
        { id:4, label: 'sex', value: '性别'},
        { id:5, label: 'email', value: '邮箱'},
        { id:6, label: 'phone', value: '手机号'},
        { id:7, label: 'type', value: '类型'},
        { id:8, label: 'address', value: '地区'},
        { id:9, label: 'createTime', value: '注册时间'},
      ],
      orderForm: {
        address: null,
        type: null,
        date: null,
        money: undefined,
      },
      timeSelect: [
        { label: '年', value: undefined },
        { label: '月', value: undefined },
        { label: '日', value: undefined },
        { label: '时', value: undefined },
        { label: '分', value: undefined },
      ],
      typeSelect: [
        '婚礼', '生日', '测试', '中文'
      ],
      adrSelect: [
        '省', '市', '区'
      ],
      adrList: [
        { label: '省', select: ['a省', 'b省'], value: null },
        { label: '市', select: ['c市', 'd市'], value: null },
        { label: '区', select: ['e区', 'f区'], value: null }
      ],
      adrDetail: '',
    }
  },
  mounted() {
    // hostId存在时发送请求查询数据
    // if (this.$store.state.hostId !== undefined) {
    //   getHostById(this.$store.state.hostId).then(res => {
    //     this.host = res.record
    //     this.host.sex = 'shab'
    //     console.log(this.host)
    //   })
    // }
    this.host = JSON.parse(localStorage.getItem('host'))
    let sex = this.host.sex
    if (sex === 0) {
      this.host.sex = '男'
    }
    else if (sex === 1) {
      this.host.sex = '女'
    }
    else {
      this.host.sex = '未公开'
    }
    // 将vuex中的host对象渲染到hostDetail上
    /*this.host = this.$store.state.host
    let sex = this.host.sex
    if (sex === 0) {
      this.host.sex = '男'
    }
    else if (sex === 1) {
      this.host.sex = '女'
    }
    else {
      this.host.sex = '未公开'
    }*/

  },
  methods: {
    // 跳转至聊天界面
    toMessage () {
      // 处于此页面说明vuex种已存在对应的主持人对象，可以直接跳转至message页面
      router.push('message')
    },
    // 输入字符串处理
    forMartOrderForm () {
      this.pop = false
      // timeStr = timeStr.replace(/-/g, '/')
      // this.orderForm.date = new Date(timeStr)
      // 处理日期字符串
      this.orderForm.date = this.timeSelect[0].value + '-' + this.timeSelect[1].value + '-' + this.timeSelect[2].value + ' ' + this.timeSelect[3].value + ':' + this.timeSelect[4].value + ':00'
      // this.orderForm.date = "2022-01-21 14:11:34"
      // 表单金额暂时按照主持人佣金
      this.orderForm.money = this.$store.state.host.money
      // 拼接地址字符串
      let adrStr = ''
      this.adrList.forEach(item => {
        adrStr += item.value
      })
      this.orderForm.address = adrStr + this.adrDetail

      // 将表单对象发送给服务器
      createForm(this.orderForm, this.$store.state.host.id, this.$store.state.userNow.id).then(res => {
        console.log(res)
      })
    }
  }
}
</script>

<style scoped>
#hostDetail-main {
  color: darkgrey;
}
</style>

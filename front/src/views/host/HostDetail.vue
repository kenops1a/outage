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
                  <p>《共产党宣言》是第一部较为完整而系统阐述科学社会主义基本原理的著作。
                    是马克思主义的百科全书，是马克思主义哲学、政治经济学、科学社会主义、党的建设思想的集中体现。</p>
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
              <v-btn color="orange" large dark>沟通</v-btn>
            </v-col>
            <v-col cols="2">
              <v-btn @click="pop = 1" color="orange" large dark>下单</v-btn>
            </v-col>
          </v-row>
        </v-card>
      </v-card>
    </v-container>
  </v-main>
</template>

<script>

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
      ]
    }
  },
  updated() {
    console.log(this.pop)
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
    console.log(this.host)
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

  }
}
</script>

<style scoped>
#hostDetail-main {
  color: darkgrey;
}
</style>

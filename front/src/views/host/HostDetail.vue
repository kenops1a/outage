<!--
* @Descripttion:
* @Project: front
* @Author: yaoMinC
* @Date: 18/1/2022
-->
<template>
  <v-main id="hostDetail-main">
    <v-container>
      <v-card class="ma-8 pa-3" min-width="450px" min-height="100%" color="grey">
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
                <v-card class="ma-8 pa-2">
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
      </v-card>
    </v-container>
  </v-main>
</template>

<script>
import { getHostById } from "@/api/host/host";

export default {
  data () {
    return {
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
  mounted() {
    // hostId存在时发送请求查询数据
    if (this.$store.state.hostId !== undefined) {
      getHostById(this.$store.state.hostId).then(res => {
        this.host = res.record
        console.log(this.host)
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

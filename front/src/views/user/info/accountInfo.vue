<!--
* @Descripttion:
* @Project: front
* @Author: yaoMinC
* @Date: 22/1/2022
-->
<template>
  <v-main style="height: 100vh; width: 100vw">
    <v-card color="pa-8 pb-16 pt-10" min-width="100%" min-height="100%">
      <v-row class="ml-4 mb-n3">
        <h2>账号信息</h2>
      </v-row>

      <v-row>
        <v-col cols="6">
          <v-simple-table>
            <template v-slot:default>
              <tbody>
              <tr v-for="(item, index) in showList" :key="index">
                <td>{{ item.label }}:</td>
                <td>
                  {{ userModel[item.value] }}
                  <v-icon size="16" color="blue" class="ml-2" @click="testMethod(item.label)">{{ "mdi mdi-pencil" }}</v-icon>
                </td>
              </tr>
              </tbody>
            </template>
          </v-simple-table>
        </v-col>
      </v-row>

      <!-- 修改信息按钮 -->
      <v-row>
        <v-col>
        </v-col>
      </v-row>
    </v-card>
  </v-main>
</template>

<script>
import router from '@/router/router'
export default {
  data () {
    return {
      showList: [
        { label: '邮箱', value: 'email' },
        { label: '昵称', value: 'nick' },
        { label: '姓名', value: 'name' },
        { label: '性别', value: 'sex' },
        { label: '年龄', value: 'age' },
        { label: '地址', value: 'address' },
        { label: '手机号', value: 'phone' },
        { label: '身份证', value: 'sfz' },
        { label: '注册时间', value: 'createTime' },
      ],
      userModel: {}
    }
  },
  mounted() {
    // 将vuex中的用户对象赋值给userModel
    this.userModel = this.$store.state.userNow
    for (let field in this.userModel) {
      if (this.userModel[field] === null) {
        this.userModel[field] = '未编辑'
      }
      console.log(field + ':' + this.userModel[field])
    }
    // 处理性别参数
    switch (this.userModel.sex) {
      case '0':
        this.userModel.sex = '男'
        break
      case '1':
        this.userModel.sex = '女'
        break
      case '2':
        this.userModel.sex = '未公开'
    }
  },
  methods: {
    testMethod (val) {
      console.log(val)
    }
  }
}
</script>

<style scoped>

</style>

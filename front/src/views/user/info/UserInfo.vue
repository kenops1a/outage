<!--
* @Descripttion:
* @Project: front
* @Author: yaoMinC
* @Date: 14/1/2022
-->
<template>
  <v-main style="height: 100vh; width: 100vw">
    <v-card color="pa-8 pb-16 pt-10" min-width="100%" min-height="100%">
      <!-- 页面标题 -->
      <v-row class="ml-4 mb-n3">
        <h2>个人信息</h2>
      </v-row>

      <!-- 信息列表 -->
      <v-row>
        <v-col cols="6">
          <v-simple-table>
            <template v-slot:default>
              <tbody>
                <tr>
                  <td>邮箱:</td>
                  <td>
                    {{ userModel.email }}
                  </td>
                </tr>
                <tr v-for="(item, index) in showList" :key="index">
                  <td>{{ item.label }}:</td>
                  <td>
                    {{ userModel[item.value] }}
                    <v-icon size="16" color="blue" class="ml-2" @click.stop="testMethod(item)">{{ "mdi mdi-pencil" }}</v-icon>
                  </td>
                </tr>
                <tr>
                  <td>注册时间:</td>
                  <td>
                    {{ userModel.createTime }}
                  </td>
                </tr>
              </tbody>
            </template>
          </v-simple-table>
        </v-col>
      </v-row>

      <!-- dialog对话框 -->
      <v-row justify="center">
        <v-dialog v-model="dialog" max-width="290">
          <v-card class="text-center pa-4">
            <span>修改{{ updateLabel.label }}</span>
            <v-text-field v-model="updateLabel.data" :placeholder="userModel[updateLabel.value]"></v-text-field>
            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn color="green darken-1" text @click="closeUpdate()">
                取消
              </v-btn>
              <v-btn color="green darken-1" text @click="enterUpdate(updateLabel.value, updateLabel.data)">
                确认
              </v-btn>
            </v-card-actions>
          </v-card>
        </v-dialog>
      </v-row>

    </v-card>
  </v-main>
</template>

<script>
import router from '@/router/router'
import { updateUserInfo } from "@/api/user/user";

export default {
  name: "UserInfo",
  data () {
    return {
      dialog: false,
      updateLabel: {
        label: null,
        value: null,
        data: null
      },
      showList: [
        { label: '昵称', value: 'nick' },
        { label: '姓名', value: 'name' },
        { label: '性别', value: 'sex' },
        { label: '年龄', value: 'age' },
        { label: '地址', value: 'address' },
        { label: '手机号', value: 'phone' },
        { label: '身份证', value: 'sfz' },
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
  updated() {
  },
  methods: {
    // 取消修改，将updateModel中编辑的属性重置为默认值null
    closeUpdate () {
      // 清空编辑内容
      this.updateLabel.data = null
      this.dialog = false
    },
    enterUpdate (label, value) {
      // 关闭修改对话框
      this.dialog = false
      // 将输入框值封装成对象, 添加必要属性（用户邮箱账号）
      let updateModel = {}
      updateModel[label] = value
      updateModel.email = this.$store.state.userNow.email
      console.log(updateModel)
      // 向后端发送修改请求
      updateUserInfo(updateModel).then(res => {
        // 修改成功，则更新vuex，localStorage中的用户对象
        if (res.success) {
          this.$store.state.userNow[label] = value
          localStorage.setItem('userNow', JSON.stringify(this.$store.state.userNow))
          alert('修改成功')
        } else {
          // 弹出失败提示
          alert('修改失败,' + res.errorMsg)
        }
      })
    },
    testMethod (val) {
      this.dialog = true
      this.updateLabel = val
    }
  }
}
</script>

<style scoped>

</style>

<!--
* @Descripttion:
* @Project: front
* @Author: yaoMinC
* @Date: 14/1/2022
-->
<template>
  <v-main style="height: 100vh; width: 100vw">
    <v-card color="pa-8 pb-16 pt-10" style="height: 120%; width: 100%;">
      <v-row class="ml-4 mb-n3">
        <h2>系统用户</h2>
      </v-row>
      <!-- 搜索 -->
      <v-row class="ml-4">
        <v-col class="ml-0 pl-0" cols="4">
          <v-text-field v-model="searchParams.nick" placeholder="输入用户昵称检索用户" solo></v-text-field>
        </v-col>
        <v-col class="ml-0 pl-0 mt-1 mr-1" cols="1">
          <v-btn color="primary" @click="getUser">搜索</v-btn>
        </v-col>
      </v-row>

      <!-- 过滤 -->
      <v-row class="ml-4 mt-0">
        <v-col class="ml-0 pl-0">
          <span>用户状态:</span>
          <v-btn-toggle tile color="grey" group>
            <v-btn value="left" @click="getForms(null)">
              <span style="color: #424242">全部</span>
            </v-btn>
            <v-btn value="center" @click="getForms('0')">
              <span style="color: #424242">已冻结</span>
            </v-btn>
            <v-btn value="right" @click="getForms('1')">
              <span style="color: #424242">正常</span>
            </v-btn>
          </v-btn-toggle>
        </v-col>
      </v-row>

      <!-- 表格显示 -->
      <v-simple-table>
        <template v-slot:default>
          <thead>
          <tr>
            <th v-for="(item, index) in headers" :key="index">
              {{ item.text }}
            </th>
          </tr>
          </thead>
          <tbody>
          <!--<tr v-for="item in desserts" :key="item.name">
            <td>{{ item.name }}</td>
            <td>{{ item.calories }}</td>
          </tr>-->
          <tr v-for="(form, index) in userList" :key="index">
            <td v-for="(item, index) in headers" :key="index" class="mb-2">
              {{ form[item.value] }}
            </td>
            <v-btn v-if="form.status === '冻结'" class="mt-2 mr-1" color="primary" small @click="handleUpdate(form, '1')">启用</v-btn>
            <v-btn v-else class="mt-2" color="red" dark small @click="handleUpdate(form, '0')">禁用</v-btn>
          </tr>
          </tbody>
        </template>
      </v-simple-table>

      <!-- 分页 -->
      <div class="text-center ma-8">
        <v-pagination
            v-model="page"
            :length="6"
            @input="getForms(null)"
        ></v-pagination>
      </div>
    </v-card>
  </v-main>
</template>

<script>
import { getUserList, getUserByNick, updateUserInfo } from "@/api/user/user";
import {success} from "@/utils/snackBar";

export default {
  data () {
    return {
      userForm: {
        status: null,
        userId: null
      },
      searchParams: {
        nick: null
      },
      page: 1,
      pageSize: 10,
      userList: [],
      headers: [
        { text: '用户昵称', value: 'nick' },
        { text: '邮箱账号', value: 'email' },
        { text: '联系方式', value: 'phone' },
        { text: '性别', value: 'sex' },
        { text: '年龄', value: 'age' },
        { text: '用户状态', value: 'status' },
        { text: '创建时间', value: 'createTime' },
      ],
    }
  },
  mounted () {
    getUserList(this.userForm, this.page, this.pageSize).then(res => {
      if (res.success) {
        res.record.forEach(item => {
          // 处理status显示状态
          switch (item.status) {
            case '0' :
              item.status = '冻结'
              break
            case '1' :
              item.status = '正常'
              break
          }
          // 处理性别显示
          switch (item.sex) {
            case '0' :
              item.sex = '男'
              break
            case '1' :
              item.sex = '女'
              break
            case '2' :
              item.sex = '未填写'
              break
          }
        })
        this.userList = res.record
      } else {
        alert("获取用户列表失败" + res.errorMsg)
      }
    })
  },
  methods: {
    // 获取订单
    getForms (status) {
      this.userForm.status = status
      getUserList(this.userForm, this.page, this.pageSize).then(res => {
        if (res.success) {
          res.record.forEach(item => {
            // 处理status显示状态
            switch (item.status) {
              case '0' :
                item.status = '冻结'
                break
              case '1' :
                item.status = '正常'
                break
            }
            // 处理性别显示
            switch (item.sex) {
              case '0' :
                item.sex = '男'
                break
              case '1' :
                item.sex = '女'
                break
              case '2' :
                item.sex = '未填写'
                break
            }
          })
          this.userList = res.record
        } else {
          alert("获取用户列表失败" + res.errorMsg)
        }
      })
    },
    getUser() {
      getUserByNick(this.searchParams).then(res => {
        this.userList = res.record
      })
    },
    handleUpdate (val, status) {
      let userModel = {}
      userModel.status = status
      userModel.email = val.email
      console.log(userModel)
      updateUserInfo(userModel).then(res => {
        if (res.success) {
          this.getForms()
          success("修改成功")
        }
      })
    }
  }
}
</script>

<style scoped>

</style>

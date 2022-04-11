<!--
* @Descripttion:
* @Project: front
* @Author: yaoMinC
* @Date: 14/1/2022
-->
<template>
  <v-main style="height: 100vh; width: 100vw">
    <v-card color="pa-8 pb-16 pt-10" style="height: 100%; width: 100%;">
      <v-row class="ml-4 mb-n3">
        <h2>系统用户</h2>
      </v-row>
      <v-row class="ml-4">
        <v-col class="ml-0 pl-0">
          <span>用户角色:</span>
          <v-btn-toggle tile color="grey" group>
            <v-btn value="left" @click="getForms(null)">
              <span style="color: #424242">全部</span>
            </v-btn>
            <v-btn value="center" @click="getForms('2')">
              <span style="color: #424242">普通用户</span>
            </v-btn>
            <v-btn value="right" @click="getForms('1')">
              <span style="color: #424242">主持人</span>
            </v-btn>
            <v-btn value="justify" @click="getForms('0')">
              <span style="color: #424242">管理员</span>
            </v-btn>
          </v-btn-toggle>
        </v-col>
      </v-row>

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
          <tr v-for="(form, index) in formList" :key="index">
            <td v-for="(item, index) in headers" :key="index" class="mb-2">
              {{ form[item.value] }}
            </td>
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
import { getFormList, removeForm, updateForm } from "@/api/host/hostForm";

export default {
  data () {
    return {
      userForm: {
        status: null,
        userId: null
      },
      hostForm: {
        formId: undefined,
        status: null,
      },
      page: 1,
      pageSize: 10,
      formList: [],
      headers: [
        { text: '主持人', value: 'nick' },
        { text: '活动日期', value: 'date' },
        { text: '金额(元)', value: 'money' },
        { text: '类型', value: 'type' },
        { text: '地址', value: 'address' },
        { text: '订单状态', value: 'status' },
        { text: '创建者', value: 'createBy' },
        { text: '创建时间', value: 'createTime' },
      ],
    }
  },
  mounted () {
    this.hostForm.createBy = null
    getFormList(this.hostForm, this.page, this.pageSize).then(res => {
      if (res.success) {
        res.record.forEach(item => {
          // 处理status显示状态
          switch (item.status) {
            case '1' :
              item.status = '已完成'
              break
            case '2' :
              item.status = '进行中'
              break
            case '0' :
              item.status = '已取消'
              break
          }
        })
        this.formList = res.record
      } else {
        alert("获取订单列表失败" + res.errorMsg)
      }
    })
  },
  methods: {
    removeForm (val) {
      console.log(val)
      removeForm(val).then(res => {
        if (res.success) {
          alert('删除成功')
          this.getForms()
        }
      })
    },
    // 获取订单
    getForms (status) {
      this.hostForm.status = status
      getFormList(this.hostForm, this.page, this.pageSize).then(res => {
        if (res.success) {
          res.record.forEach(item => {
            // 处理status显示状态
            switch (item.status) {
              case '1' :
                item.status = '已完成'
                break
              case '2' :
                item.status = '进行中'
                break
              case '0' :
                item.status = '已取消'
                break
            }
          })
          this.formList = res.record
        } else {
          this.formList = null
        }
      })
    },
    // 修改订单状态
    alterForm (formId, status) {
      this.hostForm.status = status
      this.hostForm.formId = formId
      updateForm(this.hostForm)
      alert('修改成功')
      this.getForms()
    }
  }
}
</script>

<style scoped>

</style>

<!--
* @Descripttion: 功能测试
* @Project: front
* @Author: yaoMinC
* @Date: 12/1/2022
-->
<template>
  <v-main style="width: 100%; height: 100%; color: darkgrey">
    <v-container class="mt-16 pt-16">
      <!-- 登录测试 -->
      <v-row>
        <router-link to="/login">跳转至登录页</router-link>
      </v-row>
      <!-- 输入测试 -->
      <!-- bug，中文输入 -->
      <v-row>
        <router-link to="/textTest">跳转至输入测试页</router-link>
      </v-row>
      <!-- 校验测试，ref表单校验 -->
      <!-- bug，不能全部校验 -->
      <v-row>
        <router-link to="/validTest">跳转至校验测试页</router-link>
      </v-row>
      <!-- 组件传参测试，props -->
      <v-row>
        <router-link to="/paramShowTest">跳转至组件传参测试页</router-link>
      </v-row>
      <!-- vuex测试 -->
      <v-row>
        <router-link to="/vuexTest">跳转至vuex测试页</router-link>
      </v-row>
      <!-- 跳转至组件切换测试 -->
      <v-row>
        <router-link to="/changeTest">跳转至组件切换</router-link>
      </v-row>
      <!-- 测试menu -->
      <v-row>
        <router-link to="/menuTest">menu测试</router-link>
      </v-row>
      <!-- 测试数据获取 -->
      <v-row>
        <router-link to="/getHostList">测试获取主持人列表</router-link>
      </v-row>
      <!-- 测试行列循环 -->
      <v-row>
        <router-link to="/colRowTest">测试行列循环</router-link>
      </v-row>
      <!-- 浮动表格测试 -->
      <v-row>
        <router-link to="/floatTest">浮动表格测试</router-link>
      </v-row>
      <!-- 多选选项传参 -->
      <v-row>
        <router-link to="/manyParamsTest">多选选项传参</router-link>
      </v-row>
      <!-- 分页测试 -->
      <v-row>
        <router-link to="/pageTest">分页测试</router-link>
      </v-row>
      <!-- 字符串转换为日期测试 -->
      <v-row>
        <router-link to="/stringToDate">字符串转日期测试</router-link>
      </v-row>
    </v-container>
  </v-main>
</template>

<script>
export default {
}
</script>

<style scoped>

</style>

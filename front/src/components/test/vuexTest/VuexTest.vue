<!--
* @Descripttion:
* @Project: front
* @Author: yaoMinC
* @Date: 13/1/2022
-->
<template>
  <v-main style="height: 100%; width: 100%">
    <v-container>
      <!-- 显示vuex中的数据 -->
      <v-row>
        <h1>{{ $store.state.loginStatus }}</h1>
        <h1>{{ $store.state.title }}</h1>
      </v-row>
      <!-- 操作vuex中的数据 -->
      <!-- 修改vuex中title中的值 -->
      <v-row>
        <v-btn @click="updateTitle">点击修改标题</v-btn>
      </v-row>
      <!-- 传入vuex中title属性的值 -->
      <v-row>
        <v-text-field @input="inputTitle" v-model="title" label="修改title"></v-text-field>
      </v-row>
    </v-container>
  </v-main>
</template>

<script>
export default {
  name: "VuexTest",
  data () {
    return {
      title: ''
    }
  },
  methods: {
    updateTitle () {
      this.$store.commit('$_updateTitle')
    },
    inputTitle () {
      this.$store.commit('$_updateTitle', this.title)
      console.log(this.title)
    }
  }
}
</script>

<style scoped>

</style>

<!--
* @Descripttion:
* @Project: front
* @Author: yaoMinC
* @Date: 12/1/2022
-->
<template>
    <v-btn @click="check">
      点击触发事件
    </v-btn>
</template>

<script>
export default {
  name: "ValidaterTest",
  methods: {
    check () {
      let pattern = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/,
      str = 'yaomingcehn@qq.com'
      console.log(pattern.test(str))
    }
  }
}
</script>

<style scoped>

</style>

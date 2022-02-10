<!--
* @Descripttion:
* @Project: outage
* @Author: yaoMinC
* @Date: 9/2/2022
-->
<template>
  <div>
    <label v-for="(item, index) in $store.state.list" :key="index">{{ item }}<br></label>
    <v-text-field v-model="message"></v-text-field>
    <v-btn @click="addMsg">添加</v-btn>
  </div>
</template>

<script>
export default {
  name: "MsgListTest",
  data () {
    return {
      message: '',
      msg: {
        label: 'xx',
        msgText: '',
        createTime: null
      },
    }
  },
  methods: {
    addMsg () {
      this.msg.createTime = new Date()
      this.msg.msgText = this.message
      this.$store.commit('$_addMsg',this.msg)
      console.log(this.$store.state.list)
    }
  }
}
</script>

<style scoped>

</style>

<!--
* @Descripttion:
* @Project: front
* @Author: yaoMinC
* @Date: 12/1/2022
-->
<template>
  <v-container>
    <v-row>
      <v-text-field color="blue-gray" placeholder="测试中文输入" v-model="testText" @keydown="keyDown"></v-text-field>
    </v-row>
    <v-row>
      <v-input v-model="testText">fsfas</v-input>
    </v-row>
  </v-container>
</template>

<script>
export default {
  name: "TextTest",
  data () {
    return {
      text: '',
      key: ''
    }
  },
  computed: {
    testText: {
      get: function () {
        return this.text
      },
      set: function (val) {
        this.text = val.toUpperCase()
      }
    }
  },
/*  watch: {
    key(newVal, oldVal) {
      this.text += this.key.toUpperCase()
    }
  },*/
  /*methods: {
    keyDown(event) {
      if (event.code.indexOf('Key') >= 0) {
        this.key = event.code.substr(3, 4)
      }
    }
  }*/
}
</script>

<style scoped>

</style>

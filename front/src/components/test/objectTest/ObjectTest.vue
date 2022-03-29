<!--
* @Descripttion:
* @Project: outage
* @Author: yaoMinC
* @Date: 12/2/2022
-->
<template>
  <v-main>

  </v-main>
</template>

<script>
export default {
  name: "ObjectTest",
  data () {
    return {
      txt: []
    }
  },
  created () {
    console.log(this.setData().person)
    // let test = {}
    // let text = ''
    // test.txtTest = function () {
    //   text = 'fdasfdsf'
    //   console.log(text)
    //
    //   // 属性方法不能直接调用data中的属性，只能对data中的属性进行赋值操作
    //   // this.txt.push('ss')
    //   // console.log(this.txt)
    // }
    // test.txtTest()
    let testObj = {}
    testObj.element = function () {
      console.log('这是一个测试')
    }
    testObj.element()
  },
  methods: {
    setData () {
      return {
        person: {
          name: 'xxx',
          age: 10,
          sex: 'M'
        },
        msgList: []
      }
    }
  }
}
</script>

<style scoped>

</style>

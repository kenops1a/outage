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
    let test = {}
    test.txtTest = function () {
      this.txt.push('ss')
      console.log(this.txt)
    }
    test.txtTest()
  },
  methods: {

  }
}
</script>

<style scoped>

</style>

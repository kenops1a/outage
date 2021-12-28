<!--
* @Descripttion:
* @Project: outage
* @Author: yaominc
* @Date: 2021/12/27
* @LastEditiors: yaominc
-->
<template>
  <div>
    <Carousels></Carousels>
  </div>
</template>

<script>
import Carousels from "@/components/component/Carousels";
export default {
  name: "Index",
  components: {Carousels},
  data () {
    return {
      home: '别看了这里啥也没有'
    }
  }
}
</script>

<style scoped>
  .home {
    margin-top: 10%;
    font-size: 2.4em;
    font-weight: bold;
  }
</style>
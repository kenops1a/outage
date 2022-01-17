<!--
* @Descripttion:
* @Project: front
* @Author: yaoMinC
* @Date: 14/1/2022
-->
<template>
    <v-main style="height: 100vh;">
      <!-- 显示vuex中的数据 -->
    </v-main>
</template>

<script>
import router from '@/router/router'
export default {
  name: "UserInfo",
  mounted() {
    router.push('/userInfo')
  }
}
</script>

<style scoped>

</style>

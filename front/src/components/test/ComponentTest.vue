<!--
* @Descripttion:
* @Project: front
* @Author: yaoMinC
* @Date: 10/1/2022
-->
<template>
  <div>
    <Login v-if="label === 1"></Login>
    <Register v-if="label === 0"></Register>
  </div>
</template>

<script>
export default {
  name: "Component",
  data () {
    return {
      label: 1,
    }
  },
  components: {
    Register: () => import('@/components/account/register/Register'),
    Login: () => import('@/components/account/login/Login')
  }
}
</script>

<style scoped>

</style>

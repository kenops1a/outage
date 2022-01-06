<!--
* @Descripttion:
* @Project: outage
* @Author: yaoMinC
* @Date: 2022/1/5
-->
<template>
  <div>
    <button v-on:click="pdLogin">测试</button>
  </div>
</template>

<script>
import { loginByPd,loginByVc } from "@/api/account/login";

export default {
  name: "Login",
  data () {
    return {
      // 账号密码表单
      pdForm: {
        email: 'xx',
        password: 'helloworld',
        verifyCode: undefined,
        remember: false
      },
      // 张号验证码表单
      vcForm: {
        email: '',
        verifyCode: undefined,
      }
    }
  },
  methods: {
    pdLogin () {
      loginByPd(this.pdForm).then(res => {
        if (res.data.success) {
          // 将后台token放入localStorage
          localStorage.setItem('token', res.data.data)
          alert("登录成功")
        } else {
          alert("登录失败")
        }
      })
    },
    vcLogin () {
      loginByVc(this.vcForm)
    }
  }
}
</script>

<style scoped>

</style>

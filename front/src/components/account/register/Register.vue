<!--
* @Descripttion:
* @Project: front
* @Author: yaoMinC
* @Date: 6/1/2022
-->
<template>
    <v-form id="register-form">
      <v-container>
        <!-- mx-auto卡片居中 -->
        <v-card id="register-card" raised="3" class="mx-auto my-12" max-width="430px" height="600px">
          <!-- 标题 -->
          <v-card-text>
            <v-row align="center" class="mx=0 mt-5">
              <v-col cols="12">
                <div id="title-text"><h2>Register</h2></div>
              </v-col>
            </v-row>
          </v-card-text>

          <!-- mb缩短行间距，mb-n，n越大行间距越大-->
          <!-- no-gutters, 无栅格间隔 -->
          <v-row id="text-box" v-for="row in rows" :key="row" class="mb-0" no-gutters align="center">
            <v-spacer></v-spacer>
              <v-col cols="9">
                <v-text-field :label="row.label" :placeholder="row.placeHolder"></v-text-field>
              </v-col>
            <v-spacer></v-spacer>
          </v-row>

          <!-- 验证码 -->
          <v-row id="verifyCode-box" justify="center" no-gutters>
            <v-col cols="6" align-self="center">
              <v-text-field label="verifyCode" placeholder="enter verifyCode"></v-text-field>
            </v-col>
            <v-col cols="3" align-self="center">
              <v-btn class="ms-2" small dark>
                <span>verifyCode</span>
              </v-btn>
            </v-col>
          </v-row>

          <!-- 按钮 -->
          <v-row id="register-btn">
            <v-col cols="2" offset="4">
              <v-btn value="Register" color="primary" large>Register With</v-btn>
            </v-col>
          </v-row>

          <!-- 跳转至登录 -->
        </v-card>
      </v-container>
    </v-form>
</template>

<script>
import { register } from "@/api/account/account";
import { getVc } from "@/api/mail/mail";

export default {
  data () {
    return {
      // 行数
      rows: [
        { id: 1, label: 'nick', placeHolder: 'enter nick'},
        { id: 1, label: 'email', placeHolder: 'enter email'},
        { id: 1, label: 'password', placeHolder: 'enter password'},
        { id: 1, label: 'password again', placeHolder: 'enter password again'},
      ],
      regForm: {
        email: '',
        password: undefined,
        enterPassword: undefined,
        nick: '',
        verifyCode: undefined
      },
    }
  },
  // 渲染前修改背景色
  mounted() {
    document.querySelector('body').setAttribute('style', 'background-color:black')
  },
  // 销毁前复原背景色
  beforeDestroy() {
    document.querySelector('body').removeAttribute('style')
  },
  methods: {
    getVerifyCode() {
      getVc(this.regForm).then(res => {
        if (res.data.success) {
          console.log("验证码获取成功")
        } else {
          console.log("验证码获取失败")
        }
      })
    },
    registerByItem () {
      register(this.regForm).then(res => {

      })
    }
  }
}

</script>

<!-- scoped标识该css样式只对当前组件生效 -->
<style scoped>
#register-card {
  margin:5% 10% 10%;
  padding: 3% 0;
  background-color: #ffffff;
}

#title-text {
  text-align: center;
}

#register-form {
  background-color: #f0f0f0;
}

</style>

<!--
* @Descripttion:
* @Project: front
* @Author: yaoMinC
* @Date: 6/1/2022
-->
<template>
  <v-main class="pt-0">
    <v-form id="register-form" ref="regValid" style="width: 100%; height: 100%">
      <v-container>
        <!-- mx-auto卡片居中 -->
        <v-card id="register-card" raised="3" class="mx-auto my-12" max-width="415px" height="600px">

          <!-- 标题 -->
          <v-card-text>
            <v-row align="center" class="mx=0 mt-5">
              <v-col cols="12">
                <div id="title-text"><h2>用户注册</h2></div>
              </v-col>
            </v-row>
          </v-card-text>

          <!-- mb缩短行间距，mb-n，n越大行间距越大-->
          <!-- no-gutters, 无栅格间隔 -->
          <v-row id="text-box" v-for="(row, index) in rows" :key="index" class="mb-0" no-gutters align="center">
            <v-spacer></v-spacer>
              <v-col cols="9">
                <v-text-field :label="row.label" :type="inputType(row.label)" color="blue-grey" v-model="regForm[row.label]" :rules="rules[row.label]" :placeholder="row.placeHolder"></v-text-field>
              </v-col>
            <v-spacer></v-spacer>
          </v-row>

          <!-- 验证码输入 -->
          <v-row id="verifyCode-box" class="ma-0" justify="center" no-gutters>
            <v-col cols="6" align-self="center">
              <v-text-field label="verifyCode" v-model="verifyUpper" :rules="rules.verifyCode" color="blue-grey" placeholder="输入验证码"></v-text-field>
            </v-col>
            <v-col cols="3" align-self="center">
              <v-btn class="ml-3 pa-2 mb-2" @click="getVerifyCode" depressed tile>
                <span>获取验证码</span>
              </v-btn>
            </v-col>
          </v-row>

          <!-- 按钮 -->
          <v-row no-gutters id="register-btn" class="mt-6">
            <v-col cols="12" class="text-center">
              <v-btn @click="registerByItem" value="register" color="#607D8B" large><span style="color: #f9fafb">注册</span></v-btn>
            </v-col>
          </v-row>

          <!-- 跳转至登录 -->
          <v-row class="mb-8 ml-11">
            <v-col cols="4">
              <span>已有账号?</span>
              <router-link to="/login" style="color: #607D8B">点击登录</router-link>
            </v-col>
          </v-row>
        </v-card>
      </v-container>
    </v-form>
  </v-main>
</template>

<script>
import { register } from "@/api/account/account";
import { getVc } from "@/api/mail/mail";

export default {
  data () {
    return {
      // 行数
      rows: [
        { id: 1, label: 'nick', placeHolder: '用户昵称'},
        { id: 2, label: 'email', placeHolder: '登录邮箱'},
        { id: 3, label: 'password', placeHolder: '密码'},
        { id: 4, label: 'enterPassword', placeHolder: '确认密码'},
      ],
      regForm: {
        email: '',
        password: undefined,
        enterPassword: undefined,
        nick: '',
        verifyCode: undefined
      },
      rules: {
        // 邮箱校验规则正则表达式
        emailRule: /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/,
        nickRule: /^[A-Za-z0-9]+$/,
        nick: [ val => (val.length >= 9 && this.rules.nickRule.test(val) ) || `昵称由英文和数字组成，且长度不小于9位，不大于16位`],
        email: [ val => this.rules.emailRule.test(val) || `邮箱格式不正确` ],
        password: [ val => (val.length >= 9 && val.length <= 16) || `密码长度应小于16位大于9位且不能含有空格` ],
        enterPassword: [ val => val === this.regForm.password || `两次密码输入不一致` ],
        verifyCode: [ val => val.length === 6 || `请输入6位不含空格的验证码` ]
      },
    }
  },
  // 渲染前修改背景色
  mounted() {
    document.querySelector('body').setAttribute('style', 'background-color:black')
  },
  updated() {
    // console.log(this.regForm.email)
  },
  // 销毁前复原背景色
  beforeDestroy() {
    document.querySelector('body').removeAttribute('style')
  },
  computed: {
    inputType () {
      return function (label) {
        if (label === 'password' || label === 'enterPassword') {
          return 'password'
        }
        return 'text'
      }
    },
    verifyUpper: {
      get: function () {
        return this.regForm.verifyCode
      },
      set: function (val) {
        this.regForm.verifyCode = val.toUpperCase()
      }
    }
  }
  ,
  methods: {
    getVerifyCode() {
      let vcModel = {
        email: this.regForm.email
      }
      getVc(vcModel).then(res => {
        if (res.data.success) {
          console.log("验证码获取成功")
        } else {
          console.log("验证码获取失败")
        }
      })
    },
    registerByItem () {
      if (this.$refs.regValid.validate()) {
        register(this.regForm).then(res => {
          if (res.data.success) {
            console.log('注册成功')
          }
          else {
            console.log('注册失败')
          }
        })
      }
    }
  }
}

</script>

<!-- scoped标识该css样式只对当前组件生效 -->
<style scoped>
#register-card {
  background-color: #ffffff;
}

#title-text {
  text-align: center;
}

#register-form {
  background-color: #f0f0f0;
}

h2 {
  font-family: "Apple SD Gothic Neo", sans-serif;
  color: #777777;
}

</style>

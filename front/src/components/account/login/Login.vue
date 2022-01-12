<!--
* @Descripttion:
* @Project: outage
* @Author: yaoMinC
* @Date: 2022/1/5
-->
<template>
  <v-main>
    <v-form id="login-form" style="height: 100%; width: 100%">
      <v-container>
      <!-- mx-auto卡片居中 -->
      <v-card id="login-card" raised="3" class="mx-auto my-12" max-width="415px" height="600px">

        <!-- 标题 -->
        <v-card-text>
          <v-row align="center" class="mx=0 mt-5">
            <v-col cols="12">
              <div id="title-text"><h2>系统用户登录</h2></div>
            </v-col>
          </v-row>
        </v-card-text>

        <!-- 头像框 -->
        <v-row class="ma-3">
          <v-col cols="12" class="text-center">
            <v-avatar color="blue-grey" size="67" class="ma-auto">
              <span class="white--text text-h6">褪色</span>
            </v-avatar>
          </v-col>
        </v-row>

        <!-- 登录方式选择按钮 -->
        <v-row no-gutters justify="center">
          <v-col cols="12" class="py-2 text-center">
            <v-btn-toggle tile group depressed>
              <v-btn value="password" @click="type=0" class="mx-3" color="grey lighten-1" outlined>
                <span style="color: #424242">账号密码登录</span>
              </v-btn>
              <v-btn value="verifyCode" @click="type=1" color="grey lighten-1" outlined>
                <span style="color: #424242">验证码登录</span>
              </v-btn>
            </v-btn-toggle>
          </v-col>
        </v-row>

        <!-- 账密登录 -->
        <div id="login-pd-box" v-if="type === 0">
          <!-- mb缩短行间距，mb-n，n越大行间距越大-->
          <!-- no-gutters, 无栅格间隔 -->
          <!-- num 循环下标 -->
          <v-row id="text-box" v-for="(row, num) in accountRows" :key="num" class="mb-0" no-gutters align="center">
            <v-spacer></v-spacer>
            <v-col cols="9">
              <!-- 绑定type属性 -->
              <v-text-field :label="row.label" :type="inputType(row.label)" :rules="rules[row.label]" :placeholder="row.placeholder" v-model="pdForm[row.label]" color="blue-grey"></v-text-field>
            </v-col>
            <v-spacer></v-spacer>
          </v-row>

          <!-- 记住我 -->
          <v-row no-gutters class="mb-0 ml-13">
            <v-col class="mb-n5">
              <v-checkbox class="mb-0" color="grey lighten" v-model="remember" label="记住我"></v-checkbox>
            </v-col>
          </v-row>
        </div>

        <!-- 验证码登录 -->
        <div id="login-vc-box" v-if="type === 1">
          <!-- 邮箱输入框 -->
          <v-row id="text-vc-box" v-for="row in verifyRows" :key="row" class="mb-0" no-gutters align="center">
            <v-spacer></v-spacer>
            <v-col cols="9">
              <v-text-field :rules="rules.email" :label="row.label" v-model="vcForm.email" color="blue-grey" :placeholder="row.placeholder"></v-text-field>
            </v-col>
            <v-spacer></v-spacer>
          </v-row>

          <!-- 验证码输入 -->
          <!-- 有个小bug，中文输入模式会一次输入4个字符 -->
          <v-row id="verifyCode-box" class="ma-0" justify="center" no-gutters>
            <v-col cols="6" align-self="center">
              <v-text-field label="verifyCode" :rules="rules.verifyCode" v-model="verifyUpper" color="blue-grey" placeholder="输入验证码"></v-text-field>
            </v-col>
            <v-col cols="3" align-self="center">
              <v-btn class="ml-3 pa-2 mb-2" depressed tile>
                <span>获取验证码</span>
              </v-btn>
            </v-col>
          </v-row>

          <!-- 记住我，不可选，只是装饰，使原本布局不发生变化 -->
          <v-row no-gutters class="mb-0 ml-13">
            <v-col class="mb-n5">
              <v-checkbox class="mb-0" label="记住我" disabled></v-checkbox>
            </v-col>
          </v-row>
        </div>
        <!-- 按钮 -->
        <v-row no-gutters id="login-btn" class="my-0">
          <v-col cols="12" class="text-center">
            <v-btn value="login" color="#607D8B" large><span style="color: #f9fafb">登录</span></v-btn>
          </v-col>
        </v-row>

        <!-- 跳转至注册 -->
        <v-row class="mb-8 ml-11">
          <v-col cols="4">
            <span>没有账号?</span>
            <router-link to="/register" style="color: #607D8B">点击注册</router-link>
          </v-col>
        </v-row>

      </v-card>
    </v-container>
    </v-form>
  </v-main>
</template>

<script>
import { loginByPd,loginByVc } from "@/api/account/account";
import { getVc } from "@/api/mail/mail";

export default {
  name: "Login",
  data () {
    return {
      // 显示组件切换标识，0-账号密码登录，1-验证码登录，2-注册
      type: 0,
      // 记住我, 默认为记不住
      remember: false,
      // 校验规则, 可能写的不对
      rules: {
        // 邮箱校验规则正则表达式
        emailRule: /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/,
        email: [ val => this.rules.emailRule.test(val) || `邮箱格式不正确` ],
        password: [ val => (val.length >= 9 && val.length <= 16) || `密码长度应小于16位大于9位且不能含有空格` ],
        verifyCode: [ val => val.length === 6 || `请输入6位不含空格的验证码` ]
      },
      // 账号密码表单
      pdForm: {
        email: '',
        password: '',
      },
      // 张号验证码表单, 涉及到url字符编码，@等字符会自动被http转义
      vcForm: {
        email: '',
        verifyCode: undefined,
      },
      accountRows: [
        {id: 0, label: 'email', placeholder: '登录邮箱'},
        {id: 1, label: 'password', placeholder: '密码'},
      ],
      verifyRows: [
        {id: 0, label: 'email', placeholder: '登录邮箱'},
      ]
    }
  },
  // 渲染前工作
  mounted() {
    // 判断localStore中是否有密码保存
    if (localStorage.getItem('password')) {
      // 将存储中的邮箱和密码渲染到组件上
      this.pdForm.email = localStorage.getItem('email')
      this.pdForm.password = localStorage.getItem('password')
    }
  },

  updated() {
    console.log("email" + this.pdForm.email)
  },
  // 计算属性
  computed: {
    // 修改label为‘password’的文本框type为password
    inputType () {
      return function (label) {
        if (label === 'password') {
          return 'password';
        }
        return 'text';
      }
    },
    // 将验证码输入值转为大写
    verifyUpper: {
      get: function () {
        return this.vcForm.verifyCode
      },
      set: function (val) {
        this.vcForm.verifyCode = val.toUpperCase()
      }
    },
    // 当输入长度为6时禁用输入
    maxLength () {
      return function (val) {
        console.log('val' + val)
        if (val.length === 6) {
          return 'disabled'
        }
        return ''
      }
    }
  },

  methods: {
    /*pdLogin () {
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
    }*/
    getVerifyCode () {
      let email = this.vcForm.email
      // 判断email是否为空
      if (email) {
        getVc(this.vcForm).then(res => {
          if (!res.data.success) {
            console.log("验证码获取失败")
          }
          else {
            console.log("验证码获取成功")
          }
        })
      }
    },
    login () {
      // 判断type，选择不同的登录方式
      if (this.type === 0) {
        loginByPd(this.pdForm).then(res => {
          // 后台是否登录成功
          if (res.data.success) {
            // 将token存入localStorage
            localStorage.setItem('token', res.data.data)

            // 判断是否记住密码，如果记住密码，则将密码存入localStorage
            if (this.remember) {
              // 在localStorage存入账号和密码
              localStorage.setItem('email', this.pdForm.email)
              localStorage.setItem('password', this.pdForm.password)
            }
            console.log("登录成功")
          } else {
            console.log("登录失败")
          }
        })
      }
      if (this.type === 1) {
        loginByVc(this.vcForm).then(res => {
          // 后台是否登录成功
          if (res.data.success) {
            // 将token存入localStorage
            localStorage.setItem('token', res.data.data)
          } else {
            console.log("登录失败")
          }
        })
      }
    }
  }
}
</script>

<style scoped>
#title-text {
  text-align: center;
}

#login-form {
  background-color: #f0f0f0;
}
h2 {
  font-family: "Apple SD Gothic Neo", sans-serif;
  color: #777777;
}


</style>

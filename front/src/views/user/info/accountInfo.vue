<!--
* @Descripttion:
* @Project: front
* @Author: yaoMinC
* @Date: 22/1/2022
-->
<template>
  <v-main style="height: 100vh; width: 100vw">
    <v-card color="pa-8 pb-16 pt-10" min-width="100%" min-height="100%">
      <v-row class="ml-4 mb-n3">
        <h2>账号信息</h2>
      </v-row>

      <!-- 账号密码管理 -->
      <v-row class="mt-8">
        <v-col cols="6">
          <v-expansion-panels multiple>
            <!-- 账号 -->
            <v-expansion-panel>
              <v-expansion-panel-header>
                邮箱账号：{{ email }}
<!--                <template v-slot:actions>-->
<!--                  <v-icon color="blue-grey">-->
<!--                    mdi mdi-pencil-->
<!--                  </v-icon>-->
<!--                </template>-->
              </v-expansion-panel-header>
              <v-expansion-panel-content>
                <v-simple-table>
                  <template v-slot:default>
                    <tbody>
                      <tr v-for="(item, index) in showList" :key="index">
                        <td>{{ item.label }}</td>
                        <td>{{ userModel[item.value] }}</td>
                      </tr>
                      <tr>
                        <td>账号类型</td>
                        <td>{{ accountType }}</td>
                      </tr>
                      <!--<tr v-for="(item, index) in showList" :key="index">
                        <td>{{ item.label }}:</td>
                        <td>
                          {{ userModel[item.value] }}
                          <v-icon size="16" color="blue-grey" class="ml-2" @click.stop="testMethod(item)">{{ "mdi mdi-pencil" }}</v-icon>
                        </td>
                      </tr>
                      <tr>
                        <td>注册时间:</td>
                        <td>
                          {{ userModel.createTime }}
                        </td>
                      </tr>-->

                    </tbody>
                  </template>
                </v-simple-table>
              </v-expansion-panel-content>
            </v-expansion-panel>

            <!-- 密码 -->
            <v-expansion-panel>
              <v-expansion-panel-header>
                登录密码：{{ password.label }}
                <template v-slot:actions>
                  <v-icon color="blue-grey">
                    mdi mdi-pencil
                  </v-icon>
                </template>
              </v-expansion-panel-header>
              <v-expansion-panel-content>
                <v-form ref="passwordForm">
                  <v-row class="pl-3 pt-n3 pr-3">
                    <v-text-field v-model="oldPassword" placeholder="输入原密码" :rules="rules.password" type="password"></v-text-field>
                  </v-row>
                  <v-row class="pl-3 pt-0 pr-3">
                    <v-text-field v-model="password.value" placeholder="输入新密码" :rules="rules.password" type="password"></v-text-field>
                  </v-row>
                  <v-row class="pa-3 pt-0">
                    <v-text-field v-model="enterPassword" placeholder="确认新密码" :rules="rules.enterPassword" type="password"></v-text-field>
                  </v-row>
                  <v-row class="pa-3 pt-0">
                    <v-btn-toggle tile group>
                      <v-btn @click="updatePassword">确认修改</v-btn>
                    </v-btn-toggle>
                  </v-row>
                </v-form>
              </v-expansion-panel-content>
            </v-expansion-panel>
          </v-expansion-panels>
        </v-col>
      </v-row>

      <v-row class="mt-8 ml-4 mb-4">
        <h2>其他</h2>
      </v-row>

      <!-- 注销账号 -->
      <v-row>
        <v-col cols="6">
          <v-expansion-panels>
            <v-expansion-panel>
              <v-expansion-panel-header>
                注销账号
                <template v-slot:actions>
                  <v-icon color="blue-grey">
                    mdi mdi-pencil
                  </v-icon>
                </template>
              </v-expansion-panel-header>
              <v-expansion-panel-content>
                <v-form ref="delUserForm">
                  <!-- 显示登录账号 -->
                  <v-row class="ma-0" no-gutters>
                    {{ this.email }}
                  </v-row>
                  <!-- 输入密码确认身份 -->
                  <v-row class="ma-0" no-gutters>
                    <v-text-field v-model="removeForm.password" placeholder="输入密码" :rules="rules.password" type="password"></v-text-field>
                  </v-row>
                  <!-- 验证码输入 -->
                  <!-- 有个小bug，中文输入模式会一次输入4个字符 -->
                  <v-row class="ma-0" no-gutters>
                    <v-col cols="6" align-self="center">
                      <v-text-field :rules="rules.verifyCode" v-model="verifyUpper" color="blue-grey" placeholder="输入验证码"></v-text-field>
                    </v-col>
                    <v-col cols="3" align-self="center">
                      <v-btn class="ml-3 pa-2 mb-2" @click="getVerifyCode" depressed tile>
                        <span>获取验证码</span>
                      </v-btn>
                    </v-col>
                  </v-row>
                  <v-row class="pa-3 pt-0">
                    <v-btn-toggle tile group>
                      <v-btn @click="removeUser">确认注销账号</v-btn>
                    </v-btn-toggle>
                  </v-row>
                </v-form>
              </v-expansion-panel-content>
            </v-expansion-panel>
          </v-expansion-panels>
        </v-col>
      </v-row>
    </v-card>
  </v-main>
</template>

<script>
import { removeAccount } from "@/api/account/account";
import { getVc } from "@/api/mail/mail";
import { updateUserInfo, getUserRoleByEmail } from "@/api/user/user";
import router from '@/router/router'
import {removeForm} from "@/api/host/hostForm";

export default {
  data () {
    return {
      accountType: null,
      showList: [
        { label: '账号状态', value: 'status' },
        { label: '注册时间', value: 'createTime' },
      ],
      userNick: this.$store.state.userNow.nick,
      userModel: {},
      email: null,
      oldPassword: null,
      password: {
        label: '未设置',
        value: null
      },
      enterPassword: null,
      rules: {
        password: [ val => (val.length >= 9 && val.length <= 16 && val.indexOf(' ') === -1) || `密码长度应小于16位大于9位且不能含有空格` ],
        enterPassword: [ val => (val === this.password.value) || `两次输入密码不一致` ],
        verifyCode: [ val => val.length === 6 || `请输入6位不含空格的验证码` ]
      },
      removeForm: {
        email: null,
        password: null
      },
      verifyCode: null
    }
  },
  mounted() {
    this.email = this.$store.state.userNow.email
    this.userModel = this.$store.state.userNow
    switch (this.userModel.status) {
      case '1' :
        this.userModel.status = '正常'
            break
      case '0' :
        this.userModel.status = '禁用'
            break
    }
    this.removeForm.email = this.email
    if (localStorage.getItem('password')) {
      this.password.label = '*********'
    }
    getUserRoleByEmail(this.removeForm).then(res => {
      if (res.success) {
        if (res.record === null) {
          this.accountType = '普通用户'
        } else {
          this.accountType = res.record.name
        }
      }
    })
    console.log(this.showList)
  },
  computed: {
    // 将验证码输入值转为大写
    verifyUpper: {
      get: function () {
        return this.verifyCode
      },
      set: function (val) {
        this.verifyCode = val.toUpperCase()
      }
    },
  },
  methods: {
    closeUpdate () {
      // 清空输入参数
      this.oldPassword = null
      this.enterPassword = null
      this.password.value = null
    },
    updatePassword () {
      if (this.$refs.passwordForm.validate()) {
        // 判断原密码是否输入正确
        if (this.oldPassword === localStorage.getItem('password')) {
          let updateForm = {}
          updateForm.email = localStorage.getItem('email')
          updateForm.password = this.password.value
          updateUserInfo(updateForm).then(res => {
            if (res.success) {
              // 刷新localStorage中存储的密码
              localStorage.setItem('password', this.password.value)
              this.$store.state.userNow.password = this.password.value
              localStorage.setItem('userNow', JSON.stringify(this.$store.state.userNow))
            }
          })
          alert('修改成功')
          this.closeUpdate()
        } else {
          alert('原密码错误')
        }
      }
    },
    removeUser () {
      if (this.$refs.delUserForm.validate()) {
        removeAccount(this.removeForm, this.verifyCode).then(res => {
          if (res.success) {
            // 清空vuex和localStorage中存储的所有用户信息
            localStorage.removeItem('email')
            localStorage.removeItem('password')
            localStorage.removeItem('host')
            localStorage.removeItem('userNow')
            localStorage.removeItem('token')
            // 清除vuex中的用户昵称
            this.$store.commit('$_setUserNick','')
            // 修改登录状态
            this.$store.commit('$_updateLoginStatus',0);
            // 清除token
            this.$store.commit('$_removeToken')
            alert('注销成功，退出系统')
            router.push('/home')
          } else {
            alert('注销失败，' + res.errorMsg)
          }
        })
      }
    },
    getVerifyCode () {
      getVc(this.removeForm).then(res => {
        if (res.success) {
          alert('验证码获取成功，请注意查看，有效时长15分钟')
        } else {
          alert('验证码获取失败，' + res.errorMsg)
        }
      })
    }
  }
}
</script>

<style scoped>

</style>

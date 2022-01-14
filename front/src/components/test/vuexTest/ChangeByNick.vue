<!--
* @Descripttion:
* @Project: front
* @Author: yaoMinC
* @Date: 14/1/2022
-->
<template>
  <v-main style="height: 100%; width: 100%">
    <v-container>
      <!-- 显示vuex中的数据 -->
      <v-row>
        <v-col>
          <v-card v-if="$store.state.loginStatus === 1">
            {{this.$store.state.userNick}}
          </v-card>
        </v-col>
        <v-col>
            <v-card v-if="$store.state.loginStatus === 0">
              登录
            </v-card>
        </v-col>
        <v-col>
          <v-btn @click="updateStatus">修改登录状asfhiagbfiuba态</v-btn>
        </v-col>
      </v-row>
    </v-container>
  </v-main>
</template>

<script>
export default {
  name: "ChangeByNick",
  data () {
    return {
      status: 0
    }
  },
  methods: {
    updateStatus () {
      if (this.status === 0) {
        console.log('status,0')
        this.status = 1
      }
      else if (this.status === 1) {
        console.log('status,1')
        this.status = 0
      }
      console.log('修改状态')
      console.log(this.status)
      this.$store.commit('$_updateLoginStatus', this.status)
    }
  }
}
</script>

<style scoped>

</style>

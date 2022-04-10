<!--
* @Descripttion:
* @Project: front
* @Tip: write_bug
* @Author: yaominc
* @Date: 10/4/2022 下午8:00
-->
<template>
  <v-snackbar v-model="isShow" timeout="2000" outlined color="green">
    {{ alertMessage }}
  </v-snackbar>
</template>

<script>
export default {
  name: "SuccessBar",
  computed: {
    isShow: {
      get() {
        return this.$store.state.successSnackBarShow
      },
      set(val) {
        this.$store.commit('$_successBarSwitch')
      }
    },
    alertMessage() {
      return this.$store.state.successSnackBarMessage
    }
  }
}
</script>

<style scoped>

</style>

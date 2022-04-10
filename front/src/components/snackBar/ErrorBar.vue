<!--
* @Descripttion:
* @Project: front
* @Tip: write_bug
* @Author: yaominc
* @Date: 10/4/2022 下午8:12
-->
<template>
  <v-snackbar v-model="isShow" timeout="2000" outlined color="#FF0000">
    {{ alertMessage }}
  </v-snackbar>
</template>

<script>
export default {
  name: "ErrorBar",
  computed: {
    isShow: {
      get() {
        return this.$store.state.errorSnackBarShow
      },
      set(val) {
        this.$store.commit('$_errorBarSwitch')
      }
    },
    alertMessage() {
      return this.$store.state.errorSnackBarMessage
    }
  }
}
</script>

<style scoped>

</style>

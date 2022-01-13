<!--
* @Descripttion:
* @Project: front
* @Author: yaoMinC
* @Date: 13/1/2022
-->
<template>
  <v-main style="height: 100%; width: 100%">
    <v-container>
      <v-row>
        <h1>{{ $store.state.loginStatus }}</h1>
        <h1>{{ $store.state.title }}</h1>
      </v-row>
    </v-container>
  </v-main>
</template>

<script>
export default {
  name: "VuexTest"
}
</script>

<style scoped>

</style>

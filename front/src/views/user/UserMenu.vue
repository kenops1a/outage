<!--
* @Descripttion:
* @Project: front
* @Author: yaoMinC
* @Date: 14/1/2022
-->
<template>
    <v-container>
      <NavLeft/>
      <router-view/>
    </v-container>
</template>

<script>
import NavLeft from "@/views/user/NavLeft";
export default {
  components: {NavLeft},
  data () {
    return {
      items: [
        { title: 'Dashboard', icon: 'mdi-view-dashboard' },
        { title: 'Account', icon: 'mdi-account-box' },
        { title: 'Admin', icon: 'mdi-gavel' },
      ],
    }
  },
}
</script>

<style scoped>

</style>

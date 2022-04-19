<!--
* @Descripttion:
* @Project: front
* @Author: yaoMinC
* @Date: 14/1/2022
-->
<template>
  <div>
    <!-- 普通用户 -->
    <NavLeft v-if="userRole === null"/>
    <!-- 主持人 -->
    <NavLeftHost v-if="userRole !==null && userRole.id === 2"/>
    <NavLeftAdmin v-if="userRole !==null && userRole.id === 3"/>
    <router-view/>
  </div>
</template>

<script>
import NavLeft from "@/views/user/NavLeft";
import NavLeftHost from "@/views/user/NavLeftHost";
import NavLeftAdmin from "@/views/user/NavLeftAdmin";
export default {
  components: {NavLeft, NavLeftHost, NavLeftAdmin},
  data () {
    return {
      items: [
        { title: 'Dashboard', icon: 'mdi-view-dashboard' },
        { title: 'Account', icon: 'mdi-account-box' },
        { title: 'Admin', icon: 'mdi-gavel' },
      ],
      userRole: this.$store.state.userRole
    }
  },
  created() {
    console.log(this.userRole)
  }
}
</script>

<style scoped>

</style>

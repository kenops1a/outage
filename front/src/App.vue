<template>
  <!-- 主项目 -->
  <!--<v-app id="inspire">
    <Header/>
    <router-view></router-view>
    <Footer/>
  </v-app>-->

  <!-- 栅格布局测试 -->
  <!--<div>
    <GridTest></GridTest>
  </div>-->

  <!-- 登录测试 -->
  <LoginTest></LoginTest>
</template>
<script>

export default {
  name: 'App',
  components: {
    // Header: () => import('@/components/Header/Header'),
    // Footer: () => import('@/components/Footer/Footer'),
    // GridTest: () => import('@/components/Test/GridTest')
    LoginTest: () => import('@/components/Test/LoginTest')
  },

  data () {
    return {
    }
  }
};
</script>

<style>

</style>

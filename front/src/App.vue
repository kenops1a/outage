<template>
  <v-app id="inspire">
    <Header/>
    <router-view></router-view>
    <Footer/>
  </v-app>
</template>
<script>

export default {
  name: 'App',
  components: {
    Header: () => import('@/components/Header/Header'),
    Footer: () => import('@/components/Footer/Footer')
  },

  data () {
    return {
    }
  }
};
</script>

<style>

</style>

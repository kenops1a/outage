<template>
  <v-app>
    <Login></Login>
  </v-app>
</template>

<script>

export default {
  name: 'App',
  components: {
    Login: () => import('@/components/login/Login')
  },

  data: () => ({
    //
  }),
};
</script>

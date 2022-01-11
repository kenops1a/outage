<template>
  <v-app id="inspire">

    <router-view/>
<!--    <CenterTest></CenterTest>-->
<!--    <Register></Register>-->
<!--    <ColRowTest></ColRowTest>-->
<!--    <ComponentTest></ComponentTest>-->
<!--    <TestComponent></TestComponent>-->
  </v-app>
</template>

<script>

// import TestComponent from "@/components/test/TestComponent";
export default {
  name: 'App',
  components: {
    // TestComponent: () => import('@/components/test/TestComponent')
    // LoginIndex: () => import('@/components/account/LoginIndex')
    Login: () => import('@/components/account/login/Login')
    // CenterTest: () =>import('@/components/test/CenterTest'),
    // Register: () => import('@/components/account/register/Register'),
    // ColRowTest: () => import('@/components/test/ColRowTest')
    // ComponentTest: () => import('@/components/test/ComponentTest')
  },

  data: () => ({
    //
  }),
};
</script>

<style>
  body {
    background-color: #f0f0f0;
  }
</style>

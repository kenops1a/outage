<template>
  <v-app id="inspire">
    <Header/>
    <router-view/>
    <Footer/>
    <!--    <CenterTest></CenterTest>-->
    <!--    <Register></Register>-->
    <!--    <ColRowTest></ColRowTest>-->
    <!--    <ComponentTest></ComponentTest>-->
    <!--    <TestComponent></TestComponent>-->
    <!--    <ContainerTest></ContainerTest>-->
  </v-app>
</template>

<script>
export default {
  name: 'App',
  components: {
    // TestComponent: () => import('@/components/test/ContainerTest/TestComponent')
    // LoginIndex: () => import('@/components/account/LoginIndex')
    // Login: () => import('@/components/account/login/Login')
    // CenterTest: () =>import('@/components/test/CenterTest'),
    // Register: () => import('@/components/account/register/Register'),
    // ColRowTest: () => import('@/components/test/ColRowTest'),
    // ComponentTest: () => import('@/components/test/ComponentTest'),
    // ContainerTest: () => import('@/components/test/ContainerTest/ContainerTest')
    Header: () => import('@/components/header/Header'),
    Footer: () => import('@/components/footer/Footer')
  },
  data: () => ({
    //
  }),
};
</script>

<style>
</style>

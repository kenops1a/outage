<template>
  <v-app id="inspire" style="background-color: floralwhite">
    <div v-if="$route.meta.keep">
      <Header/>
    </div>
    <router-view/>
    <success-bar/>
    <error-bar/>
    <div v-if="$route.meta.keep">
      <Footer/>
    </div>
    <!--    <CenterTest></CenterTest>-->
    <!--    <Register></Register>-->
    <!--    <ColRowTest></ColRowTest>-->
    <!--    <ComponentTest></ComponentTest>-->
    <!--    <TestComponent></TestComponent>-->
    <!--    <ContainerTest></ContainerTest>-->
  </v-app>
</template>

<script>
import SuccessBar from "@/components/snackBar/SuccessBar";
import ErrorBar from "@/components/snackBar/ErrorBar";
export default {
  name: 'App',
  components: {
    ErrorBar,
    SuccessBar,
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
  created() {
    console.log("---powered by 4x")
  }

};
</script>

<style>
/*body {
  color: #777777;
}*/
</style>

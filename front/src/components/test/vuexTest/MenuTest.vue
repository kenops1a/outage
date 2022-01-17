<!--
* @Descripttion:
* @Project: front
* @Author: yaoMinC
* @Date: 14/1/2022
-->
<template>
  <v-main style="height: 100%; width: 100%">
   <v-app-bar app color="white" v-show="($route.name !== 'login' && $route.name !== 'register')">
     <v-menu offset-y>
       <template v-slot:activator="{ on, attrs }">
         <!--        <v-btn class="ml-3 pa-2 mb-2" depressed tile v-bind="attrs" v-on="on">-->
         <!--        </v-btn>-->
         <label v-bind="attrs" v-on="on">
           {{$store.state.userNick}}
         </label>
       </template>
       <v-list>
         <v-list-item v-for="(item, index) in items" :key="index">
           <v-list-item-title>{{ item.title }}</v-list-item-title>
         </v-list-item>
       </v-list>
     </v-menu>
   </v-app-bar>
  </v-main>
</template>

<script>
export default {
  data: () => ({
    items: [
      { title: 'Click Me' },
      { title: 'Click Me' },
      { title: 'Click Me' },
      { title: 'Click Me 2' },
    ],
    label: 'test'
  }),
}
</script>

<style scoped>

</style>

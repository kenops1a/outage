<!--
* @Descripttion:
* @Project: front
* @Author: yaoMinC
* @Date: 14/1/2022
-->
<template>
    <v-main style="height: 100vh; width: 100vw">
      <v-card color="pa-8 pb-16 pt-10" style="height: 100%; width: 100%;">
        <v-row class="ml-4 mb-n3">
          <h2>个人信息</h2>
        </v-row>
        <v-simple-table>
          <template v-slot:default>
            <thead>
            <tr>
              <th></th>
            </tr>
            </thead>
            <tbody>
            <!--<tr v-for="item in desserts" :key="item.name">
              <td>{{ item.name }}</td>
              <td>{{ item.calories }}</td>
            </tr>-->
              <tr>
                <td></td>
              </tr>
            </tbody>
          </template>
        </v-simple-table>
      </v-card>
    </v-main>
</template>

<script>
import router from '@/router/router'
export default {
  name: "UserInfo",
  mounted() {
    // router.push('/userInfo')
  }
}
</script>

<style scoped>

</style>

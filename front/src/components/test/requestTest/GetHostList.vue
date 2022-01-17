<!--
* @Descripttion:
* @Project: front
* @Author: yaoMinC
* @Date: 17/1/2022
-->
<template>
  <v-main style="height: 100%; width: 100%">
    <v-container>
      <v-row align="center">
        <v-col cols="3" v-for="(host, index) in hostList" :key="index">
          <v-card class="ma-2" min-width="200px" max-width="300px" min-height="200px">
            <v-card-text class="text--primary">
              <div>{{ host.nick }}</div>
            </v-card-text>
          </v-card>
        </v-col>
      </v-row>
    </v-container>
  </v-main>
</template>

<script>
import { getHostList,getHostListByLikeParam } from "@/api/host/host";

export default {
  name: "GetHostList",
  data () {
    return {
      params: {
        page: 0,
        pageSize: 6
      },
      hostList: []
    }
  },
  mounted() {
    getHostList(this.params).then(res => {
      console.log(res.record)
      this.hostList = res.record
    })
  },
  methods: {
  }
}
</script>

<style scoped>

</style>

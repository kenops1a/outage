<!--
* @Descripttion:
* @Project: front
* @Author: yaoMinC
* @Date: 18/1/2022
-->
<template>
  <v-main style="height: 100%; width: 100%">
    <v-container>
      <div class="text-center mt-5">
        <v-pagination v-model="pageSearch.page" :length="6"></v-pagination>
      </div>
    </v-container>
  </v-main>
</template>

<script>
export default {
  name: "PageTest",
  data () {
    return {
      pageSearch: {
        page: 1,
        pageSize: 6
      }
    }
  }
}
</script>

<style scoped>

</style>

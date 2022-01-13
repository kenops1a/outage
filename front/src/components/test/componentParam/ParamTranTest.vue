<!--
* @Descripttion:
* @Project: front
* @Author: yaoMinC
* @Date: 13/1/2022
-->
<template>
  <v-main style="width: 100%; height: 100%">
    <v-container class="mt-16 pt-16">
      <v-row>
        <v-col>
          <h1>{{title}}</h1>
          <v-text-field v-model="title"></v-text-field>
        </v-col>
      </v-row>
    </v-container>
  </v-main>
</template>

<script>

export default {
  name: "ParamTranTest",
  props: {
    title: String
  },
  data () {
    return {
      num: 0
    }
  },
  methods: {
    addOne () {
      this.num ++
      console.log(this.num)
    }
  }
}
</script>

<style scoped>

</style>

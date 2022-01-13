<!--
* @Descripttion:
* @Project: outage
* @Author: yaoMinC
* @Date: 2022/1/5
-->
<template>
  <v-app>
    <v-container>
      <v-row no-gutters style="height: 150px">
        <v-col v-for="align in alignments" :key="align" :align-self="align">
          <v-card class="pa-2">one of three columns</v-card>
        </v-col>
      </v-row>

      <v-row no-gutters style="height: 150px" justify="center">
        <v-col cols="4">
          <v-card class="pa-2">one of three columns</v-card>
        </v-col>
      </v-row>

      <v-row no-gutters style="height: 150px" justify="center">
        <v-col cols="4">
          <v-card class="pa-2">one of one columns</v-card>
        </v-col>
      </v-row>
    </v-container>

    <v-container>
      <v-row no-gutters style="height: 150px">
        <v-col>
          <v-avatar
              color="orange"
              size="62"
          >
            <span class="white--text text-h5">62</span>
          </v-avatar>
        </v-col>
      </v-row>
    </v-container>
    </v-app>
</template>

<script>

export default {
  name: "TestComponent",
  data () {
    return {
      alignments: [
        'start',
        'center',
        'end'
      ]
    }
  }
}
</script>

<style scoped>

</style>

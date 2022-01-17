<!--
* @Descripttion:
* @Project: front
* @Author: yaoMinC
* @Date: 7/1/2022
-->
<template>
  <v-main style="height: 100%; width: 100%">
    <v-container>
      <v-row align="center">
        <v-col v-for="(item, index) in items" :key="index">
          <v-card min-width="200px" max-width="300px" min-height="200px" class="ma-2">
            <div>
              {{ item.name }}
            </div>
          </v-card>
        </v-col>
      </v-row>
    </v-container>
  </v-main>
</template>

<script>
export default {
  name: "ColRowTest",
  data () {
    return {
      items: [
        { id:1, name: 'item1'},
        { id:2, name: 'item2'},
        { id:3, name: 'item3'},
        { id:4, name: 'item4'},
        { id:5, name: 'item5'},
        { id:6, name: 'item6'},
        { id:7, name: 'item7'},
        { id:8, name: 'item8'},
        { id:9, name: 'item9'},
        { id:10, name: 'item10'},
      ]
    }
  }
}
</script>

<style scoped>

</style>

<!--
* @Descripttion:
* @Project: outage
* @Author: yaominc
* @Date: 2021/12/27
* @LastEditiors: yaominc
-->
<template>
  <div id="host-menu">
    <div id="card-box">
      <ul>
        <li v-for="item in list" :key="item.id">
          <v-card class="card-item" max-width="300" min-width="300" elevation="4">
            <v-card-text>
              <p class="text-h6 text--primary">
                电脑配件{{item.name}}
              </p>
              <p>简介</p>
              <div class="text--primary">
                普普通通的电脑配件罢了，<br>
                没什么特别的
              </div>
            </v-card-text>
            <v-card-actions>
              <v-btn text color="deep-purple accent-3">
                了解详情
              </v-btn>
            </v-card-actions>
          </v-card>
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
export default {
  name: "HostMenu",
  data () {
    return {
      index: 10,
      list: [
        { id:1, name: "1" },
        { id:2, name: "2" },
        { id:3, name: "3" },
        { id:4, name: "4" },
        { id:5, name: "5" },
        { id:6, name: "6" },
      ]
    }
  }
}
</script>

<style scoped>
  #card-box {
    padding: 30vh 9vw 9vh;
    margin-left: 8vw;
  }
  ul {
    display: flex;
    position: relative;
    list-style: none;
    flex-flow: row wrap;
  }
  .card-item {
    position: relative;
    margin: 1vh;
    width: 300px;
  }
</style>
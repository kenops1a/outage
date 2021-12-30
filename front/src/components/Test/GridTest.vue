<!--
* @Descripttion: write_bug
* @Project: outage
* @Author: yaominc
* @Date: 2021/12/30
-->
<template>
  <div id="grid-test">
    <!-- 两行，1：全长，2：三个等长按钮 -->
    <v-row>
      <v-col>
        <v-btn block>Hello</v-btn>
      </v-col>
    </v-row>
    <v-row>
      <v-col>
        <v-btn block>World</v-btn>
      </v-col>
      <v-col>
        <v-btn block>World</v-btn>
      </v-col>
      <v-col>
        <v-btn block>World</v-btn>
      </v-col>
    </v-row>
    <!-- 2/1长 -->
    <v-row>
      <v-col cols="8">
        <v-btn block>Hello</v-btn>
      </v-col>
      <v-col cols="4">
        <v-btn block>World</v-btn>
      </v-col>
    </v-row>

    <!-- 1/1长 -->
    <v-row>
      <v-col cols="6">
        <v-btn block>Hello</v-btn>
      </v-col>
      <v-col cols="6">
        <v-btn block>World</v-btn>
      </v-col>
    </v-row>

    <!-- 抽屉菜单 -->
      <div class="ma-12 pa-12">
        <v-card>
          <v-navigation-drawer permanent expand-on-hover>
            <v-list>
              <v-list-item class="px-2">
                <v-list-item-avatar>
                  <v-img src="https://randomuser.me/api/portraits/women/85.jpg"></v-img>
                </v-list-item-avatar>
              </v-list-item>

              <v-list-item link>
                <v-list-item-content>
                  <v-list-item-title class="text-h6">
                    Sandra Adams
                  </v-list-item-title>
                  <v-list-item-subtitle>sandra_a88@gmail.com</v-list-item-subtitle>
                </v-list-item-content>
              </v-list-item>
            </v-list>

            <v-divider></v-divider>

            <v-list nav dense>
              <v-list-item link>
                <v-list-item-icon>
                  <v-icon>mdi-folder</v-icon>
                </v-list-item-icon>
                <v-list-item-title>My Files</v-list-item-title>
              </v-list-item>
              <v-list-item link>
                <v-list-item-icon>
                  <v-icon>mdi-account-multiple</v-icon>
                </v-list-item-icon>
                <v-list-item-title>Shared with me</v-list-item-title>
              </v-list-item>
              <v-list-item link>
                <v-list-item-icon>
                  <v-icon>mdi-star</v-icon>
                </v-list-item-icon>
                <v-list-item-title>Starred</v-list-item-title>
              </v-list-item>
            </v-list>
          </v-navigation-drawer>
        </v-card>
      </div>
  </div>
</template>

<script>
export default {
  name: "GridTest"
}
</script>

<style>
#grid-test {
  margin: 100px;
}
</style>
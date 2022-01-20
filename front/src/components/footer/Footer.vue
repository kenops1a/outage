<!--
* @Descripttion:
* @Project: front
* @Author: yaoMinC
* @Date: 12/1/2022
-->
<template>
  <v-footer dark padless>
    <v-card width="100vw" class="black white--text text-center">
      <v-card-text>
        <v-btn v-for="icon in icons" :key="icon" class="mx-4 white--text" icon>
          <v-icon size="24px" style="color: darkgrey">
            {{ icon }}
          </v-icon>
        </v-btn>
      </v-card-text>

      <v-card-text class="white--text pt-0">
        <p style="color: darkgrey">
          楼下一个男人病得要死，那间壁的一家唱着留声机；<br>
          对面是弄孩子。楼上有两人狂笑；<br>
          还有打牌声。河中的船上有女人哭着她死去的母亲。人类的悲欢并不相通，我只觉得他们吵闹。<br>
          ——《小杂感》
        </p>
      </v-card-text>

      <v-divider></v-divider>

      <v-card-text class="white--text">
        <span style="color: darkgrey">
          {{ new Date().getFullYear() }} — <strong>4x</strong>
        </span>
      </v-card-text>
    </v-card>
  </v-footer>
</template>

<script>
export default {
  data () {
    return {
      icons: [
        'mdi-facebook',
        'mdi-twitter',
        'mdi-linkedin',
        'mdi-instagram',
      ]
    }
  }
}
</script>

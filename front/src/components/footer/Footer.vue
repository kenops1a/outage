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
          这里是空的。
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

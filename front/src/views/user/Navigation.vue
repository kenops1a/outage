<template>
  <!-- permanent不管屏幕尺寸如何，抽屉都能看到gejb -->
  <v-navigation-drawer app class="pt-4" permanent>
<!--  <v-navigation-drawer app class="pt-4" permanent :color="backgroundColor" :mini-variant="miniVariant" :width="navigationWidth">-->
<!--    <v-avatar v-if="$route.path !== '/setting'" class="d-block text-center mx-auto mb-9" size="42">-->
<!--      <v-img :src="$store.state.user.icon"/>-->
<!--    </v-avatar>-->
    <div class="d-block text-center" v-for="(button, index) in buttons" :key="index">
      <v-tooltip right>
        <template v-slot:activator="{ on, attrs }">
          <v-btn class="mt-0" v-bind="attrs" v-on="on" to="/weblog">
            <span>{{button.name}}</span>
          </v-btn>
        </template>
        <span>{{button.name}}</span>
      </v-tooltip>
    </div>

<!--    <div class="d-block text-center mx-auto mb-9" v-for="(button, index) in navigationButton" :key="index">-->
<!--      <v-tooltip right>-->
<!--        <template v-slot:activator="{ on, attrs }">-->
<!--          <v-btn icon v-bind="attrs" v-on="on" @click="iconButtonClick(button.routerAddress)">-->
<!--            <v-icon :color="iconButtonColor">{{ button.mdiIcon }}</v-icon>-->
<!--          </v-btn>-->
<!--        </template>-->
<!--        <span>{{ button.tip }}</span>-->
<!--      </v-tooltip>-->
<!--    </div>-->
    <div class="d-block text-center mx-auto mb-9">
      <v-tooltip right>
        <template v-slot:activator="{ on, attrs }">
          <v-btn v-bind="attrs" v-on="on" to="/userInfo">
            <v-avatar color="red" size="46"></v-avatar>
          </v-btn>
        </template>
        <span>退出登录</span>
      </v-tooltip>
    </div>

<!--    <div class="d-block text-center mx-auto mb-9">-->
<!--      <v-tooltip right>-->
<!--        <template v-slot:activator="{ on, attrs }">-->
<!--          <v-btn icon v-bind="attrs" v-on="on" @click="exit">-->
<!--            <v-icon :color="iconButtonColor">mdi-logout</v-icon>-->
<!--          </v-btn>-->
<!--        </template>-->
<!--        <span>退出登录</span>-->
<!--      </v-tooltip>-->
<!--    </div>-->
<!--  </v-navigation-drawer>-->
  </v-navigation-drawer>
</template>

<script>

export default {
  data () {
    return {
      buttons: [
        { name: '1fasdfasdf'},
        { name: '1fasdfasdf'},
        { name: '1fasdfasdf'},
        { name: '1fasdfasdf'},
      ]
    }
  },
  // props: {
  //   buttons: [
  //     { name: '1fasdfasdf'},
  //     { name: '1fasdfasdf'},
  //     { name: '1fasdfasdf'},
  //     { name: '1fasdfasdf'},
  //   ]
  // }
}

</script>

<!--<script>
  import {getAllClassification} from "@/network/classification";
  import Avatar from "@/views/setting/Avatar/Avatar";
  import {getByLabel} from "@/network/picture";
  import {messagePersistence} from "@/network/friend";

  export default {
    name: "Navigation",
    components: {Avatar},
    data: () => ({
      labels: [],
    }),
    props: {
      navigationButton: [],
      labelButtonColor: { type: String, default: '#000000' },
      miniVariant: { type: Boolean, default: true },
      navigationWidth: { type: String, default: '' },
      backgroundColor: { type: String, default: 'grey lighten-3' }
    },
    computed: {
      iconButtonColor() {
        return this.$store.state.theme.navigationButton
      }
    },
    methods: {
      iconButtonClick(routerAddress) {
        this.$router.push(routerAddress)
      },
      exit() {
        this.$store.commit('SET_USER', {})
        sessionStorage.setItem("token", "false")
        this.$router.push('/login')
      }
    }
  }
</script>-->

<style scoped>

</style>

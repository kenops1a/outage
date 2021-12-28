import Vue from 'vue'
import App from './App.vue'
import vuetify from './plugins/vuetify'
import VueRouter from 'vue-router'
import routers from "@/config/router";

// 声明该vueapp使用vuerouter
Vue.use(VueRouter)

const router = new VueRouter({
  mode: 'history',
  // 滚动条复位
  scrollBehavior: () => ({ y:0 }),
  routes: routers
})

// 使用开发模式
Vue.config.productionTip = true

new Vue({
  vuetify,
  router,
  render: h => h(App)
}).$mount('#app')

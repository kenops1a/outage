import Vue from 'vue'
import App from './App.vue'
import vuetify from './plugins/vuetify'
import router from '@/router/router'
import store from '@/store/store'

// 使用vue开发模式
Vue.config.productionTip = true

// 路由跳转之后，滚动条复位
router.afterEach((to,from,next) => {
  window.scrollTo(0,0);
});

new Vue({
  vuetify,
  router,
  // 跟router一样，将我们创建的vuex实例挂载到vue实例中
  store,
  render: h => h(App)
}).$mount('#app')

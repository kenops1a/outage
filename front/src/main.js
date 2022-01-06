import Vue from 'vue'
import App from './App.vue'
import vuetify from './plugins/vuetify'

// 使用vue开发模式
Vue.config.productionTip = true


new Vue({
  vuetify,
  render: h => h(App)
}).$mount('#app')
